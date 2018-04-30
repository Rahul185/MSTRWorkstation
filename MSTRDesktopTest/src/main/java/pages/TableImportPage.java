package pages;

import java.util.Iterator;

import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import sikulifactory.FindBy;
import sikulifactory.SikuliElement;
import sikulifactory.SikuliFactory;

public class TableImportPage {

Screen sikuli;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\ImportFromTable-Select.PNG")
	public SikuliElement importFromTable;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Finish.PNG")
	public SikuliElement finish;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\TablesCount.PNG")
	public SikuliElement tablesCount;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\PrepareData.PNG")
	public SikuliElement prepareData;
		
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Preview.PNG")
	public SikuliElement preview;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\AddAllColumns.PNG")
	public SikuliElement addAllColumns;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\ExecuteSQL.PNG")
	public SikuliElement executeSQL;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\EditSQL.PNG")
	public SikuliElement editSQL;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Yes.PNG")
	public SikuliElement yes;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\SwitchToBuildMode.PNG")
	public SikuliElement switchToBuildMode;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Add.PNG")
	public SikuliElement add;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\BuildQuery.PNG")
	public SikuliElement buildQuery;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\EloquaDSN.PNG")
	public SikuliElement eloquaDSN;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\ShopifyDSN.PNG")
	public SikuliElement shopifyDSN;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Shopify-NameSpaceSelector.PNG")
	public SikuliElement shopifyNameSpaceSelector;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\TableSearch.PNG")
	public SikuliElement tableSearch;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Shoppify-OrderShoppifyLines.PNG")
	public SikuliElement shoppifyOrderShippingLines;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Shoppify-OrderShoppifyLinesTaxLines.PNG")
	public SikuliElement shoppifyOrderShippingLinesTaxLines;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Shoppify-OrderShoppifyLinesForTableSelector.PNG")
	public SikuliElement shoppifyOrderShippingLinesForTableSelector;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Shoppify-OrderShoppifyLinesTaxLinesForTableSelector.PNG")
	public SikuliElement shoppifyOrderShippingLinesTaxLinesForTableSelector;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Shoppify-OrderShoppifyLines_added_verification")
	public SikuliElement shoppifyOrderShoppifyLinesAddedVerification;
	
	@FindBy(image=helpers.Constants.filePath + "tableImport\\Shoppify-OrderShoppifyLinesTaxLines_added_verification")
	public SikuliElement shoppifyOrderShoppifyLinesTaxLinesAddedVerification;
	
	
	
	
	
	public TableImportPage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}
	
	public int selectDataSourceName(String dsnName){
	     SoftAssert verify = new SoftAssert();
		 verify.assertTrue(importFromTable.exists(10), "DSN page is not displayed");
		 verify.assertAll();
		 
	     SikuliElement selectDataSourceName = null;
	     switch (dsnName) {
	     case "Eloqua":
	    	 selectDataSourceName = eloquaDSN;
	    	 break;
	     case "Shopify":
	    	 selectDataSourceName = shopifyDSN;
		   	 break;
		    }
		 verify.assertTrue(importFromTable.exists(10), dsnName + " DSN is not displayed");
		 verify.assertAll();
		 return selectDataSourceName.click();
	}
	
	public int nameSpaceSelector(String dsnName){
	     SoftAssert verify = new SoftAssert();
	     SikuliElement nameSpaceSelector = null;
	     switch (dsnName) {
	     case "Shopify":
	    	 nameSpaceSelector = shopifyNameSpaceSelector;
	    	 break;
	     }

		 verify.assertTrue(nameSpaceSelector.exists(60), dsnName + " DSN is not displayed");
		 verify.assertAll();
		 return nameSpaceSelector.click();
	}
	
	
	public void tableSelector(String dsnName, String tableName){
	     SoftAssert verify = new SoftAssert();
	     SikuliElement table1 = null;
	     SikuliElement table2 = null;
	     switch (dsnName) {
	     case "Shopify":
	    	 table1 = shoppifyOrderShippingLines;
	    	 table2 = shoppifyOrderShippingLinesTaxLines;
	    	 
	    	 break;
	     }
	     
	     sikuli.wait(10.0);
		 verify.assertTrue(tableSearch.exists(50), "Search Table textbox is not displayed");
		 
		 tableSearch.type(tableName);

//		 verify.assertTrue(table1.exists(10), dsnName + " Table 1 is not displayed");
//		 verify.assertTrue(table2.exists(10), dsnName + " Table 2 is not displayed");
		
		 
		 table1.doubleClick();
		 verify.assertTrue(finish.exists(15), dsnName + " Table 1 is not selected");
		 
		 table2.doubleClick();
		 verify.assertTrue(tablesCount.exists(15), dsnName + " Table 2 is not selected");
		 
		 verify.assertTrue(prepareData.exists(10), "Prepare Data button is not displayed");
		 prepareData.click();
		 verify.assertTrue(preview.exists(300), "Prepared data is not displayed");
		 verify.assertAll();
	}
	
	public void tableImporter(String dsnName, String tableName) {
	     SoftAssert verify = new SoftAssert();
	     SikuliElement table1 = null;
	     SikuliElement table2 = null;
	     SikuliElement verifyTable1Added =null;
	     SikuliElement verifyTable2Added = null;
	     
	     switch (dsnName) {
	     case "Shopify":
	    	 table1 = shoppifyOrderShippingLinesForTableSelector;
	    	 table2 = shoppifyOrderShippingLinesTaxLinesForTableSelector;
	    	 verifyTable1Added= shoppifyOrderShoppifyLinesAddedVerification;
	    	 verifyTable2Added= shoppifyOrderShoppifyLinesTaxLinesAddedVerification;
	    	 break;
	     }
	     
		 verify.assertTrue(tableSearch.exists(10), "Search Table textbox is not displayed");
		 
		 tableSearch.type(tableName);

//		 verify.assertTrue(table1.exists(10), dsnName + " Table 1 is not displayed");
//		 verify.assertTrue(table2.exists(10), dsnName + " Table 2 is not displayed");
		
		 
		 table1.doubleClick();
		 sikuli.wait(8.0);
		 
		 
		 table2.doubleClick();
//		 sikuli.wait(8.0);
//		 verify.assertTrue(verifyTable1Added.exists(20), dsnName + " Table 1 is not selected");
//		 verify.assertTrue(verifyTable2Added.exists(20), dsnName + " Table 2 is not selected");
		 sikuli.wait(5.0);
		 verify.assertAll();
		 
	}
	
	
public void addAllColumns(){
	 SoftAssert verify = new SoftAssert();
	 sikuli.wait(10.0);
	 verify.assertTrue(addAllColumns.exists(10), "Add All Columns is not displayed");
	 
	  addAllColumns.doubleClick();
	  verify.assertAll();
		sikuli.wait(20.0);
		
		
	
	}


public void executeSQL(){
	 SoftAssert verify = new SoftAssert();
	 verify.assertTrue(executeSQL.exists(10), "Execute SQL is not displayed");
	 executeSQL.click();
	 try {
		sikuli.wait(200);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void editSQL(){
	 SoftAssert verify = new SoftAssert();
	 verify.assertTrue(editSQL.exists(10), "Edit SQL is not displayed");

	 editSQL.click();
	 
	 verify.assertTrue(yes.exists(10), "Yes prompt is not displayed");
	 verify.assertAll();
	 yes.click();
	 
}

public int switchToBuildMode(){
	 SoftAssert verify = new SoftAssert();
	 verify.assertTrue(switchToBuildMode.exists(10), "Switch To Build Mode is not displayed");
	 
	 switchToBuildMode.click();
	 
	 verify.assertTrue(add.exists(15), "Add is not displayed");
	 add.click();
	 
	 verify.assertTrue(finish.exists(180), "Failed to add Tables");
	 
	 return finish.click();
	 
}

}
