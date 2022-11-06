package homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ioc {

    private Ioc() {

    }

    static TestLoggingInterface createTestLoggingClass() {

        InvocationHandler handler = new MyIocHandler(new TestLogging());

        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class},
                handler);
    }

    static class MyIocHandler implements InvocationHandler {

        private final TestLoggingInterface myTestLogging;
        private final Set<String> methodsWithAnnotationLog = new HashSet<>();

        MyIocHandler(TestLoggingInterface myTest) {

            this.myTestLogging = myTest;

            addMethodsWithLogAnnotation();

        }

        private void addMethodsWithLogAnnotation() {

            Method[] methods = myTestLogging.getClass().getMethods();

            for (var method : methods) {

                if (method.isAnnotationPresent(Log.class)) {

                    methodsWithAnnotationLog.add(getMethodAndParamTypeAsString(method));
                }
            }

        }

        private String getMethodAndParamTypeAsString(Method method) {
            return method.getName() + Arrays.toString(method.getParameterTypes());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (methodsWithAnnotationLog.contains(getMethodAndParamTypeAsString(method))) {

                System.out.println("executed method: " + method.getName() + " param: " + Arrays.toString(args));

            }
            return method.invoke(myTestLogging, args);
        }
    }
}
