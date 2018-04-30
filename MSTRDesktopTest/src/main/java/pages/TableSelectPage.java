//package pages;
//
//import org.sikuli.script.Screen;
//import org.testng.asserts.SoftAssert;
//
//import sikulifactory.FindBy;
//import sikulifactory.SikuliElement;
//import sikulifactory.SikuliFactory;
//
//public class TableSelectPage {
//
//Screen sikuli;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\ImportFromTable-Select.PNG")
//	public SikuliElement importFromTable;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\TableSearch.PNG")
//	public SikuliElement tableSearch;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\Finish.PNG")
//	public SikuliElement finish;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\TablesCount.PNG")
//	public SikuliElement tablesCount;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\PrepareData.PNG")
//	public SikuliElement prepareData;
//	
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\Preview.PNG")
//	public SikuliElement preview;
//	
//	// Eloqua objects
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\EloquaDSN.PNG")
//	public SikuliElement eloquaDSN;
//	
//	//Shopify Objects
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\ShopifyDSN.PNG")
//	public SikuliElement shopifyDSN;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\Shopify-NameSpaceSelector.PNG")
//	public SikuliElement shopifyNameSpaceSelector;
//		
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\Shoppify-OrderShoppifyLinesForTableSelector.PNG")
//	public SikuliElement shoppifyOrderShippingLinesForTableSelector;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\Shoppify-OrderShoppifyLinesTaxLinesForTableSelector.PNG")
//	public SikuliElement shoppifyOrderShippingLinesTaxLinesForTableSelector;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\Shoppify-OrderShoppifyLines.PNG")
//	public SikuliElement shoppifyOrderShippingLines;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\Shoppify-OrderShoppifyLinesTaxLines.PNG")
//	public SikuliElement shoppifyOrderShippingLinesTaxLines;
//	
//	@FindBy(image=helpers.Constants.filePath + "dataSourceName\\Shoppify-AssertWaitElement.PNG")
//	public SikuliElement shoppifyTableElementForAssertion;
//	
//	
//	public TableSelectPage(Screen sikuli) {
//		this.sikuli = sikuli;
//		SikuliFactory.initElements(sikuli, this);
//	}
//	
//	public void selectDataSourceName(String dsnName){
//	     SoftAssert verify = new SoftAssert();
//		 verify.assertTrue(importFromTable.exists(50), "DSN page is not displayed");
//		 verify.assertAll();
//		 
//	     SikuliElement selectDataSourceName = null;
//	     switch (dsnName) {
//	     case "Eloqua":
//	    	 selectDataSourceName = eloquaDSN;
//	    	 break;
//	     case "Shopify":
//	    	 selectDataSourceName = shopifyDSN;
//		   	 break;
//		    }
//		 verify.assertTrue(importFromTable.exists(50), dsnName + " DSN is not displayed");
//		 verify.assertAll();
//		 selectDataSourceName.click();
//	}
//	
//	public void nameSpaceSelector(String dsnName){
//	     SoftAssert verify = new SoftAssert();
//	     SikuliElement nameSpaceSelector = null;
//	     switch (dsnName) {
//	     case "Shopify":
//	    	 nameSpaceSelector = shopifyNameSpaceSelector;
//	    	 break;
//	     }
//
//		 verify.assertTrue(shopifyNameSpaceSelector.exists(100), dsnName + " Shopify Name Space couldn't be found");
//		 verify.assertAll();
//		 nameSpaceSelector.click();
//	}
//	
//	
//	public void tableSelector(String dsnName, String tableName){
//	     SoftAssert verify = new SoftAssert();
//	     SikuliElement table1 = null;
//	     SikuliElement table2 = null;
//	     switch (dsnName) {
//	     case "Shopify":
//	    	 table1 = shoppifyOrderShippingLinesForTableSelector;
//	    	 table2 = shoppifyOrderShippingLinesTaxLinesForTableSelector;
//	    	 break;
//	     }
//	     
//		 verify.assertTrue(tableSearch.exists(50), "Search Table textbox is not displayed");
//		 verify.assertTrue(shoppifyTableElementForAssertion.exists(50));
//		
//		 tableSearch.type(tableName);
//
//		 verify.assertTrue(table1.exists(50), dsnName + " Table 1 is not displayed");
//		 verify.assertTrue(table2.exists(50), dsnName + " Table 2 is not displayed");
//		
//		 
//		 table1.doubleClick();
//		 verify.assertTrue(finish.exists(50), dsnName + " Table 1 is not selected");
//		 
//		 table2.doubleClick();
//		 verify.assertTrue(tablesCount.exists(50), dsnName + " Table 2 is not selected");
//		 
//		 verify.assertTrue(prepareData.exists(50), "Prepare Data button is not displayed");
//		 prepareData.click();
//		 verify.assertTrue(preview.exists(300), "Prepared data is not displayed");
//		 verify.assertAll();
//	}
//	
//	
//	
//}
