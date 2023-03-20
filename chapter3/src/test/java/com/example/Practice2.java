package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practice2 {
	WebDriver driver;

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://tv.naver.com/");

    }
    @Test
    public void linksTest() {
    	List<WebElement> links = driver.findElements(By.tagName("a"));
    	System.out.println("Total Links : " + links.size());
    	
    	long count = links.stream().filter(item -> item.isDisplayed()).count();
    	System.out.println("Total Link visible " + count);
    }
    
    @Test
    public void imgAltTest() {
    	List<WebElement> images = driver.findElements(By.tagName("img"));
    	
    	System.out.println("Total images : " + images.size());
    	
    	List<WebElement> imagesWithOutAlt = images.stream()
    			.filter(item -> item.getAttribute("alt") == "")
    			.collect(Collectors.toList());
    	
    	System.out.println("Total images without alt attribute " + imagesWithOutAlt);
    }
    @Test
    public void maxLikeTest() {
    	List<Info> contents = new ArrayList<>();
    	contents.add(new Info("미스터트롯2",102187,1691));
    	contents.add(new Info("집사부일체",5452,37));
    	contents.add(new Info("SBS인기가요",4057,279));
    	
    	Info info = contents.stream().max(Comparator.comparing(item -> item.getLike())).get();
    	System.out.println("가장 많은 좋아요 채널은 : " + info.getName());
    }
    class Info {
    	String name;
        int hit;
        int like ;

        public Info(String name, int hit, int like) {
            this.name = name;
            this.hit = hit;
            this.like = like;
        }

        public String getName() {
            return name;
        }

        public int getHit() {
            return hit;
        }
        
        public int getLike() {
            return like;
        }

    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
