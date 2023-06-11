package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
		
		WebElement btn_close = driver.findElement(By.className("btn_close"));
		btn_close.click();
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
		
		WebElement help_icon = driver.findElement(By.className("u_cbox_btn_help"));
		assertThat(help_icon.isDisplayed()).isEqualTo(true);
	}
	// 댓글 영역 출력 
	public void commentBox() {
		CommentBtn();
		
		WebElement nick = driver.findElement(By.className("u_cbox_nick"));
		WebElement text = driver.findElement(By.className("u_cbox_text_wrap"));
		WebElement date = driver.findElement(By.className("u_cbox_date"));
		WebElement report = driver.findElement(By.className("u_cbox_in_report"));
		WebElement reply = driver.findElement(By.className("u_cbox_reply_txt"));
		WebElement replyCnt = driver.findElement(By.className("u_cbox_reply_cnt"));
		WebElement recomm = driver.findElement(By.className("u_cbox_ico_recomm"));
		WebElement recommCnt = driver.findElement(By.className("u_cbox_cnt_recomm"));
		WebElement unrecomm = driver.findElement(By.className("u_cbox_ico_unrecomm"));
		WebElement unrecommCnt = driver.findElement(By.className("u_cbox_cnt_unrecomm"));
		
		System.out.print(nick.getText() + "\n" + text.getText() + "\n" + date.getText() + "\t" + report.getText() + "\n");
		System.out.println(reply.getText() + replyCnt.getText() + "\t\t\t [" + recomm.getText() + recommCnt.getText() + "] [" + unrecomm.getText() + unrecommCnt.getText() + "]");
		
	}
	//페이징 
	public void paging() {
		CommentBtn();
		
		WebElement page = driver.findElement(By.className("u_cbox_page_wrap"));
		Actions actions = new Actions(driver);
		actions.moveToElement(page).perform();
		
		List<WebElement> page_num = page.findElements(By.tagName("a"));
		
		for(WebElement num : page_num) {
			num.click();
			actions.moveToElement(page).perform();
			//stale element reference : stale element not found 에러 발생
			
			/* 해결방안 모색중...
			 * 1) Thread.sleep(); -> 해결안됨 
			 * 2) 리스트 초기화 -> 해결안됨 
			 */

		}
	}
	//새로고침
	public void refresh() {
		CommentBtn();
		
		WebElement refresh = driver.findElement(By.className("u_cbox_btn_refresh"));
		refresh.click();
	}
	//통계보기 
	public void chart() {
		CommentBtn();
		
		WebElement chart = driver.findElement(By.className("u_cbox_chart_fold"));
		WebElement chart_text = driver.findElement(By.className("u_cbox_chart_fold_cnt"));
		WebElement chart_open = driver.findElement(By.className("u_cbox_chart_open"));
		
		assertThat(chart_text.getText()).isEqualTo("접기");
		assertThat(chart_open.isDisplayed()).isEqualTo(true);
		//[접기] 선택 
		chart.click();
		assertThat(chart_text.getText()).isEqualTo("통계보기");
	}
	//Best 댓글 
	public void Best() {
		CommentBtn();
		
		WebElement best_label = driver.findElement(By.xpath("//*[@id=\"cbox_module_wai_u_cbox_sort_option_tab1\"]/span[2]"));
		best_label.click();
		
		WebElement best_ico = driver.findElement(By.className("u_cbox_ico_best"));
		assertThat(best_ico.isDisplayed()).isEqualTo(true);
		
		WebElement all = driver.findElement(By.className("u_cbox_in_view_comment"));
		Actions actions = new Actions(driver);
		actions.moveToElement(all).click().perform();

	}
	//전체 댓글
	public void All() {
		CommentBtn();
		
		//최신순 노출 확인
		WebElement c_list = driver.findElement(By.className("u_cbox_list"));
		List<WebElement> comment = c_list.findElements(By.className("u_cbox_comment"));
		
		for(WebElement info : comment) {
			WebElement date = info.findElement(By.className("u_cbox_date"));
			/* 
			 * date_value로 확인? or 노출되는 값으로 확인? -> 현재는 노출되는 값 
			 * String date_value = date.getAttribute("data-value").substring(0,19);
			 */
			
			System.out.println(date.getText());
		}
		//하단 페이징
		WebElement page = driver.findElement(By.className("u_cbox_page_wrap"));
		Actions actions = new Actions(driver);
		actions.moveToElement(page).perform();
		
		WebElement next_page = driver.findElement(By.xpath("//*[@id=\"cbox_module\"]/div/div[8]/div/a[1]"));
		next_page.click();
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
