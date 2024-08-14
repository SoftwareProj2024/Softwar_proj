package acceptance;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommunicationAndFeedbackTest {
	
	MyApp app;
	
	public CommunicationAndFeedbackTest(MyApp app) {
		super();
		this.app = app;
	}
	
	@Given("I am logged in as user")
	public void i_am_logged_in_as_user() {
    app.login("musa", "123", "user");
	}
	@Given("I am on the user dashboard")
	public void i_am_on_the_user_dashboard() {
    app.user.UserDashboard();
    app.user.printDashboard();
	}
	@When("I choose to send a message to {string} and enter the message {string}")
	public void i_choose_to_send_a_message_to_and_enter_the_message(String recipientType, String message) {
		app.user.chatWithOwner_Supplier( recipientType,message);
	}
	@Then("return back to the user dashboard")
	public void return_back_to_the_user_dashboard() {
		  app.user.UserDashboard();
		    app.user.printDashboard();
	}
	@Given("I have received a purchased dessert")
	public void i_have_received_a_purchased_dessert() {
	    app.login("musa", "123", "user");

    app.user.purchaseProduct("milk", 3);
    assertTrue(app.user.receivedAPurchasedDessert);
//    assertTrue(true);
	}
	@When("I navigate to the My Orders page")
	public void i_navigate_to_the_my_orders_page() {
app.user.printAllOrdersForCurrentUser();
	}
	@When("I select the dessert to provide feedback on and enter feedback message {string}")
	public void i_select_the_dessert_to_provide_feedback_on_and_enter_feedback_message(String string) {
     app.user.sendFeedback(string);
	}
	
	
}
