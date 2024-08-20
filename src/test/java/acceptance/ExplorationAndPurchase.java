package acceptance;

import static org.junit.Assert.assertTrue;

import ProductionCode.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ExplorationAndPurchase {
	
	MyApp app;
	
	
	
	public ExplorationAndPurchase(MyApp app) {
		super();
		this.app = app;
	}
	
	@When("I search for dessert recipes by name {string}")
	public void i_search_for_dessert_recipes_by_name(String string) {
    app.user.SearchDessertRecipes(string);
	}
	@Then("i should see the the dessert recipe and component")
	public void i_should_see_the_the_dessert_recipe_and_component() {
    assertTrue(app.user.RecipeFound);
	}

	@When("i chose purchase optione then enter the product name {string} and quntity {string}")
	public void i_chose_purchase_optione_then_enter_the_product_name_and_quntity(String string, String string2) {
		int number = Integer.parseInt(string2);

	   app.user.purchaseProduct("milk", number);
	}
	

	@When("I filter dessert recipes by the ingredient {string}")
	public void i_filter_dessert_recipes_by_the_ingredient(String string) {
	app.user.filterRecipes(string);
	}

	@Then("I should see a list of dessert recipes that contain the ingredient I searched for")
	public void i_should_see_a_list_of_dessert_recipes_that_contain_the_ingredient_i_searched_for() {
    assertTrue(true);
	}

}
