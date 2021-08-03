import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VerifyErrorMessagesTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Case 2 - Verify error messages for mandatory fields")
    void withEmptyFields() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String URL = "http://automationpractice.com/index.php";

        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(2500, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        WebDriverManager.chromedriver().setup();

        // Click on Sign in button
        driver.findElement(By.linkText("Sign in")).click();

        // Enter a new email address
        driver.findElement(By.cssSelector("[name='email_create']")).sendKeys("ErrorMsgValidation@auto.test");

        // Click on "Create an account"
        driver.findElement(By.xpath("//button[@name='SubmitCreate']")).click();

        // Try to wait a little to enable to press the button
        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Submit Form
        driver.findElement(By.name("submitAccount")).click();

        // Wait for loading form content
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //There are 8 errors
        //You must register at least one phone number.
        //lastname is required.
        //firstname is required.
        //passwd is required.
        //address1 is required.
        //city is required.
        //The Zip/Postal code you've entered is invalid. It must follow this format: 00000
        //This country requires you to choose a State.

        String messageText1 = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[1]")).getText();
        // Validate 1. error message
        assertEquals(messageText1, "You must register at least one phone number.");
        String messageText2 = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[2]")).getText();
        // Validate 2. error message
        assertEquals(messageText2, "lastname is required.");
        String messageText3 = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[3]")).getText();
        // Validate 3. error message
        assertEquals(messageText3, "firstname is required.");
        String messageText4 = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[4]")).getText();
        // Validate 4. error message
        assertEquals(messageText4, "passwd is required.");
        String messageText5 = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[5]")).getText();
        // Validate 5. error message
        assertEquals(messageText5, "address1 is required.");
        String messageText6 = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[6]")).getText();
        // Validate 6. error message
        assertEquals(messageText6, "city is required.");
        String messageText7 = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[7]")).getText();
        // Validate 7. error message
        assertEquals(messageText7, "The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
        String messageText8 = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li[8]")).getText();
        // Validate 8. error message
        assertEquals(messageText8, "This country requires you to choose a State.");
        driver.quit();
    }
}