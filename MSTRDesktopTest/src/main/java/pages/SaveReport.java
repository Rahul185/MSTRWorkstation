package pages;

import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import sikulifactory.FindBy;
import sikulifactory.SikuliElement;
import sikulifactory.SikuliFactory;

public class SaveReport {

Screen sikuli;
	
	@FindBy(image=helpers.Constants.filePath + "saveReport\\ImportAsInMemory.PNG")
	public SikuliElement inMemory;
	
	@FindBy(image=helpers.Constants.filePath + "saveReport\\ConnectLive.PNG")
	public SikuliElement connectLive;
	
	
	public SaveReport(Screen sikuli) {
		this.sikuli = sikuli;
		SikuliFactory.initElements(sikuli, this);
	}
	
	public int saveReport(String dataAccessMode){
		SoftAssert verify = new SoftAssert();
		SikuliElement dataAccess = null;
		switch (dataAccessMode) {
		case "InMemory":
			dataAccess = inMemory;
			break;
		case "Live":
			dataAccess = connectLive;
		break;

		default:
			break;
		}
		verify.assertTrue(dataAccess.exists(50), dataAccessMode + "Data Access Mode option is not visible");
		return dataAccess.click();
	}
	
}
