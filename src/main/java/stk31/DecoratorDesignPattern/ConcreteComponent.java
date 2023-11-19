package stk31.DecoratorDesignPattern;

public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("Concrete Component does something.");
    }
}
