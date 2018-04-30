package example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.ImagePath.PathEntry;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.firefox.FirefoxDriver;		
import org.testng.Assert;

import pages.DataSourcePage;
import pages.DossierPage;
import pages.HomePage;
import excelUtils.*;

//import xlsx_operations.WriteExcelTest;
//import common.AddExternalData;

public class NewTest extends Setup {
	Screen sikuli;
	//App sikuliApp;
	//static final String version = "desktop10.11.0.4";
	//public static final String path = "C:\\workspace\\MSTRDesktopTest\\src\\main\\resources\\sikuli-images\\"+version;




	@Test
	public void Impala_InMemory_DSN() throws Exception
	{
		//MSTR10.11.0003_Win10_Paypal_Desktop_B.xlsx

		File fos = new File("C:\\workspace\\MSTRDesktopTest\\src\\main\\resources\\excelResultsTemplates\\Desktop_B.xlsx");
		File fos1 = new File("C:\\workspace\\MSTRDesktopTest\\src\\test\\resources\\results\\test.xlsx");
		WriteExcel we = new WriteExcel();
		we.copyExcel(fos, fos1);
		//FileInputStream f1 = new FileInputStream(fos1);
		
		
//		ReadExcel re = new ReadExcel("C:\\workspace\\MSTRDesktopTest\\src\\main\\resources\\excel\\MSTRDesktop Data.xlsx" , 1);
//		String testCase = "Impala_InMemory_DSN";
//		System.out.println(re.getCellData(testCase, "DataSourceName"));
//		sikuli = new Screen();
//		HomePage homepage = new HomePage(sikuli);
//		DossierPage dossierpage = new DossierPage(sikuli);
//		DataSourcePage datasourcepage = new DataSourcePage(sikuli);
//		homepage.clickOnNewDossier();
//		dossierpage.maximizeDossierScreen();
//		dossierpage.clickOnNewData();
//		datasourcepage.searchForDataSource(re.getCellData(testCase, "DataSourceName"));

	}
	
	//@Test
	public void Impala_Live_DSN() throws Exception
	{
		ReadExcel re = new ReadExcel("C:\\workspace\\MSTRDesktopTest\\src\\main\\resources\\excel\\MSTRDesktop Data.xlsx" , 1);
		String testCase = "Impala_InMemory_DSN";
		System.out.println(re.getCellData(testCase, "DataSourceName"));
		sikuli = new Screen();
		HomePage homepage = new HomePage(sikuli);
		DossierPage dossierpage = new DossierPage(sikuli);
		DataSourcePage datasourcepage = new DataSourcePage(sikuli);
		homepage.clickOnNewDossier();
		dossierpage.maximizeDossierScreen();
		dossierpage.clickOnNewData();
		datasourcepage.searchForDataSource(re.getCellData(testCase, "DataSourceName"));

	}


}
