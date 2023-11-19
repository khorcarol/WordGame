package stk31.DecoratorDesignPattern;

public class Main {
    public static void main(String[] args) {
        Component c = new DecoratorOne(new ConcreteComponent());
        c.doSomething();
    }
}
