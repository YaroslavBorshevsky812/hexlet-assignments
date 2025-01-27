package exercise;

// BEGIN
public class App {

    public static void printSquare(Circle circle) {
        try {
            int square = (int) Math.round(circle.getSquare());
            System.out.println(square);
        }
        catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Вычисление окончено");
    }

    public static void main(String[] args) throws NegativeRadiusException {
        Point point = new Point(5, 7);
        Circle circle1 = new Circle(point, -2);
        App.printSquare(circle1);
    }
}
// END
