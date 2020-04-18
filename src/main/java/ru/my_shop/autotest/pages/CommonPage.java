package ru.my_shop.autotest.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;

public class CommonPage extends AbstractPage {

    // Поле "Поиск"
    private static final SelenideElement SEARCH_FIELD = $("input#f14_6");
    // Кнопка "Поиск"
    private static final SelenideElement SEARCH_BUTTON = $("input#search_submit");
    // Элемент меню с указанным наименованием (на странице содержится несколько элементов меню)
    private static final String MENU_ELEMENT_WITH_NAME_XPATH = "//div[@id = 'menu-catalog-btn']//div[contains(., '%s')]";
    // Ссылка на указанную категорию (на странице содержится несколько категорий)
    private static final String CATEGORY_WITH_NAME_LINK_XPATH = "//a[contains(@id, 'top')]/div[contains(., '%s')]";
    // Ссылка на указанный элемент каталога (подкатегория/раздел/подраздел)
    private static final String CATALOG_ELEMENT_WITH_NAME_LINK_XPATH =
            "//tbody//a[contains(@href, '/shop/catalogue') and contains(@title, '%s')] ";
    // Ссылка [Корзина]
    private static final SelenideElement CARD_LINK = $x("//div[@id = 'cart_total']/..");
    // Ссылка с названием
    protected static final String LINK_WITH_NAME_XPATH = "//a[contains(., '%s')]";




    /**
     * Поиск товара по имени товара
     *
     * @param productName - наименование товара
     */
    public CommonPage searchProduct(String productName) {
        clearField(SEARCH_FIELD);
        logger.info("Поле 'Поиск' очищено");
        setValueInField(SEARCH_FIELD, productName);
        logger.info("В поле 'Поиск' установлено значение {}", productName);
        clickElement(SEARCH_BUTTON);
        logger.info("Нажата кнопка 'Поиск'");
        return this;
    }

    /**
     * Нажать на элемент меню
     *
     * @param menuElementName - наименование элемента меню
     */
    public CommonPage clickElementMenu(String menuElementName) {
        clickElement(format(MENU_ELEMENT_WITH_NAME_XPATH, menuElementName));
        logger.info(format("Нажат элемент меню '%s'", menuElementName));
        return this;
    }

    /**
     * Выбрать категорию
     *
     * @param categoryName - наименование категории
     */
    public CommonPage selectCategory(String categoryName) {
        clickElement(format(CATEGORY_WITH_NAME_LINK_XPATH, categoryName));
        logger.info(format("Выбрана категория '%s'", categoryName));
        return this;
    }

    /**
     * Выбрать элемент каталога (подкатегория/раздел/подраздел)
     *
     * @param catalogElement - наименование элемента каталога
     */
    public CommonPage selectCatalogElement(String catalogElement) {
        clickElement(format(CATALOG_ELEMENT_WITH_NAME_LINK_XPATH, catalogElement));
        logger.info(format("Элемент каталога '%s' успешно выбран", catalogElement));
        return this;
    }

    /**
     * Нажать на ссылку [Корзина]
     */
    public CommonPage goToCard() {
        clickElement(CARD_LINK);
        logger.info(format("Нажать на ссылка [Корзина]"));
        return this;
    }
}
