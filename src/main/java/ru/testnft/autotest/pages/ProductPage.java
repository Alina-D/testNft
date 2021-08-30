package ru.testnft.autotest.pages;

import com.codeborne.selenide.ElementsCollection;

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

    private static final String COUNT_XPATH = "//input[@value='1'] | //input[@class='css-6menzp']";
    private static final String PLUS_XPATH =
            "//div[contains(text(), 'Количество')]/following-sibling::div/div/div/div[3] | " +
            "//div[contains(text(), 'Количество')]/following-sibling::div//div[@class='css-vurnku'][2]";

    // ---------------------------------------- ElementsCollection -------------------------------------------
    // Список с информацией о товаре на главной странице
    private ElementsCollection productInfoOnHomePageList = $$("[data-o='show_case_6'] .hb_text div");

    // --------------------------------------------- Методы ------------------------------------------------


    /**
     * Обновляет страницу за 10 мин до начала торгов
     */
    public void refreshBoxPage() {

        boolean day = getTime("дн.").equals("00");
        boolean hour = getTime("ч.").equals("00");
        boolean min = Integer.parseInt(getTime("мин.")) < 10;

        while (!(day && hour && min)) {

            sleep(500000);
            refresh();

            day = getTime("дн.").equals("00");
            hour = getTime("ч.").equals("00");
            min = Integer.parseInt(getTime("мин.")) < 10;

            printTimeBeforeBidding();
        }

    }

    /**
     * Получает значение в одной из ячеек времени
     */
    private String getTime(String fieldName) {
        return getElementText(getSelenide(format(TIME_XPATH, fieldName)));
    }

    /**
     * Печатает оставшееся время до начала торгов
     */
    private void printTimeBeforeBidding() {
        logger.info(format("До окончания торгов: %sдн. %sч. %sмин. %sсек.",
                getTime("дн."),
                getTime("ч."),
                getTime("мин."),
                getTime("сек.")));
    }

    /**
     * Нажимает кнопку с указанным именем
     *
     * @param btnName - имя кнопки
     */
    public void waitAndClickBtn(String btnName) {
        getSelenide(format(BTN_TEXT_XPATH, btnName)).waitUntil(visible, 600000, intervalMs).click();
        logger.info("Кнопка [%s] появилась. Выполнено нажатие на кнопку.");
    }

    /**
     * Устанавливает кол-во боксов
     *
     * @param count - имя кнопки
     */
    public void setCountBoxes(int count) {
        getSelenide(COUNT_XPATH);
    }
}
