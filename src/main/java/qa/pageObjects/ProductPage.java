package qa.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.abstractComponents.AbstractComponents;

public class ProductPage extends AbstractComponents {

	WebDriver driver;
	
	@FindBy (css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".card-body button:nth-of-type(2)")
	WebElement addToCart;
	
	@FindBy(xpath="//*[contains(text(),'Product Added')]")
	WebElement productAddedMsg;
	
	@FindBy (xpath="//*[text()=' Sign Out ']")
	WebElement signOut;
	
	
	By productNameBy=By.cssSelector("b");
	By addToCartBy=By.cssSelector(".card-body button:nth-of-type(2)");
	
	public ProductPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getAllProducts()
	{
		return products;
	}
	public WebElement sortProductByName(String productName)
	{
		WebElement actualProduct=products.stream().filter(product -> product.findElement(productNameBy).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
	    return actualProduct;
	}
	public void sortedProductAddedTocart(String productName)
	{
		sortProductByName(productName).findElement(addToCartBy).click();
		waitUntilVisibilityOfElement(driver, productAddedMsg);
	}
	
	public String getProductAddedToCartMessage()
	{
		return productAddedMsg.getText();
	}
	
	public boolean verifyWhetherTheUserIsLandOnProductPage()
	{
		if(isElementPresent(signOut)==true)
		{
		   return true;
		}
		else {
			return false;
		}
	}
	

	
	
	
}
