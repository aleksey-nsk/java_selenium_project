package litecart_using_page_object.tests;

import org.junit.BeforeClass;
import litecart_using_page_object.app.Application;

// Базовый класс для тестов:
public class TestBase {

  public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
  public static Application app;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");

    if (tlApp.get() != null) {
      app = tlApp.get();
      return;
    }

    app = new Application();
    tlApp.set(app);

    Runtime.getRuntime().addShutdownHook(
        new Thread( () -> {app.quit(); app = null;} )
    );
  }
}
