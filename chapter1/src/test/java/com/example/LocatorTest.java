package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LocatorTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();

    }

    @BeforeMethod
    public void navigate() {

        driver.get("http://demo-store.seleniumacademy.com/");

    }

    @Test
    public void byIdLocatorExample() {
    	// id속성으로 검색창 찾기 
        WebElement searchBox = driver.findElement(By.id("search"));

        searchBox.sendKeys("Bags");
        searchBox.submit();

        assertThat(driver.getTitle())
                .isEqualTo("Search results for: 'Bags'");
    }

    @Test
    public void byClassNameLocatorExample() {

        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.sendKeys("Electronics");

        WebElement searchButton =
                driver.findElement(By.className("search-button"));
        searchButton.click();

        assertThat(driver.getTitle())
                .isEqualTo("Search results for: 'Electronics'");
    }

    @Test
    public void byLinkTextLocatorExample() {

        WebElement myAccountLink =
                driver.findElement(By.linkText("MY ACCOUNT"));

        myAccountLink.click();

        assertThat(driver.getTitle())
                .isEqualTo("Customer Login");
    }

    @Test
    public void byPartialLinkTextLocatorExample() {

        WebElement orderAndReturns =
                driver.findElement(By.partialLinkText("PRIVACY"));

        orderAndReturns.click();

        assertThat(driver.getTitle())
                .isEqualTo("Privacy Policy");
    }

    @Test
    public void byTagNameLocatorExample() {

        // 홈페이지의 모든 링크를 가져온다. 
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Found links:" + links.size());

        // 링크 텍스트를 자바 8 스트림 API로 출력한다.
        links.stream()
                .filter(elem -> elem.getText().length() > 0)
                .forEach(elem -> System.out.println(elem.getText()));
    }

    @Test
    public void byXPathLocatorExample() {

        WebElement searchBox =
                driver.findElement(By.xpath("//*[@id='search']"));

        searchBox.sendKeys("Bags");
        searchBox.submit();

        assertThat(driver.getTitle())
                .isEqualTo("Search results for: 'Bags'");
    }

    @Test
    public void byCssSelectorLocatorExample() {

        WebElement searchBox =
                driver.findElement(By.cssSelector("#search"));

        searchBox.sendKeys("Bags");
        searchBox.submit();

        assertThat(driver.getTitle())
                .isEqualTo("Search results for: 'Bags'");
    }
    
    @Test
    public void byCssSelectoComplexExample() {

        WebElement aboutUs =
                driver.findElement(By.cssSelector("a[href*='/about-magento-demo-store/']"));

        aboutUs.click();

        assertThat(driver.getTitle())
                .isEqualTo("About Us");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
