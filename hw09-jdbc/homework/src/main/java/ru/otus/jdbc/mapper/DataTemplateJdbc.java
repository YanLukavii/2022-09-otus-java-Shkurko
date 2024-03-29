package ru.otus.jdbc.mapper;

import org.apache.commons.text.WordUtils;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData<T> entitySQLMetaData;
    private final EntityClassMetaData<T> entityClass;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData<T> entitySQLMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClass = entitySQLMetaData.getEntityClassMetaData();
    }

    public Object[] getFieldsForRs(ResultSet rs) throws SQLException {
        List<Field> fieldList = entityClass.getAllFields();
        Object[] objects = new Object[fieldList.size()];
        objects[0] = rs.getLong(entityClass.getIdField().getName());
        for (int i = 1; i < fieldList.size(); i++) {
            objects[i] = rs.getString(fieldList.get(i).getName());
        }
        return objects;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                if (rs.next()) {
                    return entityClass.getConstructor()
                            .newInstance(getFieldsForRs(rs));
                }
                throw new RuntimeException("Unexpected error");
            } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new DataTemplateException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), rs -> {
            var clientList = new ArrayList<T>();
            try {
                    while (rs.next()) {
                        clientList.add(entityClass.getConstructor().newInstance(getFieldsForRs(rs)));
                    }
                return clientList;
            } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new DataTemplateException(e);
            }
        }).orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    @Override
    public long insert(Connection connection, T client) {
        List<Field> listFields = entityClass.getFieldsWithoutId();
        List<Object> objectCollection = new ArrayList<>();
        try {
            for (Field field : listFields) {
                String fieldsGetterMethodName = generateFieldsGetterName(field);
                if (client.getClass().getDeclaredMethod(fieldsGetterMethodName).invoke(client) != null) {
                    objectCollection.add(client.getClass().getDeclaredMethod(fieldsGetterMethodName).invoke(client));
                }
            }
            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), objectCollection);

        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T client) {
        List<Field> listFields = entityClass.getAllFields();
        List<Object> objectCollection = new ArrayList<>();
        try {
            for (Field field : listFields) {
                String fieldsGetterMethodName = generateFieldsGetterName(field);
                if (client.getClass().getDeclaredMethod(fieldsGetterMethodName).invoke(client) != null) {
                    objectCollection.add(client.getClass().getDeclaredMethod(fieldsGetterMethodName).invoke(client));
                }
            }
            dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(), objectCollection);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    private String generateFieldsGetterName(Field field) {
        StringBuilder fieldsUpper = new StringBuilder();
        String fieldsGetterMethodName = "";
        fieldsUpper.append(" ").append(field.getName());
        fieldsGetterMethodName = "get" + WordUtils.capitalizeFully(fieldsUpper.substring(1));
        return fieldsGetterMethodName;
    }
}
