package Test;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseEvent {
	
	WebDriver driver = null;
	
	@BeforeClass
	public void openbrowser() {
		WebDriverManager.chromedriver().setup();	
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void RightClick() {
		driver.get("https://www.browserstack.com/guide/action-class-in-selenium");
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"product-nav\"]/ul/li[4]/a"));
		Actions act = new Actions(driver);
		act.contextClick(element).build().perform();
		
	}
	
	@Test(priority = 2)
	public void MouseHover() throws InterruptedException {
		
		driver.get("https://www.browserstack.com/guide/action-class-in-selenium");
		
		WebElement web = driver.findElement(By.xpath("//*[@id=\"product-menu-toggle\"]"));
		
		Actions act = new Actions(driver);
		act.moveToElement(web).build().perform();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"product-menu-dropdown\"]/div[1]/ul[2]/li[2]/a")).click();
		
		String s = driver.getTitle();
		System.out.println(s);
		Assert.assertEquals(s,"Interactive Mobile App Testing on 2000+ iOS & Android Devices | BrowserStack");


	}
	
	@AfterClass
	public void exit() {
		driver.quit();
	}

}

