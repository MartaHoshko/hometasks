package com.epam;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class VerifyTrainingList {
    @Test()
    public void VerifyTrainingList() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 40);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("eva.kaller1@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("11111");

        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();

        WebElement openNewsPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, \"main-nav__list\")]//li//descendant::a[contains(@class, \"topNavItem training click hover\")]")));
        openNewsPage.click();
        Thread.sleep(1000);

        WebElement expandLocations = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));
        expandLocations.click();
        Thread.sleep(1000);

       // WebElement locationSearchInput = driver.findElement(By.xpath("//div[contains(.,'Ukraine') and contains(@class, 'location__not-active-label')]"));
           //  locationSearchInput.click();

        WebElement locationSearchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(.,'Україна') and contains(@class, 'location__not-active-label')]")));
       locationSearchInput.click();
        Thread.sleep(1000);

        WebElement locationCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Львів')]//span")));
        locationCheckbox.click();


        WebElement collapseSkillsArrow = driver.findElement(By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']"));
        collapseSkillsArrow.click();

//        List<WebElement> newsPageResultsList = driver.findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));
//        newsPageResultsList.forEach(element-> Assert.assertTrue(element.getText().contains("Lviv"))) ;
//             //   String.format("Element %s does not contain 'Lviv' word.",element)));
        driver.quit();

    }
}
