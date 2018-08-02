package litecart_without_page_object;

import org.junit.Test;

public class EnterAdminPanelTest extends TestBase {

  @Test
  public void test003() {
    System.out.print("\n\n***** Внутри метода test003() *****\n\n");
    goToAdminPanel();
    exitAdminPanel();
  }
}
