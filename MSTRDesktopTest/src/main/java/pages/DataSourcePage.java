package pages;

import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import bsh.org.objectweb.asm.Constants;
import excelUtils.ReadExcel;
import sikulifactory.FindBy;
import sikulifactory.SikuliElement;
import sikulifactory.SikuliFactory;

public class DataSourcePage {
	
	Screen sikuli;
	//public static final String value = helpers.Constants.filePath;
	
	@FindBy(image=helpers.Constants.filePath + "dataSource\\Search-DataSource.PNG")
	public SikuliElement searchDataSource;
	
	@FindBy(image= helpers.Constants.filePath + "dataSource\\ConnectLive.PNG")
	public SikuliElement connectLive;
	
	@FindBy(image=helpers.Constants.filePath + "dataSource\\BuildQuery.PNG")
	public SikuliElement buildQuery;
	
	@FindBy(image=helpers.Constants.filePath + "dataSource\\TypeQuery.PNG")
	public SikuliElement typeQuery;
	
	@FindBy(image=helpers.Constants.filePath + "dataSource\\SelectTables.PNG")
	public SikuliElement selectTables;
	
	@FindBy(image=helpers.Constants.filePath + "dataSource\\Next.PNG")
	public SikuliElement nextButton;
	
	// Eloqua Objects
	@FindBy(image=helpers.Constants.filePath + "dataSource\\Eloqua-DataSource.PNG")
	public SikuliElement eloquaDataSource;
	
	//Shopify Objects
	@FindBy(image=helpers.Constants.filePath + "dataSource\\Shopify-DataSource.PNG")
	public SikuliElement shopifyDataSource;
	
	
	
	public DataSourcePage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}
	
	
	public int clickConnectLive(){
	     SoftAssert verify = new SoftAssert();
		 verify.assertTrue(connectLive.exists(50), "Connect Live button is not displayed");
		 verify.assertAll();
		 return connectLive.click();
	}
	
	public int searchForDataSource(String dsName){
	     SoftAssert verify = new SoftAssert();
		 verify.assertTrue(searchDataSource.exists(50), "Search DataSource textbox is not displayed");
		 verify.assertAll();
		 return searchDataSource.type(dsName);
	}
	
	public int selectDataSource(String dsName){
	     SoftAssert verify = new SoftAssert();
	     SikuliElement selectDataSource = null;
	     switch (dsName) {
	     case "Eloqua":
	    	 selectDataSource = eloquaDataSource;
	    	 break;
	     case "Shopify":
	    	 selectDataSource = shopifyDataSource;
	    	 break;
	     }
		 verify.assertTrue(selectDataSource.exists(50), dsName + "DataSource is not displayed");
		 verify.assertAll();
		 return selectDataSource.click();
	}
	
	public int operation(String dsOperation){
	     SoftAssert verify = new SoftAssert();
	     SikuliElement operation = null;
	     switch (dsOperation) {
	     case "BuildQuery":
	    	 operation = buildQuery;
	    	 break;
	     case "SelectTables":
	    	 operation = selectTables;
	    	 break;
	     case "TypeQuery":
	    	 operation = typeQuery;
	    	 break;
	     }
		 
	     verify.assertTrue(operation.exists(50), dsOperation + " is not displayed");
		 verify.assertAll();
		 operation.click();
		 
		 verify.assertTrue(nextButton.exists(50), "Next Button is not displayed");
		 verify.assertAll();
		 return nextButton.click();
	}
	
}
