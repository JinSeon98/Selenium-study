package chapter6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/*
 * EventListenr 클래스 생성 ; EventFiringWebDriver 클래스를 통해 발생하는 모든 이벤트 처리
 * AbstractWebDriverEventListener 클래스 상속하여 구현
 * 네비게이션, 얼럿 관련 이벤트 메서드 구현
 */

public class IAmTheEventListener extends AbstractWebDriverEventListener {
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before Navigate To " +url + " and Current url is: "+ driver.getCurrentUrl());
	}
	
	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("After Navigate To " +url + " and Current url is: "+ driver.getCurrentUrl());
	}
	
	@Override
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Before Navigate Back. Right now I'm at " + driver.getCurrentUrl());
	}
	
	@Override
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("After Navigate Back. Right now I'm at " + driver.getCurrentUrl());
	}
	
	@Override
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Before Navigate Forward. Right now I'm at " + driver.getCurrentUrl());
	}
	
	@Override
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("After Navigate Forward. Right now I'm at " + driver.getCurrentUrl());
	}
	
	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		System.out.println("Before Navigate Refresh");
	}
	
	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		System.out.println("After Navigate Refresh");
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Before Accept Alert. Alert Text is : "+driver.switchTo().alert().getText());
	}
	
	@Override
	public void afterAlertAccept(WebDriver driver) {
		System.out.println("After Accept Alert.");
	}
	
	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		System.out.println("Before Dismiss Alert. Alert Text is : "+driver.switchTo().alert().getText());
	}
	
	@Override
	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("After Dismiss Alert.");
	}
}
