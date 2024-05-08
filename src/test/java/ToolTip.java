import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToolTip {
//Method #1 – Fetching the title attribute as tooltip text. To access or verify static tooltips that are implemented using the HTML “title” attribute, one needs to use the getAttribute(“title”) method for the specific WebElement
//Method #2 – Using the Action Class- mouseover	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/tool-tips");
		driver.manage().window().maximize();
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");

		WebElement tooltipbtn = driver.findElement(By.id("toolTipButton"));
		//using getAttribute("title")
//		String tooltiptxt = tooltipbtn.getAttribute("title");
//		System.out.println(tooltiptxt);
		
		//using Actions class --mouse over to get the tooltip web element in DOM
		Actions action = new Actions(driver);
		action.moveToElement(tooltipbtn).build().perform();
		Thread.sleep(4000);
		
		//get the tooltip web element xpath now by pausing the script using setTimer(()=>{debugger},5000) in console
		WebElement tooltip = driver.findElement(By.xpath("//div[@role=\"tooltip\"]/div[@class=\"tooltip-inner\"]"));
		String tooltiptxt = tooltip.getText();
		System.out.println(tooltiptxt);
		
	}

}
