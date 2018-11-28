package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataDriventest {
	
	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	
	@BeforeMethod
    public void initialisation() {
		
		
		System.setProperty("webdriver.chrome.driver", "/home/rushi/Downloads/chromedriver");
		driver = new ChromeDriver();
		
		// To launch facebook
	     driver.get("http://www.facebook.com/");
	     // To maximize the browser
	     driver.manage().window().maximize();
	     // implicit wait
	     driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void fblogin() throws IOException, InterruptedException {
		
		// Import excel sheet.
		File src= new File("/home/rushi/Desktop/git-Rushi2828/DataDrivenProject"
				+ "/src/main/java/Resources/testDATA.xlsx");
		
		// Load the file.
		FileInputStream fis = new FileInputStream(src);
		
		// Load he workbook.
		 workbook = new XSSFWorkbook(fis);
		 
		// Load the sheet in which data is stored.
		 
		 sheet= workbook.getSheetAt(0);
		 for(int i = 1; i<= sheet.getLastRowNum(); i++) {
			 /*I have added test data in the cell A2 as "testemailone@test.com" and B2 as "password"
			 Cell A2 = row 1 and column 0. It reads first row as 0, second row as 1 and so on 
			 and first column (A) as 0 and second column (B) as 1 and so on*/ 
			 
			 
			 // Import data for Email.
			 cell = sheet.getRow(i).getCell(0);
			 cell.setCellType(cell.CELL_TYPE_STRING);
			 driver.findElement(By.id("email")).clear();
			 driver.findElement(By.id("email")).sendKeys(cell.getStringCellValue()); 
			 
			 
			 			 
			// Import data for password
			 cell = sheet.getRow(i).getCell(1);
			 cell.setCellType(cell.CELL_TYPE_STRING);
			 driver.findElement(By.id("pass")).clear();
			 driver.findElement(By.id("pass")).sendKeys(cell.getStringCellValue());
			 
			 Thread.sleep(5000);
			 
			 //Write excel sheet
			 FileOutputStream fos = new FileOutputStream(src);
			 
			 //Message to written in excel sheet
			 String message = "Pass";
			 
			// Create cell where data needs to be written.
			 sheet.getRow(i).createCell(2).setCellValue(message);
			 
			// finally write content
			 workbook.write(fos);
			 
			
		 }
		 
	}
		 @AfterMethod
		 public void tearDown() {
			 
			 driver.quit();
		 }
		 
		 
		
		
	}

