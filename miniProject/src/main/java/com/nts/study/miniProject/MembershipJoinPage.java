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
	 * �����ϱ� ��ư
	 */
	@FindBy(id ="joinButton")
	WebElement joinButton;
	
	/**
	 * �簡�� ��ư
	 */
	@FindBy(id="plusAgree")
	WebElement plusAgree;
	
	
	public MembershipJoinPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://nid.naver.com/membership/join");
	}
	
	/**
	 * ����� ���� ����
	 * @param userId ����� ���� ���̹� ID
	 * @param userPw ����� ���� ���̹� PW
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
	 * ����� ���� ����
	 * @param userId ����� ���� ���̹� ID
	 * @param userPw ����� ���� ���̹� PW
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
		/*�л� ���� �ڵ� �ʿ�*/
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
		/*�л� ���� �ڵ� �ʿ�*/
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
