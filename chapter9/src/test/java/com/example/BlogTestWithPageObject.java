package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BlogTestWithPageObject {
	WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
  @Test
  public void testLogin() {
	  
	  // UserLoginPage 객체 인스턴스 생성 
	  UserLoginPage loginPage = PageFactory.initElements(driver, UserLoginPage.class);
	  
	  //로그인 수행 
	  loginPage.login();
  }
  // 블로그 새글 등록 
  @Test
  public void testAddNewPost() {
	  testLogin();
	  
	  AddNewPostPage addNewPost = PageFactory.initElements(driver, AddNewPostPage.class);
	  
	  addNewPost.addNewPost();
  }

/*  @AfterMethod
  public void afterMethod() {
  }
*/
}
