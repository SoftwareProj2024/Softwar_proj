package acceptance;

import java.io.FileNotFoundException;
import java.io.IOException;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderManagementStep {
MyApp app;

public OrderManagementStep(MyApp app) {
	super();
	this.app = app;
}

@When("I navigate to the order management page")
public void i_navigate_to_the_order_management_page() {
    app.navigateTo("order management page");
}
@Then("I should see a list of orders")
public void i_should_see_a_list_of_orders() throws FileNotFoundException, IOException {
	app.listOrders();
   }

@Given("I am on the order management page")
public void i_am_on_the_order_management_page() {
   app.navigateTo("order management");
}
@When("I select an order number {string} and I choose {string}")
public void i_select_an_order_number_and_i_choose(String oNum, String op) throws IOException {
    // Write code here that turns the phrase above into concrete actions
    app.processOrder(oNum,op);
}
@Then("the order status becomes {string}")
public void the_order_status_becomes(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@Then("I should see the updated status in the order list and return to management page")
public void i_should_see_the_updated_status_in_the_order_list_and_return_to_management_page() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}




}
