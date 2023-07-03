package com.nts.study.miniProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class iimsMemPage {

    WebDriver driver;
	/** IIMS 관리툴 ID*/
	String iimsID = "NT11940";
	/** IIMS 관리툴 PW*/
	String iimsPW = "asd!";


    @FindBy(id = "user-id")
    WebElement id;

    @FindBy(id = "user-pw")
    WebElement password;

    @FindBy(id = "login-btn")
    WebElement submit;
    

    public iimsMemPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://alpha-iims.navercorp.com/view/svc/main?svcId=MEM&roleId=00002&menuId=MEM_000004");
    }

    public void login() {
    	id.sendKeys(iimsID);
        password.sendKeys(iimsPW);
        submit.click();
    }
    
    /**
     * 
     * @param membershipID
     * @return iims 관리툴 객체 전달
     * 
     * 관리툴에서 멤버십 환불 처리 
     * 1. 사용자 정보 조회
     * 2. 환불처리
     * 3. 사용자 정보 삭제
     * 4. ndi 삭제
     */
    public iimsMemPage deleteMembership(String membershipID) {
    	
    	searchMembership(membershipID);
    	refundMembership(membershipID);
    	deleteUserInfo();
    	deleteNdi();
    	
        return PageFactory.initElements(driver,
        		iimsMemPage.class);
    }
   
	/**
     * 멤버십 Ndi 삭제 처리
     */
    private void deleteNdi() {
		
    	WebElement deleteUserInfo = driver.findElement(By.cssSelector("#deleteNdi"));
    	deleteUserInfo.click();
    	/*1초 딜레이 필요?*/
    	driver.switchTo().alert().accept();

    	/*1초 딜레이 필요? 얼럿 성공인지 확인 판단 추가 필요*/
	}

	/**
     * 멤버십 사용자 정보 삭제 처리
     */
    private void deleteUserInfo() {
    	
    	WebElement deleteUserInfo = driver.findElement(By.cssSelector("#deleteUser"));
    	deleteUserInfo.click();
    	/*1초 딜레이 필요?*/
    	driver.switchTo().alert().accept();

    	/*1초 딜레이 필요? 얼럿 성공인지 확인 판단 추가 필요*/
		driver.switchTo().alert().accept();
	}

	/**
     * @param membershipID 멤버십 사용자 ID
     * 
     * 멤버십 강제 환불처리 및 얼럿 처리
     */
    private void refundMembership(String membershipID) {
		
		/** 멤버십 구독정보 선택 
		 * -> 없을 때 처리 작업해야함
		 * */ 
		driver.findElement(By.cssSelector("/html/body/div/section[2]/table/tbody/tr[4]/td/a"));
		
		/*1초 딜레이 필요?*/
		
		/** 강제 전액 환불*/
		driver.findElement(By.id("requestFullRefund")).click();
		driver.switchTo().alert().accept();
		
		/*1초 딜레이 필요? 얼럿 성공인지 확인 판단 추가 필요*/
		driver.switchTo().alert().accept();
		
		/** 멤버십 사용자ID 삭제*/
		driver.findElement(By.cssSelector("#deleteNdi")).click();;
    	
    
    }

    /**
     * @param membershipID 멤버십 사용자 ID
     * 
     *  iims > 관리도구 > 사용자관리 > 멤버십 사용자 ID 입력 후 조회
     */
	private void searchMembership(String membershipID) {
		/** iims 툴바 > 관리도구*/
    	WebElement iimsToolBar = driver.findElement(By.cssSelector("#carousel > div.owl-stage-outer > ul > li:nth-child(4) > div > a"));
    	iimsToolBar.click();
    	
    	/** 사용자관리 메뉴*/
    	WebElement userManagerMenu = driver.findElement(By.cssSelector("#menu > li:nth-child(2) > a > span"));
    	userManagerMenu.click();

    	/*1초 딜레이 필요?*/
    	
    	driver.switchTo().frame("svc-iframe");
    	/** 멤버십 사용자ID 입력*/
    	WebElement membershipIdTextBox = driver.findElement(By.cssSelector(".form-group>#userId"));
    	membershipIdTextBox.sendKeys(membershipID);

    	/** 멤버십 사용자ID 조회*/
		driver.findElement(By.cssSelector("#search_form > button"));
	}
    
}
