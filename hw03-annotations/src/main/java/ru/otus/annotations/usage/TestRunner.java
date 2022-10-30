package ru.otus.annotations.usage;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {

    private static <T> T instantiate(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void run(Class<?> clazz) throws Exception {

        Method[] methodsAll = clazz.getDeclaredMethods();

        ArrayList<Method> listMethodsAnnotationTest = new ArrayList<>();
        ArrayList<Method> listMethodsAnnotationBefore = new ArrayList<>();
        ArrayList<Method> listAnnotationAfter = new ArrayList<>();

        for (Method method : methodsAll) {

            var hasAnnotationTest = clazz.getMethod(method.getName()).isAnnotationPresent(Test.class);
            var hasAnnotationBefore = clazz.getMethod(method.getName()).isAnnotationPresent(Before.class);
            var hasAnnotationAfter = clazz.getMethod(method.getName()).isAnnotationPresent(After.class);

            if (hasAnnotationTest) {
                listMethodsAnnotationTest.add(method);
            }
            if (hasAnnotationBefore) {
                listMethodsAnnotationBefore.add(method);
            }
            if (hasAnnotationAfter) {
                listAnnotationAfter.add(method);
            }
        }
        int passedTests = listMethodsAnnotationTest.size();

        if (listMethodsAnnotationTest.size() == 0) {

            System.err.println("No tests found for given includes");

        } else {

            for (Method method : listMethodsAnnotationTest) {

                Object obj = instantiate(clazz);

                for (Method x : listMethodsAnnotationBefore) {
                    x.invoke(obj);
                }
                try {
                    method.invoke(obj);
                } catch (InvocationTargetException e) {
                    passedTests--;
                    System.err.println(method.getName() + " не пройден! ERROR");
                }
                for (Method x : listAnnotationAfter) {
                    x.invoke(obj);
                }
            }
        }
        System.out.println("------RESULTS------");
        System.out.println("All tests " + listMethodsAnnotationTest.size());
        System.out.println("Passed " + passedTests);
        System.out.println("Failed " + (listMethodsAnnotationTest.size() - passedTests));

    }


    public static void main(String[] args) throws Exception {

        run(TestClass.class);

    }

}
