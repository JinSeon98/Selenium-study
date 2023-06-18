package com.example;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewTest {
	WebDriver driver;

	@BeforeClass
	public void beforeMethod() {
		// Chromedriver 경로설정 > 리소스 밑에 드라이버 파일 사용
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

		// 웹드라이버 세션초기화
		driver = new ChromeDriver();
	}

	@Test
	public void test1_webtoonSearch() throws InterruptedException {

		// 웹툰사이트 이동
		driver.get("https://comic.naver.com/index");

		// 웹툰 검색 텍스트 박스 선택
		WebElement searchBox = driver.findElement(By.className("SearchBar__search_input--k5nfk"));

		// click 후 isSelected() 메서드 false 값 노출됨.. 이유 확인 필요
		// searchBox.click();

		// 엘리먼트 존재 확인 메서드
		if (searchBox.isEnabled())
			System.out.println("웹툰 검색 박스 엘리먼트 존재 확인");
		// 엘리먼트 노출 확인 메서드
		if (searchBox.isDisplayed())
			System.out.println("웹툰 검색 박스 엘리먼트 노출 확인");

		// 웹툰명 입력
		searchBox.sendKeys("호랑이형님");

		// 웹툰 검색 돋보기 아이콘
		WebElement searchButton = driver.findElement(By.className("SearchBar__btn_search--SsL7v"));

		// 웹툰 검색 돋보기아이콘 클릭
		searchButton.click();

		// 페이지 로딩시간 필요하여 추가 > 페이지 바뀌었는지 확인 가능한 기능 있는지 추후 찾아보기
		Thread.sleep(1000);

		// 검색한 웹툰 이름
		WebElement webtoonName = driver.findElement(By.className("ContentTitle__search_title--nndrf"));

		// 웹툰 이름 가진 엘리먼트에서 값 가져온 후 비교
		AssertJUnit.assertEquals(webtoonName.getText(), "호랑이형님");
	}

	@Test
	public void test2_postSize() throws InterruptedException {

		// 웹툰 검색 텍스트 박스 선택
		WebElement webtoonPoster = driver.findElement(By.className("Poster__image--d9XTI"));

		// 웹툰 포스터 사이즈
		Dimension posterSize = webtoonPoster.getSize();

		AssertJUnit.assertEquals("웹툰 포스터 세로 크기 156 확인", posterSize.height, 156);
		AssertJUnit.assertEquals("웹툰 포스터 가로 크기 120 확인", posterSize.width, 120);
	}

	@Test
	public void test3_getLinkElements() throws InterruptedException {

		List<WebElement> links = driver.findElements(By.tagName("a"));

		System.out.println("Found links:" + links.size());

		int findWebtoonLinkCount = 0;

		for (WebElement webtoonLinkElement : links) {
			String linkName = webtoonLinkElement.getText();
			if (linkName.length() <= 0)
				continue;

			System.out.println(linkName);

			if (linkName.equals("호랑이 형님"))
				findWebtoonLinkCount++;
		}

		AssertJUnit.assertEquals(findWebtoonLinkCount, 1);

		/*
		 * 텍스트 가지고 있는 링크 찾는 예제 코드 using Java 8 Streams API 위 for문 간단 정리 links.stream()
		 * .filter(elem -> elem.getText().length() > 0) .forEach(elem ->
		 * System.out.println(elem.getText()));
		 */
	}

	@AfterClass
	public void afterMethod() {
		driver.quit();
	}

}
