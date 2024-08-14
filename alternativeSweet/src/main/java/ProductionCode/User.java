package ProductionCode;

import java.io.*;
import java.util.*;

public class User {
    private String currentUsername;
    private static final String FEEDBACK_FILE_PATH = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\feedback.txt";
    private static final String OWNER_FILE_PATH = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\messagesToOwner.txt";
    private static final String SUPPLIER_FILE_PATH = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\messagesToSupplier.txt";
    private static final String PRODUCTS_FILE_PATH = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\products.txt";
    private static final String ORDERS_FILE_PATH = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\orders.txt";
    private Map<String, Integer> orders = new HashMap<>();
    private int currentOrderNumber = 1;
    private String username;
    private String password;
    public boolean receivedAPurchasedDessert;
    private MyApp app;

    // Constructor with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Default constructor
    public User() {
        // Default constructor
    }

    // Setter for MyApp instance
    public void setApp(MyApp app) {
        this.app = app;
        this.currentUsername = app.getCurrentUsername(); // Set current username from app
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private List<String> dashboardOptions;

    public void UserDashboard() {
        // Initialize the list of options available in the user dashboard
        dashboardOptions = new ArrayList<>();
        dashboardOptions.add("1. Post and share personal dessert creations");
        dashboardOptions.add("2. Browse and search for dessert recipes");
        dashboardOptions.add("3. Filter recipes based on dietary needs or food allergies");
        dashboardOptions.add("4. Purchase desserts directly from store owners");
        dashboardOptions.add("5. Directly communicate with store owners and suppliers");
        dashboardOptions.add("6. Provide feedback on purchased products and shared recipes");
        dashboardOptions.add("7. Manage personal account");
        dashboardOptions.add("8. Logout");
    }

    // Function to print the user dashboard options
    public void printDashboard() {
        System.out.println("User Dashboard:");
        for (String option : dashboardOptions) {
            System.out.println(option);
        }
    }

    public void chatWithOwner_Supplier(String userType, String message) {
        String filePath;

        // Determine the file path based on the user type
        if ("owner".equalsIgnoreCase(userType)) {
            filePath = OWNER_FILE_PATH;
        } else if ("supplier".equalsIgnoreCase(userType)) {
            filePath = SUPPLIER_FILE_PATH;
        } else {
            System.err.println("Invalid user type. Please specify 'owner' or 'supplier'.");
            return;
        }

        // Write the message to the determined file path
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(message);
            writer.newLine(); // Add a new line after each message
            System.out.println("Message sent to " + userType + " and saved successfully.");
        } catch (IOException e) {
            System.err.println("Error while saving the message: " + e.getMessage());
        }
    }
    public void purchaseProduct(String productName, int quantity) {
//        if (currentUsername == null || currentUsername.isEmpty()) {
//            System.out.println("No user is currently logged in.");
//            return;
//        }

        // Step 1: Check if the product exists in the products file
        boolean productAvailable = false;
        try (BufferedReader productReader = new BufferedReader(new FileReader(PRODUCTS_FILE_PATH))) {
            String line;
            while ((line = productReader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase(productName.trim())) {
                    productAvailable = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error while checking product availability: " + e.getMessage());
        }

        if (!productAvailable) {
            System.out.println("Product not available.");
            return;
        }

        // Step 2: Process the order
        boolean orderUpdated = false;
        StringBuilder updatedOrders = new StringBuilder();
        boolean orderFound = false;

        try (BufferedReader orderReader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;

            while ((line = orderReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String user = parts[1];
                    String existingProduct = parts[3];

                    if (user.equals(currentUsername) && existingProduct.equalsIgnoreCase(productName)) {
                        int existingQuantity = Integer.parseInt(parts[2]);
                        updatedOrders.append(currentOrderNumber).append(",").append(currentUsername).append(",").append(existingQuantity + quantity).append(",").append(productName).append("\n");
                        orderUpdated = true;
                        orderFound = true;
                    } else {
                        updatedOrders.append(line).append("\n");
                        receivedAPurchasedDessert=true;
                    }
                }
            }

            if (!orderFound) {
                updatedOrders.append(currentOrderNumber).append(",").append(currentUsername).append(",").append(quantity).append(",").append(productName).append("\n");
                currentOrderNumber++;
            }

            // Write updated orders back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE_PATH))) {
                writer.write(updatedOrders.toString());
            }

            // Set receivedAPurchasedDessert to true after successful processing
            receivedAPurchasedDessert = true;

        } catch (IOException e) {
            System.err.println("Error while processing the order: " + e.getMessage());
        }
    }


    public void printAllOrdersForCurrentUser() {
        if (currentUsername == null || currentUsername.isEmpty()) {
            System.out.println("No user is currently logged in.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE_PATH))) {
            String line;
            boolean hasOrders = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String user = parts[1];
                    if (user.equals(currentUsername)) {
                        System.out.println("Order Number: " + parts[0]);
                        System.out.println("Username: " + parts[1]);
                        System.out.println("Quantity: " + parts[2]);
                        System.out.println("Product Name: " + parts[3]);
                        System.out.println("------------------------------");
                        hasOrders = true;
                    }
                }
            }

            if (!hasOrders) {
                System.out.println("No orders found for user: " + currentUsername);
            }
        } catch (IOException e) {
            System.err.println("Error while reading orders: " + e.getMessage());
        }
    }

    public void sendFeedback(String feedbackMessage) {
        if (currentUsername == null || currentUsername.isEmpty()) {
            System.out.println("No user is currently logged in. Cannot send feedback.");
            return;
        }

        String feedbackEntry = currentUsername + "," + feedbackMessage;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FEEDBACK_FILE_PATH, true))) {
            writer.write(feedbackEntry);
            writer.newLine(); // Add a new line after each feedback
            System.out.println("Feedback sent successfully.");
        } catch (IOException e) {
            System.err.println("Error while saving the feedback: " + e.getMessage());
        }
    }

    // Placeholder for isProductAvailable method
    /*
    public boolean isProductAvailable(String productName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase(productName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error while checking product availability: " + e.getMessage());
        }
        return false;
    }
    */

  
}
