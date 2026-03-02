package ayushacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ayushacademy.abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cart h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkout;

	By checkoutBy = By.cssSelector(".totalRow button");

	public List<WebElement> getCartProducts() {
		return cartProducts;
	}

	public boolean checkMatchProduct (String productName) {
			List<WebElement> cartProd = getCartProducts();
			boolean result = cartProd.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
			return result;
	}

	public CheckOutPage goToCheckout() {

		scrollWindow();
		waitForElementToAppear(checkoutBy);
		checkout.click();

		return new CheckOutPage(driver);
	}

}
