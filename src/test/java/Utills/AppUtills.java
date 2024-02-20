package Utills;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppUtills {
	
public static	WebDriver driver;

	@Parameters({"url"})
	@BeforeSuite
	public void launchApp(String url) {
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	@AfterSuite
	public void logoutApp() {
		
		driver.close();
	}

}
