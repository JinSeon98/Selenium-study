package com.practice;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

/* 
 * Chapter 3 실습
 * 
 * 네이버 TV 페이지에서 chapter 3 내용을 바탕으로
 * java 8 스트림 API를 사용하여 테스트에 활용해보자
 */

public class PracticeChapter3 {
	
	WebDriver driver;
	
	/*
	 * 인기영상 TOP 100 영상 중 keyword가 포함된 채널이 개수 세기
	 * Stream.filter(), Strema.count() 사용
	 */
	@Test
	public void countPopularVideo() {
		driver.findElement(By.xpath("//*[@id=\"menuScrollBox\"]/ul/li[2]/a")).click(); //인기영상 페이지로 이동

		List<WebElement> channel = driver.findElements(By.className("chn")); //채널 리스트
		String keyword="미스터트롯2";
		
		long cnt = channel.stream().filter(item->item.getText().contains(keyword)).count(); //keyword가 포함된 채널 필터링과 개수 세기
		System.out.println("TOP100에 올라간 \""+keyword+"\" 채널 동영상 수: "+cnt);
	}
	
	/*
	 * keyword 검색 화면에서 재생수가 가장 많은 동영상과 좋아요수가 가장 적은 동영상 출력하기
	 * Stream.min(), Stream.max() 사용
	 */
	@Test
	public void compareVideo() {
		//keyword 검색
		String keyword  = "이찬원";
		driver.findElement(By.id("searchQuery")).sendKeys(keyword);
		driver.findElement(By.id("searchButton")).click();
		driver.findElement(By.xpath("//*[@id=\"searchClip\"]/div[1]/a")).click();
		
		WebElement clipList = driver.findElement(By.id("clip_list"));
		List<WebElement> clipCard = clipList.findElements(By.className("inner")); //동영상 카드 리스트
		List<Video> videoInfo = new ArrayList<>(); //동영상 정보 리스트
		
		for(WebElement we : clipCard) {
			WebElement titleWE = we.findElement(By.cssSelector("dl > dt > a"));
			WebElement channelWE = we.findElement(By.cssSelector("dl > dd > span.ch_txt > a"));
			WebElement viewWE = we.findElement(By.cssSelector("dl > dd > span.cds_ifc.cnp"));
			WebElement likeWE = we.findElement(By.cssSelector("dl > dd > span.cds_ifc.bch"));
			
			//string -> int로 변환할 수 있도록 형태 수정
			String view = viewWE.getText().substring(5);
			view = view.replaceAll("[^\\w+]", "");
			String like = likeWE.getText().substring(6);
			like = like.replaceAll("[^\\w+]", "");

			videoInfo.add(new Video(titleWE.getText(), channelWE.getText(), Integer.parseInt(view), Integer.parseInt(like)));
		}

		DecimalFormat decFormat = new DecimalFormat("###,###"); //숫자 천단위 끊어읽기

		Video maxViewVideo = videoInfo.stream().max(Comparator.comparing(item->item.getView())).get(); //재생수가 가장 많은 동영상 저장
				
		System.out.println("[\""+keyword+"\"(으)로 검색한 동영상 중 가장 재생 수가 많은 동영상]\n");
		System.out.println("제목 : "+maxViewVideo.getTitle());
		System.out.println("채널명 : "+maxViewVideo.getChannel());
		System.out.println("재생수 : "+decFormat.format(maxViewVideo.getView()));
		System.out.println("좋아요수 : "+decFormat.format(maxViewVideo.getLike())+"\n");
		
		Video minLikeVideo = videoInfo.stream().min(Comparator.comparing(item->item.getLike())).get(); //좋아요수가 가장 적은 동영상 저장
		
		System.out.println("[\""+keyword+"\"(으)로 검색한 동영상 중 가장 좋아요 수가 적은 동영상]\n");
		System.out.println("제목 : "+minLikeVideo.getTitle());
		System.out.println("채널명 : "+minLikeVideo.getChannel());
		System.out.println("재생수 : "+decFormat.format(minLikeVideo.getView()));
		System.out.println("좋아요수 : "+decFormat.format(minLikeVideo.getLike()));
	}
	
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users/USER/eclipse-workspace/chromedriver.exe");

		// 웹드라이버 세션 초기화
		driver = new ChromeDriver();
		driver.get("https://tv.naver.com/"); //네이버 TV url
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //묵시적 대기 시간
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}