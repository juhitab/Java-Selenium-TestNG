import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {

	public static void main(String[] args) {
		
		//not setting chromedriver path, since selenium 4
		WebDriver driver = new ChromeDriver();//launches the browser
		driver.get("https://demo.guru99.com/V1/index.php");
		
		driver.findElement(By.linkText("Bank Project")).click();
		
		driver.findElement(By.name("uid")); //can use name attribute, as name is unique in this page
		WebElement userid = driver.findElement(By.xpath("//input[@name='uid']")); //findElement returns a WebElement object
		userid.sendKeys("mngr568518"); //action for text fields
		driver.findElement(By.name("password")).sendKeys("yhApepU");
		
//		String label =driver.findElement(By.cssSelector("span[class*=mandatory)")).getText(); //css selector by substring match of attribute value--syntax: tag[attribute*='value']/An invalid or illegal selector was specified
//		System.out.println(label);
		//click on login button
		driver.findElement(By.cssSelector("input[value=LOGIN]")).click(); //tag[attribute=value] -no quotes should be used
		
	}

}
