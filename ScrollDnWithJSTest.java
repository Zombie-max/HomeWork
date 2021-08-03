import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

class ScrollDnWithJSTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Case 4 - Scroll down with JavaScript")
    void scrollDn() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String URL = "http://automationpractice.com/index.php";

        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        WebDriverManager.chromedriver().setup();

        // Get the footer position on web page
        int pos = driver.findElement(By.xpath("//*[@id='social_block']")).getLocation().getY() + 100;

        // Scrolling down by 100 pixel till the element is visible on screen (FHD is 1080p)
        while (pos >= 1080) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
            pos = pos - 100;
            // Wait a little for visualize the events
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}