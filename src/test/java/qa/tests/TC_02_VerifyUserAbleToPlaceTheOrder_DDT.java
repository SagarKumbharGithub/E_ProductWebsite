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
import qa.testComponents.XlUtils;

public class TC_02_VerifyUserAbleToPlaceTheOrder_DDT extends BaseClass {

	String productName="ADIDAS ORIGINAL";
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	PaymentPage paymentPage;
	ConfirmationPage confirmationPage;
	
	@Test(dataProvider="PurchaseOrder", groups = {"Regression","Smoke"})
	public void verifyUserAbleToPlaceTheOrder(String username, String password , String productName)
	{
		loginPage= new LoginPage(driver);
		loginPage.userLoginToApplication(username, password);
		productPage=new ProductPage(driver);
		productPage.sortedProductAddedTocart(productName);
		productPage.goToCartPage();
		cartPage=new CartPage(driver);
		boolean flag=cartPage.verifyAddedProductAvailableInCartPage(productName);
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
	@DataProvider(name="PurchaseOrder")
	public Object[][] getData() throws IOException
	{
		  return XlUtils.getExcelData(System.getProperty("user.dir")+"\\src\\test\\java\\qa\\testData\\TestData.xlsx", "PurchaseOrder");
	
	}
}
