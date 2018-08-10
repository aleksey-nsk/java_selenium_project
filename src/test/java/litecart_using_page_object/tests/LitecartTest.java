package litecart_using_page_object.tests;

import org.junit.Test;

public class LitecartTest extends TestBase {

  @Test
  public void test013() {
    System.out.print("\n\n***** Внутри метода test013() *****\n\n");
    app.addOneProductToBasket();
    app.addOneProductToBasket();
    app.addOneProductToBasket();
    app.deleteAllProductsFromBasket();
  }
}
