package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NaverTvTest {
	
	WebDriver driver;

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        //드라이버 열기 
        driver.get("https://tv.naver.com/");

    }
    @Test
    public void searchContents() {

        // 검색창 찾기   
        WebElement searchBox = driver.findElement(By.id("searchQuery"));
        //검색창에 '뉴스' 검색어 입력 
        searchBox.sendKeys("뉴스");

        WebElement searchButton =
                driver.findElement(By.className("btn_search"));

        searchButton.click();
        
        WebElement tit = driver.findElement(By.className("hash_keyword"));
    	System.out.println(tit.getText() + " 검색결과");

    }

	@AfterMethod
    public void tearDown() {
        driver.quit();
    }
  
}
