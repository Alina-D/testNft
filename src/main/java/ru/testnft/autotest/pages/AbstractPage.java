package ru.testnft.autotest.pages;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.testnft.autotest.helpers.ConfigContainer;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс предоставляет базовый функционал для классов-наследников
 */
public abstract class AbstractPage {

    // ------------------------------------------- Поля класса ----------------------------------------------

    protected ConfigContainer config;
    // Инициализация логгера Logback
    protected static final Logger logger = LoggerFactory.getLogger(AbstractPage.class);
    // Допустимое время ожидания появления элемента на странице (миллисекунды)
    protected int waitingTimeMs = 10000;
    // Ожидания появления элемента на странице (10 мин. в миллисекундах)
    protected int waitingTime10Min = 600000;
    // Интервал времени проверки появление ожидаемого элемента на странице (миллисекунды)
    protected int intervalMs = 1;

    // ------------------------------------------- Конструктор -----------------------------------------------
    public AbstractPage() {
        this.config = ConfigContainer.getInstance();
    }

    // --------------------------------------------- Методы --------------------------------------------------

    /**
     * Вернуть элемент типа SelenideElement ($x или $) автоматически распознав тип локатора по его содержимому.
     *
     * @param locator локатор элемента
     * @return элемент типа SelenideElement ($x или $)
     */
    protected SelenideElement getSelenide(String locator) {
        return locator.contains("//") ? $x(locator) : $(locator);
    }

    /**
     * Нажать на элемент
     *
     * @param locator - локатор элемента (SelenideElement)
     */
    protected void clickElement(SelenideElement locator) {
        locator.waitUntil(visible, waitingTimeMs, intervalMs).click();
    }

    /**
     * Нажать на элемент
     *
     * @param locator - локатор элемента (String)
     */
    protected void clickElement(String locator) {
        getSelenide(locator).waitUntil(visible, waitingTimeMs, intervalMs).click();
    }

    /**
     * Очистить поле
     *
     * @param locator - локатор элемента
     */
    protected void clearField(SelenideElement locator) {
        locator.waitUntil(visible, waitingTimeMs, intervalMs).clear();
    }

    /**
     * Установить значение в поле
     *
     * @param locator - локатор элемента
     */
    protected void setFieldValue(SelenideElement locator, String value) {
        locator.waitUntil(visible, waitingTimeMs, intervalMs).sendKeys(value);
    }

    /**
     * Получить текст элемента
     *
     * @param locator - локатор элемента
     * @return текст элемента (String)
     */
    protected String getElementText(SelenideElement locator) {
        return locator.waitUntil(visible, waitingTimeMs, intervalMs).getText();
    }

}
