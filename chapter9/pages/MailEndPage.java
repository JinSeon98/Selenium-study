package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailEndPage {
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div[1]/button")
	WebElement deleteBtn;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div[3]/button")
	WebElement unReadBtn;
	
	@FindBy(xpath = "//*[@id=\"mail_read_scroll_view\"]/div/div[1]/h4/span[1]/label")
	WebElement bookmark;
	
	public MailEndPage(WebDriver driver) {
		this.driver = driver;
		driver.get(driver.getCurrentUrl());
	}
	
	public MailMainPage deleteMail() {
		deleteBtn.click();
		
		return PageFactory.initElements(driver, MailMainPage.class);
	}
	
	public String unReadMail() {
		unReadBtn.click();
		
		return unReadBtn.getText();
	}
	
	public void bookmarkMail() {
		bookmark.click();
	}
	
	public String getBookmarkTxt() {
		return bookmark.getText();
	}
}
