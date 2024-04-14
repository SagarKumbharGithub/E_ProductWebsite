package qa.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;
	
	@FindBy (css="[routerlink*='cart']")
	WebElement cartHeader;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToCartPage()
	{
		cartHeader.click();
	}
	
	public static void waitUntilVisibilityOfElement(WebDriver driver, WebElement ele)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public boolean isElementPresent(WebElement ele)
	{
	 try {
		ele.isDisplayed();
		return true;
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}
	}
	
}
