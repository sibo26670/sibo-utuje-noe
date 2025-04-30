package Q2;

import java.util.*;

// Abstract Class: ShoppingItem
abstract class ShoppingItem {
    String itemId;
    String itemName;
    String itemDescription;
    double price;
    int stockAvailable;

    abstract void updateStock(int quantity);
    abstract void addToCart();
    abstract void generateInvoice();
    abstract void validateItem();
}

// Concrete Class: ElectronicsItem
class ElectronicsItem extends ShoppingItem {
    String warrantyDetails;

    @Override
    void updateStock(int quantity) {
        this.stockAvailable -= quantity;
    }

    @Override
    void addToCart() {
        if (stockAvailable > 0) {
            System.out.println("Electronics item added to cart: " + itemName);
        } else {
            System.out.println("Out of stock.");
        }
    }

    @Override
    void generateInvoice() {
        System.out.println("Invoice generated for Electronics Item: " + itemName);
    }

    @Override
    void validateItem() {
        if (stockAvailable <= 0) {
            System.out.println("Invalid stock availability.");
        }
    }
}

// Encapsulation: Customer Class
class Customer {
    private String customerId;
    private String customerName;
    private String email;
    private String address;
    private String phone;

    public Customer(String customerId, String customerName, String email, String address, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getCustomerName() {
        return customerName;
    }
}

// Encapsulation: ShoppingCart Class
class ShoppingCart {
    private String cartId;
    private List<ShoppingItem> cartItems = new ArrayList<>();
    private double totalPrice = 0.0;

    public void addItem(ShoppingItem item) {
        if (item.stockAvailable > 0) {
            cartItems.add(item);
            totalPrice += item.price;
            System.out.println(item.itemName + " has been added to the cart.");
        } else {
            System.out.println(item.itemName + " is out of stock.");
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

// Q1.Main Class
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create customer
        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        Customer customer = new Customer("C001", name, email, "123 Q1.Main Street", "1234567890");
        ShoppingCart cart = new ShoppingCart();

        // Create an item
        ElectronicsItem phone = new ElectronicsItem();
        phone.itemId = "E001";
        phone.itemName = "Smartphone";
        phone.itemDescription = "Latest model smartphone with great features.";
        phone.price = 699.99;
        phone.stockAvailable = 5;

        // Display available item
        System.out.println("\nAvailable Item:");
        System.out.println("1. " + phone.itemName + " - $" + phone.price);

        System.out.println("\nEnter the item number to add to the cart:");
        int choice = scanner.nextInt();

        if (choice == 1) {
            cart.addItem(phone);
            phone.updateStock(1);
        } else {
            System.out.println("Invalid choice.");
        }

        // Display total price
        System.out.println("\nCart Total: $" + cart.getTotalPrice());

        // Payment simulation
        System.out.println("\nProceed to Payment (yes/no):");
        scanner.nextLine(); // Consume newline
        String paymentOption = scanner.nextLine();

        if (paymentOption.equalsIgnoreCase("yes")) {
            System.out.println("Payment Successful. Thank you for shopping with us!");
        } else {
            System.out.println("Payment canceled. Your cart is saved for later.");
        }

        scanner.close();
    }
}

