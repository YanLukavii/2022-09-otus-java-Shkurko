package ru.otus.annotations.usage;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.annotation.Annotation;
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

    private static ArrayList<Method> getArrayListOfMethodsWithDesiredAnnotation(Method[] methodsAll, Class<?> annotationClass, Class<?> clazz) throws NoSuchMethodException {

        ArrayList<Method> list = new ArrayList<>();

        for (Method method : methodsAll) {

            var hasAnnotation = clazz.getMethod(method.getName()).isAnnotationPresent((Class<? extends Annotation>) annotationClass);

            if (hasAnnotation) {
                list.add(method);
            }
        }
        return list;
    }

    private void run(Class<?> clazz) throws Exception {

        Method[] methodsAll = clazz.getDeclaredMethods();

        ArrayList<Method> listMethodsAnnotationTest = getArrayListOfMethodsWithDesiredAnnotation(methodsAll, Test.class, clazz);
        ArrayList<Method> listAnnotationAfter = getArrayListOfMethodsWithDesiredAnnotation(methodsAll, After.class, clazz);
        ArrayList<Method> listMethodsAnnotationBefore = getArrayListOfMethodsWithDesiredAnnotation(methodsAll, Before.class, clazz);


        int passedTests = listMethodsAnnotationTest.size();

        if (listMethodsAnnotationTest.size() == 0) {

            System.err.println("No tests found for given includes... " + clazz.getName());

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

        new TestRunner().run(TestClass.class);

    }

}
