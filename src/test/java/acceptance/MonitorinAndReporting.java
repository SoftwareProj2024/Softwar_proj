package acceptance;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MonitorinAndReporting {

MyApp app;

	
	
	public MonitorinAndReporting(MyApp app) {
	super();
	this.app = app;
}
	
	 /*@Given("I am logged in as an admin")
	    public void i_am_logged_in_as_an_admin() {
	        app.login("mohammad123","123","Admin");
	        assertTrue(app.isUserLoggedIn);
	    }*/
	
	@When("I navigate to the reporting page")
	public void i_navigate_to_the_reporting_page() {
		 app.login("mohammad123","123","Admin");
	        assertTrue(app.isUserLoggedIn);
		app.navigateTo("reporting page");
	}
	@When("I select {string}")
	public void i_select(String ReportType) throws FileNotFoundException, IOException {
	 
		app.selectReport(ReportType); 
	  }
	@Then("I should see a report of profits")
	public void i_should_see_a_report_of_profits() {
	  assertTrue(app.reportShown);
	}
	@Then("I should see a financial report for the selected")
	public void i_should_see_a_financial_report_for_the_selected() {
		assertTrue(app.reportGenerated);
	}
	@Then("I should see a list of best-selling products in each store")
	public void i_should_see_a_list_of_best_selling_products_in_each_store() throws NumberFormatException, IOException {
	    app.getBestSellingProduct();
	}

}
