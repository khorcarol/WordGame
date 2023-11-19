package stk31.StrategyDesignPattern;

public class Main {
    public static void main(String[] args) {
        Context c = new Context();
        c.setStrategyInterface(new ConcreteStrategy());
        c.getStrategyInterface().Do();
    }
}
