package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DoneMailPage {
	WebDriver driver;
	
	@FindBy(className = "mail_status_title")
	WebElement status;
	
	public DoneMailPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getMailStatus() {
		return status.getText();
	}
}
