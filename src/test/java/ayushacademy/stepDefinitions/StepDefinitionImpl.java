package ayushacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ayushacademy.TestComponents.BaseTest;
import ayushacademy.pageobject.CartPage;
import ayushacademy.pageobject.CheckOutPage;
import ayushacademy.pageobject.ConfirmationPage;
import ayushacademy.pageobject.LandingPage;
import ayushacademy.pageobject.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	LandingPage landingPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) 
	{
		 productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		
		boolean result = cartPage.checkMatchProduct(productName);
		Assert.assertTrue(result);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("india");
		confirmationPage = checkOutPage.goToSubmit();

	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) 
	{
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) {
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
