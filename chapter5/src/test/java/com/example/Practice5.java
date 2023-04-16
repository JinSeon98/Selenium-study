package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Practice5 {
	WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }
	
  @Test
  public void select() {
	driver.get("https://vibe.naver.com/chart");
	  
	//xpath 이용해서 모달 팝업 닫기버튼 찾기  
	WebElement close_btn = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/a[2]"));
	//재생 버튼 
	WebElement play_btn = driver.findElement(By.id("btn_now"));
  
  	Actions actions = new Actions(driver);
  	
  	close_btn.click();
  	play_btn.click();
 
  	
	WebElement volume_bar = driver.findElement(By.className("bar_volume"));
	// volume bar max 로 조정 
	actions.clickAndHold(volume_bar)
			.moveByOffset(100,0).release().perform();
  	
  }
}
