package tests;

import org.testng.annotations.Test;
import pages.*;
import org.testng.annotations.BeforeMethod;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

public class MailTest {
	WebDriver driver;

	String id = "nvqa_rejoinyj01";
	String pw = "qweqwe";

	LoginPage loginPage;
	MailMainPage mailMainPage;

	/*
	 * 메일 쓰기 테스트
	 * 1. 메일 메인 페이지 > 메일 쓰기 버튼 선택 
	 * 2. 메일 쓰기 페이지 > 받는 사람, 제목, 본문 작성 
	 * 3. 보내기 버튼 선택 
	 * 4. 메일 전송 완료 페이지 > 메일 전송 상태 문구 확인 (전송 성공 or 실패)
	 */
	@Test
	public void WriteMail() {

		String recipient = "nvqa_rejoinyj01@naver.com";
		String title = "메일 작성 테스트100";
		String content = "메일 작성 테스트 중 입니다.";

		WriteMailPage writeMailPage = mailMainPage.clickWriteMailBtn();
		DoneMailPage doneMailPage = writeMailPage.writeMail(recipient, title, content);

		assertThat(doneMailPage.getMailStatus()).isEqualTo("메일을 성공적으로 보냈습니다.");

		/*
		 * try { Thread.sleep(10000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	/*
	 * 내게 쓰기 테스트
	 * 1. 메일 메인 페이지 > 내게 쓰기 버튼 선택 
	 * 2. 내게 쓰기 페이지 > 제목, 본문 작성 
	 * 3. 보내기 버튼 선택 
	 * 4. 메일 전송 완료 페이지 > 메일 전송 상태 문구 확인 (전송 성공 or 실패)
	 */
	@Test
	public void WriteMailToMe() {
		String title = "메일 작성 테스트";
		String content = "메일 작성 테스트 중 입니다.";

		WriteMailToMePage writeMailToMePage = mailMainPage.clickWriteMailToMeBtn();
		DoneMailPage doneMailPage = writeMailToMePage.writeMail(title, content);

		assertThat(doneMailPage.getMailStatus()).isEqualTo("메일이 저장되었습니다.");
	}

	/*
	 * 단건 메일 삭제 테스트
	 * 1. 메일 메인 페이지 > 메일 목록 > 메일 선택 
	 * 2. 메일 End 페이지 > 삭제 버튼 선택 
	 * 3. 메일 메인 페이지 > 토스트 팝업 > 삭제 완료 문구 확인
	 */
	@Test
	public void deleteSingleMail() {
		String title = "메일 작성 테스트";

		MailEndPage mailEndPage = mailMainPage.clickMailTitle(title);
		mailMainPage = mailEndPage.deleteMail();

		assertThat(mailMainPage.getToastMessage()).isEqualTo("1개 메일을 휴지통으로 이동했습니다.");
	}

	/*
	 * 단건 메일 안읽음 테스트
	 * 1. 메일 메인 페이지 > 메일 목록 > 메일 선택 
	 * 2. 메일 End 페이지 > 안읽음 버튼 선택 
	 * 3. 안읽음 버튼 문구 > 읽음
	 */
	@Test
	public void unReadSingleMail() {
		String title = "메일 작성 테스트80";

		MailEndPage mailEndPage = mailMainPage.clickMailTitle(title);
		String btnTxt = mailEndPage.unReadMail();

		assertThat(btnTxt).isEqualTo("읽음");
	}

	/*
	 * 다건 메일 삭제 테스트
	 * 1. 메일 메인 페이지 > 메일 목록 > 메일 체크박스 선택
	 * 2. 삭제 버튼 선택
	 * 3. 토스트 팝업 > 삭제 완료 문구 확인
	 */
	@Test
	public void deleteMultipleMails() {
		String[] titles = { "메일 작성 테스트76", "메일 작성 테스트90", "메일 작성 테스트86", "메일 작성 테스트99" };

		mailMainPage.clickMailCheckBox(titles);
		mailMainPage.clickDeleteBtn();

		assertThat(mailMainPage.getToastMessage()).isEqualTo(titles.length + "개 메일을 휴지통으로 이동했습니다.");
	}

	/*
	 * 다건 메일 읽음 테스트
	 * 1. 메일 메인 페이지 > 메일 목록 > 메일 체크박스 선택
	 * 2. 읽음 버튼 선택
	 * 3-1. 안읽은 메일 1개 이상 > 읽음 버튼 문구 > 읽음
	 * 3-2. 모두 읽은 메일 > 읽음 버튼 문구 > 안읽음
	 */
	@Test
	public void ReadMultipleMails() {
		String[] titles = { "메일 작성 테스트100", "메일 작성 테스트99", "메일 작성 테스트98", "메일 작성 테스트97" };

		mailMainPage.clickMailCheckBox(titles);
		String beforeReadBtn = mailMainPage.getReadBtnTxt();
		mailMainPage.clickReadBtn();
		String afterReadBtn = mailMainPage.getReadBtnTxt();
		
		if(beforeReadBtn == "읽음")
			assertThat(afterReadBtn).isEqualTo("안읽음");
		else if(beforeReadBtn == "안읽음")			
			assertThat(afterReadBtn).isEqualTo("읽음");
	}

	/*
	 * 메일 검색 테스트
	 * 1. 메일 메인 페이지 > 검색창 > 검색어 입력
	 * 2. 검색 버튼 선택
	 * 3-1. 검색 문구 확인(키워드)
	 * 3-2. 검색어가 없을 경우 > 팝업 문구 > 검색어를 입력하세요
	 */
	@Test
	public void searchMail() {
		String keyword = "";
		
		mailMainPage.searchMail(keyword);

		if(keyword == "")
			 assertThat(driver.findElement(By.className("layer_message")).getText()).isEqualTo("검색어를 입력하세요");
		else 
			assertThat(driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[3]/div[1]/em")).getText()).isEqualTo(keyword);
	}
	
	/*
	 * 메일 메인에서 북마크 테스트
	 * 1. 메일 메인 페이지 > 메일 목록 > 북마크 버튼 선택
	 * 2-1. 북마크 버튼 체크된 경우 > 북마크 버튼 문구 > 중요 메일 선택됨
	 * 2-2. 북마크 버튼 해제된 경우 > 북마크 버튼 문구 > 중요 메일	
	 */
	@Test
	public void bookmarkMailMain() {
		String title = "메일 작성 테스트80";
				
		String beforeBookmark = mailMainPage.getBookmarkBtnTxt(title);
		mailMainPage.clickBookmarkBtn(title);
		String afterBookmark = mailMainPage.getBookmarkBtnTxt(title);

		if(beforeBookmark == "중요 메일")
			assertThat(afterBookmark).isEqualTo("중요 메일 선택됨");
		else if(beforeBookmark == "중요 메일 선택됨")			
			assertThat(afterBookmark).isEqualTo("중요 메일");
	}
	
	/*
	 * 메일 End에서 북마크 테스트
	 * 1. 메일 메인 페이지 > 메일 목록 > 메일 선택 
	 * 2. 메일 End 페이지 > 북마크 버튼 선택 
	 * 3-1. 북마크 버튼 체크된 경우 > 북마크 버튼 문구 > 중요 메일 선택됨
	 * 3-2. 북마크 버튼 해제된 경우 > 북마크 버튼 문구 > 중요 메일
	 */
	@Test
	public void bookmarkMailEnd() {
		String title = "메일 작성 테스트80";

		MailEndPage mailEndPage = mailMainPage.clickMailTitle(title);
		String beforeBookmark = mailEndPage.getBookmarkTxt();
		mailEndPage.bookmarkMail();
		String afterBookmark = mailEndPage.getBookmarkTxt();

		
		if(beforeBookmark == "중요 메일")
			assertThat(afterBookmark).isEqualTo("중요 메일 선택됨");
		else if(beforeBookmark == "중요 메일 선택됨")			
			assertThat(afterBookmark).isEqualTo("중요 메일");
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
