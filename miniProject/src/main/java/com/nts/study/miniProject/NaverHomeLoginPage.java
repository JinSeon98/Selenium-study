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
	 * ���̹� �α���
	 * @param userId
	 * @param userPw
	 * @return
	 */
	public IMembership login(String userId, String userPw ,Membership membershiptype) {
		id.sendKeys(userId);
		pw.sendKeys(userPw);
		submit.click();
		
		if(membershiptype == Membership.FAMILY) {
			 return PageFactory.initElements(driver,
					 MembershipFamilyJoinPage.class);
		}
		else if(membershiptype == Membership.STUDEMT) {
			 return PageFactory.initElements(driver,
					 MembershipStudentJoinPage.class);
		}
		
		//TODO ���� ó���ؾ���
		return PageFactory.initElements(driver,
				 MembershipFamilyJoinPage.class);
		
	}
	
}
