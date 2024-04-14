package qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.abstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {

	WebDriver driver;
	@FindBy (css="[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".list-group-item:nth-child(3)")
	WebElement india;
	
	@FindBy (css=".action__submit")
	WebElement placeOrder;
	
	
	public PaymentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectCountry()
	{
		selectCountry.sendKeys("india");
		india.click();
		
	}
	public void clickOnPlaceOrder()
	{
		placeOrder.click();
	}
	
}
