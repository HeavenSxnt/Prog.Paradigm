import java.util.*;
import java.io.*;

public class PetShop{
    private ArrayList<Pet> animals;
    private ArrayList<Customer> customers;

    public PetShop() {
        animals = new ArrayList<>();  // Initialize the list
        customers = new ArrayList<>();
    }

    public void addAnimal(Pet pet){
        animals.add(pet);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
        for (Customer existingCustomer : customers) {
            if (existingCustomer.getName().equalsIgnoreCase(customer.getName())) {
                System.out.println("Error: Customer name '" + customer.getName() + "' already exists!");
                return;  // If duplicate found, return early without adding the customer
            }
        }
        customers.add(customer);  // Add customer if no duplicate
    }

    public void sellAnimal(String petName, String customerName) {
        for (Pet pet : animals) {
            if (pet.getName().equalsIgnoreCase(petName)) {
                // Pet found, remove it from the list
                animals.remove(pet);

                // Find the customer and add the pet to their list
                for (Customer customer : customers) {
                    if (customer.getName().equalsIgnoreCase(customerName)) {
                        customer.addAnimal(pet);
                        System.out.println(customerName + " bought " + petName + "!");
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // The following code is for testing
        // Uncomment for testing
       PetShop shop = new PetShop();

       // Adding animals to the shop
        shop.addAnimal(new Pet("Bella", "Dog", 3, 500.0));
        shop.addAnimal(new Pet("Milo", "Cat", 2, 300.0));
        shop.addAnimal(new Pet("Charlie", "Parrot", 1, 150.0));
        shop.addAnimal(new Pet("Max", "Rabbit", 4, 200.0));

        // Adding customers to the shop
        shop.addCustomer(new Customer("Alice", "123-456-7890"));
        shop.addCustomer(new Customer("Bob", "987-654-3210"));
        shop.addCustomer(new Customer("Bob", "877-655-3311"));
 
        System.out.println("[Before the customer buys a pet]");
        System.out.println("List of avaliable PET:");
        for(Pet e: shop.animals)
            System.out.println(e);
        System.out.println("List of Customer:");
        for(Customer e: shop.customers)
            System.out.println(e);

        // Selling animals to customers
        shop.sellAnimal("Bella", "Alice");
        shop.sellAnimal("Milo", "Bob");
        shop.sellAnimal("Max", "Bob");
        System.out.println();

        System.out.println("[After the customer buys a pet]");
        System.out.println("List of avaliable PET:");
        for(Pet e: shop.animals)
            System.out.println(e);
        System.out.println("List of Customer:");
        for(Customer e: shop.customers)
            System.out.println(e);
    }
}