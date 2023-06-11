package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import chapter7.WriteNewMailPage;

public class MailMainPage {
	WebDriver driver;

	@FindBy(css = ".lnb_task > a")
	WebElement writeBtn;

	@FindBy(css = ".lnb_task > a.item.button_write_to_me")
	WebElement writeToMeBtn;

	@FindBy(xpath = "//*[@id=\"mail_list_wrap\"]/ul")
	WebElement mailContainer;

	@FindBy(className = "button_task svg_read")
	WebElement readBtn;

	@FindBy(className = "button_task svg_delete")
	WebElement deleteBtn;

	@FindBy(className = "menu_count")
	WebElement UnReadBtn;

	public MailMainPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://mail.naver.com/v2/folders/0/all");
	}

	public WriteMailPage clickWriteMailBtn() {
		writeBtn.click();

		return PageFactory.initElements(driver, WriteMailPage.class);
	}

	public WriteMailToMePage clickWriteMailToMeBtn() {
		writeToMeBtn.click();

		return PageFactory.initElements(driver, WriteMailToMePage.class);

	}

	public MailEndPage clickMail(String title) {
		List<WebElement> allMailTitle = mailContainer.findElements(By.cssSelector(".mail_title > a > span"));

		for (WebElement ele : allMailTitle) {
			if (ele.getText().equals(title)) {
				Actions builder = new Actions(driver);
				builder.moveToElement(ele);
				builder.click(ele);

				Action compositeAction = builder.build();
				compositeAction.perform();

				break;
			}
		}

		return PageFactory.initElements(driver, MailEndPage.class);
	}

	public void clickMailCheckBox(int[] mailNum) {
		List<WebElement> allMailCheckBox = mailContainer.findElements(By.cssSelector("ul > li"));
		int i = 1, j = 0;

		for (WebElement ele : allMailCheckBox) {
			if (i++ == mailNum[j]) {
				Actions builder = new Actions(driver);
				builder.moveToElement(ele);
				builder.click(ele);

				Action compositeAction = builder.build();
				compositeAction.perform();

				j++;
			}
			if (mailNum.length < j)
				break;
		}

	}

	public void clickReadBtn() {
		readBtn.click();
	}

	public void clickDeleteBtn() {
		deleteBtn.click();
	}

	public void countUnReadMail() {
		UnReadBtn.findElement(By.className("count"));
	}

	public String getToastMessage() {
		return driver.findElement(By.cssSelector(".toast_message > span")).getText();
	}
}
