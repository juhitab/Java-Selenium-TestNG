import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DisappearingDropdown {
//setTimeout(()=>{debugger;},10000) --type this in Console of dev tools (beside Elements)--pauses the debugger after 10seconds delay (within this 10 seconds perform the desired operation
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.xpath("//a[contains(@href,'claim')]")).click();
		driver.findElement(By.xpath("//button[text()=' Assign Claim ']")).click();
		driver.findElement(By.xpath("//div/label[text()='Employee Name']/ancestor::div/following-sibling::div//input")).sendKeys("m");
		
//		setTimeout(()=>{debugger;},10000) --type this in Console of dev tools (beside Elements)--pauses the debugger after 10seconds delay (within this 10 seconds perform the click on the text box and type for hints

		driver.findElement(By.xpath("//div[@role='listbox']/div[1]")).click(); //--first option in listbox
		
		
		//flipkart-searchbox -disappearing dropdown suggestions
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//input[contains(@title,\"Search\")]")).sendKeys("mac");
		driver.findElement(By.xpath("//input[contains(@title,\"Search\")]")).sendKeys("book");//separating character of mac+book in sendkeys as search engine not able to list apt suggestions as macbook entered too fast (all characters at once)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[text()=' pro']"))));
		driver.findElement(By.xpath("//div[text()=' pro']")).click();
	}

}
