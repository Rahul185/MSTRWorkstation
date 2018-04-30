package pages;

import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import sikulifactory.FindBy;
import sikulifactory.SikuliElement;
import sikulifactory.SikuliFactory;

public class DossierPage {
	
	Screen sikuli;
	
	@FindBy(image=helpers.Constants.filePath + "dossierScreen\\Maximize.PNG")
	private SikuliElement maximize;
	
	@FindBy(image=helpers.Constants.filePath + "dossierScreen\\New-Data.PNG")
	private SikuliElement newData;
	
	@FindBy(image=helpers.Constants.filePath + "dossierScreen\\dossierScreen.PNG")
	public SikuliElement dossierScreen;
	
	@FindBy(image=helpers.Constants.filePath + "dossierScreen\\Save.PNG")
	public SikuliElement save;
	
	@FindBy(image=helpers.Constants.filePath + "dossierScreen\\Shopify-OrderId.PNG")
	public SikuliElement attribute;
	
	@FindBy(image=helpers.Constants.filePath + "dossierScreen\\Shopify-OrderId-Selected.PNG")
	public SikuliElement attributeSelected;
	
	@FindBy(image=helpers.Constants.filePath + "dossierScreen\\FolderPath.PNG")
	public SikuliElement folderPath;
	
	@FindBy(image=helpers.Constants.filePath + "dossierScreen\\SaveDossier.PNG")
	public SikuliElement saveDossier;
	
	
	public DossierPage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}

	public int maximizeDossierScreen(){
	     SoftAssert verify = new SoftAssert();
		 verify.assertTrue(maximize.exists(50), "Maximize button is not visible");
		 verify.assertAll();
		 return maximize.click();
	}
	
	public int clickOnNewData() throws InterruptedException{
	     SoftAssert verify = new SoftAssert();
		 verify.assertTrue(newData.exists(50), "Failed on clicking New Data button on Dossier Page");
		 verify.assertAll();
		 return newData.click();
	}
	
	public void saveDossier(String dossierName){
		SoftAssert verify = new SoftAssert();
		verify.assertTrue(save.exists(180), "Dossier Screen is not visible");
		
		verify.assertTrue(attribute.exists(50), "Attribute is not visible");
		attribute.doubleClick();
		
		verify.assertTrue(attribute.exists(60), "Attribute is not added");
		save.click();
		
		verify.assertTrue(folderPath.exists(50), "Folder Path is not Visible");
		folderPath.type(Key.DELETE);
		folderPath.type(Key.BACKSPACE);
		folderPath.type("C:\\Users\\jadmin\\Desktop\\Dossier\\" + dossierName);
		
		verify.assertTrue(saveDossier.exists(50), "Folder Path is not Visible");
		saveDossier.click();		
	}
}
