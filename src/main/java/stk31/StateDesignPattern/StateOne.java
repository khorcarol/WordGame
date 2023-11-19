package stk31.StateDesignPattern;


public class StateOne implements StateInterface {
    public void Do(Context context) {
        System.out.println("Doing State One");
        context.setStateInterface(new StateTwo());

    }
}

