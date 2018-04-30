package example;
import java.io.File;

import org.apache.log4j.Logger;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import pages.DataModelling;
import pages.DataSourcePage;
import pages.DossierPage;
import pages.HomePage;
import pages.SaveReport;

import pages.TableImportPage;

import loggingUtils.LoggerModule;
import excelUtils.ReadExcel;

//import xlsx_operations.WriteExcelTest;
//import common.AddExternalData;

public class Shopify extends Setup {
	Screen sikuli;
	String DataFile = "C:\\workspace\\MSTRDesktopTest\\src\\main\\resources\\excel\\MSTRDesktop Data.xlsx";
	
	//@Test
	public void Shopify_InMemory_DSNLess() throws Exception
	{	String className =  this.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("Class" + className);
		System.out.println("Method" + methodName);
		
		LoggerModule loggerModule = new LoggerModule();
		Logger logger = loggerModule.getLogger(className);
		try{
			ReadExcel re = new ReadExcel(DataFile , 1);
			String testCase = "Shopify_Live_DSNLess";
			String dsName = re.getCellData(testCase, "DataSourceName");
			String tableName = re.getCellData(testCase, "TableName");
			String dossierName = re.getCellData(testCase, "DossierName");

			logger.info(dsName);
			logger.info(tableName);
			logger.info(dossierName);
	
			sikuli = new Screen();
			HomePage homepage = new HomePage(sikuli);
			DossierPage dossierpage = new DossierPage(sikuli);
			DataSourcePage datasourcepage = new DataSourcePage(sikuli);
			DataModelling dataModelling = new DataModelling(sikuli);
			TableImportPage tableImportPage = new TableImportPage(sikuli);
			SaveReport report = new SaveReport(sikuli);
			
			//homepage.maximizeScreen();
	
			homepage.clickOnNewDossier();
	
			dossierpage.maximizeDossierScreen();
			dossierpage.clickOnNewData();
			
			datasourcepage.searchForDataSource(dsName);
			datasourcepage.selectDataSource(dsName);
			
			datasourcepage.operation("SelectTables");
			
			tableImportPage.selectDataSourceName(dsName);
			tableImportPage.nameSpaceSelector(dsName);
			tableImportPage.tableSelector(dsName, tableName);
			
			dataModelling.performDataModelling(dsName);
			dataModelling.addNewTable();
			
			datasourcepage.searchForDataSource(dsName);
			datasourcepage.selectDataSource(dsName);
			
			datasourcepage.operation("BuildQuery");
			tableImportPage.selectDataSourceName(dsName);
			tableImportPage.nameSpaceSelector(dsName);
			tableImportPage.tableImporter(dsName,tableName);
			tableImportPage.addAllColumns();
			tableImportPage.executeSQL();
			tableImportPage.editSQL();
			tableImportPage.switchToBuildMode();
			
			report.saveReport("InMemory");
			dossierpage.saveDossier(dossierName);
		}
		catch(Exception e) {
			logger.error(e.toString());
			loggerModule.captureErrorImage(className, "Exception");
		}
	}
	
	@Test
	public void Shopify_Live_DSNLess() throws Exception
	{	String className =  this.getClass().getName();
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("Class" + className);
		System.out.println("Method" + methodName);
		
		LoggerModule loggerModule = new LoggerModule();
		Logger logger = loggerModule.getLogger(className);
		try{
			ReadExcel re = new ReadExcel(DataFile , 1);
			String testCase = "Shopify_Live_DSNLess";
			String dsName = re.getCellData(testCase, "DataSourceName");
			String tableName = re.getCellData(testCase, "TableName");
			String dossierName = re.getCellData(testCase, "DossierName");

			logger.info(dsName);
			logger.info(tableName);
			logger.info(dossierName);
	
			sikuli = new Screen();
			HomePage homepage = new HomePage(sikuli);
			DossierPage dossierpage = new DossierPage(sikuli);
			DataSourcePage datasourcepage = new DataSourcePage(sikuli);
			DataModelling dataModelling = new DataModelling(sikuli);
			TableImportPage tableImportPage = new TableImportPage(sikuli);
			SaveReport report = new SaveReport(sikuli);
			
			//homepage.maximizeScreen();
//	
//			homepage.clickOnNewDossier();
//	
//			dossierpage.maximizeDossierScreen();
//			dossierpage.clickOnNewData();
//			
//			datasourcepage.clickConnectLive();
//			datasourcepage.searchForDataSource(dsName);
//			datasourcepage.selectDataSource(dsName);
//			
//			datasourcepage.operation("SelectTables");
//			
//			tableImportPage.selectDataSourceName(dsName);
//			tableImportPage.nameSpaceSelector(dsName);
//			tableImportPage.tableSelector(dsName, tableName);
//			
//			dataModelling.performDataModelling(dsName);
//			dataModelling.addNewTable();
//			
//			datasourcepage.searchForDataSource(dsName);
//			datasourcepage.selectDataSource(dsName);
//			
//			datasourcepage.operation("BuildQuery");
//			tableImportPage.selectDataSourceName(dsName);
//			tableImportPage.nameSpaceSelector(dsName);
//			tableImportPage.tableImporter(dsName,tableName);
//			tableImportPage.addAllColumns();
//			tableImportPage.executeSQL();
//			tableImportPage.editSQL();
//			tableImportPage.switchToBuildMode();
			
			report.saveReport("Live");
			dossierpage.saveDossier(dossierName);
		}
		catch(Exception e) {
			logger.error(e.toString());
			loggerModule.captureErrorImage(className, "Exception");
		}
	}

}
