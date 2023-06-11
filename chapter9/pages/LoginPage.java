package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(id="id")
	WebElement id;
	
	@FindBy(id="pw")
	WebElement pw;
	
	@FindBy(id="btn_login")
	WebElement submit;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https://www.naver.com/");
		/*id = driver.findElement(By.id("id"));
		pw = driver.findElement(By.id("pw"));
		submit = driver.findElement(By.className("btn_login"));*/

	}
	
	public MailMainPage login(String sid, String spw) {
		id.sendKeys(sid);
		pw.sendKeys(spw);
		pw.sendKeys(Keys.ENTER);
		
		return PageFactory.initElements(driver, MailMainPage.class);
	}
}
