package stk31.DecoratorDesignPattern;

public class BaseDecorator implements Component{
    Component wrapped;

    @Override
    public void doSomething() {
        wrapped.doSomething();
    }
}
