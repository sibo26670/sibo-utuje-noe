    package Q1;
    
    import java.util.*;
    
    abstract class StockItem {
        protected String itemId;
        protected String itemName;
        protected int quantityInStock;
        protected double pricePerUnit;
        protected String category;
        protected String supplier;
    
        public StockItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier) {
            if (quantityInStock < 0 || pricePerUnit <= 0) throw new IllegalArgumentException("Invalid stock or price.");
            this.itemId = itemId;
            this.itemName = itemName;
            this.quantityInStock = quantityInStock;
            this.pricePerUnit = pricePerUnit;
            this.category = category;
            this.supplier = supplier;
        }
    
        public abstract void updateStock(int quantity);
        public abstract double calculateStockValue();
        public abstract void generateStockReport();
        public abstract boolean validateStock();
    }
    
    // Electronics Item
    class ElectronicsItem extends StockItem {
        private int warrantyMonths;
        private double discountPercent;
    
        public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                               String supplier, int warrantyMonths, double discountPercent) {
            super(itemId, itemName, quantityInStock, pricePerUnit, "Electronics", supplier);
            if (discountPercent > 50) throw new IllegalArgumentException("Discount cannot exceed 50%");
            if (warrantyMonths < 0 || warrantyMonths > 60) throw new IllegalArgumentException("Invalid warranty period.");
            this.warrantyMonths = warrantyMonths;
            this.discountPercent = discountPercent;
        }
    
        public void applyDiscount() {
            pricePerUnit -= pricePerUnit * (discountPercent / 100.0);
        }
    
        @Override
        public void updateStock(int quantity) {
            quantityInStock += quantity;
        }
    
        @Override
        public double calculateStockValue() {
            return quantityInStock * pricePerUnit;
        }
    
        @Override
        public void generateStockReport() {
            System.out.println("[Electronics] " + itemName + " | Stock: " + quantityInStock + " | Warranty: " + warrantyMonths + " months");
        }
    
        @Override
        public boolean validateStock() {
            return quantityInStock > 0;
        }
    }
    
    // Clothing Item
    class ClothingItem extends StockItem {
        private List<String> sizes;
        private List<String> colors;
        private double discountPercent;
    
        public ClothingItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                            String supplier, List<String> sizes, List<String> colors, double discountPercent) {
            super(itemId, itemName, quantityInStock, pricePerUnit, "Clothing", supplier);
            if (discountPercent > 50) throw new IllegalArgumentException("Discount cannot exceed 50%");
            this.sizes = sizes;
            this.colors = colors;
            this.discountPercent = discountPercent;
        }
    
        @Override
        public void updateStock(int quantity) {
            quantityInStock += quantity;
        }
    
        @Override
        public double calculateStockValue() {
            double discountedPrice = pricePerUnit - (pricePerUnit * discountPercent / 100.0);
            return quantityInStock * discountedPrice;
        }
    
        @Override
        public void generateStockReport() {
            System.out.println("[Clothing] " + itemName + " | Sizes: " + sizes + " | Colors: " + colors);
        }
    
        @Override
        public boolean validateStock() {
            return quantityInStock > 0;
        }
    }
    
    // Grocery Item
    class GroceryItem extends StockItem {
        private Date expirationDate;
    
        public GroceryItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                           String supplier, Date expirationDate) {
            super(itemId, itemName, quantityInStock, pricePerUnit, "Groceries", supplier);
            this.expirationDate = expirationDate;
        }
    
        public boolean isNearExpiry() {
            long diff = expirationDate.getTime() - new Date().getTime();
            return diff < (5L * 24 * 60 * 60 * 1000); // within 5 days
        }
    
        @Override
        public void updateStock(int quantity) {
            quantityInStock += quantity;
        }
    
        @Override
        public double calculateStockValue() {
            return quantityInStock * pricePerUnit;
        }
    
        @Override
        public void generateStockReport() {
            System.out.println("[Grocery] " + itemName + " | Expiry: " + expirationDate + " | Near Expiry: " + isNearExpiry());
        }
    
        @Override
        public boolean validateStock() {
            return new Date().before(expirationDate);
        }
    }
    
    // Furniture Item
    class FurnitureItem extends StockItem {
        private double weightKg;
    
        public FurnitureItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                             String supplier, double weightKg) {
            super(itemId, itemName, quantityInStock, pricePerUnit, "Furniture", supplier);
            this.weightKg = weightKg;
        }
    
        public boolean isWellPacked() {
            return weightKg < 100; // Arbitrary condition
        }
    
        @Override
        public void updateStock(int quantity) {
            quantityInStock += quantity;
        }
    
        @Override
        public double calculateStockValue() {
            return quantityInStock * pricePerUnit;
        }
    
        @Override
        public void generateStockReport() {
            System.out.println("[Furniture] " + itemName + " | Weight: " + weightKg + "kg");
        }
    
        @Override
        public boolean validateStock() {
            return isWellPacked();
        }
    }
    
    // Perishable Items and another
    class PerishableItem extends StockItem {
        private Date expirationDate;
    
        public PerishableItem(String itemId, String itemName, int quantityInStock, double pricePerUnit,
                              String supplier, Date expirationDate) {
            super(itemId, itemName, quantityInStock, pricePerUnit, "Perishable", supplier);
            this.expirationDate = expirationDate;
        }
    
        public boolean isExpired() {
            return new Date().after(expirationDate);
        }
    
        @Override
        public void updateStock(int quantity) {
            quantityInStock += quantity;
        }
    
        @Override
        public double calculateStockValue() {
            return quantityInStock * pricePerUnit;
        }
    
        @Override
        public void generateStockReport() {
            System.out.println("[Perishable] " + itemName + " | Expired: " + isExpired());
        }
    
        @Override
        public boolean validateStock() {
            return !isExpired();
        }
    }
    
    // Encapsulated Product class
    class Product {
        private String productId;
        private String productName;
        private String brand;
        private String supplier;
        private int stockQuantity;
    
        public Product(String productId, String productName, String brand, String supplier, int stockQuantity) {
            if (stockQuantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
            if (productName.isEmpty() || brand.isEmpty()) throw new IllegalArgumentException("Invalid product info.");
            this.productId = productId;
            this.productName = productName;
            this.brand = brand;
            this.supplier = supplier;
            this.stockQuantity = stockQuantity;
        }
    }
    
    // Encapsulated Supplier class
    class Supplier {
        private String supplierId;
        private String companyName;
        private String contactPerson;
        private String phone;
        private String email;
    
        public Supplier(String supplierId, String companyName, String contactPerson, String phone, String email) {
            if (!phone.matches("\\d{10}") || !email.contains("@")) throw new IllegalArgumentException("Invalid contact.");
            this.supplierId = supplierId;
            this.companyName = companyName;
            this.contactPerson = contactPerson;
            this.phone = phone;
            this.email = email;
        }
    }
    
    // Encapsulated Warehouse class
    class Warehouse {
        private String warehouseId;
        private String location;
        private int capacity;
        private String managerName;
    
        public Warehouse(String warehouseId, String location, int capacity, String managerName) {
            this.warehouseId = warehouseId;
            this.location = location;
            this.capacity = capacity;
            this.managerName = managerName;
        }
    }
    
    public class AdvancedStockManagementSystem {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<StockItem> stockItems = new ArrayList<>();
    
            while (true) {
                System.out.println("\n=== Stock Management Menu ===");
                System.out.println("1. Add Electronics Item");
                System.out.println("2. Generate Stock Reports");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // consume newline
    
                switch (option) {
                    case 1 -> {
                        System.out.print("Item ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Quantity: ");
                        int qty = scanner.nextInt();
                        System.out.print("Price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Supplier: ");
                        String sup = scanner.nextLine();
                        System.out.print("Warranty (months): ");
                        int warranty = scanner.nextInt();
                        System.out.print("Discount (%): ");
                        double discount = scanner.nextDouble();
                        ElectronicsItem item = new ElectronicsItem(id, name, qty, price, sup, warranty, discount);
                        item.applyDiscount();
                        stockItems.add(item);
                        System.out.println("Electronics item added.");
                    }
                    case 2 -> {
                        System.out.println("--- Stock Reports ---");
                        for (StockItem item : stockItems) {
                            item.generateStockReport();
                            System.out.println("Stock Value: $" + item.calculateStockValue());
                            System.out.println("Valid: " + item.validateStock());
                            System.out.println();
                        }
                    }
                    case 3 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }
