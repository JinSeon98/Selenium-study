package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

//Vod 구현 
public class VodEnd {

	WebDriver driver;
	
	public VodEnd(WebDriver driver) {
	      this.driver = driver;
	      driver.get("https://dev.tv.naver.com/v/200695233/list/4499085");
	  }
	
	//댓글 버튼 클릭 
	public void CommentBtn() {
		WebElement CommentBtn = driver.findElement(By.cssSelector("#clipInfoArea > div.watch_btn > a.btn_comment"));
		
		CommentBtn.click();
	}
	
	//댓글 헤드 영역
	public void Comment_Head() {
		CommentBtn();
		
		WebElement commentTitle = driver.findElement(By.className("u_cbox_title"));
		WebElement commentCount = driver.findElement(By.className("u_cbox_count"));
		WebElement refreshBtn = driver.findElement(By.className("u_cbox_btn_refresh"));
		
		assertThat(commentTitle.getText()).isEqualTo("댓글");
		assertThat(commentCount.isDisplayed()).isEqualTo(true);
		assertThat(refreshBtn.isDisplayed()).isEqualTo(true);
		
		/*
		 *
		 * 댓글 입력창 ('주제와 무관한 댓글, 악플은 삭제될 수 있습니다.')
		*/
	}
	
	//댓글 차트 영역
	public void CommentChart() {
		//CommentBtn();
		
		WebElement chartTitle = driver.findElement(By.className("u_cbox_chart_title"));
		WebElement chartSex = driver.findElement(By.className("u_cbox_chart_sex"));
		WebElement chartAge = driver.findElement(By.className("u_cbox_chart_age"));
		WebElement foldText = driver.findElement(By.className("u_cbox_chart_fold_cnt"));
		WebElement foldIcon = driver.findElement(By.className("u_cbox_chart_fold_ico"));
		
		assertThat(chartTitle.getText()).isEqualTo("누가 댓글을 썼을까요?");
		assertThat(chartSex.isDisplayed()).isEqualTo(true);
		assertThat(chartAge.isDisplayed()).isEqualTo(true);
		assertThat(foldText.getText()).isEqualTo("접기");
		assertThat(foldIcon.isDisplayed()).isEqualTo(true);
		
	}
	public void Login() {
		WebElement loginBtn = driver.findElement(By.className("btn_login"));
		loginBtn.click();
		
		WebElement id = driver.findElement(By.id("id"));
		WebElement pw = driver.findElement(By.id("pw"));
		WebElement submit = driver.findElement(By.id("log.login"));
		
		id.sendKeys("nvqa_rejoinjs01");
		pw.sendKeys("qweqwe");
		submit.click();
		
	}
	
	public void CommentInput() {
		Login();
		CommentBtn();
		
		WebElement text = driver.findElement(By.className("u_cbox_guide"));
		
		assertThat(text.getText()).isEqualTo("주제와 무관한 댓글, 악플은 삭제될 수 있습니다.");
	}
	
	public void Sort_option() {
		CommentBtn();
		
		WebElement sort_option = driver.findElement(By.className("u_cbox_sort_option"));
		List<WebElement> option_wrap = sort_option.findElements(By.className("u_cbox_sort_option_wrap")); 
		
		for(WebElement sort : option_wrap) {
			WebElement select = sort.findElement(By.cssSelector("a"));
			WebElement option = sort.findElement(By.cssSelector("a > span.u_cbox_sort_label"));
			
			String attribute = select.getAttribute("aria-selected");
			Boolean flag = Boolean.parseBoolean(attribute);
			
			//선택된 값이 있으면 V표시 출력 
			if(flag) {
				System.out.print("V " + option.getText() + " ");
			}
			else {
				System.out.print(option.getText() + " ");
			}
		}
		// 도움말 구현 
		
	}
	// 비로그인 > 댓글
	public void noLoginComment() {
		CommentBtn();
		
		WebElement Co_loginLink = driver.findElement(By.className("u_cbox_link"));
		Co_loginLink.click();
		
		driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		
	}
	// 비로그인 > 답글
	public void noLoginReply() {
		CommentBtn();
		
		WebElement Reply_btn = driver.findElement(By.className("u_cbox_btn_reply"));
		Reply_btn.click();
		
		WebElement Reply_loginLink = driver.findElement(By.xpath("//*[@id=\"cbox_module_wai_u_cbox_content_wrap_tabpanel\"]/ul/li[1]/div[2]/div[2]/div/form/fieldset/div/div/div[1]/div/label/a"));
		Reply_loginLink.click();
		
		driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
	}
}
