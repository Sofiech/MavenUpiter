package Lesson1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


/**
 * FileName: FirstTest
 * Author: aspid
 * Date: 15.09.2022 7:41
 * Description:
 */
public class FirstTest {

    private final Logger logger = LogManager.getLogger(FirstTest.class);
    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
            logger.info("Драйвер поднят");
    }
    @AfterEach
    public void setDown(){
        if(driver != null)
            driver.quit();
        }
    @Test
    public void Example(){
        logger.error("Я есть ошибка");
        Assertions.assertEquals(1,1,"1=1");
    driver.get("https://otus.ru");
    Assertions.assertEquals("ОТУС", driver.getTitle());
    }
}
