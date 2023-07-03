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
	 * 가입하기 버튼
	 */
	@FindBy(id ="joinButton")
	WebElement joinButton;
	
	/**
	 * 재가입 버튼
	 */
	@FindBy(id="plusAgree")
	WebElement plusAgree;
	
	/**
	 * 멤버십 혜택 시작하기 버튼
	 */
	@FindBy(id="submitButton")
	WebElement submitButton;
	

	
	
	public MembershipFamilyJoinPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://nid.naver.com/membership/join");
	}
	
	/**
	 * @param userId 멤버십 가입 네이버 ID
	 * @param userPw 멤버십 가입 네이버 PW
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
	 * 페이 비밀번호 입력 메소드
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
	 * 일반 모달 가입 > 약관동의 + 페이결제
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
	 * 페이 결제 창 전환 > 클릭 앤 결제 
	 */
	//TODO 페이 따로 클래스 만드는 게 좋을듯
	private void pay() {
		this.parentWindow = driver.getWindowHandle();

		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		//TODO 엘리먼트 찾는 코드 변경 필요
		driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div[4]/div[2]/ul/li[3]/div[1]/div/label")).click();
		driver.findElement(By.className("SubmitButton_article__Z2VjB")).click();
		
		//TODO 페이키패드 넣는 코드 필요
		 driver.switchTo().window(parentWindow);
	}

	private void agreeConditions() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#joinAgreePopup > div > div.terms_area > div > div.terms_all > div > label")).click();
	}
}
