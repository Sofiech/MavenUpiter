package Lesson3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

/**
 * FileName: WindowTest
 * Author: aspid
 * Date: 23.09.2022 21:11
 * Description:
 */
public class WindowTest extends BestTest{
    @Test

    public void windowTest() throws InterruptedException {
        Point point = driver.manage().window().getPosition();
        point.x = point.x + 50;
        driver.manage().window().getSize();
        
        Thread.sleep(1000);

        driver.manage().window().setSize(new Dimension(800, 600));
        System.out.println(driver.manage().window().getSize());


    }
}
