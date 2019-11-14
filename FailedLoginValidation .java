package com.epam;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

public class FailedLoginValidation {
    private StringWriter verificationErrors;

    @Test ()
    public void  verifyLoginWithIncorrectCredentials () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("eva.kaller1777@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("11779791");
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();

        WebElement verifyIfLoginFailed = driver.findElement(By.xpath("//div[@class='popup__error-message ng-binding']"));
        Assert.assertTrue(verifyIfLoginFailed.isDisplayed());
        Thread.sleep(10000);
        driver.quit();
    }
}
