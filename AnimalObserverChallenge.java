import java.util.*;

interface Walkable {
    void walk();
}

class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor called.");
    }

    public void speak() {
        System.out.println("Some generic animal sound.");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Animal[name=" + name + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Animal)) return false;
        Animal other = (Animal) obj;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}

class Bird extends Animal implements Walkable {
    protected String name; // Hides parent's name
    private boolean canFly;

    public Bird(String name, boolean canFly) {
        super(name);
        this.name = name;
        this.canFly = canFly;
        System.out.println("Bird constructor called.");
    }

    public Bird() {
        this("Unknown Bird", true);
        System.out.println("Bird default constructor called using this().");
    }

    @Override
    public void speak() {
        System.out.println("Chirp!");
    }

    @Override
    public String toString() {
        return "Bird[name=" + name + ", canFly=" + canFly + "]";
    }

    public void printNameVersions() {
        System.out.println("Child name: " + this.name);
        System.out.println("Parent name: " + super.name);
    }

    @Override
    public void walk() {
        System.out.println("Bird walks gracefully with short hops.");
    }
}

class Penguin extends Bird {
    public Penguin(String name) {
        super(name, false);
        System.out.println("Penguin constructor called using super().");
    }

    @Override
    public void speak() {
        System.out.println("Squawk!");
    }

    @Override
    public void walk() {
        System.out.println("Penguin waddles adorably.");
    }

    @Override
    public String toString() {
        return "Penguin[name=" + name + "]";
    }
}

class ZooKeeper {
    public void greetAnimal(Animal a) {
        System.out.println("ZooKeeper: Hello, " + a.getName() + "!");
    }

    public void greetAnimal(Bird b) {
        System.out.println("ZooKeeper: Hello flying friend, " + b.getName() + "!");
    }
}

class AnimalObserverChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Animal> zoo = new HashSet<>();

        System.out.print("Enter the name of the animal: ");
        String name1 = sc.nextLine();

        System.out.print("Choose type (1=Animal, 2=Bird, 3=Penguin): ");
        int type1 = sc.nextInt();
        sc.nextLine(); // consume newline

        Animal a1 = null;

        if (type1 == 1) {
            a1 = new Animal(name1);
        } else if (type1 == 2) {
            System.out.print("Can it fly? (true/false): ");
            boolean canFly = sc.nextBoolean();
            a1 = new Bird(name1, canFly);
        } else if (type1 == 3) {
            a1 = new Penguin(name1);
        }

        a1.speak();
        System.out.println(a1);

        ZooKeeper zk = new ZooKeeper();
        if (a1 instanceof Bird) {
            zk.greetAnimal((Bird) a1);
        } else {
            zk.greetAnimal(a1);
        }

        zoo.add(a1);

        // Second animal
        sc.nextLine(); // consume newline
        System.out.print("\nEnter the name of another animal: ");
        String name2 = sc.nextLine();

        System.out.print("Choose type (1=Animal, 2=Bird, 3=Penguin): ");
        int type2 = sc.nextInt();
        sc.nextLine(); // consume newline

        Animal a2 = null;

        if (type2 == 1) {
            a2 = new Animal(name2);
        } else if (type2 == 2) {
            System.out.print("Can it fly? (true/false): ");
            boolean canFly2 = sc.nextBoolean();
            a2 = new Bird(name2, canFly2);
        } else if (type2 == 3) {
            a2 = new Penguin(name2);
        }

        System.out.println("Names are equal? " + a1.equals(a2));
        zoo.add(a2);

        System.out.println("\n--- Unique Animals in the Zoo ---");
        System.out.println("Total: " + zoo.size());

        for (Animal a : zoo) {
            System.out.println(a);
            if (a instanceof Walkable) {
                ((Walkable) a).walk();
            }
        }

        if (a1 instanceof Bird) {
            ((Bird) a1).printNameVersions();
        }

        sc.close();
    }
}