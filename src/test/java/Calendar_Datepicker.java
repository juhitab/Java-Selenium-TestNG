import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.CalendarUtils;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Calendar_Datepicker {
//scenario-select year from dropdown, select month by clicking next/previous button, then select date for the desired month
	public static void main(String[] args) throws ParseException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/date-picker");
		driver.manage().window().maximize();
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
		
		String expdate = "02-October-2026";
		/*
		 * //if month name to be selected from dropdown, use string split
		 * 
		 * String[] dateArr = expdate.split("-");
		 * String d = dateArr[0]; String m = dateArr[1]; //month name -March - use
		 * Select to select from dropdown using visibleText String y = dateArr[2];
		 */
		//if month to be selected using next/prev button, need the integer value of month to compare expected month is greater/less than displayed month, also how many times to click button (loop iteration)
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH);//month can be MMM format-Mar also--full month name -> MMMM
		Date date = format.parse(expdate);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int expmonth = cal.get(Calendar.MONTH) + 1; //no need to convert into string as we will compare integer values of months
		String expday = String.valueOf(cal.get(Calendar.DATE)); //since selenium selects by text value
		String year = String.valueOf(cal.get(Calendar.YEAR));
		
		//select year
		driver.findElement(By.id("datePickerMonthYearInput")).click();
		Select selectYear = new Select(driver.findElement(By.xpath("//select[contains(@class,'year-select')]")));
		selectYear.selectByVisibleText(year);
		
		//get displayed month
		String displayed = driver.findElement(By.xpath("//div[contains(@class,'current-month')]")).getText();
		String[] disDate = displayed.split(" ");
		String displayedmonth = disDate[0];
		//convert the displayed month name also to integer value
		format = new SimpleDateFormat("MMMM", Locale.ENGLISH);
		cal.setTime(format.parse(displayedmonth));
		int disMonth = cal.get(Calendar.MONTH) + 1;
		
		//compare expmonth and disMonth
		
		int flag = 0;
		if(expmonth>disMonth) //click next
			flag = 1; //if expmonth<=disMonth, flag remains 0
		int iteration = Math.abs(expmonth-disMonth); //iteration count to run the loop for clicking prev/next button
		
		for(int i=1;i<=iteration;i++) {
			if(flag==1) { //click next
				driver.findElement(By.xpath("//button[contains(@class,'next')]")).click();
			}
			else //click prev - expmonth<disMonth (for expmonth=disMonth, iteration =0, so doesn't enter loop)
				driver.findElement(By.xpath("//button[contains(@class,'prev')]")).click();
		}
		
		System.out.println(driver.findElement(By.xpath("//div[contains(@class,'current-month')]")).getText()); //can add validation if correct month and year is displayed
		
		//select date
		//this calendar contains selectable date of previous and next months also--categorized based on class--use of not(contains()) in xpath
		//div[contains(@class,'react-datepicker__week')]/div[text()=2 and not(contains(@class,'outside-month'))]
		//div[contains(@class,'react-datepicker__week')]/div[text()=2][not(contains(@class,'outside-month'))]  ----same as above xpath
		
		driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__week')]/div[text()=" + expday + "and not(contains(@class,'outside-month'))]")).click();
		
		//should validate
		System.out.println(driver.findElement(By.id("datePickerMonthYearInput")).getText());
		
		//to select date, another way is to iterate over every date in the calendar displayed like a web table if rows and columns are present (tr, td)
		
	}

}
