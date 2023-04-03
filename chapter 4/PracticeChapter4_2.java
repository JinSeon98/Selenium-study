package com.example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class PracticeChapter4_2 {
	
	WebDriver driver;
	
  @Test
  public void storeCookies() {
	  driver.findElement(By.id("email")).sendKeys("ntsqayeji@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("dPwl1998");
      driver.findElement(By.tagName("button")).click();

      File dataFile = new File("./target/browser.data");
      try {
          dataFile.delete();
          dataFile.createNewFile();
          FileWriter fos = new FileWriter(dataFile);
          BufferedWriter bos = new BufferedWriter(fos);
          for (Cookie ck : driver.manage().getCookies()) {
              bos.write((ck.getName() + ";" + ck.getValue() + ";" + ck.
                      getDomain()
                      + ";" + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.
                      isSecure()));
              bos.newLine();
          }
          bos.flush();
          bos.close();
          fos.close();
      } catch (Exception ex) {
          ex.printStackTrace();
      }
  }
  
  @Test
  public void loadCookies() {
      try {
          File dataFile = new File("./target/browser.data");
          FileReader fr = new FileReader(dataFile);
          BufferedReader br = new BufferedReader(fr);
          String line;
          while ((line = br.readLine()) != null) {
              StringTokenizer str = new StringTokenizer(line, ";");
              while (str.hasMoreTokens()) {
                  String name = str.nextToken();
                  String value = str.nextToken();
                  String domain = str.nextToken();
                  String path = str.nextToken();
                  Date expiry = null;
                  String dt;
                  if (!(dt = str.nextToken()).equals("null")) {
                      SimpleDateFormat formatter =
                              new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
                      expiry = formatter.parse(dt);
                  }

                  boolean isSecure = new Boolean(str.nextToken()).booleanValue();
                  Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                  driver.manage().addCookie(ck);
              }
          }

          assertThat(driver.findElement(By.className("x1lliihq x6ikm8r x10wlt62 x1n2onr6")).getText())
                  .isEqualTo("Yeji Park");

      } catch (Exception ex) {
          ex.printStackTrace();
      }
  }
  
  @BeforeMethod
  public void beforeMethod() throws IOException{
		System.setProperty("webdriver.chrome.driver", "C://Users/USER/eclipse-workspace/chromedriver.exe");

		// 웹드라이버 세션 초기화
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/"); // 구글 메인 url
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 묵시적 대기 시간
  }

  @AfterMethod
  public void afterMethod() {
		//driver.quit();
  }

}
