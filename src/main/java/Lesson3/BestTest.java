package Lesson3;

import Lesson1.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

/**
 * FileName: BestTest
 * Author: aspid
 * Date: 23.09.2022 20:13
 * Description:
 */
  public class BestTest {

    protected WebDriver driver;
    protected org.apache.logging.log4j.Logger logger = LogManager.getLogger(BestTest.class);

    @BeforeEach
    public void StartUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
       // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }

    @AfterEach
    public void End(){
        if (driver!=null)
            driver.quit();
    }
}
