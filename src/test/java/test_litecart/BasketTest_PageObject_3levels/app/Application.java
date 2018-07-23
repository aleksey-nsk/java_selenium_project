package test_litecart.BasketTest_PageObject_3levels.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_litecart.BasketTest_PageObject_3levels.pages.BasketPage;
import test_litecart.BasketTest_PageObject_3levels.pages.MainPage;
import test_litecart.BasketTest_PageObject_3levels.pages.ProductPage;

import java.util.concurrent.TimeUnit;

// Все технические подробности спрятаны внутрь
// класса Application
// В нем создаем драйвер и используем его
public class Application {

    // private WebDriverWait wait;
    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;

    public Application() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver();
        // wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    public void quit() {
        driver.quit();
        driver = null; // сам дописал
    }

    // Метод проверяющий есть ли элемент:
    private boolean areElementsPresent(WebDriver driver, By locator){
        return driver.findElements(locator).size() > 0;
    }

    // Метод для добавления 1 товара в корзину:
    public void addOneProductToBasket(){
        System.out.println("Начало функции addOneProductToBasket");
        System.out.println("Открываю главную страницу магазина"); // добавил сюда
        // driver.get("http://localhost/litecart/en/"); // добавил сюда
        mainPage.open();


        // System.out.print("\nНачало функции addOneProductToBasket\n");
        System.out.println("Открываю первый товар из списка Most Popular");
        // driver.findElement(By.xpath("//div[@id='box-most-popular']//ul/li[1]")).click();
        mainPage.firstProduct().click();
        // int currentAmount = Integer.parseInt(driver.findElement(By.xpath("//span[@class='quantity']")).getText());
        int currentAmount = Integer.parseInt(productPage.amountProductsInBasket().getText());
        int increasedAmount = currentAmount + 1;
        System.out.print("currentAmount = " + currentAmount + "\n");
        System.out.print("increasedAmount = " + increasedAmount + "\n");

        // Для некоторых товаров необходимо указать размер:
        if (areElementsPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            // driver.findElement(By.xpath("//select[@name='options[Size]']")).sendKeys("Small");
            productPage.selectSize("Small");
        }

        System.out.print("Жму кнопку для добавления в корзину\n");
        // driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
        productPage.addToBasketButton().click();

        System.out.println("Жду пока счётчик товаров в корзине обновится... ");
        // increasedAmount = 22; // для проверки падения теста
        /* (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//span[@class='quantity']"),
                String.valueOf(increasedAmount)
        )); */
        productPage.waitForCounterUpdating(currentAmount);
        System.out.println("Счётчик обновлён!");

         // System.out.print("Возвращаюсь на главную страницу\n");
        // driver.findElement(By.xpath("//li[@class='general-0']")).click();
        System.out.println("Конец функции addOneProductToBasket\n");
    }

    // Метод для удаления всех товаров из корзины:
    public void deleteAllProductsFromBasket() {
        System.out.println("Начало функции deleteAllProductsFromBasket");
        System.out.println("Открываю корзину");
        // driver.findElement(By.xpath("//div[@id='cart']/a[@class='link']")).click();
        mainPage.open();
        mainPage.openBasketButton().click();
        // int amountProductsInBasket = driver.findElements(By.xpath("//ul[@class='shortcuts']/li")).size();
        int amountProductsInBasket = basketPage.productsInBasket().size();
        System.out.print("Количество товаров в корзине = " + amountProductsInBasket + "\n");

        for (int i=1; i<=amountProductsInBasket; i++){
            // int currentLines = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr")).size();
            int oldLines = basketPage.linesInTable().size();
            int newLines = oldLines - 1;
            System.out.print("Текущее количество строк в таблице: " + oldLines + "\n");

            System.out.print("Удаляю один товар\n");
            // driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            basketPage.removeProductButton().click();

            if (i<amountProductsInBasket){
                System.out.print("В таблице осталось строк: " + newLines + "\n");
                System.out.print("Жду пока количество строк в таблице обновится... ");
                // newLines = 12; // для проверки падения теста
                /* (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath("//table[@class='dataTable rounded-corners']/tbody/tr"),
                        newLines
                )); */
                basketPage.waitForTableUpdating(oldLines);
                System.out.print("Таблица обновилась!\n");
            }
            else {
                System.out.print("Жду появления сообщения о том что корзина пуста... ");
                String expectedMessage = "There are no items in your cart.";
                // expectedMessage = "Test Message"; // для проверки падения теста
                /* (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                        By.xpath("//div[@id='checkout-cart-wrapper']//em"),
                        expectedMessage
                )); */
                basketPage.waitForEmptyBasketMessage(expectedMessage);
                System.out.print("Сообщение появилось!\n");
            }
        }
        System.out.print("Конец функции deleteAllProductsFromBasket\n");

        // Чисто для отладки. Удалить потом!!!!
        if (areElementsPresent(driver, By.xpath("//div[@id='net-takogo']"))) { System.out.println("элемент найден"); }
    }
}
