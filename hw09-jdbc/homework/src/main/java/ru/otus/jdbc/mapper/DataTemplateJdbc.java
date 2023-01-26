package ru.otus.jdbc.mapper;

import org.apache.commons.text.WordUtils;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
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
    private final EntitySQLMetaData entitySQLMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs -> {
            try {
                if (entitySQLMetaData.getEntityClassMetaData().getAllFields().size() == 2) {
                    if (rs.next()) {
                        return (T) entitySQLMetaData
                                .getEntityClassMetaData()
                                .getConstructor()
                                .newInstance(rs.getLong("id"), rs.getString("name"));
                    }

                }else
                if (entitySQLMetaData.getEntityClassMetaData().getAllFields().size() == 3) {
                   if (rs.next()) {
                       return (T) entitySQLMetaData
                               .getEntityClassMetaData()
                               .getConstructor()
                               .newInstance(rs.getLong("id"), rs.getString("label"), rs.getString("param1"));
                   }

                }

                return null;
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
                if (entitySQLMetaData.getEntityClassMetaData().getAllFields().size() == 2) {
                    while (rs.next()) {
                        clientList.add((T) entitySQLMetaData
                                .getEntityClassMetaData()
                                .getConstructor()
                                .newInstance(rs.getLong("id"), rs.getString("name")));
                    }
                }
                if (entitySQLMetaData.getEntityClassMetaData().getAllFields().size() == 3) {
                    while (rs.next()) {
                        clientList.add((T) entitySQLMetaData
                                .getEntityClassMetaData()
                                .getConstructor()
                                .newInstance(rs.getLong("id"), rs.getString("label"), rs.getString("param1")));
                    }
                }
                return clientList;
            } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new DataTemplateException(e);
            }
        }).orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    @Override
    public long insert(Connection connection, T client) {
        List<Field> listFields = entitySQLMetaData.getEntityClassMetaData().getFieldsWithoutId();

        List<Object> objectCollection = new ArrayList<>();
        try {
            for (Field x : listFields) {
                StringBuilder fieldsUpper = new StringBuilder();
                String fieldsGetterMethodName = "";
                fieldsUpper.append(" ").append(x.getName());
                fieldsGetterMethodName = "get" + WordUtils.capitalizeFully(fieldsUpper.substring(1));

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
        List<Field> listFields = entitySQLMetaData.getEntityClassMetaData().getAllFields();

        List<Object> objectCollection = new ArrayList<>();
        try {
            for (Field x : listFields) {
                StringBuilder fieldsUpper = new StringBuilder();
                String fieldsGetterMethodName = "";
                fieldsUpper.append(" ").append(x.getName());
                fieldsGetterMethodName = "get" + WordUtils.capitalizeFully(fieldsUpper.substring(1));

                if (client.getClass().getDeclaredMethod(fieldsGetterMethodName).invoke(client) != null) {
                    objectCollection.add(client.getClass().getDeclaredMethod(fieldsGetterMethodName).invoke(client));
                }
            }
            dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(), objectCollection);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }
}
