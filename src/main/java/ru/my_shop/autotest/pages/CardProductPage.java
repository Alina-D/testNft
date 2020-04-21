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
     * @return this - ссылка на текущий объект
     */
    public CardProductPage setAmountOfProductInBuyBlock(int amountProduct) {
        clearField(AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD);
        setValueInField(AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD, Integer.toString(amountProduct));
        logger.info("Установлено количество товара в размере {} шт. в блоке 'Купить'", amountProduct);
        return this;
    }

    /**
     * Добавить товар в корзину из каточки товара
     *
     * @return this - ссылка на текущий объект
     */
    public CardProductPage addProductToCartFromProductCard() throws InterruptedException {
        for (int countTries = 0; getElementText(CART_ICON).contains("0"); countTries++) {
            clickElement(format(BUTTON_IN_BUY_BLOCK, "Купить"));
            Thread.sleep(500);
            assertNotEquals("Не удалось добавить товар в корзину", countTries, 20);
        }
        logger.info("Нажата кнопка 'Купить' в блоке 'Купить'");
        return this;
    }

    /**
     * Проверяет, что карточка товара содержит верную информацию
     *
     * @return this - ссылка на текущий объект
     * // todo добавить анотацию return, перепроверить. что все верно
     */
    public CardProductPage checksThatProductCardContainsCorrectInfo() {
        String nameProduct = getElementText(NAME_PRODUCT_TITLE);
        assertFalse("Наименование товара на карточке товара указано не верно",
                nameProduct.contains(config.accessProductModel().getName()));
        logger.info("Карточка товара содержит верное наименование '{}'", nameProduct);
        return this;
    }

    /**
     * Получает и сохраняет информацию о товаре на карточке товара
     *
     * @return this - ссылка на текущий объект
     * todo нужно, что бы получило и наличие и дату доставки, и разделить бы это все на методы
     */
    public CardProductPage getAndSaveProductInfoOnCardProduct() {
        ProductModel product = config.accessProductModel();
        HashMap<String, String> detailInfoProduct = product.getDetailInfo();
        detailInfoProduct.put("Подробное описание", getElementText(DETAILED_DESCRIPTION_PRODUCT_STRING));
        setBasicProductInfo(detailInfoProduct);
        setProductFeatures(detailInfoProduct);
        logger.info(product.getDetailInfo().toString());
        return this;
    }

    /**
     * Устанавливает характеристики товара
     *
     * @param detailInfoProduct - HashMap для записи детальной информация товара
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setProductFeatures(HashMap<String, String> detailInfoProduct) {
        for (int index = 0; index < NAME_PRODUCT_FEATURES_LIST.size(); index++) {
            detailInfoProduct.put(getElementText(NAME_PRODUCT_FEATURES_LIST.get(index)),
                    getElementText(VALUE_PRODUCT_FEATURES_LIST.get(index)));
        }
        return this;
    }

    /**
     * Устанавливает базовую информацию товара
     *
     * @param detailInfoProduct - HashMap для записи детальной информация товара
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setBasicProductInfo(HashMap<String, String> detailInfoProduct) {
        for (SelenideElement productInfo : BASIC_PRODUCT_INFO_LIST) {
            String[] productInfoList = getElementText(productInfo).split(": ");
            detailInfoProduct.put(productInfoList[0], productInfoList[1]);
        }
        return this;
    }
}
