package ru.my_shop.autotest.pages;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public abstract class AbstractPage {

    protected static final Logger logger
            = LoggerFactory.getLogger(AbstractPage.class);

    protected int delayTimeMs = 100000;
    protected int delayTime300 = 300000; //30000
    protected long pollingIntervalMs = 50;
    protected int timeForIsDisplay1 = 5;
    protected int timeForIsDisplay2 = 15; //30


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
        locator.waitUntil(visible, delayTimeMs, pollingIntervalMs).click();
    }

    /**
     * Нажать на элемент
     *
     * @param locator - локатор элемента (String)
     */
    protected void clickElement(String locator) {
        getSelenideElement(locator).waitUntil(visible, delayTimeMs, pollingIntervalMs).click();
    }

    /**
     * Очистить поле
     *
     * @param locator - локатор элемента
     */
    protected void clearField(SelenideElement locator) {
        locator.waitUntil(visible, delayTimeMs, pollingIntervalMs).clear();
    }

    /**
     * Установить значение в поле
     *
     * @param locator - локатор элемента
     */
    protected void setValueInField(SelenideElement locator, String value) {
        locator.waitUntil(visible, delayTimeMs, pollingIntervalMs).sendKeys(value);
    }
}
