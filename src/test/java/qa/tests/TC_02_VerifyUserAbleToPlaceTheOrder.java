package qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.pageObjects.CartPage;
import qa.pageObjects.ConfirmationPage;
import qa.pageObjects.LoginPage;
import qa.pageObjects.PaymentPage;
import qa.pageObjects.ProductPage;
import qa.testComponents.BaseClass;

public class TC_02_VerifyUserAbleToPlaceTheOrder extends BaseClass {

	String productName="ADIDAS ORIGINAL";
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	PaymentPage paymentPage;
	ConfirmationPage confirmationPage;
	
	@Test(dataProvider="purchaseDetails", groups = {"Regression","Smoke"})
	public void verifyUserAbleToPlaceTheOrder(HashMap<String, String> input)
	{
		loginPage= new LoginPage(driver);
		loginPage.userLoginToApplication(input.get("email"), input.get("password"));
		productPage=new ProductPage(driver);
		productPage.sortedProductAddedTocart(input.get("productName"));
		productPage.goToCartPage();
		cartPage=new CartPage(driver);
		boolean flag=cartPage.verifyAddedProductAvailableInCartPage(input.get("productName"));
		Assert.assertTrue(flag);
		cartPage.clickOnCheckoutButton();
		paymentPage= new PaymentPage(driver);
		paymentPage.selectCountry();
		paymentPage.clickOnPlaceOrder();
		confirmationPage= new ConfirmationPage(driver);
		String actualMsg=confirmationPage.getConfirmationMessage();
		String expectedMsg="THANKYOU FOR THE ORDER.";
		Assert.assertEquals(actualMsg, expectedMsg);
		
	}
	
	@DataProvider(name="purchaseDetails")
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\qa\\testData\\PurchaseOrder.json");
	    
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
	}
}
