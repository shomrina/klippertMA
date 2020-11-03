import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SampleTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);    //создание переменной, которую можно использовать для получения параметров из конфиг.пропертис

    @Ignore
    @Test
    public void Log() {
        logger.info("this is info");
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");

    }

    @Ignore
    @Test
    public void openPage() {
        driver.get(cfg.url());
        logger.info("Открыта страница отус");
    }

    @Test //пример из кода преподавателя!!!
    public void fillAboutMyself() {
        //1. Открыть otus.ru
        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        //2. Авторизоваться на сайте
        auth();
        //3. Войти в личный кабинет
        enterLK();
        //4. В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        driver.findElement(By.id("id_fname")).clear(); //имя
        driver.findElement(By.id("id_fname_latin")).clear();
        driver.findElement(By.id("id_lname")).clear();
        driver.findElement(By.id("id_lname_latin")).clear();
        driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).clear();

        driver.findElement(By.id("id_fname")).sendKeys("Марина");
        driver.findElement(By.id("id_fname_latin")).sendKeys("Marina");
        driver.findElement(By.id("id_lname")).sendKeys("Клипперт");
        driver.findElement(By.id("id_lname_latin")).sendKeys("Klippert");
        driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).sendKeys("06.06.1987");
        //Страна
        if(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText().contains("Россия"))
        {
            driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Россия')]")).click();
        }
        //Город
        if(!driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText().contains("Санкт-Петербург"))
        {
            driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Санкт-Петербург')]")).click();
        }
        //уровень англ.
        if(!driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText().contains("Средний (Intermediate)"))
        {
            driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).click();
            driver.findElement(By.xpath("//*[contains(text(), 'Средний (Intermediate)')]")).click();
        }
        //todo добавить контактную информацию (2 шт)
        //контактная информация. добавление контактов
        driver.findElement(By.cssSelector("button.lk-cv-block__action:nth-child(6)")).click();
        Select dropdownContact = new Select(driver.findElement(By.name("contact-1-service")));
        dropdownContact.selectByVisibleText("Facebook");
        driver.findElement(By.id("id_contact-1-value")).sendKeys("link1");
        logger.info("Добавлен контакт фейсбук");

         /*  driver.findElement(By.cssSelector("button.lk-cv-block__action:nth-child(6)")).click();
        dropdownContact.selectByVisibleText("OK");
        driver.findElement(By.id("id_contact-3-value")).sendKeys("link2");
        logger.info("Добавлен контакт ОК");*/

        //5. Нажать сохранить
        driver.findElement(By.xpath("//*[contains(text(), 'Сохранить и продолжить')]")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://otus.ru/lk/biography/skills/"));
        //6. Открыть https://otus.ru в “чистом браузере”
        driver.quit();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
        driver.get(cfg.url());
        //7. Авторизоваться на сайте
        auth();
        //8. Войти в личный кабинет
        enterLK();
        //9. Проверить, что в разделе о себе отображаются указанные ранее данные
        Assert.assertEquals("Marina", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assert.assertEquals("Клипперт", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assert.assertEquals("Klippert", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assert.assertEquals("06.06.1987", driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).getAttribute("value"));
        Assert.assertEquals("Россия", driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText());
        Assert.assertEquals("Санкт-Петербург", driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText());
        Assert.assertEquals("Средний (Intermediate)", driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).getText());

        ///todo добавить проверку что есть инфа о контактах




    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void auth()
    {
        String login = "milagrous@gmail.com";
        String password = "fJ!ntyy2wRg9Fdh";
        String locator = "button.header2__auth";
        driver.findElement(By.cssSelector(locator)).click();
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).sendKeys(login);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
        logger.info("Авторизация прошла успешно");
    }

    private void enterLK()
    {
        //todo переписать переход через экшены и клики (не по ссылке)
        String locator = ".ic-blog-default-avatar";
        WebElement icon = driver.findElement(By.cssSelector(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(icon).build().perform();
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Перешли в личный кабинет");
    }






    @Ignore
    @Test  //моя попытка написать тест, не работает!!! но могут быть полезны какието вещи, потому пока не удаляю
    public void myTestFill(){
        //мои потуги
        //1 открыть отус
        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        //2 авторизоваться на сайте
        driver.findElement(By.cssSelector("button.header2__auth")).click(); //кнопка вход
        logger.info("Осуществлен переход на страницу авторизации");
        //3 вход в ЛК
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).clear(); //email
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).sendKeys("milagrous@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Введите пароль']")).clear();  //pass
        driver.findElement(By.xpath("//input[@placeholder='Введите пароль']")).sendKeys("fJ!ntyy2wRg9Fdh");
        driver.findElement(By.cssSelector("input[placeholder='Введите пароль'")).clear();  //pass
        driver.findElement(By.cssSelector("input[placeholder='Введите пароль'")).sendKeys("fJ!ntyy2wRg9Fdh");
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();  //нажатие кнопки войти
        logger.info("Авторизация выполнена успешно: открыт личный кабинет");
        //Thread.sleep(1000);
        //4 Заполнение личных данных и двух контактов в разделе "О себе"
        driver.get("https://otus.ru/lk/biography/personal/"); //поскольку цель теста не проверка выпадающего списка, то используем обычный переход по ссылке, но если надо, то используется action
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://otus.ru/lk/biography/personal/"));
        logger.info("Открыт раздел 'О себе'");
        driver.findElement(By.id("id_fname")).clear(); //имя
        driver.findElement(By.id("id_fname")).sendKeys("Марина");
        driver.findElement(By.id("id_lname")).clear(); //фамилия
        driver.findElement(By.id("id_lname")).sendKeys("Клипперт");
        driver.findElement(By.name("date_of_birth")).clear(); //дата рождения
        driver.findElement(By.name("date_of_birth")).sendKeys("06.06.1987");
        driver.findElement(By.name("date_of_birth")).sendKeys(Keys.ENTER);
        logger.info("Заполнены ФИО и дата рождения");

        driver.findElement(By.name("country")).clear();
        driver.findElement(By.name("country")).findElements(By.cssSelector(".lk-cv-block__select-scroll_country > button")).get(1).click();
        //выбор страны
       // driver.findElement(By.name("country")).clear();
        driver.findElement(By.name(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).click();
        Select dropdownCountry = new Select(driver.findElement(By.className("lk-cv-block__select-scroll_country")));
        dropdownCountry.selectByVisibleText("Россия");
        logger.info("Заполнена Страна");
        //выбор города
       // driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).click();
        Select dropdownCity = new Select(driver.findElement(By.className("lk-cv-block__select-scroll_city")));
        dropdownCity.selectByVisibleText("Санкт-Петербург");
        logger.info("Заполнен Город");
        //выбор уровня англ
       // driver.findElement(By.name("english_level")).clear();
        driver.findElement(By.name("english_level")).click();
        Select dropdownEngLvl = new Select(driver.findElement(By.cssSelector(".lk-cv-block__select-options.lk-cv-block__select-scroll")));
        dropdownEngLvl.selectByVisibleText("Средний (Intermediate)");
        logger.info("Заполнен уровень английского");
        //готовность к переезду
        driver.findElement(By.className("container__col container__col_9")).findElements(By.cssSelector("> label")).get(0).click();
        logger.info("указана готовность к переезду");

        //контактная информация. добавление контактов
        driver.findElement(By.cssSelector("button.lk-cv-block__action:nth-child(6)")).click();
        Select dropdownContact = new Select(driver.findElement(By.name("contact-1-service")));
        dropdownContact.selectByVisibleText("Facebook");
        driver.findElement(By.id("id_contact-1-value")).sendKeys("link1");
        logger.info("Добавлен контакт фейсбук");

        driver.findElement(By.cssSelector("button.lk-cv-block__action:nth-child(6)")).click();
        dropdownContact.selectByVisibleText("OK");
        driver.findElement(By.id("id_contact-3-value")).sendKeys("link2");
        logger.info("Добавлен контакт ОК");

        //5 нажать Сохранить
        driver.findElement(By.cssSelector("button.button_md-4:nth-child(2)")).submit();
    }
}
