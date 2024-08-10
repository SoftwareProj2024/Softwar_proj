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
	@When("the user when the user chooses manage account and then update account and the user chooses update {string} and enters a new password {string}")
	public void the_user_when_the_user_chooses_manage_account_and_then_update_account_and_the_user_chooses_update_and_enters_a_new_password(String string, String string2) {
	    
	}
	@Then("update successful")
	public void update_successful() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("user returns to dash")
	public void user_returns_to_dash() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
	
}
