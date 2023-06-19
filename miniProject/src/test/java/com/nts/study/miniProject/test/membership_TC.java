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
	/** ����� ���� ID*/
	String userID = "nvqa_rejoinsb02";
	/** ����� ���� PW*/
	String userPW = "qweqwe";
	/** IIMS ������ ID*/
	String iimsID = "NT11940";
	/** IIMS ������ PW*/
	String iimsPW = "abcd";

	@BeforeMethod
	public void beforeClass() {

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

	@Test(description = "�йи� ����� ��Ŭ�� ���� ����")
	public void TC_01_membership_Month_Join() {
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipJoinPage membershipJoin = naverLoginPage.login(userID, userPW);
		membershipJoin.membershipJoinMonth(User.ONCLICK_FAMILY);
	}
	
	@Test(description = "�йи� ����� ��Ŭ�� ���� ����")
	public void TC_02_membership_Year_Join() {
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipJoinPage membershipJoin = naverLoginPage.login(userID, userPW);
		membershipJoin.membershipJoinMonth(User.ONCLICK_STUDENT);
	}
	
	@Test(description = "��Ʃ��Ʈ ����� ��Ŭ�� ���� ����")
	public void TC_03_student_membership_Month_Join() {
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipJoinPage membershipJoin = naverLoginPage.login(userID, userPW);
		membershipJoin.membershipJoinMonth(User.NORMAL_FAMILY);
	}
	
	@Test(description = "��Ʃ��Ʈ ����� ��Ŭ�� ���� ����")
	public void TC_04_student_membership_Year_Join() {
		NaverHomeLoginPage naverLoginPage = PageFactory.initElements(driver, NaverHomeLoginPage.class);
		MembershipJoinPage membershipJoin = naverLoginPage.login(userID, userPW);
		membershipJoin.membershipJoinYear(User.NORMAL_STUDENT);
	}
	
	/*
	 * 1.��Ŭ�� �ʼ� ��� üũ ��üũ ���̽� �߰�
	 * 2.��Ŭ�� �ƴ� ��� ������� üũ + ���� + Ű�е� �Է� �߰� (Ű�е� �Է� ��� ���� �ǽ����� ����..?)
	 */
	
	@AfterMethod
	public void afterClass() {
		 driver.quit();
	}
}
