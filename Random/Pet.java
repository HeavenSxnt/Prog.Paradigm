// Animal.java

public class Pet {
    private String name;
    private String species;
    private int age;
    private double price;

    // Constructor
    public Pet(String name, String species, int age, double price) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.price = price;
    }

    // Getter and Setter methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Method to display animal details
    @Override
    public String toString() {
        return "{Animal-Name:" + name + "(" + species + "), Age:" + age + ", Price:" + price + "}";
    }
}