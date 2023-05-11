package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/* 
 * Chapter 6 실습(클래스)
 * 
 * 네이버 웹툰 페이지에서 chapter 6 내용을 바탕으로
 * 웹 드라이버 이벤트 수행해보자
 */

public class PracticeDriver {

	public static void main(String... args){

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");

        WebDriver driver = new ChromeDriver();
        
        try {
            EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(driver);
            
            
            PracticeEventListener eventListener = new PracticeEventListener();
            eventFiringDriver.register(eventListener);
            
            eventFiringDriver.get("https://comic.naver.com/webtoon/detail?titleId=790713&no=122&week=tue");
            
            //eventFiringDriver에서 발생한 이벤트가 아니여서 clickOn 메서드 실행 X
            driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[4]/button[2]")).click();
            
            WebElement popup = eventFiringDriver.findElement(By.xpath("//*[@id=\"cbox_module\"]/div/div[2]/div/form/fieldset/div/div/div[1]/div/label/a"));
            
            for(int i = 0; i < 2; i++) {
            	//eventFiringDriver에서 발생한 이벤트이기 때문에 clicOn 메서드 실행
            	popup.click();
            	// 첫번째는 팝업 취소 선택 
            	if(i == 0) {
        		   eventFiringDriver.switchTo().alert().dismiss();
            	}
            	// 두번째는 팝업 확인 선택 
            	else {
        		   eventFiringDriver.switchTo().alert().accept();
            	}
           }
            
            eventFiringDriver.navigate().back();
        } finally {
            driver.close();
            driver.quit();
        }
    }

}
