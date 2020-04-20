package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.my_shop.autotest.models.ProductModel;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;


/**
 * Класс описывающий страницу "Карточка товара"
 */
public class CardProductPage extends CommonPage {

    // ------------------------------------------- Конструктор -----------------------------------------------
    public CardProductPage() {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------------- String -------------------------------------------------
    // Кнопка в блоке "Купить" (в блоке "Купить" может быть несколько кнопок)
    private static final String BUTTON_IN_BUY_BLOCK = "input[value='%s']";

    // ------------------------------------------ SelenideElement ---------------------------------------------
    // Поле "Количество товара" в блоке "Купить"
    private static final SelenideElement AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD = $("[name='quantity']");
    // Заголовок с именем товара
    private static final SelenideElement NAME_PRODUCT_TITLE = $("[itemprop='name']");
    // Подробное описание товара
    private static final SelenideElement DETAILED_DESCRIPTION_PRODUCT_STRING = $("[itemprop='description']");

    // ----------------------------------------- ElementsCollection --------------------------------------------
    // Список с наименованиями характеристик товара
    private final ElementsCollection NAME_PRODUCT_FEATURES_LIST =
            $$x("//div[@id='tabs-1']//tbody//table//tr/td[1]");
    // Список со значениями характеристик товара
    private final ElementsCollection VALUE_PRODUCT_FEATURES_LIST =
            $$x("//div[@id='tabs-1']//tbody//table//tr/td[2]");
    // Список с основной информацией о товаре
    private final ElementsCollection BASIC_PRODUCT_INFO_LIST = $$("[data-o='good_description'] table tr");


    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Установить количество товара в блок "Купить"
     *
     * @param amountProduct - количество товара
     */
    public CardProductPage setAmountOfProductInBuyBlock(int amountProduct) {
        clearField(AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD);
        setValueInField(AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD, Integer.toString(amountProduct));
        logger.info("Установлено количество товара в размере {} шт. в блоке 'Купить'", amountProduct);
        return this;
    }

    /**
     * Добавить товар в корзину из каточки товара
     */
    public CardProductPage addProductToCartFromProductCard() throws InterruptedException {
        for (int countTries = 0; CART_ICON.getText().contains("0"); countTries++) {
            clickElement(format(BUTTON_IN_BUY_BLOCK, "Купить"));
            Thread.sleep(500);
            assertNotEquals("Не удалось добавить товар в корзину", countTries, 20);
        }
        logger.info("Нажата кнопка 'Купить' в блоке 'Купить'");
        return this;
    }

    /**
     * Проверяет, что карточка товара содержит верную информацию
     * // todo добавить анотацию return, перепроверить. что все верно
     */
    public CardProductPage checksThatProductCardContainsCorrectInfo() {
        assertFalse("Наименование товара на карточке товара указано не верно",
                NAME_PRODUCT_TITLE.getText().contains(config.accessProductModel().getName()));
        logger.info("Карточка товара содержит верное наименование '{}'", NAME_PRODUCT_TITLE.getText());
        return this;
    }

    // todo спользовать методы из AbstractPage везде

    /**
     * Получает и сохраняет информацию о товаре на карточке товара
     * todo нужно, что бы получило и наличие и дату доставки, и разделить бы это все на методы
     */
    public CardProductPage getAndSaveProductInfoOnCardProduct() {
        ProductModel product = config.accessProductModel();
        HashMap<String, String> detailInfoProduct = product.getDetailInfo();
        detailInfoProduct.put("Подробное описание", DETAILED_DESCRIPTION_PRODUCT_STRING.getText());
        setBasicProductInfo(detailInfoProduct);
        setProductFeatures(detailInfoProduct);
        logger.info(product.getDetailInfo().toString());
        return this;
    }

    /**
     * Устанавливает характеристики товара
     */
    private CardProductPage setProductFeatures(HashMap<String, String> detailInfoProduct) {
        for (int index = 0; index < NAME_PRODUCT_FEATURES_LIST.size(); index++) {
            detailInfoProduct.put(NAME_PRODUCT_FEATURES_LIST.get(index).getText(), VALUE_PRODUCT_FEATURES_LIST.get(index).getText());
        }
        return this;
    }

    /**
     * Устанавливает базовую информацию товара
     */
    private CardProductPage setBasicProductInfo(HashMap<String, String> detailInfoProduct) {
        for (SelenideElement productInfo : BASIC_PRODUCT_INFO_LIST) {
            String[] productInfoList = productInfo.getText().split(": ");
            detailInfoProduct.put(productInfoList[0], productInfoList[1]);
        }
        return this;
    }

}
