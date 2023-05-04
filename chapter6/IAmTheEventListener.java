package chapter6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

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
		System.out.println("Before Dismiss Alert. Alert Text is : "+driver.switchTo().alert().getText());
	}
	
	@Override
	public void afterAlertAccept(WebDriver driver) {
		System.out.println("After Dismiss Alert.");
	}
}
