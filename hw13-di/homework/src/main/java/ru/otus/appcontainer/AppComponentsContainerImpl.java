package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...
        checkNameDuplicate(configClass);
        initContext(configClass);
    }

    private TreeSet<Integer> getOrder(Class<?> configClass) {
        TreeSet<Integer> orderTree = new TreeSet<>();
        var methods = configClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AppComponent.class))
                orderTree.add(method.getAnnotation(AppComponent.class).order());
        }
        return orderTree;
    }

    private void initContext(Class<?> configClass) {
        for (int i = 0; i < getOrder(configClass).size(); i++) {
            var methods = configClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(AppComponent.class) && method.getAnnotation(AppComponent.class).order() == i) {
                    Object obj = null;
                    try {
                        var parameterTypes = method.getParameterTypes();
                        obj = method.invoke(configClass.getDeclaredConstructor().newInstance(), getObjectsFromAppComponents(parameterTypes, appComponents));
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), obj);
                    appComponents.add(obj);
                }
            }
        }
    }

    private void checkNameDuplicate(Class<?> configClass) {
        var methods = configClass.getMethods();
        List<String> nameList = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AppComponent.class)) {
                nameList.add(method.getAnnotation(AppComponent.class).name());
            }
        }
        Set<String> nameSet = new HashSet<>(nameList);
        if (nameSet.size() != nameList.size()) {
            throw new RuntimeException();
        }
    }

    private Object[] getObjectsFromAppComponents(Class<?>[] params, List<Object> appComponents) {
        Object[] objectsForInvoke = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            for (Object appComponent : appComponents) {
                if (params[i].isAssignableFrom(appComponent.getClass())) {
                    objectsForInvoke[i] = appComponent;
                }
            }
        }
        return objectsForInvoke;
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private void duplicateCheck() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Object appComponent : appComponents) {
            classSet.add(appComponent.getClass());}
        if (classSet.size() != appComponents.size()) {
            throw new RuntimeException();
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        duplicateCheck();
        for (Object x : appComponents) {
            if (x.getClass().equals(componentClass) || componentClass.isAssignableFrom(x.getClass())) {
                return (C) x;
            }
        }
        throw new RuntimeException();
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        for (Map.Entry<String, Object> objectEntry : appComponentsByName.entrySet()) {
            if (objectEntry.getKey().equals(componentName)) {
                return (C) objectEntry.getValue();
            }
        }
        throw new RuntimeException();
    }
}
