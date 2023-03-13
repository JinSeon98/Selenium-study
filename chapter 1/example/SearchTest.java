package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchTest {

	WebDriver driver;

	@Test
	public void searchProduct() {
		// 검색창을 찾아 검색어를 입력
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("phones");
		WebElement searchButton = driver.findElement(By.className("search-button"));
		searchButton.click();
		assertThat(driver.getTitle()).isEqualTo("Search results for: 'Phones'");
		searchBox.clear();
	}

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo-store.seleniumacademy.com/");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
