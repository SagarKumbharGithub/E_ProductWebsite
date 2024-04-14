package qa.pageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	 
	@FindBy (xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	public boolean verifyAddedProductAvailableInCartPage(String productName)
	{
		return cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	
	}
	public void clickOnCheckoutButton()
	{
		checkout.click();
	}
	
	
}
