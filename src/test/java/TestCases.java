import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class TestCases {

    WebDriver driver;

    @Test (testName = "Verify if a user will be able to login with a valid username and valid password.")
    public void test1(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.hudl.com/login");
        assertTrue(driver.getTitle().contains("Log In - Hudl"));
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("serhiibashniak@gmail.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Password1");
        driver.findElement(By.xpath("//button[@id='logIn']")).click();
        new WebDriverWait(driver, 10, 200).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Coach B')]")));
        String actualText = driver.findElement(By.xpath("//span[contains(text(),'Coach B')]")).getText();
        assertThat(actualText).isEqualTo("Coach B");
        driver.quit();
    }

    @Test (testName = "Verify if a user cannot login with a valid username and an invalid password.")
    public void test2(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.hudl.com/login");
        assertTrue(driver.getTitle().contains("Log In - Hudl"));
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("serhiibashniak@gmail.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Password2");
        driver.findElement(By.xpath("//button[@id='logIn']")).click();
        new WebDriverWait(driver, 10, 200).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login-error-container']")));
        assertThat(driver.findElement(By.xpath("//div[@class='login-error-container']")).isDisplayed()).isTrue();
        driver.quit();
    }

    @Test (testName = "Verify if a user cannot login with a invalid username and an valid password.")
    public void test3(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.hudl.com/login");
        assertTrue(driver.getTitle().contains("Log In - Hudl"));
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("serhiibashniak@gmail.commmm");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Password1");
        driver.findElement(By.xpath("//button[@id='logIn']")).click();
        new WebDriverWait(driver, 10, 200).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login-error-container']")));
        assertThat(driver.findElement(By.xpath("//div[@class='login-error-container']")).isDisplayed()).isTrue();
        driver.quit();
    }

    @Test (testName = "Verify if a user cannot login without entering username or password.")
    public void test4(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.hudl.com/login");
        assertTrue(driver.getTitle().contains("Log In - Hudl"));
        driver.findElement(By.xpath("//button[@id='logIn']")).click();
        new WebDriverWait(driver, 10, 200).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login-error-container']")));
        assertThat(driver.findElement(By.xpath("//div[@class='login-error-container']")).isDisplayed()).isTrue();
        driver.quit();
    }

    @Test (testName = "Verify if a user cannot login by entering username and not entering password")
    public void test5() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.hudl.com/login");
        assertTrue(driver.getTitle().contains("Log In - Hudl"));
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("serhiibashniak@gmail.com");
        driver.findElement(By.xpath("//button[@id='logIn']")).click();
        new WebDriverWait(driver, 10, 200).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login-error-container']")));
        assertThat(driver.findElement(By.xpath("//div[@class='login-error-container']")).isDisplayed()).isTrue();
        driver.quit();
    }
}
