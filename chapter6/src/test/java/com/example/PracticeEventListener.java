package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/*
 * EventListenr 클래스 생성 ; EventFiringWebDriver 클래스를 통해 발생하는 모든 이벤트 처리
 * AbstractWebDriverEventListener 클래스 상속하여 구현
 * 네비게이션, 얼럿 관련 이벤트 메서드 구현
 */

public class PracticeEventListener extends AbstractWebDriverEventListener {
	@Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Before Navigate To "+ url);
    }
	
	 @Override
	 public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
	    System.out.println("Before click on element");
	 }
	 
	 @Override
	  public void afterClickOn(WebElement webElement, WebDriver webDriver) {
	    System.out.println("After click on element");
	 }
	 
	 @Override
	public void beforeAlertDismiss(WebDriver driver) {
		System.out.println("Before Dismiss Alert. Alert Text is : "+driver.switchTo().alert().getText());
	}
	 
	@Override
    public void afterAlertDismiss(WebDriver webDriver) {
		System.out.println("팝업 취소");
    }
	
	@Override
	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Before Accept Alert. Alert Text is : "+driver.switchTo().alert().getText());
	}
	
	@Override
    public void afterAlertAccept(WebDriver webDriver) {
		System.out.println("팝업 확인");
    }
    
	@Override
    public void beforeNavigateBack(WebDriver driver) {
        System.out.println("Before Navigate Back.");
    }
    
	@Override
    public void onException(Throwable error, WebDriver webDriver) {
    	System.out.println("Error occurred: " + error);
    }

}
