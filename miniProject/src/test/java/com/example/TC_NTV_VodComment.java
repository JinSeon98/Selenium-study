package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

/* 
 * MiniProject
 * 
 * NTV VOD영역 
 * 공통댓글 TC를 자동화로 구현해보자
 * 
 */

public class TC_NTV_VodComment {

  WebDriver driver;

  @BeforeMethod
  public void setup() throws IOException {

      System.setProperty("webdriver.chrome.driver",
              "./src/test/resources/drivers/chromedriver");
      driver = new ChromeDriver();
      // 창 크기 최대화 
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test(description = "댓글, 댓글수, 새로고침 확인")
  public void TC_01_댓글_타이틀_영역() {
	VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	vod.Comment_Head();
  }
  
  @Test(description = "누가 댓글을 썼을까요? | 성별, 연령대별 통계 | 접기^ 확인")
  public void TC_01_02_댓글_차트() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  vod.CommentChart();
  }
  
  @Test	(description = "댓글 입력창 ('주제와 무관한 댓글, 악플은 삭제될 수 있습니다.')")
  public void TC_01_03_플레이스홀더() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  vod.CommentInput();
  }
  
  @Test (description = "[BEST댓글] | V 전체댓글 | 도움말")
  public void TC_01_04_정렬() {
	 VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	 vod.Sort_option();
  }
  
  @Test (description = "[확인]선택 > 로그인페이지 노출")
  public void TC_08_01_비로그인_댓글() {
	 VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	 vod.noLoginComment();
  }
  
 @Test (description = "[확인]선택 > 로그인페이지 노출")
 public void TC_08_02_비로그인_답글() {
	VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	vod.noLoginReply();
 }
  
}
