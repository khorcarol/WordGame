package stk31.StateDesignPattern;

public class StateTwo implements StateInterface {
    public void Do(Context context) {
        System.out.println("Doing State Two");
        context.setStateInterface(new StateOne());

    }
}