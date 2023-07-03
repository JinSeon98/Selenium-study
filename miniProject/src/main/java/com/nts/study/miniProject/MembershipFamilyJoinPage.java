package com.nts.study.miniProject;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.sikuli.script.Pattern;
//import org.sikuli.script.Screen;

public class MembershipFamilyJoinPage implements IMembership {
	
	WebDriver driver;
	Boolean onclickUser = false;
	public static String parentWindow;
	
	
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
	
	/**
	 * ����� ���� �����ϱ� ��ư
	 */
	@FindBy(id="submitButton")
	WebElement submitButton;
	

	
	
	public MembershipFamilyJoinPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://nid.naver.com/membership/join");
	}
	
	/**
	 * @param userId ����� ���� ���̹� ID
	 * @param userPw ����� ���� ���̹� PW
	 */
	public void membershipJoin(User usertype) {
		
		if(usertype == User.REJOIN_MONTH) {
			oneClickMonthMembershipJoin();
		}
		else if(usertype == User.REJOIN_YEAR) {
			oneClickYearMembershipJoin();
		}
		else if(usertype == User.NORMAL_MONTH) {
			normalMonthMembershipJoin();
		}
		else if(usertype == User.NORMAL_YEAR) {
			normalYearMembershipJoin();
		}
	}
	
	private void oneClickYearMembershipJoin() {
		joinButton.click();
		plusAgree.click();
	}

	private void oneClickMonthMembershipJoin() {
		joinButton.click();
		submitButton.click();
			
	}
	
	/**
	 * ���� ��й�ȣ �Է� �޼ҵ�
	 * @throws FindFailed 
	 *
	 * @throws IOException
	 */
//	public void inputPayPW()  {
//		for(String winHandle : driver.getWindowHandles()){
//		    driver.switchTo().window(winHandle);
//		}
//		
//		
//		Screen screen = new Screen();
//		
//		Pattern keypad_1 = new Pattern("image/payKeyPad/keypad_1.png");
//		Pattern keypad_2 = new Pattern("image/payKeyPad/keypad_2.png");
//		Pattern keypad_3 = new Pattern("image/payKeyPad/keypad_3.png");
//		
//		screen.find(keypad_1);
//		screen.wait(keypad_1,5).click();
//		screen.wait(keypad_1,5).click();
//		
//		screen.find(keypad_2);
//		screen.wait(keypad_2,5).click();
//		screen.wait(keypad_2,5).click();
//		
//		screen.find(keypad_3);
//		screen.wait(keypad_3,5).click();
//		screen.wait(keypad_3,5).click();
//		
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
	
	//TODO
	private void normalMonthMembershipJoin() {
		joinButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		agreeConditions();
		submitButton.click();

		pay();
	}

	/**
	 * �Ϲ� ��� ���� > ������� + ���̰���
	 */
	private void normalYearMembershipJoin() {
		joinButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("annual")).click();
		agreeConditions();
		submitButton.click();

		pay();
	}

	/**
	 * ���� ���� â ��ȯ > Ŭ�� �� ���� 
	 */
	//TODO ���� ���� Ŭ���� ����� �� ������
	private void pay() {
		this.parentWindow = driver.getWindowHandle();

		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		//TODO ������Ʈ ã�� �ڵ� ���� �ʿ�
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div[4]/div[2]/ul/li[3]/div[1]/div/label")).click();
		driver.findElement(By.className("SubmitButton_article__Z2VjB")).click();
		
		//TODO ����Ű�е� �ִ� �ڵ� �ʿ�
		 driver.switchTo().window(parentWindow);
	}

	private void agreeConditions() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#joinAgreePopup > div > div.terms_area > div > div.terms_all > div > label")).click();
	}
}
