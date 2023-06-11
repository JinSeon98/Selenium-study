package tests;

import org.testng.annotations.Test;
import pages.*;
import org.testng.annotations.BeforeMethod;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

public class MailTest {
	WebDriver driver;

	String id = "nvqa_rejoinyj01";
	String pw = "qweqwe";
	
	LoginPage loginPage;
	MailMainPage mailMainPage;
	
	@Test
	public void WriteMail() {
		String recipient = "nvqa_rejoinyj01@naver.com";
		String title = "메일 작성 테스트";
		String content = "메일 작성 중 입니다.";
		
		WriteMailPage writeMailPage = mailMainPage.clickWriteMailBtn();
		SendMailSuccessPage sendMailSuccessPage = writeMailPage.writeMail(recipient, title, content);
				
		assertThat(sendMailSuccessPage.getMailStatus()).isEqualTo("메일을 성공적으로 보냈습니다.");
	}
	
	@Test
	public void WriteMailToMe() {
		String title = "메일 작성 테스트";
		String content = "메일 작성 중 입니다.";
		
		WriteMailToMePage writeMailToMePage = mailMainPage.clickWriteMailToMeBtn();
		SendMailSuccessPage sendMailSuccessPage = writeMailToMePage.writeMail(title, content);
				
		assertThat(sendMailSuccessPage.getMailStatus()).isEqualTo("메일이 저장되었습니다.");
	}
	
	@Test
	public void deleteSingleMail() {
		String title = "메일 작성 테스트";

		MailEndPage mailEndPage = mailMainPage.clickMail(title);
		mailMainPage = mailEndPage.deleteMail();
		
		assertThat(mailMainPage.getToastMessage()).isEqualTo("1개 메일을 휴지통으로 이동했습니다.");
	}
	
	@Test
	public void unReadSingleMail() {
		String title = "똑!소리나는 네이버 메일 가입을 환영합니다.";

		MailEndPage mailEndPage = mailMainPage.clickMail(title);
		String btnText = mailEndPage.unReadMail();
		
		assertThat(btnText).isEqualTo("읽음");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C://Users/USER/eclipse-workspace/chromedriver.exe");

		// 웹드라이버 세션 초기화
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 묵시적 대기 시간

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		mailMainPage = loginPage.login(id, pw);
	}

	@AfterMethod
	public void afterMethod() {
	}

}
