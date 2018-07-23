package test_litecart.UserRegistrationTest_PageObject_3levels;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toSet;

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
        // Заполняю форму регистрации:
        registrationPage.firstnameInput().sendKeys(customer.getFirstname());
        registrationPage.lastnameInput().sendKeys(customer.getLastname());
        registrationPage.adressInput().sendKeys(customer.getAddress());
        registrationPage.postcodeInput().sendKeys(customer.getPostcode());
        registrationPage.cityInput().sendKeys(customer.getCity());

        // Не все элементы простые. Есть более сложные (2 выпадающих меню):
        /*
        driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys(customer.getCountry() + Keys.ENTER);
        driver.findElement(By.xpath("//select[@name='zone_code']")).click();
        driver.findElement(By.xpath(String.format("//select[@name='zone_code']/option[@value='%s']", customer.getZone()))).click();
        */
        // Вместо этого сделаем вспомогательные методы selectCountry и selectZone:
        registrationPage.selectCountry(customer.getCountry());
        registrationPage.selectZone(customer.getZone());

        registrationPage.emailInput().sendKeys(customer.getEmail());
        registrationPage.phoneInput().sendKeys(customer.getPhone());
        registrationPage.passwordInput().sendKeys(customer.getPassword());
        registrationPage.confirmedPasswordInput().sendKeys(customer.getPassword());
        // System.out.print("Нажимаю кнопку Create Account...\n");
        // Нажимаю кнопку Create Account
        registrationPage.createAccountButton().click();



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
        // Добавим в тест проверки. Проверки выполняются через
        // админскую панель:
        driver.get("http://localhost/litecart/admin");
        // Проверка, если логин уже выполнен
        // то повторно его выполнять не надо:
        if (driver.findElements(By.id("box-login")).size() > 0) {
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
        }

        // После входа в админскую панель открываем
        // страницу со списком клиентов:
        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        // Загружаем строки таблицы, которые содержат нужную инфу
        // и у каждой строки берем текст третьего столбца (идентификатор клиента).
        /* Set<String> oldIds = driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet()); */
        return driver.findElements(By.cssSelector("table.dataTable tr.row")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
    }
}
