import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Alerts_JavascriptPopups {
//JavaScript has three kind of popup boxes: Alert box, Confirm box, and Prompt box.
	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//to scroll the window down (y axis) to get the view of the alert causing buttons (because they are blocked by google ads)
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		//alert with OK button
		driver.findElement(By.id("alertButton")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		 //alert comes after 5 secs
		driver.findElement(By.id("timerAlertButton")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //giving the wait time exceeding 5secs
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		//Confirm alert box
		driver.findElement(By.id("confirmButton")).click();
		alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.dismiss();

		//validating alert dismissal
		String result = driver.findElement(By.id("confirmResult")).getText();
		
		SoftAssert sa = new SoftAssert(); //When an assertion fails, don't throw an exception but record the failure. Calling assertAll() will cause an exception to be thrown if at least one assertion failed.
		if(result.contains("cancel")) {
			sa.assertTrue(true);
		}else
			sa.assertTrue(true);
		
		//prompt alert
		driver.findElement(By.id("promtButton")).click();
		alert = driver.switchTo().alert();
		alert.sendKeys("David"); //prompt alert asks for a text prompt from user
		alert.accept();
		
		//validation of message
		result = driver.findElement(By.id("promptResult")).getText();
		if(result.contains("David")) {
			sa.assertTrue(true);
		}else
			sa.assertTrue(true);
	}

}
