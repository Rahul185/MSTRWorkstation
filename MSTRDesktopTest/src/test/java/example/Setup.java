package example;

import org.sikuli.script.App;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Setup {
	
	Screen sikuli;
	public App sikuliApp;

	String path = "C:\\Program Files\\MicroStrategy\\Desktop\\Desktop.EXE";

	
	//@BeforeMethod
	public App openApp() throws Exception
	{
		sikuliApp = App.open(path);
		return sikuliApp;
	}
	
//	@AfterMethod
	public void after() {
		sikuliApp.close();
	}

}
