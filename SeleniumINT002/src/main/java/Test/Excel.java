package Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Excel {
	
	
	
	@Test(dataProvider = "DATA")
	public void login(String email, int pass) throws InterruptedException {
		
		WebDriverManager.edgedriver().setup();	
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.name("username")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(String.valueOf(pass));
		Thread.sleep(2000);
		driver.quit();
		
	}
	
	
	@DataProvider(name = "DATA")
	public Object[][] readData() throws Exception {
		
		File f = new File("C:\\Users\\2269224\\eclipse-workspace\\SeleniumINT002\\src\\main\\resources\\dataset\\data.xlsx");
		FileInputStream input = new FileInputStream(f);
		XSSFWorkbook w = new XSSFWorkbook(input);
		XSSFSheet s = w.getSheet("Sheet1");
		
		int rowCount = s.getPhysicalNumberOfRows();
		int colCount = s.getRow(0).getPhysicalNumberOfCells();
		
		Object [][] data = new Object[rowCount][colCount];
		
		for(int r=0 ; r<rowCount-1 ; r++) {
			XSSFRow row = s.getRow(r+1);
			for(int c=0;c<colCount;c++) {
				
				XSSFCell cell = row.getCell(c);
				
				CellType celltype = cell.getCellType();
				
				switch(celltype) {
				
				case STRING:
					data[r][c] = cell.getStringCellValue();
					System.out.println(data[r][c]);
					break;
					
				case NUMERIC :
					data[r][c] = (int)cell.getNumericCellValue();
					System.out.println(data[r][c]);
					break;
				}
			}
		}
		
		return data;
	}
	
}
