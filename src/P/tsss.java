package P;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;


public class tsss
{
	WebDriver wd;
	@BeforeTest
	public void openBroswer()
	{
	wd= new FirefoxDriver();
	wd.manage().window().maximize();
	wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wd.get("http://www.demo.guru99.com/V4/index.php");
	}
	@AfterTest
	public void closeBroswer()
	{
	wd.quit();
	}
	@Test(dataProvider="empLogin")
	public void login(String userName , String password)
	{
	wd.findElement(By.xpath("//input[@name='uid']")).sendKeys(userName);
	wd.findElement(By.xpath("//input[@name='password']")).sendKeys( password);
	wd.findElement(By.xpath(" //input[@name='btnLogin']")).submit();
	wd.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
	Assert.assertEquals(wd.getTitle(), "Guru99 Bank Manager HomePage");
	}

	/*@DataProvider
	public Object[][] dp() {
	return new Object[][] {
	new Object[] { new String[]{"mngr48076","erYjUnU"}}
	};
	}*/
	@DataProvider(name="empLogin")
	public Object[][] loginData() {
	Object[][] arrayObject = getExcelData("C:\\Selenium\\TestData\\sampledoc.xlsx","Sheet1");
	return arrayObject;
	}
	public String[][] getExcelData(String fileName, String sheetName) {
	String[][] arrayExcelData = null;
	try {
	FileInputStream fs = new FileInputStream(fileName);
	XSSFWorkbook wb=new XSSFWorkbook(fs);
	XSSFSheet ws=wb.getSheet(sheetName);
	int totalNoOfRows = ws.getLastRowNum()+1;
	int totalNoOfCols = ws.getRow(0).getLastCellNum();
	arrayExcelData = new String[totalNoOfRows][totalNoOfCols];

	for(int i=1; i<totalNoOfRows; i++)
	{
	XSSFRow row = ws.getRow(i);
	for (int j=0; j<totalNoOfRows;j++)
	{
	XSSFCell cell = row.getCell(j);
	String value= cell.getStringCellValue();
	arrayExcelData[i][j]=value;
	}
	}

	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	e.printStackTrace();
	} 
	return arrayExcelData;
	}

	}



