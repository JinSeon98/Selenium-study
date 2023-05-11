package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

/* 
 * Chapter 1 실습
 * 
 * 네이버 웹툰 검색 페이지에서 chapter 1 내용을 바탕으로
 * 웹 엘리먼트를 지정하고 사용자 액션을 실행해보자
 */

public class PraticeChapter1 {
	
	WebDriver driver;

	//By.className(), sendKeys(), clear() 이용하여 웹툰 검색
	@Test
	public void searchWebtoon() {
		WebElement searchBox = driver.findElement(By.className("SearchBar__search_input--k5nfk"));
		WebElement searchBtn = driver.findElement(By.className("SearchBar__btn_search--SsL7v"));
		WebElement searchResult = driver.findElement(By.className("SearchResultLayout__title_area--TdVIz"));
		
		searchBox.clear();
		searchBox.sendKeys("휴재 일기");
		searchBtn.click();
		
		WebElement title = driver.findElement(By.className("SearchResultList__summary--ZrQ31"));
		
		//getText()를 이용하여 줄거리 내용 출력 
		System.out.println("Context : "+ title.getText());
		assertThat(searchResult.getText()).isEqualTo("'휴재 일기'\n"
				+ "에 대한 검색결과 입니다.");
	}
	
	//By.xpath()를 이용하여 웹툰 메인페이지로 이동
	@Test
	public void clickLogo() {
		WebElement logo = driver.findElement(By.xpath("//*[@id=\"wrap\"]/header/div[1]/h1/a[2]"));
		logo.click();
	}

	@BeforeMethod
	public void beforeMethod() {
		// chromedriver 경로 설정
		System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");

		// 웹드라이버 세션 초기화
		driver = new ChromeDriver();
		driver.get("https://comic.naver.com/search?keyword=%EC%9D%BC%EA%B8%B0"); //네이버 웹툰 url
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		//driver.quit();
	}

}