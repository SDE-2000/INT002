package MiniProject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HumanResource {
	
	String browser= "edge";
	public WebDriver driver = null;

	@BeforeTest
	public void OpenBrowser() {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("mozila")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}
	
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
	
	@Test
	public void testing() throws InterruptedException {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.name("username")).sendKeys("Admin");
		//Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys("admin123");
		//Thread.sleep(2000);
		
		System.out.println(driver.findElement(By.tagName("button")).isEnabled());
		driver.findElement(By.tagName("button")).click();
		
		if(driver.getCurrentUrl().contains("dashboard")) {
			System.out.println("The URL contains the string: dashboard");
		}
		
		driver.findElement(By.xpath("//span[text()='Admin']")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Job')]")).click();
		//Thread.sleep(2000);
		
		int c=0;
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='oxd-dropdown-menu']/li"));
		for(int i=0; i<list.size(); i++) {
			WebElement ele = list.get(i);
			String name = ele.getText();
			if(name.equalsIgnoreCase("Job Titles")) {
				c=1;
				break;
			}
		}
		if(c==1) {
			System.out.println("Job Titles tab is found");
		}
		else {
			System.out.println("Not Found");
		}
		
		driver.findElement(By.linkText("Job Titles")).click();

		//Thread.sleep(2000);

		int c1=0;
		List<WebElement> list1 = driver.findElements(By.className("oxd-table-body"));
		for(int i=0; i<list1.size(); i++) {
			WebElement ele = list1.get(i);
			String name = ele.getText();
			System.out.println("List of all Jobs Available: ");
			System.out.println(name);
			if(name.indexOf("Automation Tester") > -1) {
				c1=1;
			}
		}
		if(c1==1) {
			System.out.println("Automation Tester is Already Present");
		}
		else if(c1==0) {
			System.out.println("Automation Tester is Not Available");
			
			driver.findElement(By.className("oxd-button--secondary")).click();
			//Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("Automation Tester");
			//Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@placeholder ='Type description here']")).sendKeys("Selenium With Java");
			//Thread.sleep(2000);
			driver.findElement(By.className("oxd-button--secondary")).click();
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("oxd-userdropdown-tab"))));

		}
		
		driver.findElement(By.className("oxd-userdropdown-tab")).click();
		//Thread.sleep(2000);
		
		driver.findElement(By.linkText("Logout")).click();
		//Thread.sleep(2000);
	}
}
