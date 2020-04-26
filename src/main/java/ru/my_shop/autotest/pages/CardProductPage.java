package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.my_shop.autotest.interfaces.GettingProductInfo;
import ru.my_shop.autotest.models.ProductModel;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;


/**
 * Класс описывающий страницу "Карточка товара"
 */
public class CardProductPage extends CommonPage implements GettingProductInfo {

    // ------------------------------------------- Конструктор -----------------------------------------------
    public CardProductPage() {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------------- String -------------------------------------------------
    // Кнопка в блоке "Купить" (в блоке "Купить" может быть несколько кнопок)
    private static final String BUTTON_IN_BUY_BLOCK = "input[value='%s']";
    // todo static это плохо
    // ------------------------------------------ SelenideElement ---------------------------------------------
    // Поле "Количество товара" в блоке "Купить"
    private static final SelenideElement AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD = $("[name='quantity']");
    // Заголовок с именем товара
    private static final SelenideElement NAME_PRODUCT_TITLE = $("[itemprop='name']");
    // Подробное описание товара
    private static final SelenideElement DETAILED_DESCRIPTION_PRODUCT_STRING = $("[itemprop='description']");
    // Информация о наличии и дате доставки товара
    private static final SelenideElement AVAILABILITY_AND_DELIVERY_PRODUCTS_INFO_STRING =
            $("[itemtype*='schema.org/Offer']");
    // нформация о цене товара
    private static final SelenideElement PRICE_PRODUCT_INFO = $("[itemtype='http://schema.org/Offer'] > b");


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
     * Проверить, что карточка товара содержит верную информацию
     *
     * @return this - ссылка на текущий объект
     * // todo перепроверить. что все верно
     */
    public CardProductPage checksThatProductCardContainsCorrectInfo() {
        String nameProduct = getElementText(NAME_PRODUCT_TITLE);
        assertFalse("Наименование товара на карточке товара указано не корректно",
                nameProduct.contains(config.getProductModel().getName()));
        logger.info("Карточка товара содержит корректное наименование '{}'", nameProduct);
        return this;
    }

    /**
     * Установить характеристики товара в параметр 'Детальная информация'
     *  todo разделить арактеристики и детальную инфку в моделе
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterProductFeatures() {
        logger.info("В параметр 'Детальная информация' добавляются характеристики товара");
        ProductModel product = config.getProductModel();
        HashMap<String, String> detailInfoProduct = product.getDetailInfo();
        for (int index = 0; index < NAME_PRODUCT_FEATURES_LIST.size(); index++) {
            String nameProductFeature = getElementText(NAME_PRODUCT_FEATURES_LIST.get(index));
            String valueProductFeature = getElementText(VALUE_PRODUCT_FEATURES_LIST.get(index));
            detailInfoProduct.put(nameProductFeature, valueProductFeature);
            logger.info("Добавлен ключ: '{}' со значением: '{}'", nameProductFeature, valueProductFeature);
        }
        return this;
    }

    /**
     * Установить базовую информацию товара в параметр 'Детальная информация'
     *
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterBasicProductInfo() {
        logger.info("В список 'Детальная информация' добавляется базовая информация товара");
        ProductModel product = config.getProductModel();
        HashMap<String, String> detailInfoProduct = product.getDetailInfo();
        for (SelenideElement productInfo : BASIC_PRODUCT_INFO_LIST) {
            String[] productInfoList = getElementText(productInfo).split(": ");
            detailInfoProduct.put(productInfoList[0], productInfoList[1]);
            logger.info("Добавлен ключ: '{}' со значением: '{}'", productInfoList[0], productInfoList[1]);
        }
        return this;
    }

    /**
     * Установить информацию о товаре
     *
     * @param numberProduct - номер товара в списке каталога
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setProductInfo(int numberProduct) {
        setParameterDetailedDescription();
        setParameterAvailabilityInfo();
        setParameterDeliveryDate();
        setParameterBasicProductInfo();
        setParameterProductFeatures();

        //todo удалить вывод текста
        ProductModel product = config.getProductModel();
        logger.info(product.toString());
        return this;
    }

    // todo станавливает или установить?
    /**
     * Установить параметр 'Наименование' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setParameterName(int numberProduct) {
        ProductModel product = config.getProductModel();
        String productName = getElementText(NAME_PRODUCT_TITLE);
        product.setDetailedDescription(productName);
        logger.info("Установлен параметр 'Наименование' товара - '{}'", productName);
        return this;
    }

    /**
     * Установить параметр 'Цена' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setParameterPrice(int numberProduct) {
        ProductModel product = config.getProductModel();
        String priceProducts = getElementText(PRICE_PRODUCT_INFO);
        product.setDetailedDescription(priceProducts);
        logger.info("Установлен параметр 'Цена' товара - '{}'", priceProducts);
        return this;
    }

    /**
     * Установить параметр 'Наличие' товара
     *
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterAvailabilityInfo() {
        ProductModel product = config.getProductModel();
        String[] listInfoInBuyBlock = getElementText(AVAILABILITY_AND_DELIVERY_PRODUCTS_INFO_STRING).split("\n");
        product.setAvailabilityInfo(listInfoInBuyBlock[2]);
        logger.info("Установлен параметр 'Наличие' товара - '{}'", listInfoInBuyBlock[2]);
        return this;
    }

    /**
     * Установить параметр 'Дата доставки' товара
     *
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterDeliveryDate() {
        ProductModel product = config.getProductModel();
        String[] listInfoInBuyBlock = getElementText(AVAILABILITY_AND_DELIVERY_PRODUCTS_INFO_STRING).split("\n");
        product.setAvailabilityInfo(listInfoInBuyBlock[3]);
        logger.info("Установлен параметр 'Дата доставки' товара - '{}'", listInfoInBuyBlock[3]);
        return this;
    }

    /**
     * Установить параметр 'Подробное описание' товара
     *
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterDetailedDescription() {
        ProductModel product = config.getProductModel();
        //todo в переменных добавлять String если это текст
        String detailInfo = getElementText(DETAILED_DESCRIPTION_PRODUCT_STRING);
        product.setDetailedDescription(getElementText(DETAILED_DESCRIPTION_PRODUCT_STRING));
        logger.info("Установлен параметр 'Подробное описание' товара - '{}'", detailInfo);
        return this;
    }
}
