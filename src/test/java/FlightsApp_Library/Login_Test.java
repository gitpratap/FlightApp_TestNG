package FlightsApp_Library;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utills.AppUtills;

public class Login_Test extends AppUtills
{
	
	@Parameters({"email", "pwd"})
	@BeforeTest
	public void loginApp(String email, String pwd)
	{
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(pwd);
		driver.findElement(By.xpath("//div/form/div[3]/div[2]/button")).click();
	}
	
	@Parameters({"date"})
	@Test(priority = 0)
	public void flightbook(String date) throws InterruptedException {
		driver.findElement(By.id("search-date")).click();
		String dt = date;
		String temp[]= dt.split("-");
		String dt2=temp[0];
		System.out.println(dt2);
		String month=temp[1];
		String year=temp[2];
		String calyr= driver.findElement(By.className("ui-datepicker-year")).getText();		
		while (!calyr.equals(year)) {
			driver.findElement(By.linkText("Next")).click();
			calyr= driver.findElement(By.className("ui-datepicker-year")).getText();	
		}
		String calmonth=driver.findElement(By.className("ui-datepicker-month")).getText();
		while (!calmonth.equalsIgnoreCase(month)) {
			driver.findElement(By.linkText("Next")).click();
			calmonth=driver.findElement(By.className("ui-datepicker-month")).getText();	
		}
		WebElement ele= driver.findElement(By.className("ui-datepicker-calendar"));
		List<WebElement> calrow=ele.findElements(By.tagName("tr"));
		for(int i=1;i<calrow.size();i++) {
			List<WebElement> calcol =calrow.get(i).findElements(By.tagName("td"));
			for(WebElement element: calcol) {
				if (!element.getText().equals(dt2)) {
					element.click();
					break;
				}
			}
		}
		Thread.sleep(2000);
		
	}
	
	
	
	@AfterTest
	public void logoutApp()
	{
		driver.findElement(By.xpath("//div[3]/ul/li[2]/div/a")).click();
		driver.findElement(By.linkText("Logout")).click();
	}
}
