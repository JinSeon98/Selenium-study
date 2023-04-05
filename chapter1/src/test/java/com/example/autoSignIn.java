package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class autoSignIn {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Chromedriver ��μ��� > ���ҽ� �ؿ� ����̹� ���� ���
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

		// ������̹� �����ʱ�ȭ
		driver = new ChromeDriver();
		
		loginAdminTool();
		driver.findElement(By.className("NID-card-list-li")).click();
		
		
	}
	
	private void loginAdminTool() {
		driver.get("https://iims.navercorp.com");
		driver.findElement(By.id("user-id")).sendKeys("NT11940");
		driver.findElement(By.id("user-pw")).sendKeys("Alsehftn5530!");
		driver.findElement(By.id("login-btn")).click();
	}

	
	@Test
	public void test1_usualUser() {
		System.out.println("test");
	}
	
	@AfterClass
	private void afterClass() {
		driver.quit();
	}

}
