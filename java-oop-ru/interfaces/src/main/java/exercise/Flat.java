package exercise;

// BEGIN
public class Flat implements Home {

    public double area;
    public double balconyArea;
    public int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home anotherHome) {
        return this.area > anotherHome.getArea() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + area + " метров " + "на " + floor +  " этаже";
    }
}
// END
