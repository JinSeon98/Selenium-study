package android_test01;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class android01 {
	public static AppiumDriver<AndroidElement> driver;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();

	@BeforeClass
	public void setUp() throws Exception {
		//adb shell dumpsys window | grep -E 'mCurrentFocus'
    	//aapt 명령어를 통해 입력 할 예정(테스트 할 앱의 패키지 명)
		capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        
        //aapt 명령어를 통해 입력 할 예정(테스트 할 앱의 launchable-activity 정보)
		capabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator"); 
        
        //자신의 디바이스 명을 설정. 아무 이름이나 적어도 괜찮습니다.
		capabilities.setCapability("deviceName", "Note10+");
        
        //adb 명령어를 통해 입력 할 예정(자기 디바이스의 udid)
		capabilities.setCapability("udid", "R3CM80BKGRM");
        
        //크게 신경 안써도 되는 문구
		capabilities.setCapability("unicodeKeyboard", "true");
		capabilities.setCapability("resetKeyboard", "true");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void Test1() throws Exception {
		driver.findElementByXPath("//android.widget.Button[@content-desc='1']").click();
		driver.findElementByXPath("//android.widget.Button[@content-desc='2']").click();
		//driver.findElementByXPath("//android.widget.Button[@text='3']").click();
		//driver.findElementByXPath("//android.widget.Button[@text='4']").click();
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void end() throws Exception {
		driver.quit();
	}
}
