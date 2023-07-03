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
	/** ����� ���� ID*/
	String userID ;
	/** ����� ���� PW*/
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
	
	/** MembershipJoin���� �����ϵ��� �Űܾ��ϳ�.. �����*/
	@AfterMethod
	public void initMembership() {
//		iimsMemPage iims = PageFactory.initElements(driver, iimsMemPage.class);	
//		iims.login(this.iimsID, this.iimsPW);
//		iims.deleteMembership(membershipID);
	}

	@Test(description = "�йи� ����� ���� ����")
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
	
	@Test(description = "�йи� ����� ���� ����")
	public void TC_02_membership_family_Year_Join() {
		this.userID = "nvqa_rejoinsb03";
		this.userPW = "qweqwe";
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipFamilyJoinPage membershipJoin = (MembershipFamilyJoinPage) naverLoginPage.login(userID, userPW,Membership.FAMILY);
		membershipJoin.membershipJoin(User.NORMAL_YEAR);
	}
	
	@Test(description = "��Ʃ��Ʈ ����� ���� ����")
	public void TC_03_membership_student_Month_Join() {
		this.userID = "nvqa_rejoinsb04";
		this.userPW = "qweqwe";
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipStudentJoinPage membershipJoin = (MembershipStudentJoinPage) naverLoginPage.login(userID, userPW,Membership.STUDEMT);
		membershipJoin.membershipJoin(User.NORMAL_MONTH);
	}
	
	@Test(description = "��Ʃ��Ʈ ����� ���� ����")
	public void TC_04_membership_student_Year_Join() {
		this.userID = "nvqa_rejoinsb05";
		this.userPW = "qweqwe";
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipStudentJoinPage membershipJoin = (MembershipStudentJoinPage) naverLoginPage.login(userID, userPW,Membership.STUDEMT);
		membershipJoin.membershipJoin(User.NORMAL_YEAR);
	}
//	
	/*
	 * 1.��Ŭ�� �ʼ� ��� üũ ��üũ ���̽� �߰�
	 * 2.��Ŭ�� �ƴ� ��� ������� üũ + ���� + Ű�е� �Է� �߰� (Ű�е� �Է� ��� ���� �ǽ����� ����..?)
	 */
	
	@AfterMethod
	public void AfterMethod() {
		 driver.quit();
	}
}
