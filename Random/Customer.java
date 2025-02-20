import java.util.ArrayList;

public class Customer {
    private String name;
    private String contactNumber;
    private ArrayList<Pet> purchasedAnimals;

    // Constructor
    public Customer(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.purchasedAnimals = new ArrayList<>();
    }

    // Getter and Setter methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public ArrayList<Pet> getPurchasedAnimals() { return purchasedAnimals; }

    // Method to add an animal to the purchased animals list
    public void addAnimal(Pet animal) {
        purchasedAnimals.add(animal);
    }
    public int countAnimal(){
        return getPurchasedAnimals().size();
    }

    // Method to display customer details
    @Override
    public String toString() {
        return "{customerName:" + name + ", (" + contactNumber + "), #purchasedAnimals:" + countAnimal() + "}";
    }
}