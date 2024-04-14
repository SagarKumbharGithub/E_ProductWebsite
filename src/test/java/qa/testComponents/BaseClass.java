package qa.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseClass {

	public WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void intiziate_driver() throws IOException
	{
		Properties pro= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\resources\\global.properties");
		pro.load(fis);
		String browserName=pro.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver= new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		// Need to download fileutils dependencies
		// Read data from JSON file 
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	
		// Data transfer from String to HashMap -Using Jackson Databind dependencies 
		// Use ObjectMapper Class
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent,  new TypeReference<List<HashMap<String,String>>> (){});
		return data;
		
	
	
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}
	
}
