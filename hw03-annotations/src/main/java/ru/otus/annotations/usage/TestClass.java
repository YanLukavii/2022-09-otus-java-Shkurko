package ru.otus.annotations.usage;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestClass {

    @Before
    public void before() {
        System.out.println("Запущен before");
    }

    @Test
    public void testWithException() {
        System.out.println("Запущен тест с ошибкой");
        throw new RuntimeException();
    }

    @Test
    public void test4() {
        System.out.println("Запущен test4");
    }

    @Test
    public void test5() {
        System.out.println("Запущен test5");
    }

    @After
    public void after() {
        System.out.println("Запущен after");
    }

}
