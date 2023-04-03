package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

/* 
 * Chapter 4-1 실습
 * 
 * 네이버 메인 페이지에서 chapter 4-1 내용을 바탕으로
 * 스크린샷 캡처, 윈도우 창과 iFrame 지정, 브라우저 내비게이션 제어 기능을 테스트에 활용해보자
 */

public class PraticeChapter4_1 {

	WebDriver driver;

	/*
	 * 네이버 메인 화면 스크린샷 캡처하기
	 * getScreenshotAs() 사용
	 */
	@Test
	public void screenCapture() {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		try {
			FileUtils.copyFile(scrFile, new File("./target/screenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * 네이버 메인 화면과 레시피 상세 화면 창 전환하기
	 * getWindowHandle(), switchTo().window() 사용
	 */
	@Test
	public void handleWindow() {
		String mainWindow = driver.getWindowHandle();
		System.out.println("First Window Handle is: "+mainWindow);
		
		driver.findElement(By.xpath("//*[@id=\"NM_THEME_CATE_LIST\"]/li[7]/a")).click(); // 레시피 목록 보기
		WebElement recipeThumb = driver.findElement(By.xpath("//*[@id=\"NM_THEME_CONTAINER\"]/div[1]/div[1]/div/a[1]")); // 레시피 목록 첫번째 게시물
		recipeThumb.click();
		
		String recipeWindow = driver.getWindowHandle();
		System.out.println("Second Window Handle is: "+recipeWindow);
		System.out.println("Number of Window Handles so for: "+driver.getWindowHandles().size());
		
		driver.switchTo().window(mainWindow); // 레시피 상세 화면에서 네이버 메인 화면으로 창 전환
	}
	
	/*
	 * 네이버 메인 화면에서 frame 간 전환하기
	 * switchTo().frame(), switchTo().defaultContent() 사용
	 */
	@Test
	public void switchBetweenFrames() {
		/*driver.switchTo().frame(0);
		WebElement firstField = driver.findElement(By.id("veta_top_inner_tgtLREC"));
		firstField.click();
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(1);
		WebElement secondField = driver.findElement(By.id("p_theme_living_div_tgtLREC"));
		firstField.click();*/
	}
	
	/*
	 * 브라우저 네비게이션 제어 기능을 활용하여 네이버 티비에서 추천 목록이 변경되는 지 확인하기
	 * navigate().to(), navigate().back(), navigate().refresh() 사용
	 */
	@Test
	public void useNavigate() {
		driver.navigate().to("https://tv.naver.com/"); // 네이버 티비 url로 이동
		
		WebElement recommend = driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/ul/li[1]/dl/dt/a")); //추천 목록 첫번째 영상
		
		System.out.println("새로고침 전 동영상 제목: "+recommend.findElement(By.cssSelector("tooltip")).getText());
		recommend.click(); 
		
		driver.navigate().back(); // 네이버 티비 메인 화면으로 이동
		
		driver.navigate().refresh(); // 새로고침 -> 추천 목록 변경됨
		
		recommend = driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/ul/li[1]/dl/dt/a")); //추천 목록 첫번째 영상
		System.out.println("새로고침 전 동영상 제목: "+recommend.findElement(By.cssSelector("tooltip")).getText());
		recommend.click();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users/USER/eclipse-workspace/chromedriver.exe");

		// 웹드라이버 세션 초기화
		driver = new ChromeDriver();
		driver.get("https://www.naver.com/"); // 네이버 메인 url
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 묵시적 대기 시간
	}

	@AfterMethod
	public void afterMethod() {
		//driver.quit();
	}

}
