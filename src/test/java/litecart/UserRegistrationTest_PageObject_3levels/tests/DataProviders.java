package litecart.UserRegistrationTest_PageObject_3levels.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import litecart.UserRegistrationTest_PageObject_3levels.model.Customer;
// import ru.stqa.training.selenium.model.Customer;

public class DataProviders {

    @DataProvider
    public static Object[][] validCustomers() {
        return new Object[][] {
                {
                        // Создадим объект с тестовыми данными:
                        Customer.newEntity()
                                .withFirstname("Firstname-"+System.currentTimeMillis()).withLastname("Lastname")
                                .withAddress("Test Adress").withPostcode("12345").withCity("Test City").withCountry("United States")
                                .withZone("KS" /*Канзас*/)
                                .withEmail("email"+System.currentTimeMillis()+"@test.ru")
                                .withPhone("+10123456789").withPassword("qwerty")
                                .build()
                },
                /* ... */
        };
    }
}
