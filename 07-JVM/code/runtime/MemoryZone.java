package runtime;

public class MemoryZone {

    /**
     * 类变量 - 方法区
     */
    private static final String CLASS_VAR = "marion";

    /**
     * 成员变量 -
     */
    private final int number = 10;

    public static void main(String[] args) {
        /**
         * 局部变量
         */
        int i = 1;
        System.out.println("hello world");
    }

    private String helloWord() {
        return "methods hello world";
    }

}
