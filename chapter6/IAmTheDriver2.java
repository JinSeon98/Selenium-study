package chapter6;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;

/* 
 * Chapter 6 실습(TestNG)
 * 
 * 네이버 웹툰 페이지에서 chapter 6 내용을 바탕으로
 * 웹 드라이버 이벤트 수행해보자
 */

public class IAmTheDriver2 {
	WebDriver driver;
	EventFiringWebDriver eventFiringDriver;

	@Test
	public void navigateEvent() {

		eventFiringDriver.get("https://comic.naver.com/index"); // 네이버 웹툰 메인으로 이동
		eventFiringDriver.get("https://comic.naver.com/webtoon/detail?titleId=700139&no=1"); // 웹툰 상세로 이동

		eventFiringDriver.navigate().refresh(); // 새로고침

		eventFiringDriver.navigate().back(); // 뒤로가기

		eventFiringDriver.navigate().forward(); // 앞으로가기
	}
	
	@Test
	public void alertAccept() {
		eventFiringDriver.get("https://comic.naver.com/webtoon/detail?titleId=700139&no=1"); // 웹툰 상세로 이동

		eventFiringDriver.findElement(By.className("u_cbox_write_wrap")).click(); // 댓글 필드 선택
		eventFiringDriver.switchTo().alert().accept(); // 얼럿 확인
	}
	
	@Test
	public void alertDismiss() {
		eventFiringDriver.get("https://comic.naver.com/webtoon/detail?titleId=700139&no=1"); // 웹툰 상세로 이동

		eventFiringDriver.findElement(By.className("u_cbox_write_wrap")).click(); // 댓글 필드 선택
		eventFiringDriver.switchTo().alert().dismiss(); //얼럿 취소
	}


	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users/USER/eclipse-workspace/chromedriver.exe");

		// 웹드라이버 세션 초기화
		driver = new ChromeDriver();
		
		eventFiringDriver = new EventFiringWebDriver(driver);

		// EventListener 여러개 등록
		IAmTheEventListener eventListener = new IAmTheEventListener();
		IAmTheEventListener2 eventListener2 = new IAmTheEventListener2();
		eventFiringDriver.register(eventListener);
		eventFiringDriver.register(eventListener2);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 묵시적 대기 시간
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

}
