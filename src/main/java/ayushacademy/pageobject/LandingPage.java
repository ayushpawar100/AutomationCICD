package ayushacademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ayushacademy.abstractcomponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
// driver. findElement (By. id ("userEmail")) . sendKeys ("kunalrana@omail.com");
// driver. findElement (By. id ("userPassword" )) . sendkeys ("Kunal@123") ;
// driver. findElement (By. id ("login")). click ();
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement login;

	@FindBy(css = "[class*='toast-message']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String pass) {

		username.sendKeys(email);
		password.sendKeys(pass);
		login.click();
		
		return new ProductCatalogue(driver);
	}

	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://www.rahulshettyacademy.com/client/#/auth/login");
	}
}
