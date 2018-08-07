package litecart_using_page_object.app;

import litecart_using_page_object.pages.AdminPanelLoginPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import litecart_using_page_object.model.Customer;
import litecart_using_page_object.pages.CustomerListPage;
import litecart_using_page_object.pages.RegistrationPage;
import java.util.Set;

// В тестах вообще нигде не видно что используется Селениум.
// Внутрь класса Application прячем все технические подробности.
// Только в этом классе видим что используется Селениум.
// Именно тут создаётся драйвер, и здесь же он используется:
public class Application {

  private WebDriver driver;
  private RegistrationPage registrationPage;
  private AdminPanelLoginPage adminPanelLoginPage;
  private CustomerListPage customerListPage;

  public Application() {
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    driver.manage().window().maximize();
    registrationPage = new RegistrationPage(driver);
    adminPanelLoginPage = new AdminPanelLoginPage(driver);
    customerListPage = new CustomerListPage(driver);
  }

  public void quit() {
    driver.quit();
  }

  // Метод для регистрации нового клиента:
  public void registerNewCustomer(Customer customer) {
    // !!! (МОЖЕТ ПЕРЕД РЕГИСТРАЦИЕЙ ВЫЙТИ ИЗ АДМИНКИ ?????????)

    // Заиспользуем класс RegistrationPage (то есть страницу-объект registrationPage):
    // Открываю страницу регистрации (Create Account):
    registrationPage.open();
    // Заполняю форму регистрации (теперь работаем с полями а не с методами):
    registrationPage.firstnameInput.sendKeys(customer.getFirstname());
    registrationPage.lastnameInput.sendKeys(customer.getLastname());
    registrationPage.adressInput.sendKeys(customer.getAddress());
    registrationPage.postcodeInput.sendKeys(customer.getPostcode());
    registrationPage.cityInput.sendKeys(customer.getCity());
    registrationPage.selectCountry(customer.getCountry());
    registrationPage.selectZone(customer.getZone());
    registrationPage.emailInput.sendKeys(customer.getEmail());
    registrationPage.phoneInput.sendKeys(customer.getPhone());
    registrationPage.passwordInput.sendKeys(customer.getPassword());
    registrationPage.confirmedPasswordInput.sendKeys(customer.getPassword());
    // Нажимаю кнопку Create Account:
    registrationPage.createAccountButton.click();

    // !!!!!!!!НАВЕРНО ВЫХОД НАДО ВЫНЕСТИ
    // КАК ОТДЕЛЬНЫЙ МЕТОД !!!!!!!
    System.out.print("Выхожу из созданной учётки (Logout)... ");
    driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
    String logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
    Assert.assertEquals("You are now logged out.", logOutMessage);
    System.out.print("Успех!\n");
  }

  public Set<String> getCustomerIds() {
    if (adminPanelLoginPage.open().isOnThisPage()) {
      adminPanelLoginPage.enterUsername("admin").enterPassword("admin").submitLogin();
      // - тут методы вытягиваются в цепочку (это так называемый флуент-интерфейс, особый стиль написания кода).
      // Для реализации этого стиля надо чтобы методы возвращали готовый объект-страницу (return this;)
    }
    return customerListPage.open().getCustomerIds();
    // - в итоге оба объекта-страницы не возвращают никаких элементов, находящихся на этих страницах.
    // Они предоставляют сервисы (предоставляют услуги) по заполнению формы и выполнению входа.
    // И услугу по получению списка идентификаторов клиентов.
  }
}
