package com.epam;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import org.testng.annotations.Test;

public class TrainingListBySkillsVerification {

    @Test()
    public void verifyTrainingsSearchWorksProperlyForSkills() throws InterruptedException {
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

        WebElement expandSkillsArrow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));
        expandSkillsArrow.click();

        WebElement bySkillsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'By skills')]")));
        bySkillsButton.click();

        WebElement skillsSearchInput = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        skillsSearchInput.sendKeys("Java");
        Thread.sleep(10000);

        WebElement javaCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Java')]//span")));
        javaCheckbox.click();
        Thread.sleep(10000);

        WebElement collapseSkillsArrow = driver.findElement(By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']"));
        collapseSkillsArrow.click();

        List<WebElement> skillsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));
        skillsSearchResultsList.forEach(element -> Assert.assertTrue(element.getText().contains("JAVA"),
                String.format("Element %s does not contain 'Java' word.", element)));


        WebElement clearSkillButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class = 'filter-field__input-item-close-icon']")));
        try {
            clearSkillButton.click();
        } catch (WebDriverException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", clearSkillButton);
        }

        WebElement expandSkillArrow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));
        expandSkillsArrow.click();

        WebElement bySkillButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'By skills')]")));
        bySkillsButton.click();
        Thread.sleep(10000);
        WebElement skillSearchInput = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        skillsSearchInput.sendKeys("DATA");
        Thread.sleep(10000);

        List<WebElement> dataCheckbox = driver.findElements(By.xpath("//label[@class='location__not-active-label ng-binding' and contains(., 'Data')]//span"));
        for (WebElement ele : dataCheckbox) {
            ele.click();
        }
        Thread.sleep(10000);
        WebElement collapseSkillArrow = driver.findElement(By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']"));
        collapseSkillsArrow.click();
        Thread.sleep(10000);

        List<WebElement> DataSearchResultsList = driver.
                findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));
        DataSearchResultsList.forEach(element -> Assert.assertTrue(element.getText().contains("DATA"),
                String.format("Element %s does not contain 'Data' word.", element)));


        WebElement clearSkillDataButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class = 'filter-field__input-item-close-icon']")));
        try {
            clearSkillDataButton.click();
        } catch (WebDriverException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click()", clearSkillDataButton);
        }
        WebElement expandSkillArrowForPascal = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));
        expandSkillsArrow.click();

        WebElement searchBySkillButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'By skills')]")));
        bySkillsButton.click();

        WebElement skillSearchInputForPascal = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        skillsSearchInput.sendKeys("Pascal");
        Thread.sleep(10000);

        Assert.assertEquals(0, driver.findElements(By.className("training-item")).size());
//        WebElement pascalSearchResultsList = driver.
//                findElement(By.name("Pascal"));
//        Assert.assertFalse(pascalSearchResultsList.
//                Assert.asserEquals(0, driver.findElements(By.className("training-item")).size());
        driver.quit();
    }
}
