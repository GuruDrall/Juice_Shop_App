package baseclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
    	//options.addArguments("--headless");  // uncomment if you want to run in headless mode
    	options.addArguments("--window-size=1920,1080");
    	
        driver = new ChromeDriver(options);
        // if dont want to use chrome option
        /* driver = new ChromeDriver(); */
        //driver.manage().window().maximize();
        
        driver.get(ConfigReader.get("baseUrl"));

        // Dismiss welcome banner if present
        try {
            driver.findElement(By.cssSelector(".close-dialog")).click();
        } catch (Exception ignored) {}
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
