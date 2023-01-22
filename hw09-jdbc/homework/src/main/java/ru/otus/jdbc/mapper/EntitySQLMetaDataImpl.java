package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {

        return "select * from " + entityClassMetaData.getName();
    }

    @Override
    public String getSelectByIdSql() {
        List<Field> fieldsList = entityClassMetaData.getFieldsWithoutId();
        StringBuilder fields = new StringBuilder();
        String name = entityClassMetaData.getName();
        for (Field x : fieldsList) {
            if (!fields.equals("")) {
                fields.append(", ");
                fields.append(x.getName());
            } else
                fields.append(x.getName());
        }
        return "select id "+ fields +" from " + name + " where id  = ?";
    }

    @Override
    public String getInsertSql() {

       // return "INSERT INTO " + entityClassMetaData.getName();
//Insert into Employees (ID, Name) values (1,'raj')
        List<Field> fieldsList = entityClassMetaData.getFieldsWithoutId();
        StringBuilder fields = new StringBuilder();
       /* for (Field x : fieldsList) {
                fields.append(x.getName());
                fields.append(", ");
        }*/

       // return "insert into " + entityClassMetaData.getName() + " (" + fields.substring(0,fields.length() - 2) + ") values (?)";
       // return "insert into   manager  (label) values (?)";
        return "insert into " + entityClassMetaData.getName() + " (" + fieldsList.get(0).getName() + ") values (?)";
    }

    @Override
    public String getUpdateSql() {
        //"update client set name = ? where id = ?"
        List<Field> fieldsList = entityClassMetaData.getFieldsWithoutId();
        StringBuilder fields = new StringBuilder();
        for (Field x : fieldsList) {
            fields.append(" set ");
            fields.append(x.getName());
            fields.append(" = ?");
            fields.append(", ");
        }
        return "update " + entityClassMetaData.getName() + fields.substring(0,fields.length() - 2) + " where id = ?";

    }

    @Override
    public EntityClassMetaData getEntityClassMetaData() {
        return entityClassMetaData;
    }


}
