package ru.otus.jdbc.mapper;

import ru.otus.crm.model.Client;
import ru.otus.crm.model.Manager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public  class test {



    public static void main(String[] args) {

        EntityClassMetaData<Client> entityClassMetaDataClient  = new EntityClassMetaDataImpl<>(Client.class);
        EntitySQLMetaData entitySQLMetaDataClient = new EntitySQLMetaDataImpl(entityClassMetaDataClient);


     /*   System.out.println(entityClassMetaDataClient.getConstructor());
        System.out.println(entityClassMetaDataClient.getAllFields());

        for (Field x:entityClassMetaDataClient.getFieldsWithoutId()
             ) {
            System.out.println(x.getName());
        }*/
Manager manager = new Manager("da");
        try {
         var hz =  manager.getClass().getDeclaredMethod("getLabel").invoke(manager);
            System.out.println(hz.toString());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }


        class RootGeneric<T> {
          //  @SuppressWarnings("unchecked")
          //  public final Class<T> persistentClass = (Class<T>) (new TypeToken<T>(getClass()) {}.getType());
        }

    }

    public static class Testt<T> {

        Class<T> persistentClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];


    }
    class RootGeneric<T> {
     //   @SuppressWarnings("unchecked")
    //    public final Class<T> persistentClass = (Class<T>) (new TypeToken<T>(getClass()) {}.getType());
    }
}
