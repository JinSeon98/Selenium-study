package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailMainPage {
	WebDriver driver;

	@FindBy(css = ".lnb_task > a")
	WebElement writeBtn;

	@FindBy(css = ".lnb_task > a.item.button_write_to_me")
	WebElement writeToMeBtn;

	@FindBy(xpath = "//*[@id=\"mail_list_wrap\"]/ul")
	WebElement mailContainer;

	@FindBy(xpath = "//*[@id=\"content\"]/div[3]/div[2]/div[1]/div[3]/button")
	WebElement readBtn;

	@FindBy(xpath = "//*[@id=\"content\"]/div[3]/div[2]/div[1]/div[4]/button")
	WebElement deleteBtn;

	@FindBy(className = "menu_count")
	WebElement UnReadBtn;

	@FindBy(className = "pagination")
	WebElement pagination;

	@FindBy(id = "search")
	WebElement searchBar;

	@FindBy(xpath = "//*[@id=\"search-bar\"]/div/button")
	WebElement searchBtn;

	public MailMainPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://mail.naver.com/v2/folders/0/all");
	}

	// 메일 쓰기 버튼 선택하여 메일 쓰기 페이지로 이동
	public WriteMailPage clickWriteMailBtn() {
		writeBtn.click();

		return PageFactory.initElements(driver, WriteMailPage.class);
	}

	// 내게 쓰기 버튼 선택하여 내게 쓰기 페이지로 이동
	public WriteMailToMePage clickWriteMailToMeBtn() {
		writeToMeBtn.click();

		return PageFactory.initElements(driver, WriteMailToMePage.class);

	}

	// 동일한 제목을 가진 메일 엘레먼트 찾기
	// 현재 첫번째 메일 목록 페이지에서만 가능(다른 페이지로 이동 불가)
	public WebElement findMail(String title) {
		List<WebElement> allMailTitle = mailContainer.findElements(By.className("mail"));

		for (WebElement ele : allMailTitle) {
			WebElement mailTitle = ele.findElement(By.cssSelector(".mail_title > a > span.text"));

			if (mailTitle.getText().equals(title)) {
				return ele;
			}
		}
		return null;
	}

	// 메일 제목 선택하여 메일 End 페이지로 이동
	public MailEndPage clickMailTitle(String title) {
		WebElement mail = findMail(title);

		mail.click();

		return PageFactory.initElements(driver, MailEndPage.class);
	}

	// 메일 제목 String 배열을 받아 체크 박스 선택
	public void clickMailCheckBox(String[] title) {
		for (String s : title) {
			WebElement mailCB = findMail(s).findElement(By.cssSelector(".button_checkbox_wrap > input"));

			Actions actions = new Actions(driver);
			actions.moveToElement(mailCB).click().build().perform();
		}
	}

	public void clickBookmarkBtn(String title) {
		WebElement bookmarkBtn = findMail(title).findElement(By.cssSelector(".toggle_bookmark_wrap > label"));

		bookmarkBtn.click();
	}

	// 체크한 메일 읽기 버튼 선택
	public void clickReadBtn() {
		readBtn.click();
	}

	// 체크한 메일 삭제 버튼 선택
	public void clickDeleteBtn() {
		deleteBtn.click();
	}

	// 토스트 메시지 문구 출력
	public String getToastMessage() {
		return driver.findElement(By.cssSelector(".toast_message > span")).getText();
	}

	// 검색창에 검색어 입력 후 검색 버튼 선택
	public void searchMail(String keyword) {
		searchBar.sendKeys(keyword);
		searchBtn.click();
	}

	// 읽음 버튼 문구 출력
	public String getReadBtnTxt() {
		return readBtn.getText();
	}

	// 읽음 버튼 문구 출력
	public String getBookmarkBtnTxt(String title) {
		WebElement bookmarkBtn = findMail(title).findElement(By.cssSelector(".toggle_bookmark_wrap > label"));
		
		return bookmarkBtn.getText();
	}
}
