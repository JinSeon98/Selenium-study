import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;

public class PracticeChapter5 {
	WebDriver driver;

	@Test
	public void controlVolume() {
		WebElement bar = driver.findElement(By.className("bar_status"));

		Actions actions = new Actions(driver);
		actions.moveByOffset(bar.getLocation().getX(), bar.getLocation().getY()).clickAndHold()
				.moveByOffset(100, 0).release().perform();
	}

	@Test
	public void movePlaylist() {
		WebElement list1 = driver.findElement(By.xpath("//*[@id=\"player\"]/div[2]/div[2]/div[2]/div/div/ul/li[1]"));
		WebElement list2 = driver.findElement(By.xpath("//*[@id=\"player\"]/div[2]/div[2]/div[2]/div/div/ul/li[2]"));

		Actions actions = new Actions(driver);
		actions.moveByOffset(list1.getLocation().getX(), list1.getLocation().getY()).clickAndHold()
				.moveByOffset(0, -50).release().perform();

	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users/USER/eclipse-workspace/chromedriver.exe");

		// 웹드라이버 세션 초기화
		driver = new ChromeDriver();
		driver.get("https://vibe.naver.com/today"); // 구글 메인 url
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 묵시적 대기 시간

		driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/a[2]")).click();
		driver.findElement(By.className("btn_play_now")).click();
		driver.findElement(By.xpath("//*[@id=\"player\"]/div[1]/div[6]/a")).click();
	}

	@AfterMethod
	public void afterMethod() {
		// driver.quit();
	}

}
