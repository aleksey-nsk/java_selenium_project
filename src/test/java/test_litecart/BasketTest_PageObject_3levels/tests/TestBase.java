package test_litecart.BasketTest_PageObject_3levels.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test_litecart.BasketTest_PageObject_3levels.app.Application;

import java.util.concurrent.TimeUnit;

// Базовый класс для тестов:
public class TestBase {

    // public static WebDriver driver;
    public static Application app;

    @BeforeClass
    public static void start(){
        System.out.print("\n\n***** Внутри метода start() *****\n\n");

        app = new Application(); // экземпляр класса Application

    }

    @AfterClass
    public static void stop(){
        System.out.print("\n\n***** Внутри метода stop() *****\n\n");


        app.quit();
    }
}
