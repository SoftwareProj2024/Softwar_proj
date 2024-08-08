package ProductionCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyApp {

    private String filePath = "";
    public boolean isUserLoggedIn;
    public boolean isSigndUp;
    
    public ArrayList<DessertCreation> sharedDesserts;
    public ArrayList<User> users;
    public ArrayList<StoreOwner> storeOwners;
    public ArrayList<MaterialSupplier> materialSuppliers;
    public boolean userDashOpen;
	public boolean isUpdated;
	private boolean supplierDashOpen;
	private boolean ownerDashOpen;
	public boolean isDessertCreationPosted;
	String dessertFilePath = "files/shared_desserts.txt";
	
	

    public MyApp() {
        super();
        
        this.users = new ArrayList<>();
        this.storeOwners = new ArrayList<>();
        this.materialSuppliers = new ArrayList<>();
        this.sharedDesserts = new ArrayList<>();
        
        
        

        // Load data from files
        loadUsersFromFile("files/users.txt", users);
        loadUsersFromFile("files/store_owners.txt", storeOwners);
        loadUsersFromFile("files/material_suppliers.txt", materialSuppliers);
        loadDessertsFromFile(dessertFilePath);
    }

    
    
    public void postDessertCreation(String title, String description) {
        if (isUserLoggedIn) {
            DessertCreation newDessert = new DessertCreation(title, description);
            sharedDesserts.add(newDessert);
            isDessertCreationPosted = true;
            saveDessertsToFile(dessertFilePath);
        } else {
            isDessertCreationPosted = false;
        }
    }
    
    private void saveDessertsToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (DessertCreation dessert : sharedDesserts) {
                writer.write(dessert.getTitle() + "," + dessert.getDescription());
                writer.newLine();
            }
            System.out.println("Dessert creations saved successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + filePath + " " + e.getMessage());
        }
    }
    
    private void loadDessertsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String title = parts[0];
                    String description = parts[1];
                    sharedDesserts.add(new DessertCreation(title, description));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    private <T extends User> void loadUsersFromFile(String filePath, ArrayList<T> userList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    String password = parts[1];
                    userList.add((T) new User(name, password));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signUp(String username, String password, String role) {
        isSigndUp = false; // Reset state before attempting sign-up
        if (role.equalsIgnoreCase("user")) {
            filePath = "files/users.txt";
            users.add(new User(username, password));
            isSigndUp=true;
        } else if (role.equalsIgnoreCase("store_owner")) {
            filePath = "files/store_owners.txt";
            storeOwners.add(new StoreOwner(username, password));
            isSigndUp=true;
        } else if (role.equalsIgnoreCase("material_supplier")) {
            filePath = "files/material_suppliers.txt";
            materialSuppliers.add(new MaterialSupplier(username, password));
            isSigndUp=true;
        } else {
            System.out.println("Invalid role specified.");
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            isSigndUp = true;
            System.out.println("User details written to file successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + filePath + " " + e.getMessage());
        }
    }

    public void login(String username, String password, String role) {
        isUserLoggedIn = false;

        if (role.equalsIgnoreCase("user")) {
            isUserLoggedIn = checkLogin(users, username, password);
        } else if (role.equalsIgnoreCase("store_owner")) {
            isUserLoggedIn = checkLogin(storeOwners, username, password);
        } else if (role.equalsIgnoreCase("material_supplier")) {
            isUserLoggedIn = checkLogin(materialSuppliers, username, password);
        } else {
            System.out.println("Invalid role specified.");
        }
        
        if (isUserLoggedIn) {
            openUserDash();
        }
    }

    private <T extends User> boolean checkLogin(ArrayList<T> userList, String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void openUserDash() {
        if (!isUserLoggedIn) return; 
        userDashOpen = true;
        System.out.println("Welcome, user!");
    }

    public void updateAccount(String username, String newPassword, String role) {
        if (role.equalsIgnoreCase("user")) {
            updateUserDetails(users, username, newPassword, "files/users.txt");
            openUserDash();
            isUpdated=true;
        } else if (role.equalsIgnoreCase("store_owner")) {
            updateUserDetails(storeOwners, username, newPassword, "files/store_owners.txt");
            isUpdated=true;
            openOwnerDash();
        } else if (role.equalsIgnoreCase("material_supplier")) {
            updateUserDetails(materialSuppliers, username, newPassword, "files/material_suppliers.txt");
            openSupplierDash();
            isUpdated=true;
        } else {
            System.out.println("Invalid role specified.");
        }
    }

    private void openSupplierDash() {
		// TODO Auto-generated method stub
    	supplierDashOpen=true;
		
	}

	private void openOwnerDash() {
		// TODO Auto-generated method stub
		ownerDashOpen=true;
	}

	private <T extends User> void updateUserDetails(ArrayList<T> userList, String username, String newPassword, String filePath) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                break;
            }
        }
        saveUsersToFile(userList, filePath);
    }

    private <T extends User> void saveUsersToFile(ArrayList<T> userList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : userList) {
                writer.write(user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
            System.out.println("User details updated successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + filePath + " " + e.getMessage());
        }
    }
}
