package controller;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SystemTestGT {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSystemTestGT() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath("//div[2]/div/div[2]/div/label")).click();
    driver.findElement(By.id("filenameTruth")).clear();
    driver.findElement(By.id("filenameTruth")).sendKeys("C:\\Users\\Joshua\\Documents\\GitHub\\SoccerAnalysis\\Implementation\\Source Code\\BocaAnalytics\\testData\\binarias.mpeg");
//    driver.findElement(By.xpath("//div[2]/label")).click();
    driver.findElement(By.id("filenameVideo")).clear();
    driver.findElement(By.id("filenameVideo")).sendKeys("C:\\Users\\Joshua\\Documents\\GitHub\\SoccerAnalysis\\Implementation\\Source Code\\BocaAnalytics\\testData\\corto.mp4");
    driver.findElement(By.xpath("(//a[contains(text(),'Subir Video')])[2]")).click();
    

//    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("body")).getText().matches("^[\\s\\S]*Cargando su video[\\s\\S]*$"));    // Warning: assertTextPresent may require manual changes
//    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

