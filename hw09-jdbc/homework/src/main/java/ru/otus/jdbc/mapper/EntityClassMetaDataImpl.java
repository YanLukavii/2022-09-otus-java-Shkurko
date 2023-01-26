package ru.otus.jdbc.mapper;

import ru.otus.crm.model.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    Class<T> persistentClass;

    public EntityClassMetaDataImpl(Class<T> tClass) {
        persistentClass = tClass;
    }

    @Override
    public String getName() {

        return persistentClass.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        var con = persistentClass.getDeclaredConstructors();

        for (Constructor<?> constructor : con) {
            if (constructor.getParameterCount() == getAllFields().size())
                return (Constructor<T>) constructor;
        }
        return null;
    }

    @Override
    public Field getIdField() {
        List<Field> list = getAllFields();
        for (Field x : list
        ) {
            if (x.isAnnotationPresent(Id.class)) {
                return x;
            }
        }
        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return List.of(persistentClass.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        List<Field> list = getAllFields();
        List<Field> result = new ArrayList<>();
        for (Field x : list) {
            if (!(x.isAnnotationPresent(Id.class))) {
                result.add(x);
            }
        }
        return result;
    }
}
