/*
Сценарий регистрации нового клиента в приложении litecart
Реализация с использованием Page Object
Пример от Алексея Баранцева
*/

package different_tests.PageObjectSecondExample.tests;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import different_tests.PageObjectSecondExample.model.Customer;
import java.util.Set;

@RunWith(DataProviderRunner.class)
public class CustomerRegistrationTest extends TestBase {

  @Test
  @UseDataProvider(value = "validCustomers", location = DataProviders.class)
  public void customerRegistrationTest(Customer customer /*тестовые данные передаём как параметр*/) {
    System.out.print("\n\n***** Внутри метода customerRegistrationTest() *****\n\n");

    // Получаем из приложения множество идентификаторов клиентов:
    Set<String> oldIds = app.getCustomerIds();

    // Создаём в приложении нового клиента:
    app.registerNewCustomer(customer);

    // Получаем из приложения новый список идентификаторов клиентов:
    Set<String> newIds = app.getCustomerIds();

    // Выполняем проверки:
    // (1) новое множество содержит все айдишники старого множества,
    // т.е. никого случайно не удалили:
    Assert.assertTrue(newIds.containsAll(oldIds));
    // (2) в новом множестве появился еще один
    // элемент (новый зарегистрированный клиент):
    Assert.assertTrue(newIds.size() == oldIds.size() + 1);
  }
}
