import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class WaitsTest extends SampleTest {
    private Logger logger = LogManager.getLogger(WaitsTest.class);

    @Test
    public void testBootstrap() throws InterruptedException {
        final String URL = "https://ng-bootstrap.github.io/#/components/alert/examples";
        final String ERROR_MESSAGE = "Message are equal";
        String button  = "//button[contains(text(),'Change message')]";
        String alertTextBefore;
        String alertTextAfter;

        driver.get(URL);
        logger.info(String.format("Открыта страница %s", URL));
        WebElement element = driver.findElement(By.xpath(button));
        logger.info("Кнопка найдена");
        alertTextBefore = getAlertText(element);
        logger.info(String.format("Alert text %s", alertTextBefore));

        logger.info("Начато ожидание");
        Thread.sleep(1500);
        logger.info("Ожидание закончено");

        alertTextAfter = getAlertText(element);
        logger.info(String.format("Alert text %s", alertTextAfter));

        Assert.assertNotEquals(alertTextBefore, alertTextAfter, ERROR_MESSAGE);
    }

    private String getAlertText(WebElement element){
        String alert = "//ngb-alert[contains(text(), 'Message successfully changed')]";  //  "//div[@class='card-body']//ngb-alert[contains(text(), 'Message successfully changed')]"
        element.click();
        WebElement alertBox = driver.findElement(By.xpath(alert));

        new WebDriverWait(driver, 4).until(visibilityOf(alertBox));  //ждём появления бокса
        return alertBox.getText();
    }
}
