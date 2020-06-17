public class Animal {
    protected String name, noise;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.noise = "Huh?";
    }

    public String makeNoise() {
        if (age < 5) {
            return noise.toUpperCase();
        } else {
            return noise;
        }
    }

    public void greet() {
        System.out.println("Animal " + name + " says: " + makeNoise());
    }

    public static void main(String[] args) {
        Animal cat = new Cat("cat", 2);
        cat.greet();
    }
}

class Cat extends Animal {

    public Cat (String name, int age) {
        super(name, age);
        this.noise = "Meow";
    }

    @Override
    public void greet() {
        System.out.println("Animal " + name + " says: " + makeNoise());
    }

    // public static void main(String[] args) {
    //     Cat cat = new Cat("cat", 4);
    //     cat.greet();
    // }
}