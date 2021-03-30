import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class NewTest {
	    private static final String APP = "C:\\apk\\TheApp-v1.9.0.apk";
	    private static final String APPIUM = "http://localhost:4723/wd/hub";
		@SuppressWarnings("rawtypes")
		private AndroidDriver driver;

	    @SuppressWarnings("rawtypes")
		@BeforeMethod
		public void setUp() throws Exception {
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("platformName", "Android");
	        caps.setCapability("platformVersion", "9");
	        caps.setCapability("deviceName", "appium");
	        caps.setCapability("automationName", "UiAutomator2");
	        caps.setCapability("app", APP);
	        driver = new AndroidDriver(new URL(APPIUM), caps);
	      //wait the driver is loaded
	        try { Thread.sleep(15000); } catch (Exception ign) {}
	    }
	    
		@AfterMethod
		public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }

	    @Test
		public void test() {
	        System.out.println("Here's our test!");
	        WebDriverWait wait = new WebDriverWait(driver, 10);

	        System.out.println("Wait that Login Screen is visible and click on that");
	        WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login Screen")));
	        screen.click();

	        System.out.println("Wait that Username is visible write alice");
	        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("username")));
	        username.sendKeys("alice");

	        System.out.println("Wait that Password is visible and write");
	        WebElement password = driver.findElement(MobileBy.AccessibilityId("password"));
	        password.sendKeys("mypassword");

	        System.out.println("Click the button to access the security area");
	        WebElement login = driver.findElement(MobileBy.AccessibilityId("loginBtn"));
	        login.click();
            
	        //wait load the page
	        try { Thread.sleep(1000); } catch (Exception ign) {}
	        
	        //assertEquals(actual, expected);
	        String sentence = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView[2]")).getText();
	        System.out.println(sentence);
	        assertEquals(sentence, "You are logged in as alice");
	        
	        System.out.println("Completed our test!");
	    }
}
