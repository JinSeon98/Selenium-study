package com.nts.study.miniProject.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nts.study.miniProject.MembershipJoinPage;
import com.nts.study.miniProject.NaverHomeLoginPage;
import com.nts.study.miniProject.User;


public class membership_TC {
	WebDriver driver;
	/** 멤버십 가입 ID*/
	String userID = "nvqa_rejoinsb02";
	/** 멤버십 가입 PW*/
	String userPW = "qweqwe";
	/** IIMS 관리툴 ID*/
	String iimsID = "NT11940";
	/** IIMS 관리툴 PW*/
	String iimsPW = "abcd";

	@BeforeMethod
	public void beforeClass() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	/** MembershipJoin에서 동작하도록 옮겨야하나.. 고민중*/
	@AfterMethod
	public void initMembership() {
//		iimsMemPage iims = PageFactory.initElements(driver, iimsMemPage.class);	
//		iims.login(this.iimsID, this.iimsPW);
//		iims.deleteMembership(membershipID);
	}

	@Test(description = "패밀리 멤버십 원클릭 월간 가입")
	public void TC_01_membership_Month_Join() {
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipJoinPage membershipJoin = naverLoginPage.login(userID, userPW);
		membershipJoin.membershipJoinMonth(User.ONCLICK_FAMILY);
	}
	
	@Test(description = "패밀리 멤버십 원클릭 연간 가입")
	public void TC_02_membership_Year_Join() {
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipJoinPage membershipJoin = naverLoginPage.login(userID, userPW);
		membershipJoin.membershipJoinMonth(User.ONCLICK_STUDENT);
	}
	
	@Test(description = "스튜던트 멤버십 원클릭 월간 가입")
	public void TC_03_student_membership_Month_Join() {
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipJoinPage membershipJoin = naverLoginPage.login(userID, userPW);
		membershipJoin.membershipJoinMonth(User.NORMAL_FAMILY);
	}
	
	@Test(description = "스튜던트 멤버십 원클릭 연간 가입")
	public void TC_04_student_membership_Year_Join() {
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipJoinPage membershipJoin = naverLoginPage.login(userID, userPW);
		membershipJoin.membershipJoinYear(User.NORMAL_STUDENT);
	}
	
	/*
	 * 1.원클릭 필수 약관 체크 미체크 케이스 추가
	 * 2.원클릭 아닌 경우 약관동의 체크 + 가입 + 키패드 입력 추가 (키패드 입력 기능 다음 실습으로 보류..?)
	 */
	
	@AfterMethod
	public void afterClass() {
		 driver.quit();
	}
}
