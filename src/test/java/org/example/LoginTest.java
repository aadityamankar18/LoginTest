package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds(10)));
        System.out.println("Test 1: started");
    }

    @Test(priority = 1)
    public void openFirstUrl() {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        System.out.println("Test 2: Title is = " + driver.getTitle());
    }

    @Test(priority = 2)
    public void openSecondUrl() throws IOException {
        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");

        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds(10)));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20))      // max wait
                .pollingEvery(Duration.ofSeconds(2))      // check every 2 sec
                .ignoring(Exception.class);               // ignore exceptions

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));

        File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C:\\Users\\aadit\\IdeaProjects\\dummyTest_03\\screenshot\\login_page.png"));
        System.out.println("Test 3: Title is = " + driver.getTitle());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Test 4: Driver Closed");
        }
    }
}
