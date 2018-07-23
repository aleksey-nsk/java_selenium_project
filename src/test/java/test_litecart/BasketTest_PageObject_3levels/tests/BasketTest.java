/*
Описание теста:

Задание 19. Реализовать многослойную архитектуру. Переделайте
созданный в задании 13 сценарий для добавления товаров в корзину
и удаления товаров из корзины, чтобы он использовал многослойную архитектуру.

А именно, выделите вспомогательные классы для работы
с главной страницей (откуда выбирается товар),
для работы со страницей товара (откуда происходит добавление товара в корзину),
со страницей корзины (откуда происходит удаление),
и реализуйте сценарий, который не напрямую обращается к операциям Selenium,
а оперирует вышеперечисленными объектами-страницами.

1) открыть главную страницу.
2) открыть первый товар из списка.
3) добавить его в корзину (при этом может случайно добавиться
товар, который там уже есть, ничего страшного).
4) подождать, пока счётчик товаров в корзине обновится.
5) вернуться на главную страницу, повторить предыдущие шаги ещё
два раза, чтобы в общей сложности в корзине было 3 единицы товара.
6) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout).
7) удалить все товары из корзины один за другим, после
каждого удаления подождать, пока внизу обновится таблица.
*/

package test_litecart.BasketTest_PageObject_3levels.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class BasketTest extends TestBase {
    // private static WebDriver driver;

    /*
    @BeforeClass
    public static void start(){
        System.out.print("\n\n***** Внутри метода start() *****\n\n");

        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver(); // инициализация драйвера

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
    }
    */

    @Test
    public void action(){
        System.out.print("\n\n***** Внутри метода action() *****\n\n");

        // System.out.print("Открываю главную страницу магазина\n"); // убрать отсюда !!!!!
        // driver.get("http://localhost/litecart/en/"); // убрать отсюда !!!!!!!!

        app.addOneProductToBasket();
        app.addOneProductToBasket();
        app.addOneProductToBasket();

        app.deleteAllProductsFromBasket();
        // может вставить проверку что корзина пуста ?????
        // Assert.assertTrue(basket.size() == 0);

        // в приложении добавить товар в корзину
        // в приложении добавить товар в корзину
        // в приложении добавить товар в корзину
        // удалить все товары из корзины
        // может вставить проверку что корзина пуста ?????

        /*
        1) открыть главную страницу.
        2) открыть первый товар из списка.
        3) добавить его в корзину (при этом может случайно добавиться
        товар, который там уже есть, ничего страшного).
        4) подождать, пока счётчик товаров в корзине обновится.
        5) вернуться на главную страницу, повторить предыдущие шаги ещё
        два раза, чтобы в общей сложности в корзине было 3 единицы товара.
        6) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout).
        7) удалить все товары из корзины один за другим, после
        каждого удаления подождать, пока внизу обновится таблица.
         */
    }

    /*
    // Метод проверяющий есть ли элемент:
    private boolean areElementsPresent(WebDriver driver, By locator){
        return driver.findElements(locator).size() > 0;
    }
    */

    /*
    private void addOneProductToBasket(){
        System.out.print("\nНачало функции addOneProductToBasket\n");
        System.out.print("Открываю первый товар из списка Most Popular\n");
        driver.findElement(By.xpath("//div[@id='box-most-popular']//ul/li[1]")).click();
        int currentAmount = Integer.parseInt(driver.findElement(By.xpath("//span[@class='quantity']")).getText());
        int increasedAmount = currentAmount + 1;
        System.out.print("currentAmount = " + currentAmount + "\n");
        System.out.print("increasedAmount = " + increasedAmount + "\n");

        // Для некоторых товаров необходимо указать размер:
        if (areElementsPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            driver.findElement(By.xpath("//select[@name='options[Size]']")).sendKeys("Small");
        }

        System.out.print("Жму кнопку для добавления в корзину\n");
        driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();

        System.out.print("Жду пока счётчик товаров в корзине обновится... ");
        // increasedAmount = 22; // для проверки падения теста
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//span[@class='quantity']"),
                String.valueOf(increasedAmount)
        ));
        System.out.print("Счётчик обновлён!\n");

        System.out.print("Возвращаюсь на главную страницу\n");
        driver.findElement(By.xpath("//li[@class='general-0']")).click();
        System.out.print("Конец функции addOneProductToBasket\n");
    }
    */

    /*
    private void deleteAllProductsFromBasket(){
        System.out.print("\nНачало функции deleteAllProductsFromBasket\n");
        System.out.print("Открываю корзину\n");
        driver.findElement(By.xpath("//div[@id='cart']/a[@class='link']")).click();
        int amountProductsInBasket = driver.findElements(By.xpath("//ul[@class='shortcuts']/li")).size();
        System.out.print("Количество товаров в корзине = " + amountProductsInBasket + "\n");

        for (int i=1; i<=amountProductsInBasket; i++){
            int currentLines = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr")).size();
            int newLines = currentLines - 1;
            System.out.print("Текущее количество строк в таблице: " + currentLines + "\n");
            System.out.print("Удаляю один товар\n");
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();

            if (i<amountProductsInBasket){
                System.out.print("В таблице осталось строк: " + newLines + "\n");
                System.out.print("Жду пока количество строк в таблице обновится... ");
                // newLines = 12; // для проверки падения теста
                (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr"),
                        newLines
                ));
                System.out.print("Таблица обновилась!\n");
            }
            else {
                System.out.print("Жду появления сообщения о том что корзина пуста... ");
                String expectedMessage = "There are no items in your cart.";
                // expectedMessage = "Test Message"; // для проверки падения теста
                (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                        By.xpath("//div[@id='checkout-cart-wrapper']//em"),
                        expectedMessage
                ));
                System.out.print("Сообщение появилось!\n");
            }
        }
        System.out.print("Конец функции deleteAllProductsFromBasket\n");
    }
    */

    /*
    @AfterClass
    public static void stop(){
        System.out.print("\n\n***** Внутри метода stop() *****\n\n");

        driver.quit();
        driver = null;
    }
    */
}
