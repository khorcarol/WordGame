package stk31.StateDesignPattern;

public class Context {
    private StateInterface stateInterface;

    public void setStateInterface(StateInterface stateInterface) {
        this.stateInterface = stateInterface;
    }

    public void Do(){
        stateInterface.Do(this);
    }

    public static void main(String[] args) {
        Context context = new Context();
        context.setStateInterface(new StateOne());
        context.Do();
        context.Do();
    }
}
