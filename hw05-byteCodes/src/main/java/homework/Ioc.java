package homework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Ioc {

    private Ioc() {
    }

    static TestLoggingInterface createTestLoggingClass() {

        InvocationHandler handler = new DemoIocHandler(new TestLogging());

        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class},
                handler);
    }

    static class DemoIocHandler implements InvocationHandler {

        private final TestLoggingInterface myTestLogging;

        DemoIocHandler(TestLoggingInterface myTest) {
            this.myTestLogging = myTest;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            var hasAnnotation = myTestLogging.getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .isAnnotationPresent(Log.class);

            if (hasAnnotation) {
                System.out.println("executed method: " + method.getName() + " param: " + Arrays.toString(args));

            }
            return method.invoke(myTestLogging, args);
        }
    }
}
