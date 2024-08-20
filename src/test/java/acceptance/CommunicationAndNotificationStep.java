package acceptance;

import static org.junit.Assert.assertTrue;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommunicationAndNotificationStep {
MyApp app;

public CommunicationAndNotificationStep(MyApp app) {
	super();
	this.app = app;
}


@Given("I am logged in as a store owner")
public void i_am_logged_in_as_a_store_owner() {
    app.login("ahmad", "156", "Store_owner");
    assertTrue(app.StoreOwnerLoggedIn);
}
@When("I navigate to the messaging dashboard")
public void i_navigate_to_the_messaging_dashboard() {
    app.navigateTo("messaging dashboard");
}
@When("I select a user {string} and I write a message {string}")
public void i_select_a_user_and_i_write_a_message(String username, String message) {
    app.sendMessageToUser(username,message);
}
@Then("message sent success and store Owner return to the messaging dashboard")
public void message_sent_success_and_user_return_to_the_messaging_dashboard1() {
   assertTrue(app.messageSentToUser);
   app.navigateTo("messaging dashboard");
}

@When("I select a supllier {string} and I write a message {string}")
public void i_select_a_supllier_and_i_write_a_message(String supplierName, String message) {
	app.sendMessageToSupplier(supplierName,message);
}
@Then("message sent success and user return to the messaging dashboard")
public void message_sent_success_and_user_return_to_the_messaging_dashboard() {
   assertTrue(app.messageSentToSupplier);
   app.navigateTo("messaging dashboard");
}

@Given("I am logged in as a user")
public void i_am_logged_in_as_a_user() {
    app.login("musa", "123", "user");
    assertTrue(app.isUserLoggedIn);
}
@When("I select a store owner {string} and I write a message {string}")
public void i_select_a_store_owner_and_i_write_a_message(String ownerName, String message) {
    app.sendMessageToOwner(ownerName, message);
}




}
