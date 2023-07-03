package com.nts.study.miniProject.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nts.study.miniProject.Membership;
import com.nts.study.miniProject.MembershipFamilyJoinPage;
import com.nts.study.miniProject.MembershipStudentJoinPage;
import com.nts.study.miniProject.NaverHomeLoginPage;
import com.nts.study.miniProject.User;
import com.nts.study.miniProject.iimsMemPage;


public class membership_TC {
	WebDriver driver;
	/** 멤버십 가입 ID*/
	String userID ;
	/** 멤버십 가입 PW*/
	String userPW ;


	
	@BeforeClass
	public void beforeClass() {
		
	}
	
	@BeforeMethod
	public void beforeMethod() {

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

	@Test(description = "패밀리 멤버십 월간 가입")
	public void TC_01_membership_family_Month_Join() {
		this.userID = "nvqa_rejoinsb02";
		this.userPW = "qweqwe";
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipFamilyJoinPage membershipJoin = (MembershipFamilyJoinPage) naverLoginPage.login(userID, userPW,Membership.FAMILY);
		membershipJoin.membershipJoin(User.NORMAL_MONTH);
		
//		iimsMemPage iims = PageFactory.initElements(driver, iimsMemPage.class);	
//		iims.login();
//		iims.deleteMembership(userID);
	}
	
	@Test(description = "패밀리 멤버십 연간 가입")
	public void TC_02_membership_family_Year_Join() {
		this.userID = "nvqa_rejoinsb03";
		this.userPW = "qweqwe";
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipFamilyJoinPage membershipJoin = (MembershipFamilyJoinPage) naverLoginPage.login(userID, userPW,Membership.FAMILY);
		membershipJoin.membershipJoin(User.NORMAL_YEAR);
	}
	
	@Test(description = "스튜던트 멤버십 월간 가입")
	public void TC_03_membership_student_Month_Join() {
		this.userID = "nvqa_rejoinsb04";
		this.userPW = "qweqwe";
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipStudentJoinPage membershipJoin = (MembershipStudentJoinPage) naverLoginPage.login(userID, userPW,Membership.STUDEMT);
		membershipJoin.membershipJoin(User.NORMAL_MONTH);
	}
	
	@Test(description = "스튜던트 멤버십 연간 가입")
	public void TC_04_membership_student_Year_Join() {
		this.userID = "nvqa_rejoinsb05";
		this.userPW = "qweqwe";
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipStudentJoinPage membershipJoin = (MembershipStudentJoinPage) naverLoginPage.login(userID, userPW,Membership.STUDEMT);
		membershipJoin.membershipJoin(User.NORMAL_YEAR);
	}
//	
	/*
	 * 1.원클릭 필수 약관 체크 미체크 케이스 추가
	 * 2.원클릭 아닌 경우 약관동의 체크 + 가입 + 키패드 입력 추가 (키패드 입력 기능 다음 실습으로 보류..?)
	 */
	
	@AfterMethod
	public void AfterMethod() {
		 driver.quit();
	}
}
