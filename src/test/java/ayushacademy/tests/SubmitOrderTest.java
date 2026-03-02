package ayushacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ayushacademy.TestComponents.BaseTest;
import ayushacademy.pageobject.CartPage;
import ayushacademy.pageobject.CheckOutPage;
import ayushacademy.pageobject.ConfirmationPage;
import ayushacademy.pageobject.OrderPage;
import ayushacademy.pageobject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	String countryName = "india";
	String confirmationMessage = "Thankyou for the order.";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// class1
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// class2
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		// class3
		boolean result = cartPage.checkMatchProduct(input.get("productName"));
		Assert.assertTrue(result);
		CheckOutPage checkOutPage = cartPage.goToCheckout();

		// class4
		checkOutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkOutPage.goToSubmit();

		// class 5
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(confirmationMessage));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("kunalrana@gmail.com", "Kunal@123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrdersDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\ayushacademy\\data\\purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
