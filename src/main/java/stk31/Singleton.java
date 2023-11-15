package stk31;

public class Singleton {
    static Singleton s = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return s;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
