package test_litecart.UserRegistrationTest_PageObject_3levels.app;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_litecart.UserRegistrationTest_PageObject_3levels.pages.AdminPanelLoginPage;
import test_litecart.UserRegistrationTest_PageObject_3levels.model.Customer;
import test_litecart.UserRegistrationTest_PageObject_3levels.pages.CustomerListPage;
import test_litecart.UserRegistrationTest_PageObject_3levels.pages.RegistrationPage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

// В тестах вообще нигда не видно что используется Селениум.
// Внутрь класса Application прячем все технические подробности. Только
// в этом классе видим что используется Селениум. Именно тут
// создаётся драйвер, и здесь же он используется.
public class Application {

    private WebDriverWait wait;
    private WebDriver driver;

    private RegistrationPage registrationPage;
    private AdminPanelLoginPage adminPanelLoginPage;
    private CustomerListPage customerListPage;

    public Application() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver(); // инициализация драйвера
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
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
        // Регистрируем нового клиента:
        // (МОЖЕТ ПЕРЕД РЕГИСТРАЦИЕЙ ВЫЙТИ ИЗ АДМИНКИ ?????????)

        // Заиспользуем класс RegistrationPage (то есть страницу объект registrationPage):
        registrationPage.open(); // открываю страницу регистрации (Create Account)
        // Заполняю форму регистрации: (теперь работаем с полями а не с методами)
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
        // System.out.print("Нажимаю кнопку Create Account...\n");
        // Нажимаю кнопку Create Account
        registrationPage.createAccountButton.click();



        // НАВЕРНО ВЫХОД НАДО ВЫНЕСТИ
        // КАК ОТДЕЛЬНЫЙ МЕТОД !!!!!!!
        System.out.print("Выхожу из созданной учётки (Logout)... ");
        driver.findElement(By.xpath("//ul[@class='list-vertical']/li[4]/a")).click();
        String logOutMessage = driver.findElement(By.xpath("//div[@class='notice success']")).getText();
        // logOutMessage = "Тестовое сообщение 2"; // для проверки падения теста
        Assert.assertEquals("You are now logged out.", logOutMessage);
        System.out.print("Успех!\n");
    }

    public Set<String> getCustomerIds() {
        /*
        // Добавим в тест проверки. Проверки выполняются через админскую панель:
        driver.get("http://localhost/litecart/admin");
        // Проверка, если логин уже выполнен то повторно его выполнять не надо:
        if (driver.findElements(By.id("box-login")).size() > 0) {
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
        }
        // После входа в админскую панель открываем страницу со списком клиентов:
        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        // Возвращаем строки таблицы, которые содержат нужную инфу
        // и у каждой строки берем текст третьего столбца (идентификатор клиента):
        return driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
        */

        if (adminPanelLoginPage.open().isOnThisPage()) {
            adminPanelLoginPage.enterUsername("admin").enterPassword("admin").submitLogin();
            // методы вытягиваются в цепочку (это так называемый флуент-интерфейс, особый стиль написания кода).
            // Для реализации этого стиля надо чтобы методы возвращали готовый объект-страницу (return this;)
        }
        return customerListPage.open().getCustomerIds();
        // - в итоге оба объекта-страницы не возвращают никаких элементов, находящихся на этих страницах.
        // Они предоставляют сервис (предоставляют услуги) по заполнению формы и выполнению входа.
        // И услугу по получению списка идентификаторов клиентов.
    }
}
