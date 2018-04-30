package pages;

import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import sikulifactory.FindBy;
import sikulifactory.FindByImage;
import sikulifactory.SikuliElement;
import sikulifactory.SikuliFactory;

public class HomePage {
	
	Screen sikuli;
	@FindBy(image=helpers.Constants.filePath + "homeScreen\\Maximize.PNG")
	private SikuliElement maximize;
	
	@FindBy(image=helpers.Constants.filePath + "homeScreen\\New-Dossier.PNG")
	public SikuliElement newDossier;
	
	
	public HomePage(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}
	
	public int maximizeScreen(){
	     SoftAssert verify = new SoftAssert();
		 verify.assertTrue(maximize.exists(20), "Maximize button is not visible");
		 verify.assertAll();
		 return maximize.click();
	}
	
	public int clickOnNewDossier(){
		// newDossier.wait(10);
	     SoftAssert verify = new SoftAssert();
		 verify.assertTrue(newDossier.exists(50), "New Dossier button is not displayed");
		 verify.assertAll();
		 return newDossier.click();
	}
	
	

}
