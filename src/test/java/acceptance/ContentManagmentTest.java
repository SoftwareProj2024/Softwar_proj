package acceptance;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContentManagmentTest {

	MyApp app;
	
	
	public ContentManagmentTest(MyApp app) {
		super();
		this.app = app;
		}
	
	
	@Given("I am on the content management page")
	public void i_am_on_the_content_management_page() throws FileNotFoundException, IOException {
        app.AdminDashboardOptiones("3");
	  assertTrue(app.contentManagementPageOpen);
	}
	@When("I select View Recipe then I should see the all recipes in the list")
	public void i_select_view_recipe_then_i_should_see_the_all_recipes_in_the_list() {
	app.contentmanagement.viewRecipes();
	}

	@Then("return back to the content management page")
	public void return_back_to_the_content_management_page() {
    app.AdminDashboardOptiones("3");
	}
	
	@When("I select Delete  and enter recipe name {string}")
	public void i_select_Delete_and_enter_recipe_name(String prductname) {
	    app.contentmanagement.deleteRecipes(prductname);
	}
	@Then("I submit and should see the all recipes in the list after deleted")
	public void i_submit_and_should_see_the_all_recipes_in_the_list_after_deleted() {
		app.contentmanagement.viewRecipes();
	}
	
	@When("I select Respond feedback and enter the feedback id {string} and write response message {string}")
	public void i_select_respond_feedback_and_enter_the_feedback_id_and_write_response_message(String string, String string2) {
	app.contentmanagement.responseFeedback(string,string2);
	}
	
	@When("I select View feedback")
	public void i_select_view_feedback() {
    app.contentmanagement.viewFeedback();
	}
	@When("I should see all feedbacks")
	public void i_should_see_all_feedbacks() {
    app.contentmanagement.viewFeedback();
	}
	
	@When("I select Delete feedback and enter id feedback {string}")
	public void i_select_delete_feedback_and_enter_id_feedback(String id) {
    app.contentmanagement.deleteFeedback( id);
	}
}
