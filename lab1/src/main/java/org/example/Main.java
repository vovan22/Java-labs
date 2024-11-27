package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.start();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Product {
    private String name;
    private String category;
    private double price;
}

@Data
class Cart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getName() + " was added to the cart.");
    }

    public void removeProduct(String productName) {
        Product product = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
        if (product != null) {
            products.remove(product);
            System.out.println(product.getName() + " was removed from the cart.");
        } else {
            System.out.println("Product not found in the cart.");
        }
    }

    public void viewCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Products in your cart:");
            products.forEach(product -> System.out.println("- " + product.getName() + " ($" + product.getPrice() + ")"));
        }
    }

    public void clearCart() {
        products.clear();
    }
}

@Data
class OrderHistory {
    private List<List<Product>> orders = new ArrayList<>();

    public void addOrder(List<Product> order) {
        orders.add(new ArrayList<>(order));
        System.out.println("Order has been placed and added to the history.");
    }

    public void viewOrderHistory() {
        if (orders.isEmpty()) {
            System.out.println("No orders have been placed yet.");
        } else {
            System.out.println("Order history:");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println("Order " + (i + 1) + ":");
                orders.get(i).forEach(product -> System.out.println("- " + product.getName() + " ($" + product.getPrice() + ")"));
            }
        }
    }
}

class Shop {
    private List<Product> catalog;
    private Cart cart;
    private OrderHistory orderHistory;

    public Shop() {
        catalog = new ArrayList<>();
        cart = new Cart();
        orderHistory = new OrderHistory();
        initializeCatalog();
    }

    private void initializeCatalog() {
        catalog.add(new Product("Laptop", "Electronics", 999.99));
        catalog.add(new Product("Smartphone", "Electronics", 499.99));
        catalog.add(new Product("Headphones", "Accessories", 49.99));
        catalog.add(new Product("Chair", "Furniture", 89.99));
        catalog.add(new Product("Desk", "Furniture", 159.99));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Welcome to the online shop!");
        do {
            System.out.println("\nAvailable commands: view, search, add, remove, cart, order, history, quit");
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "view":
                    viewCatalog();
                    break;
                case "search":
                    searchProduct(scanner);
                    break;
                case "add":
                    addProductToCart(scanner);
                    break;
                case "remove":
                    removeProductFromCart(scanner);
                    break;
                case "cart":
                    cart.viewCart();
                    break;
                case "order":
                    placeOrder();
                    break;
                case "history":
                    orderHistory.viewOrderHistory();
                    break;
                case "quit":
                    System.out.println("Thank you for visiting the online shop!");
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        } while (!command.equals("quit"));
    }

    private void viewCatalog() {
        System.out.println("\nProduct Catalog:");
        catalog.forEach(product -> System.out.println("- " + product.getName() + " (Category: " + product.getCategory() + ", Price: $" + product.getPrice() + ")"));
    }

    private void searchProduct(Scanner scanner) {
        System.out.print("Enter product name or category to search: ");
        String query = scanner.nextLine().trim().toLowerCase();
        List<Product> results = catalog.stream()
                .filter(product -> product.getName().toLowerCase().contains(query) || product.getCategory().toLowerCase().contains(query))
                .collect(Collectors.toList());
        if (results.isEmpty()) {
            System.out.println("No products found matching your search.");
        } else {
            System.out.println("Search results:");
            results.forEach(product -> System.out.println("- " + product.getName() + " (Category: " + product.getCategory() + ", Price: $" + product.getPrice() + ")"));
        }
    }

    private void addProductToCart(Scanner scanner) {
        System.out.print("Enter product name to add to cart: ");
        String productName = scanner.nextLine().trim();
        Product product = catalog.stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
        if (product != null) {
            cart.addProduct(product);
        } else {
            System.out.println("Product not found in catalog.");
        }
    }

    private void removeProductFromCart(Scanner scanner) {
        System.out.print("Enter product name to remove from cart: ");
        String productName = scanner.nextLine().trim();
        cart.removeProduct(productName);
    }

    private void placeOrder() {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. Add products before placing an order.");
        } else {
            orderHistory.addOrder(cart.getProducts());
            cart.clearCart();
        }
    }
}
