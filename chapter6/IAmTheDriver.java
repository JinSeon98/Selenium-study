package chapter6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/* 
 * Chapter 6-1 실습
 * 
 * 네이버 웹툰 페이지에서 chapter 6 내용을 바탕으로
 * 웹 드라이버 이벤트 수행해보자
 */

public class IAmTheDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C://Users/USER/eclipse-workspace/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		try {
			EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(driver);
			IAmTheEventListener eventListener = new IAmTheEventListener();
			eventFiringDriver.register(eventListener);
			eventFiringDriver.get("https://comic.naver.com/index");
			eventFiringDriver.get("https://comic.naver.com/webtoon/detail?titleId=700139&no=1");
						
			eventFiringDriver.navigate().refresh();
			
			driver.findElement(By.className("u_cbox_write_wrap")).click();
			eventFiringDriver.switchTo().alert().accept();
			
			eventFiringDriver.navigate().back();
			
			driver.findElement(By.className("u_cbox_write_wrap")).click();
			eventFiringDriver.switchTo().alert().dismiss();
			
			eventFiringDriver.navigate().forward();

			
		} finally {
			driver.close();
			driver.quit();
		}
	}

}
