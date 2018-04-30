package pages;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import sikulifactory.FindBy;
import sikulifactory.SikuliElement;
import sikulifactory.SikuliFactory;

public class DataModelling {
Screen sikuli;

	@FindBy(image=helpers.Constants.filePath + "dataModelling\\ChangeDataType.PNG")
	public SikuliElement changeDataType;

	@FindBy(image=helpers.Constants.filePath + "dataModelling\\DataType-Text.PNG")
	public SikuliElement textDataType;

	@FindBy(image=helpers.Constants.filePath + "dataModelling\\DataType-Long.PNG")
	public SikuliElement longDataType;

	@FindBy(image=helpers.Constants.filePath + "dataModelling\\DoNotImport.PNG")
	public SikuliElement doNotImport;

	@FindBy(image=helpers.Constants.filePath + "dataModelling\\ConvertToMetric.PNG")
	public SikuliElement convertToMetric;

	@FindBy(image=helpers.Constants.filePath + "dataModelling\\CreateMultiFormAttribute.PNG")
	public SikuliElement createMultiFormAttribute;

	@FindBy(image=helpers.Constants.filePath + "dataModelling\\Submit.PNG")
	public SikuliElement submit;

	@FindBy(image=helpers.Constants.filePath + "dataModelling\\AddNewTable.PNG")
	public SikuliElement addNewTable;
	
	// Shopify Objects
	@FindBy(image=helpers.Constants.filePath + "dataModelling\\Shoppify-OrderId.PNG")
	public SikuliElement shoppifyDataTypeChange;
	
	@FindBy(image=helpers.Constants.filePath + "dataModelling\\Shoppify-OrderShippingLines-1.PNG")
	public SikuliElement shoppifyDoNotImport;
	
	@FindBy(image=helpers.Constants.filePath + "dataModelling\\Shoppify-OrderShippingLines-2.PNG")
	public SikuliElement shoppifyConvertToMetric;
	
	@FindBy(image=helpers.Constants.filePath + "dataModelling\\Shoppify-OrderShippingLines-3.PNG")
	public SikuliElement shoppifyMultiAttribute;
	
	
	
	public DataModelling(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}
	
	public void performDataModelling(String dsName) throws Exception{	     
	     SikuliElement dataTypeChangeField = null;
	     SikuliElement doNotImportField = null;
	     SikuliElement convertToMetricField = null;
	     SikuliElement multiAttributeField = null;
	     switch (dsName) {
	     case "Shopify":
	    	 dataTypeChangeField = shoppifyDataTypeChange;
	    	 doNotImportField = shoppifyDoNotImport;
	    	 convertToMetricField = shoppifyConvertToMetric;
	    	 multiAttributeField = shoppifyMultiAttribute;
		   	 break;
		    }
			 changeDataType(dataTypeChangeField);
		     doNotImport(doNotImportField);
		     convertToMetric(convertToMetricField);
		     multiAttribute(dataTypeChangeField, multiAttributeField);
	}
	
	private void changeDataType(SikuliElement element){
		 SoftAssert verify = new SoftAssert();
		 verify.assertTrue(element.exists(100), "Data Type Change Field is not displayed");
	     sikuli.wait(5.0); 
		 element.rightClick();
		 verify.assertTrue(changeDataType.exists(100), "Change Data Type Option is not displayed");
		 changeDataType.click();
		 verify.assertTrue(longDataType.exists(100), "Long Data Type Option is not displayed");
		 longDataType.click();
		 sikuli.wait(10.0);
		 
		 element.rightClick();
		 verify.assertTrue(changeDataType.exists(100), "Change Data Type Option is not displayed");
		 changeDataType.click();
		 verify.assertTrue(textDataType.exists(100), "Text Data Type Option is not displayed");
		 textDataType.click();
		 sikuli.wait(10.0);
	}
	
	private void doNotImport(SikuliElement element){
		 SoftAssert verify = new SoftAssert();
		 verify.assertTrue(element.exists(100), "Do Not Import Field is not displayed");
		 verify.assertAll();
		 
		 element.rightClick();
		 verify.assertTrue(doNotImport.exists(60), "Do Not Import Option is not displayed");
		 doNotImport.click();
		 sikuli.wait(10.0);
	}
	
	private void convertToMetric(SikuliElement element){
		 SoftAssert verify = new SoftAssert();
		 verify.assertTrue(element.exists(50), "Convert to Metric Field is not displayed");
		 verify.assertAll();
		 
		 element.rightClick();
		 verify.assertTrue(convertToMetric.exists(50), "Convert to Metric is not displayed");
		 convertToMetric.click();
		 sikuli.wait(10.0);
	}
	
	private void multiAttribute(SikuliElement element1, SikuliElement element2){
		SoftAssert verify = new SoftAssert();
		verify.assertTrue(element1.exists(50), "Field 1 is not displayed");
		verify.assertTrue(element2.exists(50), "Field 2 is not displayed");
		
		
		element1.click();
		sikuli.wait(3.0);
		sikuli.keyDown(Key.CTRL);
		element2.click();
		sikuli.wait(10.0);
		element2.rightClick();
		
		verify.assertTrue(createMultiFormAttribute.exists(10), "Create Multi Form Attribute option is not displayed");
		createMultiFormAttribute.click();
		
		verify.assertTrue(submit.exists(50), "Submit Button is not displayed");
		submit.click();
		sikuli.wait(10.0);
		verify.assertAll();
		
	}
	
	public void addNewTable(){
		SoftAssert verify = new SoftAssert();
		verify.assertTrue(addNewTable.exists(50), "Add New Table is not displayed");
		verify.assertAll();
		addNewTable.click();
		sikuli.wait(5.0);
	}
	
}
