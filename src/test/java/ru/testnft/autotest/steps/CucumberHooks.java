package ru.testnft.autotest.steps;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import ru.testnft.autotest.helpers.ConfigContainer;

import static com.codeborne.selenide.Selenide.open;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

/**
 * Класс описывающий Hooks (код выполняющийся до и после каждого теста)
 */
public class CucumberHooks extends AbstractSteps {
    private WebDriver driver;

    @Before
    public void setupTest() {

        // Загружаем настройки тестовой среды из файла "config.properties"
        ConfigContainer.getInstance().loadConfig();

        logger.info("Выполняется открытие браузера");
        ChromeDriverManager.getInstance(CHROME).setup();

        Configuration.startMaximized = true;
        open(config.getProperties("url.authorization"));
        logger.info("Открыта страница авторизации");
    }

    @After
    public void teardown() {
          logger.info("Тест выполнен. Браузер автоматически НЕ закрывается!!!");

//        logger.info("Выполняется закрытие браузера");
//        if (driver != null) {
//            driver.quit();
//        }
    }

}
