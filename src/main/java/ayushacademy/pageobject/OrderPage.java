package ayushacademy.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ayushacademy.abstractcomponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> products;

	public Boolean verifyOrdersDisplay(String productName) {
		Boolean result = products.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return result;
	}
}