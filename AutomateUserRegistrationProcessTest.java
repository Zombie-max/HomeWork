import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutomateUserRegistrationProcessTest {

    @Before
    public void startUp() {
    }

    @Test
    @DisplayName("Case 1 - Automate User Registration Process")
    public void signUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String URL = "http://automationpractice.com/index.php";

        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        WebDriverManager.chromedriver().setup();

        // TODO
        // Sometimes occurs an error: Resource Limit Is Reached
        // The website is temporarily unable to service your request as it exceeded resource limit. Please try again later.

        // Click on Sign in button
        driver.findElement(By.linkText("Sign in")).click();

        // Enter a new email address
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        driver.findElement(By.cssSelector("[name='email_create']")).sendKeys(generatedString + "@gmail.co");

        // Click on "Create an account"
        driver.findElement(By.xpath("//button[@name='SubmitCreate']")).click();

        // Wait for loading form content
        try {
            sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Select Radio Button
        driver.findElement(By.xpath("//input[@id='id_gender1']")).click();

        // Enter name
        driver.findElement(By.name("customer_firstname")).sendKeys("Zombori");
        driver.findElement(By.name("customer_lastname")).sendKeys("Ferenc");
        // Define password
        driver.findElement(By.id("passwd")).sendKeys("Jelszo123");

        // Name copied automatically
        // Enter address
        driver.findElement(By.id("company")).sendKeys("-");
        driver.findElement(By.id("address1")).sendKeys("Any street 123");
        driver.findElement(By.id("city")).sendKeys("Miami");

        // Select any US State
        WebElement statedropdown = driver.findElement(By.name("id_state"));
        Select oSelect = new Select(statedropdown);
        oSelect.selectByVisibleText("Florida");
        // Enter 5 digits postcode
        driver.findElement(By.name("postcode")).sendKeys("12345");

        // Select US as Country
        WebElement countrydropDown = driver.findElement(By.name("id_country"));
        Select oSelectC = new Select(countrydropDown);
        oSelectC.selectByVisibleText("United States");

        // Enter Mobile Number
        driver.findElement(By.id("phone_mobile")).sendKeys("1234567890");

        // Submit Form
        driver.findElement(By.name("submitAccount")).click();
        String userText = driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a")).getText();

        // Validate that user has created
        if (userText.contains("Zombori Ferenc")) {
            System.out.println("User Verified Successfully, Test case PASSED.");
        } else {
            System.out.println("User Verification Failed, Test case FAILED.");
        }

        assertEquals(userText, "Zombori Ferenc");
        driver.quit();
    }

    @After
    public void tearDown() {
    }

}
