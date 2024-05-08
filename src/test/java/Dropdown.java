import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdown {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
		Select country = new Select(driver.findElement(By.xpath("//div[@class=\"information closable\"]/following-sibling::p/select")));
		country.selectByVisibleText("Algeria");
		Thread.sleep(4000);
		country.selectByIndex(10);
		Thread.sleep(4000);
		country.selectByValue("CAN"); //<option value="CAN">Canada</option>
		Thread.sleep(4000);
		country.deselectByVisibleText("Canada"); //Exception in thread "main" java.lang.UnsupportedOperationException: You may only deselect options of a multi-select
		Thread.sleep(4000);
		//for multi-select- https://demoqa.com/select-menu 
	}

}
