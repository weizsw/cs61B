public class Dog {
    public void bark(Dog d) {
        System.out.println("A");
    }
    public static void main(String[] args) {
        Corgi c = new Dog();
    }
}

class Corgi extends Dog {
    public void bark(Corgi c) {
        System.out.println("B");
    }

    @Override
    public void bark(Dog d) {
        System.out.println("C");
    }
    public void play(Dog d) {
        System.out.println("D");
    }
    public void play(Corgi C) {
        System.out.println("E");
    }
}