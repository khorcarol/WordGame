package stk31.StrategyDesignPattern;

public class Context {
    private StrategyInterface strategyInterface;

    public void setStrategyInterface(StrategyInterface strategyInterface) {
        this.strategyInterface = strategyInterface;
    }

    public StrategyInterface getStrategyInterface() {
        return strategyInterface;
    }
}