package qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {

	WebDriver driver;
	
	@FindBy (id="userEmail")
	private WebElement userId;
	
	@FindBy (id="userPassword")
	private WebElement userPassword;
	
	@FindBy (id="login")
	private WebElement login;

	@FindBy (xpath="//*[contains(text(),'Incorrect email')]")
    private WebElement errorMsg;
	

	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterUserName(String user)
	{
		userId.sendKeys(user);
	}
	
	public void enterUserPassword(String pass)
	{
		userPassword.sendKeys(pass);
	}
	
	public void clickToLoginButton()
	{
		login.click();
	}
	public void userLoginToApplication(String user, String pass)
	{
		enterUserName(user);
		enterUserPassword(pass);
		clickToLoginButton();
	}
	public String getLoginErrorMessageText()
	{
		waitUntilVisibilityOfElement(driver, errorMsg);
	return errorMsg.getText();		
	}
}
