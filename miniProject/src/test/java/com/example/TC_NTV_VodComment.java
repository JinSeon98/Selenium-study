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
  
  @Test(description = "댓글 영역 ")
  public void TC_01_댓글영역() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  //1. 댓글, 댓글수, 새로고침 확인
	  vod.Comment_Head();
	  
	  //2. 누가 댓글을 썼을까요? | 성별, 연령대별 통계 | 접기^ 확인
	  vod.CommentChart();
	  
	  //3. 댓글 입력창 ('주제와 무관한 댓글, 악플은 삭제될 수 있습니다.')
	  vod.CommentInput();
	  
	  //4. [BEST댓글] | V 전체댓글 | 도움말
	  vod.Sort_option();
	  
	  //5. 닉네임 | (BEST) 댓글내용 | 등록시간 | [신고] | [답글 n] | [공감 n] | [비공감 n]
	  vod.commentBox();
  }
  
  @Test (description = "페이징")
  public void TC_01_06_페이징() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  vod.paging();
  }
  
  @Test (description = "새로고침 확인")
  public void TC_02_새로고침() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  vod.refresh();
  }
  
  @Test (description = "통계영역 접힘 or 펼쳐짐")
  public void TC_03_통계보기() {
	 VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	 vod.chart();
  }
  
  @Test (description = "Best댓글 선택 > [전체 댓글 더보기] 노출확인 및 선택 ")
  public void TC_04_Best댓글() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  vod.Best();
  }
  
  @Test (description = "전체 댓글 선택 > 하단 페이징 ")
  public void TC_05_전체댓글() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  vod.All();
  }
  
  @Test (description = "우측 도움말 선택 > 안내레이어 문구 확인 ")
  public void TC_06_도움말() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  vod.help();
  }
  
  @Test (description = "답글 펼치기 > 리스트 노출, 답글접기 확인 ")
  public void TC_07_답글펼치기() {
	  VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	  vod.Reply();
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
 
 @Test (description = "댓글 400자 입력")
 public void TC_09_01_댓글입력() {
	VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	vod.CommentWrite();
 }

 @Test (description = "신고")
 public void TC_10_신고하기() {
	VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	vod.Report();
 }
 
 @Test (description = "[확인]선택 > 로그인페이지 노출")
 public void TC_11_비로그인_예외상황() {
	 VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	 vod.noLoginException();
 }
 
 @Test (description = "공감 선택된 상태 > 비공감 선택")
 public void TC_12_공감() {
	 VodEnd vod = PageFactory.initElements(driver, VodEnd.class);
	 vod.recomm();
 }
}
