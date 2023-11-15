package stk31;

public class Main{
    class Person {

    public String mName;
    protected int mAge;
    private double mHeight;
}
class Student extends Person {
    public void do_something() {
        mName = "Bob";
        mAge = 70;

    }

}

}