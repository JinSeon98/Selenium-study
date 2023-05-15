package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserLoginPage {

  WebDriver driver;
  
  @FindBy(id = "id")
  WebElement id;

  @FindBy(id = "pw")
  WebElement password;

  @FindBy(id = "log.login")
  WebElement submit;

  public UserLoginPage(WebDriver driver) {
      this.driver = driver;
      driver.get("https://nid.naver.com/nidlogin.login?url=https%3A%2F%2Fsection.blog.naver.com%2FBlogHome.naver");
  }
  
  public void login() {
	  // 임의의 id -> 실제값x
	  id.sendKeys("id");
	  // 임의의 pw -> 실제값x
	  password.sendKeys("pw");
	  submit.click();
  }


}
