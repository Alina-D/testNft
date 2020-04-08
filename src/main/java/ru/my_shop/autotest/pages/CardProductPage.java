package ru.my_shop.autotest.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CardProductPage extends CommonPage{


    // Поле для установки количества товаров в блоке "Купить"
    private static final SelenideElement AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD = $("[name='quantity']");
    // Кнопка "Купить" в блоке "Купить"
    private static final SelenideElement BUY_BUTTON = $("input[value='Купить'");

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
}
