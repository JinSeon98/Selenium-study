package com.nts.study.miniProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MembershipJoinPage {
	
	WebDriver driver;
	Boolean onclickUser = false;
	
	
	/**
	 * 가입하기 버튼
	 */
	@FindBy(id ="joinButton")
	WebElement joinButton;
	
	/**
	 * 재가입 버튼
	 */
	@FindBy(id="plusAgree")
	WebElement plusAgree;
	
	
	public MembershipJoinPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://nid.naver.com/membership/join");
	}
	
	/**
	 * 멤버십 월간 가입
	 * @param userId 멤버십 가입 네이버 ID
	 * @param userPw 멤버십 가입 네이버 PW
	 */
	public void membershipJoinMonth(User usertype) {
		
		if(usertype == User.ONCLICK_FAMILY) {
			oneClickFamilyJoin();
		}
		else if(usertype == User.ONCLICK_STUDENT) {
			oneClickStudentJoin();
		}
		else if(usertype == User.NORMAL_FAMILY) {
			normalClickFamilyJoin();
		}
		else if(usertype == User.NORMAL_STUDENT) {
			normalClickStudentJoin();
		}
	}
	
	/**
	 * 멤버십 연간 가입
	 * @param userId 멤버십 가입 네이버 ID
	 * @param userPw 멤버십 가입 네이버 PW
	 */
	public void membershipJoinYear(User usertype) {
		if(usertype == User.ONCLICK_FAMILY) {
			//TODO
		}
		else if(usertype == User.ONCLICK_STUDENT) {
			//TODO
		}
		else if(usertype == User.NORMAL_FAMILY) {
			//TODO
		}
		else if(usertype == User.NORMAL_STUDENT) {
			//TODO
		}
	}
	
	private void oneClickStudentJoin() {
		/*학생 인증 코드 필요*/
		joinButton.click();
		plusAgree.click();
	}

	private void oneClickFamilyJoin() {
		joinButton.click();
		plusAgree.click();
	}
	
	//TODO
	private void normalClickStudentJoin() {
		agreeConditions();
		/*학생 인증 코드 필요*/
		joinButton.click();
		plusAgree.click();
	}

	//TODO
	private void normalClickFamilyJoin() {
		agreeConditions();
		joinButton.click();
		plusAgree.click();
	}

	private void agreeConditions() {
		driver.findElement(By.cssSelector("#paymentTerm > label")).click();
		driver.findElement(By.cssSelector("#joinAgreePopup > div > div.terms_area > div > div.terms_item > div:nth-child(3) > label")).click();
		driver.findElement(By.cssSelector("##joinAgreePopup > div > div.terms_area > div > div.terms_item > div:nth-child(4) > label")).click();
		driver.findElement(By.cssSelector("##joinAgreePopup > div > div.terms_area > div > div.terms_item > div:nth-child(4) > label")).click();
	}
}
