package ProductionCode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyApp {

    private String filePath = "";
    public boolean isUserLoggedIn;
    public boolean isSignedUp;

    public ArrayList<User> users;
    public ArrayList<Admin> admin;
    public ArrayList<StoreOwner> store_owners;
    public ArrayList<MaterialSupplier> material_suppliers;
    public ArrayList<Product> Products;
    private boolean UserLoggedIn;
    public boolean StoreOwnerLoggedIn;
    private boolean MaterialSupplierLoggedIn;
    public boolean AdminLoggedIn;
    public boolean userDashOpen;
    // admin
    public boolean adminDashbordOpen;
    public boolean userManagementPageOpen;
    public boolean isUserListVisible;
    public boolean addUserChoise;
	public boolean isSigndUp;
	public boolean addedSuccessfully;
	public boolean updatedSuccessfully;
	public boolean deletedSuccessfully;
	private String ownerName;
	public boolean updateMessage;
	public boolean deletedProductSuccessfully;
	public boolean reportGenerated;
	public boolean discountMessagepos;
	public boolean messageSentToUser;
	public boolean messageSentToSupplier;
	private String loggedName;
	private String ROLE;
	private String loggedPassword;
private ArrayList<Order> orders;

    
    public MyApp() throws FileNotFoundException, IOException {
        super();

        this.users = new ArrayList<>();
        this.store_owners = new ArrayList<>();
        this.material_suppliers = new ArrayList<>();
        this.admin = new ArrayList<>();
        this.Products=new ArrayList<>();
        this.orders=new ArrayList<>();
        
        loadData("files/users.txt", "user");
        loadData("files/store_owners.txt", "Store_owner");
        loadData("files/material_suppliers.txt", "Material_supplier");
        loadData("files/admin.txt", "Admin");
        loadProducts();
    }

    private void loadData(String fileName, String role) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    String password = parts[1];
                    switch (role) {
                        case "user":
                            this.users.add(new User(name, password));
                            break;
                        case "Store_owner":
                            this.store_owners.add(new StoreOwner(name, password));
                            break;
                        case "Material_supplier":
                            this.material_suppliers.add(new MaterialSupplier(name, password));
                            break;
                        case "Admin":
                            this.admin.add(new Admin(name, password));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SignUp(String username, String password, String role) {
        switch (role) {
            case "user":
                filePath = "files/users.txt";
                users.add(new User(username, password));
                break;
            case "Store_owner":
                filePath = "files/store_owners.txt";
                store_owners.add(new StoreOwner(username, password));
                break;
            case "Material_supplier":
                filePath = "files/material_suppliers.txt";
                material_suppliers.add(new MaterialSupplier(username, password));
                break;
            case "Admin":
                filePath = "files/admin.txt";
                admin.add(new Admin(username, password));
                break;
        }

        updateFile(filePath, username, password, false);
        isSignedUp = true;
        System.out.println(role + " added successfully!");
        isSigndUp=true;
    }

    private void updateFile(String filePath, String username, String password, boolean isDelete) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            if (!isDelete) {
                writer.write(username + "," + password);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while updating the file: " + filePath + " " + e.getMessage());
        }
    }

    private void rewriteFile(String filePath, ArrayList<?> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Object obj : list) {
                if (obj instanceof User) {
                    writer.write(((User) obj).getUsername() + "," + ((User) obj).getPassword());
                } else if (obj instanceof StoreOwner) {
                    writer.write(((StoreOwner) obj).getUsername() + "," + ((StoreOwner) obj).getPassword());
                } else if (obj instanceof MaterialSupplier) {
                    writer.write(((MaterialSupplier) obj).getUsername() + "," + ((MaterialSupplier) obj).getPassword());
                } else if (obj instanceof Admin) {
                    writer.write(((Admin) obj).getUsername() + "," + ((Admin) obj).getPassword());
                }else if (obj instanceof Product) {
                    writer.write(((Product) obj).getProductName() + "," + ((Product) obj).getPrice()+","+((Product) obj).getExpDate());
                }
                else if (obj instanceof Order) {
                    writer.write(((Order) obj).orderNum + "," + ((Order) obj).senderName+","+((Order) obj).reciver+","+((Order) obj).productName+","+((Order) obj).status);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while rewriting the file: " + filePath + " " + e.getMessage());
        }
    }

    public void login(String username, String password, String role) {
        if(role.equals("user")) {
                for (User a : users) {
                    if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                        isUserLoggedIn = true;
                        UserLoggedIn = true;
                        openUserDash();
                        loggedName=username;
                        ROLE="user";
                        loggedPassword=password;
                        return;
                    }
                }
        }
               
            if(role.equals("Store_owner")) {
                for (StoreOwner a : store_owners) {
                    if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                        isUserLoggedIn = true;
                        StoreOwnerLoggedIn = true;
                        loggedName=username;
                        ROLE="Store_owner";
                        loggedPassword=password;
                        return;
                    }
                }
            }
            if(role.equals( "Material_supplier")) {
                for (MaterialSupplier a : material_suppliers) {
                    if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                        isUserLoggedIn = true;
                        MaterialSupplierLoggedIn = true;
                        loggedName=username;
                        ROLE="Material_supplier";
                        loggedPassword=password;
                        return;
                    }
                }
            }
            if(role.equals("Admin")){
                for (Admin a : admin) {
                    if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                        isUserLoggedIn = true;
                        AdminLoggedIn = true;
                        loggedName=username;
                        ROLE="Admin";
                        loggedPassword=password;
                        return;
                    }
                }
            }
            
        }
    

    public void openUserDash() {
        if (!UserLoggedIn) return;
        userDashOpen = true;
        System.out.println("Welcome user");
    }

    public void viewAllUsers() {
        System.out.println("List of all users:");
        for (User user : users) {
            System.out.println("Username: " + user.getUsername());
        }
        for (StoreOwner storeOwner : store_owners) {
            System.out.println("Store Owner: " + storeOwner.getUsername());
        }
        for (MaterialSupplier supplier : material_suppliers) {
            System.out.println("Supplier: " + supplier.getUsername());
        }
        isUserListVisible = true;
    }

    
    
    public void addUser(String username, String password, String role) {
        SignUp(username, password, role);
        String message="User added successfully.";
        addedSuccessfully=true;
        printMessage(message);
    }

  

	public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
        store_owners.removeIf(storeOwner -> storeOwner.getUsername().equals(username));
        material_suppliers.removeIf(supplier -> supplier.getUsername().equals(username));
        admin.removeIf(adminUser -> adminUser.getUsername().equals(username));

        rewriteFile("files/users.txt", users);
        rewriteFile("files/store_owners.txt", store_owners);
        rewriteFile("files/material_suppliers.txt", material_suppliers);
        rewriteFile("files/admin.txt", admin);

        System.out.println("User " + username + " deleted successfully!");
        String message="User deleted successfully.";
        deletedSuccessfully=true;
        printMessage(message);
        
    }

    public void updateUser(String oldUsername, String newUsername, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                user.setPassword(newPassword);
                rewriteFile("files/users.txt", users);
                System.out.println("User updated successfully!");
                String message="User updated successfully.";
                updatedSuccessfully=true;
                printMessage(message);
                return;
            }
        }
        for (StoreOwner storeOwner : store_owners) {
            if (storeOwner.getUsername().equals(oldUsername)) {
                storeOwner.setUsername(newUsername);
                storeOwner.setPassword(newPassword);
                rewriteFile("files/store_owners.txt", store_owners);
                System.out.println("Store Owner updated successfully!");
                String message="User updated successfully.";
                updatedSuccessfully=true;
                printMessage(message);
                return;
            }
        }
        for (MaterialSupplier supplier : material_suppliers) {
            if (supplier.getUsername().equals(oldUsername)) {
                supplier.setUsername(newUsername);
                supplier.setPassword(newPassword);
                rewriteFile("files/material_suppliers.txt", material_suppliers);
                System.out.println("Material Supplier updated successfully!");
                String message="User updated successfully.";
                updatedSuccessfully=true;
                printMessage(message);
                return;
            }
        }
        for (Admin adminUser : admin) {
            if (adminUser.getUsername().equals(oldUsername)) {
                adminUser.setUsername(newUsername);
                adminUser.setPassword(newPassword);
                rewriteFile("files/admin.txt", admin);
                System.out.println("Admin updated successfully!");
                String message="User updated successfully.";
                updatedSuccessfully=true;
                printMessage(message);
                return;
            }
        }

        System.out.println("User " + oldUsername + " not found.");
    }

    public void AdminDashboardOptiones(String option) {
        switch (option) {
            case "1":
            	   userManagementPageOpen=true;

       		    System.out.println("User Management Page is now open.");
       		    System.out.println("Options:");
       		    System.out.println("1. View All Users");
       		    System.out.println("2. Add User");
       		    System.out.println("3. Delete User");
       		    System.out.println("4. Update User");
       		    System.out.println("5. Back to Admin Dashboard");

                break;
            case "2":
                MonitorAndReport();
                break;
            case "3":
                ContentManagement();
                break;
        }
    }
    
    public void AdminDashboardpage() {
    	   adminDashbordOpen = true;
   	    System.out.println("Admin Dashboard is now open.");
   	    System.out.println("Available Tasks:");
   	    System.out.println("1. Manage User Accounts");
   	    System.out.println("2. Monitor and Report");
   	    System.out.println("3. Content Management");
    }

    public void printMessage(String message) {
        System.out.println(message);
	}

    private void ContentManagement() {
		// TODO Auto-generated method stub
		
	}

	private void MonitorAndReport() {
		// TODO Auto-generated method stub
		
	}

	public void navigateTo(String string) {
		// TODO Auto-generated method stub
		if (!StoreOwnerLoggedIn || !MaterialSupplierLoggedIn) {
			return;
		}
		
		
		
	}

	public void addProduct(String productName, String price, String expDate) {
	    Product newProduct = new Product(productName, price, expDate);
	    Products.add(newProduct);  // Add to the existing list
	    rewriteFile("files/products.txt", Products);  // Write the entire list back to the file
	}


	public void editProduct(String productName, String price, String expDate) {
		// TODO Auto-generated method stub
		String updatedline=productName+","+price+","+expDate;
		try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get("files/products.txt"));

            // Modify the content as needed
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                if (line.contains(productName)) {
                    updatedLines.add(updatedline); // Replace the line
                    updateMessage=true;
                } else {
                    updatedLines.add(line); // Keep the line unchanged
                }
            }

            // Write the updated content back to the file
            Files.write(Paths.get("files/products.txt"), updatedLines);
            
            System.out.println("File has been updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
		 		
	}

	public boolean isProductInList(String name) {
		 for (Product product : Products) {
	            if (product.getProductName().equals(name)) {
	                return true;
	            }
	        }
	        return false;
	    }
	
	
	 private void loadProducts() throws FileNotFoundException, IOException {
	        try (BufferedReader br = new BufferedReader(new FileReader("files/products.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 3) {
	                    String productname = parts[0];
	                    String price = parts[1];
	                    String exp=parts[2];

	                    Product a=new Product(productname, price, exp);
	                    Products.add(a);
	                }
	            }
	        }
	 }

	 public void deleteProduct(String productName) {
		    Products.removeIf(product -> product.getProductName().equals(productName));
		    rewriteFile("files/products.txt", Products);  // Write the updated list back to the file
		    deletedProductSuccessfully = true;
		}


	public void getSalesReport() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		 try (BufferedReader br = new BufferedReader(new FileReader("files/purchasedProducts.txt"));
				 BufferedReader br2 = new BufferedReader(new FileReader("files/products.txt"))
				 ) {
	            String line1;
	            String line2;
	            double TotalSales=0;
	            double Profit=0;
	            int quantity=0;
	            while ((line1 = br.readLine()) != null) {
	                String[] parts = line1.split(",");
	                if (parts.length == 3) {
	                    String purchasedName = parts[0];
	                    String quan = parts[1];
	                    String purchasedPrice=parts[2];
	                    
	                    
	                    TotalSales+=(Double.parseDouble(purchasedPrice)*Double.parseDouble(quan));
	                    quantity+=Double.parseDouble(quan);
	                }
	            }
	            
	            Profit=TotalSales-quantity*50;
	            System.out.println("the Total number of sales "+quantity);
	            System.out.println("the total sales is"+TotalSales);
                System.out.println("the profit is "+Profit);
               reportGenerated=true;
	        }
		
		
		
	}

	public void getBestSellingProduct() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		 try (BufferedReader br = new BufferedReader(new FileReader("files/purchasedProducts.txt"))) {
	            String line;
	            int quantity=0;
	            String name="";
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 3) {
	                    String productname = parts[0];
	                    String quan = parts[1];
	                    String exp=parts[2];

	                    if (quantity<Integer.parseInt(quan)) {
							quantity=Integer.parseInt(quan);
							name=productname;
						}
	                }
	            } 
	            
	            System.out.println("the highest number of sales "+ quantity);
	            System.out.println("the best selling product is "+ name);
	                
	                    }
	}

	public void addDiscount(String discount) throws IOException {
		// TODO Auto-generated method stub
		 try (BufferedReader br = new BufferedReader(new FileReader("files/products.txt"))) {
	            String line;
	            double newPrice=0;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 3) {
	                    String productname = parts[0];
	                    String price = parts[1];
	                    String exp=parts[2];

	                    if (Double.parseDouble(price)==Double.parseDouble(discount)) {
							newPrice=Double.parseDouble(price)*35/100;
							Product a =new Product(productname, ""+newPrice, exp);
							deleteProduct(productname);
							addProduct(productname, ""+newPrice, exp);
							System.out.println("the discount at "+productname+" the original price is "+price+" the price after discount is "+newPrice);
							discountMessagepos=true;
						}
	                    
	                }
	            } 
	                
	                    }
		
		
		
	}

	public void sendMessageToUser(String username, String message) {
		// TODO Auto-generated method stub
		
		String path ="files/messagesToSuppliers.txt";
		
        String content = username+", "+message;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,true))) {
            writer.write(content);
            writer.newLine(); // Optional: add a new line after the content
            System.out.println("File has been written successfully.");
            messageSentToUser=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
		 		
		
		
	}

	public void sendMessageToSupplier(String supplierName, String message) {
		// TODO Auto-generated method stub
		
String path ="files/messagesToUsers.txt";
		
        String content = supplierName+", "+message;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,true))) {
            writer.write(content);
            writer.newLine(); // Optional: add a new line after the content
            System.out.println("File has been written successfully.");
            messageSentToSupplier=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
		 		
		
		
	
		
	}

	public void sendMessageToOwner(String ownerName2, String message) {
		// TODO Auto-generated method stub
String path ="files/messagesToOwner.txt";
		
        String content = ownerName2+", "+message;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,true))) {
            writer.write(content);
            writer.newLine(); // Optional: add a new line after the content
            System.out.println("File has been written successfully.");
            messageSentToSupplier=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	public void showAccountDetails() {
		// TODO Auto-generated method stub
		System.out.println("Account name : "+this.loggedName);
		System.out.println("Account password : "+this.loggedPassword);
		System.out.println("Account role : "+this.ROLE);
		
	}

	public void EditBusinessInformation(String op, String accName, String password) {
		// TODO Auto-generated method stub
		if(op.equals("Edit Business Information")) {
			updateUser(loggedName, accName, password);
			
		}
		
	}

	public void listOrders() throws FileNotFoundException, IOException {
// TODO Auto-generated method stub

		
		try (BufferedReader br = new BufferedReader(new FileReader("files/orders.txt"))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                	String ordernum= parts[0];
                    String StoreOwner = parts[1];
                    String reciever = parts[2];
                    String productname =parts[3];
                    String status=parts[4];

                    System.out.println(ordernum+", "+StoreOwner+", "+reciever+", "+productname+", "+status);
                    
                }
            } 
                
                    }
	}

	public void processOrder(String oNum, String op) throws IOException {
		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new FileReader("files/orders.txt"))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                	String ordernum= parts[0];
                    String StoreOwner = parts[1];
                    String reciever = parts[2];
                    String productname =parts[3];
                    String status=parts[4];

                    if (oNum.equals(ordernum)) {
						
					Order o=new Order(ordernum, StoreOwner, reciever, productname, status);
					orders.add(o);
					rewriteFile("files/orders.txt", orders);
					
                    
                                        
                }
            } 
                
                    }
		}
	}

	

//	public void userManagmenetpage(int option) {
//        switch (option) {
//            case 1:
//                viewAllUsers();
//                break;
//            case 2:
//                System.out.println("Enter the username:");
//                String addUsername = new Scanner(System.in).next();
//                System.out.println("Enter the password:");
//                String addPassword = new Scanner(System.in).next();
//                System.out.println("Enter the role (user, Store_owner, Material_supplier, Admin):");
//                String addRole = new Scanner(System.in).next();
//                addUser(addUsername, addPassword, addRole);
//                break;
//            case 3:
//                System.out.println("Enter the username to delete:");
//                String deleteUsername = new Scanner(System.in).next();
//                deleteUser(deleteUsername);
//                break;
//            case 4:
//                System.out.println("Enter the old username:");
//                String oldUsername = new Scanner(System.in).next();
//                System.out.println("Enter the new username:");
//                String newUsername = new Scanner(System.in).next();
//                System.out.println("Enter the new password:");
//                String newPassword = new Scanner(System.in).next();
//                updateUser(oldUsername, newUsername, newPassword);
//                break;
//            case 5:
//                System.out.println("Returning to Admin Dashboard...");
//                adminDashbordOpen = true;
//                userManagementPageOpen = false;
//                break;
//            default:
//                System.out.println("Invalid option. Please choose again.");
//                break;
//        }
//    }
}