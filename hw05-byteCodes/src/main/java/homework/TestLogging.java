package homework;

public class TestLogging implements TestLoggingInterface {

    //@Log
    public void calculation(int param) {
        System.out.println("отработал с " + param);
    }

    //@Log
    public void calculation(int param1, int param2) {
        System.out.println("отработал с " + param1 + " " + param2);
    }

    @Log
    public void calculation(int param1, int param2, String param3) {
        System.out.println("отработал с " + param1 + " " + param2 + " " + param3);
    }
}


