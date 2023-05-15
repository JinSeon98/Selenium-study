package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewPostPage {
	
	WebDriver driver;
	
	@FindBy(id = "mainFrame")
	WebElement mainframe;
	
	@FindBy(tagName = "p")
	WebElement newPostTitle;
	
	public AddNewPostPage(WebDriver driver) {
	      this.driver = driver;
	      driver.get("https://blog.naver.com/nvqa_tv001?Redirect=Write");
	  }
	
	public void addNewPost() {
		driver.switchTo().frame(mainframe);
		
		newPostTitle.click();
		// click까지 동작하고 문자입력 실패함  
		newPostTitle.sendKeys("Hello");
	}

}
