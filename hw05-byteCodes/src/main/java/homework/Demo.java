package homework;

public class Demo {

    public static void main(String[] args) {

        new Demo().action();

    }

    public void action() {

        TestLoggingInterface test = Ioc.createTestLoggingClass();
        test.calculation(1);
        test.calculation(1, 2);
        test.calculation(1, 2, "someText");

    }
}
