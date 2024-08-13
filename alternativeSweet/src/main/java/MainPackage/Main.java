package MainPackage;


import java.io.IOException;
import java.util.Scanner;

import ProductionCode.ContentManagement;
import ProductionCode.MyApp;
import ProductionCode.User;

public class Main {
    public static void main(String[] args) {
        try {
            MyApp app = new MyApp();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Alternative Sweet Management System!");

            while (true) {
                System.out.println("Please choose an option:");
                System.out.println("1. Sign Up");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Enter Username:");
                        String username = scanner.nextLine();
                        System.out.println("Enter Password:");
                        String password = scanner.nextLine();
                        System.out.println("Enter Role (user, Store_owner, Material_supplier, Admin):");
                        String role = scanner.nextLine();
                        app.SignUp(username, password, role);
                        break;

                    case "2":
                        System.out.println("Enter Username:");
                        username = scanner.nextLine();
                        System.out.println("Enter Password:");
                        password = scanner.nextLine();
                        System.out.println("Enter Role (user, Store_owner, Material_supplier, Admin):");
                        role = scanner.nextLine();
                        app.login(username, password, role);

                        if (app.isUserLoggedIn) {
                            switch (role) {
                                case "Admin":
                                    adminDashboard(app, scanner);
                                    break;
                                case "Store_owner":
                                    storeOwnerDashboard(app, scanner);
                                    break;
                                case "Material_supplier":
                                    supplierDashboard(app, scanner);
                                    break;
                                default:
                                    userDashboard(app.user, scanner);
                                    break;
                            }
                        } else {
                            System.out.println("Login failed. Please check your credentials.");
                        }
                        break;

                    case "3":
                        System.out.println("Exiting the system. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void adminDashboard(MyApp app, Scanner scanner) throws IOException {
        while (true) {
            app.AdminDashboardpage();
            System.out.println("Enter your choice:");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    app.AdminDashboardOptiones("1");
                    userManagement(app, scanner);
                    break;
                case "2":
                    app.AdminDashboardOptiones("2");
                    System.out.println("Generate Financial Report or view Best Selling Products:");
                    String reportOption = scanner.nextLine();
                    if (reportOption.equals("Generate Financial Report")) {
                        app.getSalesReport();
                    } else if (reportOption.equals("Best Selling Product")) {
                        app.getBestSellingProduct();
                    }
                    break;
                case "3":
                    app.AdminDashboardOptiones("3");
                    contentManagement(app, scanner);
                    break;
                default:
                    System.out.println("Returning to main menu.");
                    return;
            }
        }
    }

    private static void userManagement(MyApp app, Scanner scanner) throws IOException {
        while (true) {
            System.out.println("User Management Options:");
            System.out.println("1. View All Users");
            System.out.println("2. Add User");
            System.out.println("3. Delete User");
            System.out.println("4. Update User");
            System.out.println("5. Back to Admin Dashboard");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    app.viewAllUsers();
                    break;
                case "2":
                    System.out.println("Enter new Username:");
                    String newUsername = scanner.nextLine();
                    System.out.println("Enter Password:");
                    String newPassword = scanner.nextLine();
                    System.out.println("Enter Role:");
                    String newRole = scanner.nextLine();
                    app.addUser(newUsername, newPassword, newRole);
                    break;
                case "3":
                    System.out.println("Enter Username to delete:");
                    String deleteUsername = scanner.nextLine();
                    app.deleteUser(deleteUsername);
                    break;
                case "4":
                    System.out.println("Enter current Username:");
                    String oldUsername = scanner.nextLine();
                    System.out.println("Enter new Username:");
                    newUsername = scanner.nextLine();
                    System.out.println("Enter new Password:");
                    newPassword = scanner.nextLine();
                    app.updateUser(oldUsername, newUsername, newPassword);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void storeOwnerDashboard(MyApp app, Scanner scanner) throws IOException {
        while (true) {
            System.out.println("Store Owner Dashboard:");
            System.out.println("1. Manage Products");
            System.out.println("2. View Orders");
            System.out.println("3. Create Special Request");
            System.out.println("4. Back to Main Menu");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageProducts(app, scanner);
                    break;
                case "2":
                    app.listOrders();
                    break;
                case "3":
                    System.out.println("Enter request details:");
                    String requestDetails = scanner.nextLine();
                    // Implement the method to handle special requests
                    // app.createSpecialRequest(requestDetails);
                    System.out.println("Special request feature not implemented yet.");
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void manageProducts(MyApp app, Scanner scanner) throws IOException {
        while (true) {
            System.out.println("Product Management:");
            System.out.println("1. Add Product");
            System.out.println("2. Edit Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Back to Store Owner Dashboard");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter Product Name:");
                    String productName = scanner.nextLine();
                    System.out.println("Enter Product Price:");
                    String productPrice = scanner.nextLine();
                    System.out.println("Enter Product Expiration Date:");
                    String expDate = scanner.nextLine();
                    app.addProduct(productName, productPrice, expDate);
                    break;
                case "2":
                    System.out.println("Enter Product Name to Edit:");
                    productName = scanner.nextLine();
                    System.out.println("Enter new Price:");
                    productPrice = scanner.nextLine();
                    System.out.println("Enter new Expiration Date:");
                    expDate = scanner.nextLine();
                    app.editProduct(productName, productPrice, expDate);
                    break;
                case "3":
                    System.out.println("Enter Product Name to Delete:");
                    productName = scanner.nextLine();
                    app.deleteProduct(productName);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void supplierDashboard(MyApp app, Scanner scanner) throws IOException {
        while (true) {
            System.out.println("Material Supplier Dashboard:");
            System.out.println("1. View Orders");
            System.out.println("2. Process Order");
            System.out.println("3. Back to Main Menu");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    app.listOrders();
                    break;
                case "2":
                    System.out.println("Enter Order Number to Process:");
                    String orderNum = scanner.nextLine();
                    app.processOrder(orderNum, "Process");
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void userDashboard(User user, Scanner scanner) {
        user.UserDashboard();
        while (true) {
            user.printDashboard();
            System.out.println("Enter your choice:");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Feature not implemented yet: Post and share personal dessert creations");
                    break;
                case "2":
                    System.out.println("Feature not implemented yet: Browse and search for dessert recipes");
                    break;
                case "3":
                    System.out.println("Feature not implemented yet: Filter recipes based on dietary needs or food allergies");
                    break;
                case "4":
                    System.out.println("Enter Product Name to Purchase:");
                    String productName = scanner.nextLine();
                    System.out.println("Enter Quantity:");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    user.purchaseProduct(productName, quantity);
                    break;
                case "5":
                    System.out.println("Enter 'owner' or 'supplier' to communicate with:");
                    String userType = scanner.nextLine();
                    System.out.println("Enter your message:");
                    String message = scanner.nextLine();
                    user.chatWithOwner_Supplier(userType, message);
                    break;
                case "6":
                    System.out.println("Enter your feedback:");
                    String feedback = scanner.nextLine();
                    user.sendFeedback(feedback);
                    break;
                case "7":
                    System.out.println("Feature not implemented yet: Manage personal account");
                    break;
                case "8":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void contentManagement(MyApp app, Scanner scanner) throws IOException {
        ContentManagement contentManagement = app.contentmanagement;
        while (true) {
            System.out.println("Content Management:");
            System.out.println("1. View Recipes");
            System.out.println("2. Delete Recipe");
            System.out.println("3. View Feedback");
            System.out.println("4. Respond to Feedback");
            System.out.println("5. Delete Feedback");
            System.out.println("6. Back to Admin Dashboard");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    contentManagement.viewRecipes();
                    break;
                case "2":
                    System.out.println("Enter the name of the recipe to delete:");
                    String recipeName = scanner.nextLine();
                    contentManagement.deleteRecipes(recipeName);
                    break;
                case "3":
                    contentManagement.viewFeedback();
                    break;
                case "4":
                    System.out.println("Enter Feedback ID to respond to:");
                    String feedbackId = scanner.nextLine();
                    System.out.println("Enter your response message:");
                    String responseMessage = scanner.nextLine();
                    contentManagement.responseFeedback(feedbackId, responseMessage);
                    break;
                case "5":
                    System.out.println("Enter Feedback ID to delete:");
                    String feedbackIdToDelete = scanner.nextLine();
                    contentManagement.deleteFeedback(feedbackIdToDelete);
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

