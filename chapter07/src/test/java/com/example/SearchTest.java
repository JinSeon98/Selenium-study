package com.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 7장 리모트 웹드라이버 살펴보기 
 */
public class SearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();

        /*
         for Google Chrome
        */

        caps.setBrowserName("chrome");

        /*
             for Mozilla Firefox
        */

        //caps.setBrowserName("firefox");
        //caps.setCapability("marionette", true);

        /*
            for IE
         */

        //caps.setBrowserName("internet explorer");

        driver = new RemoteWebDriver(new URL("http://172.30.1.31:4444/wd/hub"), caps);
        driver.get("http://demo-store.seleniumacademy.com/");
        
        /*
         * MAC에서 특정 port 정보 확인 방법
         * lsof -i :{$포트번호}
         * 
         * 포트 죽이기
         * kill -9 {$PID}
         */

    }

    @Test
    public void searchProduct() {

        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.sendKeys("Phones");

        WebElement searchButton =
                driver.findElement(By.className("search-button"));

        searchButton.click();

        assertThat(driver.getTitle())
                .isEqualTo("Search results for: 'Phones'");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
