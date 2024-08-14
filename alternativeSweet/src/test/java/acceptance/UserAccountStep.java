package acceptance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserAccountStep {
	
	MyApp app;
	
	
	public UserAccountStep(MyApp app) {
		super();
		this.app = app;
	}
	@Given("the user is not logged in")
	public void the_user_is_not_logged_in() {
	   assertFalse(app.isUserLoggedIn);
	}
	@When("the user enters username  {string} password {string} and role {string}")
	public void the_user_enters_username_password_and_role(String un, String password, String role) {
	  app.SignUp(un,password,role);
	  assertTrue(app.isSigndUp);
	}
	@Then("the user account should be created successfully")
	public void the_user_account_should_be_created_successfully() {
		  assertTrue(app.isSigndUp);
	}

	@When("the user enters username {string} and password {string} role {string}")
	public void the_user_enters_username_and_password_role(String username, String password, String role) {
	   app.login(username,password,role);
	   assertTrue(app.isUserLoggedIn);
	}

	
	@Then("user enters to the dash")
	public void user_enters_to_the_dash() {
	   app.openUserDash();
	   assertTrue(app.userDashOpen);

	}

	@Given("the user is logged in")
	public void the_user_is_logged_in() {
	    assertTrue(app.isUserLoggedIn=true);
	}
	@When("I choose to update my information with oldusername {string} and I enter the new username {string} and I enter the new password {string}")
	public void i_choose_to_update_my_information_with_oldusername_and_i_enter_the_new_username_and_i_enter_the_new_password(String string, String string2, String string3) {
	    app.updateUser(string, string2, string3);

	}


	@Then("update successful")
	public void update_successful() {
    assertTrue(app.updatedSuccessfully);
	}
	
	@Then("user returns to dash")
	public void user_returns_to_dash() {
		assertFalse(false);
	}

	
	
}
