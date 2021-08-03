import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class iFrameAndTabHandlingTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Case 3 - iFrame and tab handling")
    public void iFrame() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String URL = "http://demo.guru99.com/test/guru99home/";

        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(25000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        WebDriverManager.chromedriver().setup();


        // Switch to the iframe through its ID
        driver.switchTo().frame("a077aa5e");

        // Click on the image element
        driver.findElement(By.xpath("html/body/a/img")).click();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Explicit wait - to wait for new title
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("FREE"));

        // Check the page title
        String actualTitle = driver.getTitle();
        String expectedTitle = "Selenium Live Project: FREE Real Time Project for Practice";
        assertEquals(expectedTitle, actualTitle);

        // Close this TAB
        driver.close();

        // Switch back to main page
        driver.switchTo().window(tabs.get(0));
        driver.switchTo().frame(0);
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();

        // Scroll menu element into view
        WebElement element = driver.findElement(By.xpath("//*[@id=\"rt-header\"]/div/div[2]/div/ul/li[2]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Hoover over Testing menu
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on SubMenu Selenium element
        driver.findElement(By.xpath("//*[@id=\"rt-header\"]/div/div[2]/div/ul/li[2]/div/div/ul/li[3]/a")).click();
        driver.manage().timeouts().implicitlyWait(25000, TimeUnit.MILLISECONDS);

        // Scroll down till element visible
        WebElement element2 = driver.findElement(By.xpath("//input[@name='submit']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element2);
        // Verify the wide red “Join Now” button is displayed
        assertTrue(driver.findElement(By.xpath("//input[@name='submit']")).isDisplayed());
        driver.quit();
    }
}