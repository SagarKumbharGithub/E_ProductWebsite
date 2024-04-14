import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandloanTest {

	@Test
	public void verifyOrderFlow() {
		
		String productName="ADIDAS ORIGINAL"; 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("sagar95kumbhar@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sk@123456789");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement actualProduct=products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		actualProduct.findElement(By.cssSelector(".card-body button:nth-of-type(2)")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'Product Added')]"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean flag=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(flag);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		driver.findElement(By.cssSelector(".list-group-item:nth-child(3)")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String successMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		String expectedMsg="THANKYOU FOR THE ORDER.";
		Assert.assertEquals(successMsg, expectedMsg);
		
	}
}
