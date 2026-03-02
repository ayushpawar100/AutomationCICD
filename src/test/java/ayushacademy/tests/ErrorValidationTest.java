package ayushacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ayushacademy.TestComponents.BaseTest;
import ayushacademy.TestComponents.Retry;
import ayushacademy.pageobject.CartPage;
import ayushacademy.pageobject.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	@Test(groups = {"ErrorHandlingTest" }, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		landingPage.loginApplication("kunalrana@gmail.com", "Kunal123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("kunalrana@gmail.com", "Kunal@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean result = cartPage.checkMatchProduct("ZARA COAT 33");
		Assert.assertFalse(result);

	}
}
