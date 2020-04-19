package ru.my_shop.autotest.pages;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.my_shop.autotest.helpers.ConfigContainer;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс предоставляет базовый функционал для классов-наследников
 */
public abstract class AbstractPage {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractPage.class);
    protected ConfigContainer config = ConfigContainer.getInstance();


    // Допустмое время ожидания появления элемента на странице (миллисекунды)
    protected int waitingTimeMs = 10000;
    // Интервал времени проверки появление ожидаемого элемента на странице (миллисекунды)
    protected long intervalMs = 50;


    /**
     * Возвращает элемент типа SelenideElement ($x или $) автоматически распознав тип локатора по его содержимому.
     *
     * @param locator локатор элемента
     * @return элемент типа SelenideElement ($x или $)
     */
    protected SelenideElement getSelenideElement(String locator) {
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
        getSelenideElement(locator).waitUntil(visible, waitingTimeMs, intervalMs).click();
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
    protected void setValueInField(SelenideElement locator, String value) {
        locator.waitUntil(visible, waitingTimeMs, intervalMs).sendKeys(value);
    }
}
