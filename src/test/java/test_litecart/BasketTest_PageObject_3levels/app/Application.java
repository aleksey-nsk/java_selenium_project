package test_litecart.BasketTest_PageObject_3levels.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test_litecart.BasketTest_PageObject_3levels.pages.BasketPage;
import test_litecart.BasketTest_PageObject_3levels.pages.MainPage;
import test_litecart.BasketTest_PageObject_3levels.pages.ProductPage;
import java.util.concurrent.TimeUnit;

// Все технические подробности спрятаны внутрь
// класса Application
// В нем создаем драйвер и используем его
public class Application {

    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;

    public Application() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    // Чисто для отладки. Удалить потом!!!!
    private boolean areElementsPresent(WebDriver driver, By locator){
        return driver.findElements(locator).size() > 0;
    }

    // Метод для добавления 1 товара в корзину:
    public void addOneProductToBasket(){
        System.out.println("\nМетод для добавления 1 товара в корзину");
        mainPage.open();
        System.out.println("Открываю первый товар из списка Most Popular");
        mainPage.firstProduct().click();
        int currentAmount = Integer.parseInt(productPage.amountProductsInBasket().getText());
        int increasedAmount = currentAmount + 1;
        System.out.print("currentAmount = " + currentAmount + "\n");
        System.out.print("increasedAmount = " + increasedAmount + "\n");

        // Для некоторых товаров необходимо указать размер:
        productPage.selectSizeIfPresent();
        /* if (areElementsPresent(driver, By.xpath("//select[@name='options[Size]']"))) {
            // driver.findElement(By.xpath("//select[@name='options[Size]']")).sendKeys("Small");
            productPage.selectSize();
        } */

        System.out.println("Жму кнопку для добавления в корзину");
        productPage.addToBasketButton().click();

        System.out.println("Жду пока счётчик товаров в корзине обновится");
        // increasedAmount = 22; // для проверки падения теста
        /* (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBePresentInElement(
                By.xpath("//span[@class='quantity']"),
                String.valueOf(increasedAmount)
        )); */
        productPage.waitForCounterUpdating(currentAmount);
    }

    // Метод для удаления всех товаров из корзины:
    public void deleteAllProductsFromBasket() {
        System.out.println("\nНачало функции deleteAllProductsFromBasket");
        System.out.println("Открываю корзину");
        mainPage.open();
        mainPage.openBasketButton().click();

        int amountProductsInBasket = basketPage.productsInBasket().size();
        System.out.print("Количество товаров в корзине = " + amountProductsInBasket + "\n");

        for (int i=1; i<=amountProductsInBasket; i++){
            int oldLines = basketPage.linesInTable().size();
            // int newLines = oldLines - 1;
            System.out.print("Текущее количество строк в таблице: " + oldLines + "\n");

            System.out.print("Удаляю один товар\n");
            basketPage.removeProductButton().click();

            if (i<amountProductsInBasket){
                // System.out.print("В таблице осталось строк: " + newLines + "\n");
                System.out.println("Жду пока количество строк в таблице обновится... ");
                basketPage.waitForTableUpdating(oldLines);
                // System.out.print("Таблица обновилась!\n");
            }
            else {
                // System.out.print("Жду появления сообщения о том что корзина пуста... ");
                basketPage.waitForEmptyBasketMessage();
            }
        }
        System.out.print("Конец функции deleteAllProductsFromBasket\n");

        // Чисто для отладки. Удалить потом!!!!
        if (areElementsPresent(driver, By.xpath("//div[@id='net-takogo']"))) { System.out.println("элемент найден"); }
    }
}
