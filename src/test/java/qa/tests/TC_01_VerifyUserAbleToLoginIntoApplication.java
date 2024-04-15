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

public class TC_01_VerifyUserAbleToLoginIntoApplication extends BaseClass{

	@Test(dataProvider="userDetails",groups= {"Regression","Smoke"})
	public void verifyUserAbleToLoginIntoApplication(HashMap<String, String> input)
	{
		
		LoginPage loginPage= new  LoginPage(driver);
		loginPage.userLoginToApplication(input.get("username"), input.get("password"));
		ProductPage productPage=new ProductPage(driver);
		boolean flag=productPage.verifyWhetherTheUserIsLandOnProductPage();
		Assert.assertTrue(flag);
	}
	
	@Test(dataProvider = "userInvalidDetails", groups= {"Regression"})
	public void verifyWhetherApplicationProvidAlertMessageWithWrongUserCredentials(HashMap<String, String>input)
	{
		LoginPage loginPage= new  LoginPage(driver);
		loginPage.userLoginToApplication(input.get("username"), input.get("password"));
		String actualError=loginPage.getLoginErrorMessageText();
		System.out.println(actualError);
		String expectedError="Incorrect email or password.";
		Assert.assertEquals(actualError, expectedError);
		System.out.println("I am run with webhooks-Jenkins");
	}
	
	@DataProvider(name="userDetails")
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\qa\\testData\\loginDetails.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		//return new Object[][] {{"sagar95kumbhar@gmail.com","Sk@123456789"},{"sk@gmail.com","123554sda"}};
	  
	/*
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("username", "sagar95kumbhar@gmail.com");
		map.put("password", "Sk@123456789");

		HashMap<String, String> map1= new HashMap<String, String>();
		map1.put("username", "sagar@gmail.com");
		map1.put("password", "Sk@123456789");
	
		return new Object [][] {{map},{map1}};
	*/
		
		
	}
	
	@DataProvider(name="userInvalidDetails")
	public Object[][] getInvalidData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\qa\\testData\\invalidLoginDetails.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
