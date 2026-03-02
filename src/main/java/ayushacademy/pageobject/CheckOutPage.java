package ayushacademy.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ayushacademy.abstractcomponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	WebElement countrySelect;
	
	@FindBy(css=".action__submit")
	WebElement submit;

	public void selectCountry (String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		countrySelect.click();
	}

	public ConfirmationPage goToSubmit () {
	
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	

}
