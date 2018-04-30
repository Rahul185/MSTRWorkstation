package loggingUtils;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class LoggerModule {
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
	String timeStamp = dateFormat.format(now);
	
	public Logger getLogger(String className){
		
		String classShort = className.split("\\.")[1];
		System.out.println(classShort);
		String fileName = "C:/workspace/MSTRDesktopTest/logs/" + classShort + "/" + timeStamp + "/logfile.log";
		
		// creates pattern layout
		PatternLayout layout = new PatternLayout();
		String conversionPattern = "%d{HH:mm:ss} %5p [%t] - " + className + ".%M - %m%n";
		layout.setConversionPattern(conversionPattern);		
		
		// creates daily rolling file appender
		RollingFileAppender rollingAppender = new RollingFileAppender();
		rollingAppender.setFile(fileName);
		rollingAppender.setLayout(layout);
		rollingAppender.activateOptions();
		
		//configures the root logger
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.DEBUG);
		rootLogger.addAppender(rollingAppender);
		
		//creates a custom logger and log messages
		Logger logger = Logger.getRootLogger();		
		return logger;
	}
	
	public void captureErrorImage(String className, String imageName){
		
		String classShort = className.split("\\.")[1];
		System.out.println(classShort);
		try 
		{
			Robot robot = new Robot();
			String format = "jpg";
			String fileName = "C:/workspace/MSTRDesktopTest/logs/" + classShort + "/" + timeStamp + "/" + imageName + "." + format;
         
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            
            ImageIO.write(screenFullImage, format, new File(fileName));
            System.out.println("A full screenshot saved!");
		}
		catch (Exception ex) 
		{
	            System.err.println(ex);
	    }
	}
}
