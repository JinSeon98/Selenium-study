package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WriteMailToMePage {
	WebDriver driver;
	
	@FindBy(id = "subject_title")
	WebElement title;

	WebElement content;

	@FindBy(className = "button_write_task")
	WebElement sendBtn;
	
	public WriteMailToMePage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://mail.naver.com/v2/new?type=toMe");
	}
	
	public SendMailSuccessPage writeMail(String stitle, String scontent) {
		title.sendKeys(stitle);
		
		driver.switchTo().frame(4);
		content = driver.findElement(By.className("workseditor-content"));
		content.sendKeys(scontent);
		
		driver.switchTo().defaultContent();
		sendBtn.click();
		
		return PageFactory.initElements(driver, SendMailSuccessPage.class);
	}
}
