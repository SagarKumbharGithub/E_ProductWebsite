package qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.pageObjects.LoginPage;
import qa.pageObjects.ProductPage;
import qa.testComponents.BaseClass;
import qa.testComponents.XlUtils;

public class TC_01_VerifyUserAbleToLoginIntoApplication_DDT extends BaseClass{

	@Test(dataProvider="validUserDetails",groups= {"Regression","Smoke"})
	public void DDT_verifyUserAbleToLoginIntoApplication(String username, String password)
	{
		
		LoginPage loginPage= new  LoginPage(driver);
		loginPage.userLoginToApplication(username, password);
		ProductPage productPage=new ProductPage(driver);
		boolean flag=productPage.verifyWhetherTheUserIsLandOnProductPage();
		Assert.assertTrue(flag);
	}
	
	@Test(dataProvider = "invalidUserDetails", groups= {"Regression"})
	public void DDT_verifyWhetherApplicationProvidAlertMessageWithWrongUserCredentials(String username, String password )
	{
		LoginPage loginPage= new  LoginPage(driver);
		loginPage.userLoginToApplication(username, password);
		String actualError=loginPage.getLoginErrorMessageText();
		System.out.println(actualError);
		String expectedError="Incorrect email or password.";
		Assert.assertEquals(actualError, expectedError);
		System.out.println("I am run with webhooks-Jenkins-2");
	}
	

	@DataProvider(name="validUserDetails")
	public Object[][] getData() throws IOException
	{
		  return XlUtils.getExcelData(System.getProperty("user.dir")+"\\src\\test\\java\\qa\\testData\\TestData.xlsx", "ValidLogin");
	
	}

	@DataProvider(name="invalidUserDetails")
	public Object[][] getInvalidData() throws IOException
	{
		  return XlUtils.getExcelData(System.getProperty("user.dir")+"\\src\\test\\java\\qa\\testData\\TestData.xlsx", "InvalidLogin");
	
	}
	
	
}
