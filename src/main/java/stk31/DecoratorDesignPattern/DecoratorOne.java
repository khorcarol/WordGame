package stk31.DecoratorDesignPattern;

public class DecoratorOne extends BaseDecorator{

    public DecoratorOne(Component wrapped){
        super.wrapped = wrapped;
    }
    @Override
    public void doSomething(){
        super.doSomething();
        System.out.println("DecoratorOne does something");
    }
}
