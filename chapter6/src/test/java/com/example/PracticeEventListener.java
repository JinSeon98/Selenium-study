package com.example;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PracticeEventListener extends AbstractWebDriverEventListener {
	@Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Before Navigate To "+ url);
    }
	
	/*@Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("After Navigate To: " + url
                + " and Current url is: " + driver.getCurrentUrl());
    }*/
	@Override
    public void afterAlertDismiss(WebDriver webDriver) {
		System.out.println("팝업 취소 선택");
    }
	@Override
    public void afterAlertAccept(WebDriver webDriver) {
		System.out.println("팝업 확인 선택완료 -> " 
						 + "현재 주소 : " + webDriver.getCurrentUrl() );
    }
    @Override
    public void beforeNavigateBack(WebDriver driver) {
        System.out.println("Before Navigate Back. Right now I'm at "
                + driver.getCurrentUrl());
    }
}
