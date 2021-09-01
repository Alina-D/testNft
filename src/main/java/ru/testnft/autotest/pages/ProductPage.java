package ru.testnft.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.junit.Assert.*;

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

        printTimeBeforeBidding();

        while (!(day && hour && min)) {

            sleep(500000);
            refresh();

            day = getTime("дн.").equals("00");
            hour = getTime("ч.").equals("00");
            min = Integer.parseInt(getTime("мин.")) < 10;

            printTimeBeforeBidding();
        }
        logger.info("Осталось менее 10 мин. до начала продаж!!!");
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
     * Ждет и нажимает кнопку с указанным именем
     *
     * @param btnName - имя кнопки
     */
    public void waitAndClickBtn(String btnName) {
        String element = format(BTN_TEXT_XPATH, btnName);

        getSelenide(element).waitUntil(visible, 600000, intervalMs);

        String script = format(
                "document.evaluate(\"%s\", document, null, " +
                "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();",
                element);

        logger.info(format("Будет выполнен скрипт: [%s]", script));
        executeJavaScript(format(script, element));
        logger.info(format("Кнопка [%s] появилась. Выполнено нажатие на кнопку.", btnName));
    }

    /**
     * Устанавливает кол-во боксов
     *
     * @param count - имя кнопки
     */
    public void setCountBoxes(String count) {
        SelenideElement countInput = getSelenide(COUNT_XPATH);
        countInput.waitUntil(visible, 600000, intervalMs);
        logger.info("Поле количества боксов появилось на странице");

        String script = format (
            "document.evaluate(\"%s\", document, null, " +
                "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.value = \"%s\"",
                COUNT_XPATH, count);

        logger.info(format("Будет выполнен скрипт: [%s]", script));
        executeJavaScript(script);
        logger.info(format("Скрипт выполнен: [%s]", script));

        String currentCountBoxes = countInput.getAttribute("value");
        if (!currentCountBoxes.equals(count)) {
            logger.info("Кол-во боксов НЕ установлено в поле!!!");
            logger.info(format("Текущее кол-во боксов: [%s]", currentCountBoxes));
            logger.info("Выполняется установка с помощью кнопки [+]");

            int countTries = 1;
            while (countTries < Integer.parseInt(count)) {
                getSelenide(PLUS_XPATH).click();
                countTries++;
                logger.info(format("Выполнено нажатие на [+]. Кол-во установленных боксов: [%s]", countTries));
            }

            assertEquals("Кол-во в поле не соответствует кол-ву выставляемых боксов",
                    count, getSelenide(COUNT_XPATH).getAttribute("value"));

            logger.info(format("Установлено верное кол-во боксов: [%s]",count));
        }
    }
}
