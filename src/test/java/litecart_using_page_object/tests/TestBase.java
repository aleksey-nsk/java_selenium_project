package litecart_using_page_object.tests;

import litecart_using_page_object.app.Application;
import org.junit.AfterClass;
import org.junit.BeforeClass;

// Базовый класс для тестов:
public class TestBase {

  protected static Application app;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");
    app = new Application(); // экземпляр класса Application
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    app.quit();
    app = null;
  }
}
