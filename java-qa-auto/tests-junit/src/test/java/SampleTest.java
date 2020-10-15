import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class SampleTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);    //создание переменной, которую можно использовать для получения параметров из конфиг.пропертис

    @Test
    public void Log() {
        logger.info("this is info");
    }


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        //использование хром-опции. капабилитис как таковые устарели. Аналогично опции устанавливаются для файрфокса, только исп-ся FirefoxOptional..
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("headless");                                     //прохождение тестов без визуального открытия браузера (чисто по дом-модели)
            options.setAcceptInsecureCerts(false);                                  //игнорирование сертификатов
            options.setHeadless(true);                                              //также тесты проходят без визуального открытия браузера
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);                    //определяет что считать загруженной страницей. EAGER - дом без цсс, фреймов; NORMAL -  по ум; NONE - ждет загр только страницы (?)
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);  //отвечает за алерты (типа "нажмите ок чтобы подписаться и прочее что блокирует работу). это пропускает их
            options.addArguments("--window-size=1920,1080");                        //установка разрешения (или возможно только размера окна?)
            //установка прокси
                /*Proxy testProxy = new Proxy();
                testProxy.setHttpProxy("myproxy:8086");
                options.setProxy(testProxy);*/
        driver = new ChromeDriver(options);
        logger.info("Драйвер поднят");

        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);      //установка таймаута (возможно использовать для ожидания загрузки)
    }

    @Ignore
    @Before
    public void setUp2() {
        WebDriverManager.chromedriver().setup();
        //установка настроек через капабилити. Метод уже устарел, но имеет место быть
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setAcceptInsecureCerts(false);
        caps.setCapability("UnexpectedAlertBehaviour", "DISMISS");
        caps.setCapability("loadStrategy", "PageLoadStrategy.EAGER");

        driver = new ChromeDriver(caps);
        logger.info("Драйвер поднят вторым способом");
    }

    @Test
    public void openPage() {
        driver.get(cfg.url());
        logger.info("Открыта страница отус");

        //работа с куками
        Cookie myCookie = new Cookie("Otus1", "Value1");
        driver.manage().addCookie(myCookie);
        driver.manage().addCookie(new Cookie( "Otus2", "Value2"));
        Cookie myCookie3 = new Cookie("Otus3", "Value3");
        driver.manage().addCookie(myCookie3);
        driver.manage().addCookie(new Cookie( "Otus4", "Value4"));
        logger.info("Установлены куки");

        System.out.println(driver.manage().getCookies());
        logger.info("Получены и выведены на экран все куки");

        System.out.println(driver.manage().getCookieNamed("Otus1"));
        logger.info("Получена и выведена на экран кука по заданному имени");

        driver.manage().deleteCookieNamed("Otus2");
        logger.info("Удалена кука по имени");

        driver.manage().deleteCookie(myCookie3);
        logger.info("Удалена кука по имени");

        driver.manage().deleteAllCookies();
        logger.info("удалены все куки");

        //assert driver.manage().getCookies().isEmpty();
        assertTrue("Куки очищены не до конца: " + driver.manage().getCookies(), driver.manage().getCookies().isEmpty()); //todo почемуто периодически проверка сама по себе падает, то есть куки не все очищаются. непонятно
                                                                                                                            //todo остается [_fbp=fb.1.1602723943484.1140202366; expires=ср, 13 янв. 2021 04:05:43 MSK; path=/; domain=.otus.ru]
        logger.info("Проверка на отсутсвие куков успешно завершена");
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
