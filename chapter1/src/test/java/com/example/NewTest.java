package com.example;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewTest {
	WebDriver driver;

	@BeforeClass
	public void beforeMethod() {
		// Chromedriver ��μ��� > ���ҽ� �ؿ� ����̹� ���� ���
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

		// ������̹� �����ʱ�ȭ
		driver = new ChromeDriver();
	}

	@Test
	public void test1_webtoonSearch() throws InterruptedException {

		// ��������Ʈ �̵�
		driver.get("https://comic.naver.com/index");

		// ���� �˻� �ؽ�Ʈ �ڽ� ����
		WebElement searchBox = driver.findElement(By.className("SearchBar__search_input--k5nfk"));

		// click �� isSelected() �޼��� false �� �����.. ���� Ȯ�� �ʿ�
		// searchBox.click();

		// ������Ʈ ���� Ȯ�� �޼���
		if (searchBox.isEnabled())
			System.out.println("���� �˻� �ڽ� ������Ʈ ���� Ȯ��");
		// ������Ʈ ���� Ȯ�� �޼���
		if (searchBox.isDisplayed())
			System.out.println("���� �˻� �ڽ� ������Ʈ ���� Ȯ��");

		// ������ �Է�
		searchBox.sendKeys("ȣ��������");

		// ���� �˻� ������ ������
		WebElement searchButton = driver.findElement(By.className("SearchBar__btn_search--SsL7v"));

		// ���� �˻� ����������� Ŭ��
		searchButton.click();

		// ������ �ε��ð� �ʿ��Ͽ� �߰� > ������ �ٲ������ Ȯ�� ������ ��� �ִ��� ���� ã�ƺ���
		Thread.sleep(1000);

		// �˻��� ���� �̸�
		WebElement webtoonName = driver.findElement(By.className("ContentTitle__search_title--nndrf"));

		// ���� �̸� ���� ������Ʈ���� �� ������ �� ��
		AssertJUnit.assertEquals(webtoonName.getText(), "ȣ��������");
	}

	@Test
	public void test2_postSize() throws InterruptedException {

		// ���� �˻� �ؽ�Ʈ �ڽ� ����
		WebElement webtoonPoster = driver.findElement(By.className("Poster__image--d9XTI"));

		// ���� ������ ������
		Dimension posterSize = webtoonPoster.getSize();

		AssertJUnit.assertEquals("���� ������ ���� ũ�� 156 Ȯ��", posterSize.height, 156);
		AssertJUnit.assertEquals("���� ������ ���� ũ�� 120 Ȯ��", posterSize.width, 120);
	}

	@Test
	public void test3_getLinkElements() throws InterruptedException {

		List<WebElement> links = driver.findElements(By.tagName("a"));

		System.out.println("Found links:" + links.size());

		int findWebtoonLinkCount = 0;

		for (WebElement webtoonLinkElement : links) {
			String linkName = webtoonLinkElement.getText();
			if (linkName.length() <= 0)
				continue;

			System.out.println(linkName);

			if (linkName.equals("ȣ���� ����"))
				findWebtoonLinkCount++;
		}

		AssertJUnit.assertEquals(findWebtoonLinkCount, 1);

		/*
		 * �ؽ�Ʈ ������ �ִ� ��ũ ã�� ���� �ڵ� using Java 8 Streams API �� for�� ���� ���� links.stream()
		 * .filter(elem -> elem.getText().length() > 0) .forEach(elem ->
		 * System.out.println(elem.getText()));
		 */
	}

	@AfterClass
	public void afterMethod() {
		driver.quit();
	}

}
