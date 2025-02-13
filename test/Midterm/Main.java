// Project 1

import java.io.*;
import java.util.*;

// Custom Exception for Invalid Products
class InvalidProductException extends Exception {
    public InvalidProductException(String message) {
        super(message);
    }
}

// Custom Exception for Invalid Numbers
class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}

// Custom Exception for Invalid Names
class InvalidNameException extends Exception {
    public InvalidNameException(String message) {
        super(message);
    }
}

// Product Class
class Product implements Comparable<Product> {
    private String productCode;
    private String productName;
    private int unitPrice;
    private int totalUnitsSold;
    private int totalSales;

    public Product(String productCode, String productName, int unitPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.totalUnitsSold = 0;
        this.totalSales = 0;
    }

    public void displayProductInfo() {
        System.out.printf("%-15s (%s) unit price = %d\n", productName, productCode, unitPrice);
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void addSales(int units) {
        totalUnitsSold += units;
        totalSales += units * unitPrice;
    }

    public int getTotalUnitsSold() {
        return totalUnitsSold;
    }

    public int getTotalSales() {
        return totalSales;
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(other.totalUnitsSold, this.totalUnitsSold); // Sort by units sold (descending)
    }
}

// Customer Class
class Customer implements Comparable<Customer> {
    private String name;
    private int points;

    public Customer(String name) {
        this.name = name;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int pointsEarned) {
        this.points += pointsEarned;
    }

    public int redeemPoints() {
        if (points >= 100) {
            points -= 100;
            return 5;  // 5% discount
        }
        return 0;  // No discount
    }

    @Override
    public int compareTo(Customer other) {
        return Integer.compare(other.points, this.points);  // Sort by points (descending)
    }
}

// Installment Class
class Installment {
    private int months;
    private double interestRate;

    public Installment(int months, double interestRate) {
        this.months = months;
        this.interestRate = interestRate;
    }

    public int getMonths() {
        return months;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void displayInstallmentInfo() {
        System.out.printf("%2d-month plan with %.2f%% monthly interest\n", months, interestRate);
    }
}

// Order Class
class Order {
    private int orderId;
    private Customer customer;
    private Product product;
    private int units;
    private int installmentPlan;

    public Order(int orderId, Customer customer, Product product, int units, int installmentPlan) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.units = units;
        this.installmentPlan = installmentPlan;
    }

    public void processOrder(List<Installment> installmentPlans) {
        float subtotal1 = product.getUnitPrice() * units;
        int pointsEarned = (int) (subtotal1 / 500);
        product.addSales(units);

        float discount = 0;
        boolean usedPoints = false;
        if (customer.getPoints() == 0) {
            discount = 200;
        } else if (customer.getPoints() >= 100) {
            discount = subtotal1 * 0.05f;
            customer.redeemPoints();
            usedPoints = true;
        }

        float subtotal2 = subtotal1 - discount;
        float totalInterest = 0;
        float totalPayment = subtotal2;
        float monthlyPayment = 0;

        if (installmentPlan > 0) {
            for (Installment plan : installmentPlans) {
                if (plan.getMonths() == installmentPlan) {
                    totalInterest = subtotal2 * (float) plan.getInterestRate() * installmentPlan / 100;
                    totalPayment = subtotal2 + totalInterest;
                    monthlyPayment = totalPayment / installmentPlan;
                    break;
                }
            }
        }

        System.out.printf("Order ID: %2d, Customer: %-5s (Points before: %,6d)\n", orderId, customer.getName(), customer.getPoints());
        System.out.printf("Product: %-14s x %2d, Subtotal(1): %,10.2f\n", product.getProductName(), units, subtotal1);
        System.out.printf("Discount applied: %,10.2f %s\n", discount, usedPoints ? "(Used 100 pts)" : "");
        System.out.printf("Subtotal(2): %,10.2f\n", subtotal2);
        if (installmentPlan > 0) {
            System.out.printf("Installment Plan: %d months, Total Interest: %,10.2f\n", installmentPlan, totalInterest);
            System.out.printf("Total Payment: %,10.2f, Monthly Payment: %,10.2f\n", totalPayment, monthlyPayment);
        } else {
            System.out.printf("Full Payment: %,10.2f\n", totalPayment);
        }
        System.out.printf("Points earned for next order: %d\n", pointsEarned);

        customer.addPoints(pointsEarned);
        System.out.println("---------------------------");
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Installment> installments = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        try {
            // Reading Product Data
            Scanner productScanner = new Scanner(new File("products.txt"));
            productScanner.nextLine(); // Skip header
            while (productScanner.hasNextLine()) {
                String[] data = productScanner.nextLine().split(",");
                products.add(new Product(data[0].trim(), data[1].trim(), Integer.parseInt(data[2].trim())));
            }
            productScanner.close();

            // Reading Installment Data
            Scanner installmentScanner = new Scanner(new File("installments.txt"));
            installmentScanner.nextLine(); // Skip header
            while (installmentScanner.hasNextLine()) {
                String[] data = installmentScanner.nextLine().split(",");
                installments.add(new Installment(Integer.parseInt(data[0].trim()), Double.parseDouble(data[1].trim())));
            }
            installmentScanner.close();

            // Reading Order Data
            Scanner orderScanner = new Scanner(new File("orders.txt"));
            orderScanner.nextLine(); // Skip header
            while (orderScanner.hasNextLine()) {
                String[] data = orderScanner.nextLine().split(",");
                int orderId = Integer.parseInt(data[0].trim());
                String customerName = data[1].trim();

                // Get or create customer
                Customer customer = customers.stream().filter(c -> c.getName().equals(customerName)).findFirst().orElse(new Customer(customerName));
                if (!customers.contains(customer)) customers.add(customer);

                // Get product
                Product product = products.stream().filter(p -> p.getProductCode().equals(data[2].trim())).findFirst().orElseThrow(() -> new InvalidProductException("Invalid product code"));

                int units = Integer.parseInt(data[3].trim());
                int installmentPlan = Integer.parseInt(data[4].trim());

                orders.add(new Order(orderId, customer, product, units, installmentPlan));
            }
            orderScanner.close();

            // Process Orders
            System.out.println("=== Order Processing ===");
            for (Order order : orders) {
                order.processOrder(installments);
            }

            // Product Summary
            System.out.println("\n=== Product Summary ===");
            Collections.sort(products);
            for (Product product : products) {
                System.out.printf("%-15s Total Sales: %3d units, %,12.2f THB\n", product.getProductName(), product.getTotalUnitsSold(), (float) product.getTotalSales());
            }

            // Customer Summary
            System.out.println("\n=== Customer Summary ===");
            Collections.sort(customers);
            for (Customer customer : customers) {
                System.out.printf("%-7s Remaining Points: %,6d\n", customer.getName(), customer.getPoints());
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}