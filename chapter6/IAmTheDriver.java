package chapter6;

import java.util.Scanner;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/* 
 * Chapter 6 실습(클래스)
 * 
 * 네이버 웹툰 페이지에서 chapter 6 내용을 바탕으로
 * 웹 드라이버 이벤트 수행해보자
 */

public class IAmTheDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C://Users/USER/eclipse-workspace/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		try {
			EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(driver);
			
			//EventListener 여러개 등록
			IAmTheEventListener eventListener = new IAmTheEventListener();
			IAmTheEventListener2 eventListener2 = new IAmTheEventListener2();
			eventFiringDriver.register(eventListener);
			eventFiringDriver.register(eventListener2);

			eventFiringDriver.get("https://comic.naver.com/index"); //네이버 웹툰 메인으로 이동
			eventFiringDriver.get("https://comic.naver.com/webtoon/detail?titleId=700139&no=1"); //웹툰 상세로 이동
						
			eventFiringDriver.navigate().refresh(); //새로고침
			
			eventFiringDriver.navigate().back(); //뒤로가기
			
			eventFiringDriver.navigate().forward(); //앞으로가기
			
			eventFiringDriver.findElement(By.className("u_cbox_write_wrap")).click(); //댓글 필드 선택
			Scanner sc = new Scanner(System.in);
			System.out.println("Alert Text is : "+driver.switchTo().alert().getText());
			System.out.print("Answer(Y/N) : ");
			String answer = sc.next();
			
			if(answer.equals("Y"))
				eventFiringDriver.switchTo().alert().accept(); //얼럿 확인
			else
				eventFiringDriver.switchTo().alert().dismiss(); //얼럿 취소
			
			/*WebElement comment = eventFiringDriver.findElement(By.className("u_cbox_contents")); 
			comment.sendKeys("댓글 수정"); //엘리먼트 값 수정 */
			
		} finally {
			driver.close();
			driver.quit();
		}
	}

}
