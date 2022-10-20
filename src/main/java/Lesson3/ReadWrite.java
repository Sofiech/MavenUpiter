package Lesson3;

import Lesson1.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ReadWrite {
    WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(ReadWrite.class);

    @BeforeEach
    public void StartUp(){
        WebDriverManager.edgedriver().setup();
        driver = (WebDriver) new EdgeDriverManager();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }

    @AfterEach
    public void End(){
        if (driver!=null)
            driver.quit();
    }

    private void auth() throws InterruptedException {
        String login = "fawodo3192@chikd73.com";
        String password = "Qazwsx123";
        String locator = "button.header2__auth.js-open-modal";
        driver.findElement(By.cssSelector(locator)).click();
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).sendKeys(login);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
        logger.info("Авторизация прошла успешно");
    }

    private void  enterLK() throws InterruptedException {
        Thread.sleep(1000);
        //String locator = ".ic-blog-default-avatar";
        //WebElement icon = driver.findElement(By.cssSelector(locator));
        //Actions actions = new Actions(driver);
        //actions.moveToElement(icon).build().perform();
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Перешел в личный кабинет");
    }

    @Test
    public void openPage() throws InterruptedException {
        //1. Открыть otus.ru
        driver.get("http://otus.ru");
        logger.info("Открыта страница отус");
        //2. Авторизоваться на сайте
        auth();
        //3. Войти в личный кабинет
        enterLK();
        //4. В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        driver.findElement(By.id("id_fname_latin")).clear();
        driver.findElement(By.id("id_lname")).clear();
        driver.findElement(By.id("id_lname_latin")).clear();
        driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).clear();

        driver.findElement(By.id("id_fname_latin")).sendKeys("Anton");
        driver.findElement(By.id("id_lname")).sendKeys("Картушин");
        driver.findElement(By.id("id_lname_latin")).sendKeys("Kartushin");
        driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).sendKeys("08.02.1992");
        //Страна
        if(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText().contains("Россия"))
        {
            driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Россия')]")).submit();
        }
        //Город
        if(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText().contains("Москва"))
        {
            driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Москва')]")).click();
        }
        //уровень англ.
        if(!driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText().contains("Начальный уровень (Beginner)"))
        {
            driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Начальный уровень (Beginner)')]")).click();
        }
        //5. Нажать сохранить
        driver.findElement(By.xpath("//*[contains(text(), 'Сохранить и продолжить')]")).click();
        Thread.sleep(2000);
        //new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://otus.ru/lk/biography/skills/"));

        //6. Открыть https://otus.ru в “чистом браузере”
        driver.quit();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
        driver.get("http://otus.ru");

        //7. Авторизоваться на сайте
        auth();
        //8. Войти в личный кабинет
        enterLK();

        //9. Проверить, что в разделе о себе отображаются указанные ранее данные
        Assertions.assertEquals("Anton", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assertions.assertEquals("Картушин", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assertions.assertEquals("Kartushin", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assertions.assertEquals("08.02.1992", driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).getAttribute("value"));
        Assertions.assertEquals("Россия", driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText());
        Assertions.assertEquals("Москва", driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText());
        Assertions.assertEquals("Начальный уровень (Beginner)", driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText());
    }
}
