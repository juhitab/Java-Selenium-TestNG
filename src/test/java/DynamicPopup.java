import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicPopup {

	public static void main(String[] args) {
	
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			//code that might trigger a dynamic popup
			driver.findElement(By.id("timerAlertButton")).click();
			handleAlert(driver, wait);//use this function wherever there is a possibility of getting the popup
			//code...again call handleAlert()
			
		}catch(Exception e) {
			//might get unhandled alert exception --if alert appears at a time when handleAlert() is not called
			//unhandledAlertException is handle here, handle alert here
			handleAlert(driver, wait);
			//again write the code that got skipped in execution flow due to this exception
		}

	}
	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e) {
			return false;
		}
	}
	public static void handleAlert(WebDriver driver, WebDriverWait wait) {
		try {
			
			wait.until(ExpectedConditions.alertIsPresent());//if alert doesn't appear within 5 seconds, throws Alert not present exception
			if(isAlertPresent(driver)) {
				driver.switchTo().alert().accept();
			}
			//or can use below code instead of writing isAlertPresent() function
			if(wait.until(ExpectedConditions.alertIsPresent())!=null) { //alert is present --no need to use the isAlertPresent() method
				driver.switchTo().alert().accept();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
