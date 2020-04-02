package ru.my_shop.autotest.steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HooksCucumber {
    private WebDriver driver;

    @Before
    public void setupTest() {
        System.out.println("Проверка: Хук @Before работает");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://google.com");
    }

    @After
    public void teardown() {
        System.out.println("Проверка: Хук @After работает");
        if (driver != null) {
            driver.quit();
        }
    }

}
