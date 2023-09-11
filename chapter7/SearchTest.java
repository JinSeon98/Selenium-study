package TestNew;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

public class SearchTest {

	WebDriver driver;

	@BeforeMethod
	public void setup() throws MalformedURLException {
//    	System.setProperty("webdriver.chrome.driver",
//                "/Users/user/Documents/chromedriver");
//        driver = new ChromeDriver();
//        driver.get("http://demo-store.seleniumacademy.com/");

		DesiredCapabilities caps = new DesiredCapabilities();

		/*
		 * for Google Chrome
		 */

		caps.setBrowserName("chrome");

		/*
		 * for Mozilla Firefox
		 */

//        caps.setBrowserName("firefox");
//        caps.setCapability("marionette", true);

		/*
		 * for IE
		 */

//        caps.setBrowserName("internet explorer");
		

		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), caps);
		driver.get("http://demo-store.seleniumacademy.com/");

	}

	@Test
	public void searchProduct() {

		// find search box and enter search string
		WebElement searchBox = driver.findElement(By.name("q"));

		searchBox.sendKeys("Phones");

		WebElement searchButton = driver.findElement(By.className("search-button"));

		searchButton.click();

		assertThat(driver.getTitle()).isEqualTo("Search results for: 'Phones'");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
