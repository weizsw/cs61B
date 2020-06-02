
public class TestPlanet {
    public static void main(String[] args) {
        Planet one = new Planet(1, 0, 1, 1, 10, "one");
        Planet two = new Planet(3, 3, 2, 2, 5, "two");

        System.out.println("F is " + one.calcForceExertedBy(two));
    }
}