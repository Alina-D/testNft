package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.*;


/**
 * Класс описывающий страницу "Карточка товара"
 */
public class CardProductPage extends CommonPage{


    // todo проверить названия локаторов (xpath)
    // Поле для установки количества товаров в блоке "Купить"
    private static final SelenideElement AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD = $("[name='quantity']");
    // Кнопка "Купить" в блоке "Купить"
    private static final SelenideElement BUY_BUTTON = $("input[value='Купить'");
    // Имя товара
    private static final SelenideElement name_product = $("[itemprop='name']");
    // Характеристики товара
    private static final ElementsCollection product_characteristics = $$("#tabs-1 tbody  table tr");
    // Подробное описание товара
    private static final SelenideElement detailed_description_product = $("[itemprop='description']");
    // Основная информация о товаре
    private static final ElementsCollection basic_product_info = $$("[data-o='good_description'] table tr");


    /**
     * Установить количество товара в блок "Купить"
     *
     * @param amountProduct - количество товара
     */
    public CardProductPage setAmountOfProductInBuyBlock(String amountProduct) {
        clearField(AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD);
        setValueInField(AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD, amountProduct);
        logger.info("Установлено количество товара в размере {} шт. в блоке 'Купить'", amountProduct);
        return this;
    }

    /**
     * Добавить товар в корзину из каточки товара
     */
    public CardProductPage addProductToCartFromProductCard() {
        clickElement(BUY_BUTTON);
        logger.info("Нажата кнопка 'Купить' в блоке 'Купить'");
        return this;
    }

    /**
     * Проверяет, что карточка товара содержит верную информацию
     * // todo добавить анотацию return&
     */
    public CardProductPage checksThatProductCardContainsCorrectInfo() {
        assertFalse("Наименование товара на карточке товара указано не верно",
                name_product.getText().contains(config.getParameter("nameProduct").toString()));
        assertNotEquals("Производитель на карточке товара указан не верно",
                name_product.getText(), config.getParameter("manufacturerProduct").toString());
        assertNotEquals("Цена на карточке товара указана не верно",
                name_product.getText(), config.getParameter("priceProduct").toString());
        return this;
    }

    /**
     * Получает информацию о товаре на карточке товара
     */
    public CardProductPage getProductInfoOnCardProduct() {
        // todo подумать как назвать информацию о товаре product_info
        basic_product_info.forEach(product_info -> logger.info(product_info.getText()));
        logger.info(detailed_description_product.getText());
        product_characteristics.forEach(characteristic -> logger.info(characteristic.getText()));
        return this;
    }
}
