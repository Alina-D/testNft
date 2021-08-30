package ru.testnft.autotest.pages;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

/**
 * Класс описывающий общую логику для всех страниц
 */
public class CommonPage extends AbstractPage {

    // ------------------------------------------- Конструктор -----------------------------------------------
    public CommonPage() {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------------- String -------------------------------------------------

    // Div с указанным текстом
    private static final String DIV_TEXT_XPATH = "//div[contains(text(), '%s')]";
    // Кнопка с указанным текстом
    public static final String BTN_TEXT_XPATH = "//button[contains(text(), '%s')]";

    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Ожидает авторизацию 16 минут
     *
     * @param elementText - текст элемента
     * @param min - минуты ожидания
     */
    public void waitAuthorization(String elementText, int min) {
        long minutes = TimeUnit.MINUTES.toMillis(min);
        getSelenide(format(DIV_TEXT_XPATH, elementText)).waitUntil(visible, minutes, intervalMs);
        logger.info("Элемент с названием %s присутствует на странице");
    }


    /**
     * При всплывании окна "Условия и положения NFT-маркетплейса Binance" принимает условия
     */
    public void acceptTerms() {
        if (getSelenide(format(DIV_TEXT_XPATH, "Условия и положения NFT-маркетплейса Binance")).isDisplayed()) {
            getSelenide(format(BTN_TEXT_XPATH, "Принять")).waitUntil(visible, waitingTime10Min, intervalMs).click();
        }
    }
}
