package com.nts.study.miniProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class iimsMemPage {

    WebDriver driver;
	/** IIMS ������ ID*/
	String iimsID = "NT11940";
	/** IIMS ������ PW*/
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
     * @return iims ������ ��ü ����
     * 
     * ���������� ����� ȯ�� ó�� 
     * 1. ����� ���� ��ȸ
     * 2. ȯ��ó��
     * 3. ����� ���� ����
     * 4. ndi ����
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
     * ����� Ndi ���� ó��
     */
    private void deleteNdi() {
		
    	WebElement deleteUserInfo = driver.findElement(By.cssSelector("#deleteNdi"));
    	deleteUserInfo.click();
    	/*1�� ������ �ʿ�?*/
    	driver.switchTo().alert().accept();

    	/*1�� ������ �ʿ�? �� �������� Ȯ�� �Ǵ� �߰� �ʿ�*/
	}

	/**
     * ����� ����� ���� ���� ó��
     */
    private void deleteUserInfo() {
    	
    	WebElement deleteUserInfo = driver.findElement(By.cssSelector("#deleteUser"));
    	deleteUserInfo.click();
    	/*1�� ������ �ʿ�?*/
    	driver.switchTo().alert().accept();

    	/*1�� ������ �ʿ�? �� �������� Ȯ�� �Ǵ� �߰� �ʿ�*/
		driver.switchTo().alert().accept();
	}

	/**
     * @param membershipID ����� ����� ID
     * 
     * ����� ���� ȯ��ó�� �� �� ó��
     */
    private void refundMembership(String membershipID) {
		
		/** ����� �������� ���� 
		 * -> ���� �� ó�� �۾��ؾ���
		 * */ 
		driver.findElement(By.cssSelector("/html/body/div/section[2]/table/tbody/tr[4]/td/a"));
		
		/*1�� ������ �ʿ�?*/
		
		/** ���� ���� ȯ��*/
		driver.findElement(By.id("requestFullRefund")).click();
		driver.switchTo().alert().accept();
		
		/*1�� ������ �ʿ�? �� �������� Ȯ�� �Ǵ� �߰� �ʿ�*/
		driver.switchTo().alert().accept();
		
		/** ����� �����ID ����*/
		driver.findElement(By.cssSelector("#deleteNdi")).click();;
    	
    
    }

    /**
     * @param membershipID ����� ����� ID
     * 
     *  iims > �������� > ����ڰ��� > ����� ����� ID �Է� �� ��ȸ
     */
	private void searchMembership(String membershipID) {
		/** iims ���� > ��������*/
    	WebElement iimsToolBar = driver.findElement(By.cssSelector("#carousel > div.owl-stage-outer > ul > li:nth-child(4) > div > a"));
    	iimsToolBar.click();
    	
    	/** ����ڰ��� �޴�*/
    	WebElement userManagerMenu = driver.findElement(By.cssSelector("#menu > li:nth-child(2) > a > span"));
    	userManagerMenu.click();

    	/*1�� ������ �ʿ�?*/
    	
    	driver.switchTo().frame("svc-iframe");
    	/** ����� �����ID �Է�*/
    	WebElement membershipIdTextBox = driver.findElement(By.cssSelector(".form-group>#userId"));
    	membershipIdTextBox.sendKeys(membershipID);

    	/** ����� �����ID ��ȸ*/
		driver.findElement(By.cssSelector("#search_form > button"));
	}
    
}
