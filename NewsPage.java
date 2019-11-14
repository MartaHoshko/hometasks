package com.epam;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewsPage {
    @Test()
    public void verifyNewsSection() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");
        driver.manage().deleteAllCookies();

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("eva.kaller1@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("11111");
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();


        WebElement openNewsPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, \"main-nav__list\")]//li//descendant::a[contains(@class, \"topNavItem news click hover\")]")));
        openNewsPage.click();
        Thread.sleep(1000);

        WebElement openMaterials = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='tab-nav__item ng-scope']//descendant::span[@class='ng-binding' and contains(text(),'Materials')]")));
        openMaterials.click();
        Thread.sleep(1000);

        List<WebElement> materialsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='news-page-list__container']//a"));
        materialsSearchResultsList.forEach(element -> org.testng.Assert.assertTrue(element.getText().contains("Materials")
                        || element.getText().contains("Useful")
                        || element.getText().contains("useful")
                        || element.getText().contains("materials"),
                String.format("Element %s does not contain 'Materials' or 'Useful' word.", element)));
        driver.quit();


    }
}
