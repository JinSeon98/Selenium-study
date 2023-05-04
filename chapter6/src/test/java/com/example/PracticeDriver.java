package com.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class PracticeDriver {

	public static void main(String... args){

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            EventFiringWebDriver eventFiringDriver = new
                    EventFiringWebDriver(driver);
            PracticeEventListener eventListener = new PracticeEventListener();
            eventFiringDriver.register(eventListener);
            eventFiringDriver.get("https://comic.naver.com/webtoon/detail?titleId=790713&no=122&week=tue");
            
            driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[4]/button[2]")).click();
            for(int i = 0; i < 2; i++) {
        	   driver.findElement(By.xpath("//*[@id=\"cbox_module\"]/div/div[2]/div/form/fieldset/div/div/div[1]/div/label/a")).click();
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
