package ru.testnft.autotest.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
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

    // Элемент меню с указанным наименованием (на странице содержится несколько элементов меню)
    private static final String TOOLBAR_XPATH = "//div[contains(text(), '%s')]";


    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Ожидает авторизацию 16 минут
     *
     * @param elementText - текст элемента
     * @param min - минуты ожидания
     * @return this - ссылка на текущий объект
     */
    public CommonPage waitAuthorization(String elementText, int min) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(min);
        getSelenideElement(format(TOOLBAR_XPATH, elementText)).waitUntil(visible, minutes, intervalMs);
        logger.info("Элемент с названием %s присутствует на странице");
        return this;
    }




}
