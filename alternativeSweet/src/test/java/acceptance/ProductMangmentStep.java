package acceptance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductMangmentStep {
	
	
	MyApp app;
	
	
	
	
	public ProductMangmentStep(MyApp app) {
		super();
		this.app=app;
		// TODO Auto-generated constructor stub
	}
	@Given("I am logged in as a store owner or material supplier")
	public void i_am_logged_in_as_a_store_owner_or_material_supplier() {
		app.login("musaed","123","Store_owner");
		assertTrue(app.StoreOwnerLoggedIn);
	    
	}
	
	
	
	
	@When("I navigate to the product management dashbored")
	public void i_navigate_to_the_product_management_dashbored() {
		app.navigateTo("productManagementDashboard");
	}
	@When("select add {string} I fill in the product name {string} and price {string} and exp date {string}")
	 public void select_i_fill_in_the_product_name_and_price_and_exp_date(String action, String productName, String price, String expDate) {
        if (action.equals("Add Product")) {
            app.addProduct(productName, price, expDate);
        } else if (action.equals("Edit")) {
            app.editProduct(productName, price, expDate);
        }
    }
	@Then("i submit the details")
	public void i_submit_the_details() {
	    assertTrue(true);
	}
	@Then("I should see the new product in my product list")
	public void i_should_see_the_new_product_in_my_product_list() {
		 boolean isProductInList = app.isProductInList("molto");
	        if (!isProductInList) {
	            throw new AssertionError("Product 'molto' was not found in the product list.");
	        }
	    
	}
	
	
	@When("I select update {string} and choose a product name {string} and update price {string} and exp date {string}")
	public void i_select_update_and_choose_a_product_name_and_update_price_and_exp_date(String update, String productName, String price, String exp) {
	    if (update.equals("Edit")) {
		app.editProduct(productName, price, update);
	    }
	    }
	@Then("i submit")
	public void i_submit() {
	    assertTrue(true);
	}
	@Then("update success message pops and user return to management dashbored")
	public void update_success_message_pops_and_user_return_to_management_dashbored() {
	    assertTrue(app.updateMessage);
	}
	
	@When("I select delete {string} and choose product name {string}")
	public void i_select_delete_and_choose_product_name(String delete, String productName) {
	   if (delete.equals("Delete")) {
		app.deleteProduct(productName);
	}
	}
	@Then("delete message pops and user return to management dashbored")
	public void delete_message_pops_and_user_return_to_management_dashbored() {
	   assertTrue(app.deletedProductSuccessfully);
	}


	@When("I navigate to the sales and profits dashboared")
	public void i_navigate_to_the_sales_and_profits_dashboared() {
	    app.navigateTo("sales and profits");
	}
	@Then("I should see a report of my sales and profits")
	public void i_should_see_a_report_of_my_sales_and_profits() throws FileNotFoundException, IOException {
	   app.getSalesReport();	
	   }

	
	@Then("I should see a list of my best-selling products")
	public void i_should_see_a_list_of_my_best_selling_products() throws FileNotFoundException, IOException {
	   app.getBestSellingProduct();
	}

	@When("I navigate to the discount management page and I select  {string}")
	public void i_navigate_to_the_discount_management_page_and_i_select(String discount) throws IOException {
	   app.navigateTo("discount mangment");
	}
	@When("I fill in the discount current price {string}")
	public void i_fill_in_the_discount_current_price(String discount) throws IOException {
		app.addDiscount(discount);
	}
	@Then("message pops original price and discount price")
	public void message_pops_original_price_and_discount_price() {
	    assertTrue(app.discountMessagepos);
	}

	
	}
	
