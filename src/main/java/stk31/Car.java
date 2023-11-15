package stk31;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Car {
    private String manufacturer;
    private int age;

    public int getAge() {
        return age;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Car(String manufacturer, int age) {
        this.age = age;
        this.manufacturer = manufacturer;
    }

}

class carComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        int a = c1.getManufacturer().compareTo(c2.getManufacturer());
        if (a == 0) {
            if (c1.getAge() > c2.getAge())
                return 1;
            else if (c1.getAge() == c2.getAge()) {
                return 0;
            }
            else
                return -1;
        }
        return a;
    }


    public static void main(String[] args) {

        List<Car> carcollection = new ArrayList<>();
        carcollection.add(new Car("abc", 1));
        carcollection.add(new Car("bc", 1));
        carcollection.add(new Car("abc", 2));
        Collections.sort(carcollection, new carComparator());
        for (Car i : carcollection) {
            System.out.println("Age: "+ i.getAge() + " Manafacturer:" + i.getManufacturer());
        }
    }
}
