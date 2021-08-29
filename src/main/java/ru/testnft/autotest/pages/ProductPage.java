package ru.testnft.autotest.pages;

import com.codeborne.selenide.ElementsCollection;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

/**
 * Класс описывающий страницу "Главная страница"
 */
public class ProductPage extends CommonPage {

    // ------------------------------------------- Конструктор -----------------------------------------------
    public ProductPage() {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    private static final String TIME_XPATH = "//div[contains(text(), '%s')]/preceding-sibling::div";

    // ---------------------------------------- ElementsCollection -------------------------------------------
    // Список с информацией о товаре на главной странице
    private ElementsCollection productInfoOnHomePageList = $$("[data-o='show_case_6'] .hb_text div");

    // --------------------------------------------- Методы ------------------------------------------------


    /**
     * Обновляет страницу за 10 мин до начала торгов
     */
    public void refreshBoxPage() {

        boolean day = getTime("дн.").equals("00");;
        boolean hour = getTime("ч.").equals("00");
        boolean min = Integer.parseInt(getTime("мин.")) < 10;

        while (day && hour && min) {

            refresh();

            day = getTime("дн.").equals("00");
            hour = getTime("ч.").equals("00");
            min = Integer.parseInt(getTime("мин.")) < 10;

            logger.info(format("До окончания торгов: %s дн. %s ч. %s мин."),
                    getTime("дн."),
                    getTime("ч."),
                    getTime("мин."));
        }

    }

    public String getTime(String fieldName) {
        return getElementText(getSelenideElement(format(TIME_XPATH, fieldName)));
    }

}
