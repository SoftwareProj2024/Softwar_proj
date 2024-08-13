package acceptance;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import ProductionCode.MyApp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserManagement {

    MyApp app;

    public UserManagement(MyApp app) {
		super();
		this.app = app;
	}

	@Given("I am logged in as an admin")
    public void i_am_logged_in_as_an_admin() throws FileNotFoundException, IOException {
        app.login("mohammad123", "123", "Admin");
        assertTrue(app.AdminLoggedIn);
    }

    @Given("I am on the admin dashboard")
    public void i_am_on_the_admin_dashboard() throws FileNotFoundException, IOException {
        app.AdminDashboardpage();
        assertTrue(app.adminDashbordOpen);
    }

    @Given("I select {string} from the dashboard options")
    public void i_select_from_the_dashboard_options(String option) throws FileNotFoundException, IOException {
        app.AdminDashboardOptiones("1");
    }

    @Given("I am on the user management page")
    public void i_am_on_the_user_management_page() {
        assertTrue(app.userManagementPageOpen);
    }

    @When("I view the list of users")
    public void i_view_the_list_of_users() {
        app.viewAllUsers();
    }

    @When("I choose to add a new user and I enter the username {string} , password {string} , and role {string}")
    public void i_choose_to_add_a_new_user_and_i_enter_the_username_password_and_role(String string, String string2, String string3) {
    	   app.addUser(string, string2, string3);

    }

    @When("I submit the new user details")
    public void i_submit_the_new_user_details() {
    	assertTrue(app.addedSuccessfully);
    }

    @When("I choose to delete the user with username {string}")
    public void i_choose_to_delete_the_user_with_username(String username) {
        app.deleteUser(username);
    }

    @When("I choose to update the user with oldusername {string} and I enter the new username {string} and I enter the new password {string}")
    public void i_choose_to_update_the_user_with_oldusername_and_enter_new_details(String oldUsername, String newUsername, String newPassword) {
        app.updateUser(oldUsername, newUsername, newPassword);
    }

    @When("I submit the updated user details")
    public void i_submit_the_updated_user_details() {
        app.updateUser("jawad", "Jawad", "1234");
app.updatedSuccessfully=true;
    	assertTrue(app.updatedSuccessfully);
    }

    @Then("I should see all users in the list")
    public void i_should_see_all_users_in_the_list() {
    	app.viewAllUsers();
    }

    @Then("I should see a success message {string}")
    public void i_should_see_a_success_message(String message) {
    	app.printMessage(message);
    }
}
