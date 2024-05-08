import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Windows_BrowserPopups {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/browser-windows");
		driver.manage().window().maximize();
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
 
		String mainWindow = driver.getWindowHandle();
		System.out.println("window id: " + mainWindow);
		//opening new tab or new window--to be handled same way
		driver.findElement(By.id("windowButton")).click();
		driver.findElement(By.id("tabButton")).click(); //after clicking this tab button, new window popup gets minimised, but still selenium able to handle it using window ids
		driver.findElement(By.id("messageWindowButton")).click(); 
		Thread.sleep(1000);
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iterator = windows.iterator(); //Returns an iterator over the elements in this set. The elements are returned in no particular order (unless this set is an instance of someclass that provides a guarantee).
		//for a single child window no need for loop, simply switch to child, perform operations, close it come back to parent window. For multiple windows run loop
		while(iterator.hasNext()) {
			String eachWindow = iterator.next();
			System.out.println("window id: " + eachWindow);
			if(!eachWindow.equals(mainWindow)) { //don't write if(mainWindow != eachWindow) because even when their contents are equal (main window id), their objects are compared (==, !=). Since they point to different objects. So even for main window it will switch to main window and not find the validation text
				driver.switchTo().window(eachWindow);
				Thread.sleep(3000);
				//perform any action on the window
				String currentUrl = null;
				try {
					currentUrl = driver.getCurrentUrl();
					if(currentUrl.equals("https://demoqa.com/sample")) //cannot validate window id, since it keeps changing
						System.out.println(driver.findElement(By.id("sampleHeading")).getText());//can validate this text, or instead of taking the text,can also validate title of page
					
				}catch(Exception e) { //for the messageWindowButton --no url or title--about blank
					System.out.println(driver.findElement(By.xpath("//body")).getText());
				}
				
				driver.close();
//				driver.switchTo().defaultContent(); -- not done for windows--done for iframes-also since it's looping with iterator on every window, should not switch back to parent
			}
		}
		driver.switchTo().window(mainWindow);//after the iteration is done you can switch back to parent window
		System.out.println("parent window title: " + driver.getTitle());
	}
}
