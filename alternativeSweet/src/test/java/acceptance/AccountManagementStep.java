package acceptance;

import static org.junit.Assert.assertTrue;

import ProductionCode.MyApp;
import ProductionCode.StoreOwner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountManagementStep {

	MyApp app;

	public AccountManagementStep(MyApp app) {
		super();
		this.app = app;
	}
	
	@Given("I am logged in as a store owner or raw material supplier")
	public void i_am_logged_in_as_a_store_owner_or_raw_material_supplier() {
	   app.login("ahmad", "156", "Store_owner");
	   assertTrue(app.StoreOwnerLoggedIn);
	}
	@When("I navigate to the account management page")
	public void i_navigate_to_the_account_management_page() {
	    app.navigateTo("account management");
	}
	@Then("I should see my account details")
	public void i_should_see_my_account_details() {
	    app.showAccountDetails();
	}

	
	@When("I navigate to the account management page And I select {string} and I enter username {string} and pass {string}  update the business information")
	public void i_navigate_to_the_account_management_page_and_i_select_and_i_update_the_business_information(String op,String accName,String password) {
	   app.navigateTo("account management");
	   app.EditBusinessInformation(op,accName,password);
	}
	@Then("I should see the updated information on the account management page")
	public void i_should_see_the_updated_information_on_the_account_management_page() {
	    assertTrue(true);
	}
	
	
	
}
