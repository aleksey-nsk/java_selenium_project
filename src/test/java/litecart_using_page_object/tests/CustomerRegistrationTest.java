package litecart_using_page_object.tests;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import litecart_using_page_object.model.Customer;

import java.util.Set;

@RunWith(DataProviderRunner.class)
public class CustomerRegistrationTest extends TestBase {

  @Test
  @UseDataProvider(value = "validCustomers", location = DataProviders.class)
  public void action(Customer customer /*тестовые данные передаём как параметр*/) {
    System.out.print("\n***** Внутри метода action() *****\n\n");

        /* Перед созданием нового клиента запоминаем текущее множество айдишников:
           Set<String> oldIds = getCustomerIds(); */
    // Получаем из приложения множество идентификаторов клиентов:
    Set<String> oldIds = app.getCustomerIds();

    // registerNewCustomer(customer);
    // Создаём в приложении нового клиента:
    app.registerNewCustomer(customer);

        /* Далее получаю новый список айдишников клиентов:
           Set<String> newIds = getCustomerIds(); */
    // Получаем из приложения новый список идентификаторов:
    Set<String> newIds = app.getCustomerIds();

    // Выполняем сравнение:
    // Новое множество содержит все айдишники старого множества, т.е.
    // никого нечайно не удалили:
    Assert.assertTrue(newIds.containsAll(oldIds));
    // Во-вторых в новом множестве появился еще один
    // дополнительный элемент (новый зарегистрировавшийся клиент):
    Assert.assertTrue(newIds.size() == oldIds.size() + 1);
  }
}
