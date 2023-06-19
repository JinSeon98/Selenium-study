package com.nts.study.miniProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NaverHomeLoginPage {
	
	WebDriver driver;
	
	@FindBy(id="id")
	WebElement id;
	
	@FindBy(id="pw")
	WebElement pw;
	
	@FindBy(id="log.login")
	WebElement submit;
	
	public NaverHomeLoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https://www.naver.com/");

	}
	
	/**
	 * 네이버 로그인
	 * @param userId
	 * @param userPw
	 * @return
	 */
	public MembershipJoinPage  login(String userId, String userPw) {
		id.sendKeys(userId);
		pw.sendKeys(userPw);
		submit.click();
		
		 return PageFactory.initElements(driver,
				 MembershipJoinPage.class);
	}
	
}
