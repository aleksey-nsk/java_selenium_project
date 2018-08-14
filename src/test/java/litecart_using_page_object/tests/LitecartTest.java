package litecart_using_page_object.tests;

import org.junit.Test;

public class LitecartTest extends TestBase {

  @Test
  public void test003() {
    System.out.print("\n\n***** Внутри метода test003() *****\n\n");
    app.openAdminPanel();
    app.exitAdminPanel();
  }

  @Test
  public void test007() {
    System.out.print("\n\n***** Внутри метода test007() *****\n\n");
    app.openAdminPanel();
    app.passAllAdminSections();
    app.exitAdminPanel();
  }

  @Test
  public void test008() {
    System.out.print("\n\n***** Внутри метода test008() *****\n\n");
    app.checkStickers();
  }

  @Test
  public void test011() {
    System.out.print("\n\n***** Внутри метода test011() *****\n\n");
    app.customerRegistration();
  }

  @Test
  public void test012() {
    System.out.print("\n\n***** Внутри метода test012() *****\n\n");
    app.openAdminPanel();
    app.addProduct();
    app.exitAdminPanel();
  }

  @Test
  public void test013() {
    System.out.print("\n\n***** Внутри метода test013() *****\n\n");
    app.addOneProductToBasket();
    app.addOneProductToBasket();
    app.addOneProductToBasket();
    app.deleteAllProductsFromBasket();
  }

  @Test
  public void test014() {
    System.out.print("\n\n***** Внутри метода test014() *****\n\n");
    app.openAdminPanel();
    app.checkLinksInNewWindow();
    app.exitAdminPanel();
  }
}
