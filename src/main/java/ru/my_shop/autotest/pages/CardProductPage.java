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
    // todo справить регист пнаименования у переменной , которая не константа
    // ------------------------------------------ SelenideElement ---------------------------------------------
    // Поле "Количество товара" в блоке "Купить"
    private SelenideElement amountProductInBuyBlockField = $("[name='quantity']");
    // Заголовок с именем товара
    private SelenideElement nameProductTitle = $("[itemprop='name']");
    // Подробное описание товара
    private SelenideElement detailedDescriptionProductString = $("[itemprop='description']");
    // Информация о наличии и дате доставки товара
    private SelenideElement availabilityAndDeliveryProductsInfoString =
            $("[itemtype*='schema.org/Offer']");
    // Иформация о цене товара
    private SelenideElement priceProductInfoString = $("[itemtype='http://schema.org/Offer'] > b");

    // ----------------------------------------- ElementsCollection --------------------------------------------
    // Список с наименованиями характеристик товара
    private ElementsCollection nameProductFeaturesList =
            $$x("//div[@id='tabs-1']//tbody//table//tr/td[1]");
    // Список со значениями характеристик товара
    private ElementsCollection valueProductFeaturesList =
            $$x("//div[@id='tabs-1']//tbody//table//tr/td[2]");
    // Список с основной информацией о товаре
    private ElementsCollection basicProductInfoList = $$("[data-o='good_description'] table tr");


    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Установить количество товара в блок "Купить"
     *
     * @param amountProduct - количество товара
     * @return this - ссылка на текущий объект
     */
    public CardProductPage setAmountOfProductInBuyBlock(int amountProduct) {
        clearField(amountProductInBuyBlockField);
        setValueInField(amountProductInBuyBlockField, Integer.toString(amountProduct));
        logger.info("Установлено количество товара в размере {} шт. в блоке 'Купить'", amountProduct);
        return this;
    }

    /**
     * Добавить товар в корзину из каточки товара
     *
     * @return this - ссылка на текущий объект
     */
    public CardProductPage addProductToCartFromProductCard() throws InterruptedException {
        for (int countTries = 0; getElementText(cartIcon).contains("0"); countTries++) {
            clickElement(format(BUTTON_IN_BUY_BLOCK, "Купить"));
            Thread.sleep(500);
            assertNotEquals("Не удалось добавить товар в корзину", countTries, 20);
        }
        logger.info("Нажата кнопка 'Купить' в блоке 'Купить'");
        return this;
    }

    /**
     * Проверить, что карточка товара содержит верное наименование
     *
     * @return this - ссылка на текущий объект
     */
    public CardProductPage checksThatProductCardContainsName() {
        String nameProduct = getElementText(nameProductTitle);
        assertFalse("Наименование товара на карточке товара указано не корректно",
                nameProduct.contains(config.getProductModel().getName()));
        logger.info("Карточка товара содержит корректное наименование '{}'", nameProduct);
        return this;
    }

    /**
     * Установить параметр характеристики товара
     *
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterProductFeatures(ProductModel product) {
        logger.info("Установить параметр 'Характеристики' товара");
        HashMap<String, String> featureProduct = product.getFeature();
        for (int index = 0; index < nameProductFeaturesList.size(); index++) {
            String nameFeature = getElementText(nameProductFeaturesList.get(index));
            String valueFeature = getElementText(valueProductFeaturesList.get(index));
            featureProduct.put(nameFeature, valueFeature);
            logger.info("Добавлен ключ: '{}' со значением: '{}'", nameFeature, valueFeature);
        }
        return this;
    }

    /**
     * Установить основную информацию товара
     *
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterBasicProductInfo(ProductModel product) {
        logger.info("Установить параметр 'Основная информация' товара");
        HashMap<String, String> basicInfoProduct = product.getBasicInfo();
        for (SelenideElement basicInfo : basicProductInfoList) {
            String[] productInfoList = getElementText(basicInfo).split(": ");
            basicInfoProduct.put(productInfoList[0], productInfoList[1]);
            logger.info("Добавлен ключ: '{}' со значением: '{}'", productInfoList[0], productInfoList[1]);
        }
        return this;
    }

    /**
     * Установить информацию о товаре
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setProductInfo(int numberProduct, ProductModel product) {
        setParameterDetailedDescription(product);
        setParameterAvailabilityInfo(product);
        setParameterDeliveryDate(product);
        setParameterBasicProductInfo(product);
        setParameterProductFeatures(product);
        return this;
    }

    /**
     * Установить параметр 'Наименование' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setParameterName(int numberProduct, ProductModel product) {
        String productName = getElementText(nameProductTitle);
        product.setDetailedDescription(productName);
        logger.info("Установлен параметр 'Наименование' товара - '{}'", productName);
        return this;
    }

    /**
     * Установить параметр 'Цена' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setParameterPrice(int numberProduct, ProductModel product) {
        String priceProducts = getElementText(priceProductInfoString);
        product.setDetailedDescription(priceProducts);
        logger.info("Установлен параметр 'Цена' товара - '{}'", priceProducts);
        return this;
    }

    /**
     * Установить параметр 'Наличие' товара
     *
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterAvailabilityInfo(ProductModel product) {
        String[] listInfoInBuyBlock = getElementText(availabilityAndDeliveryProductsInfoString).split("\n");
        product.setAvailabilityInfo(listInfoInBuyBlock[2]);
        logger.info("Установлен параметр 'Наличие' товара - '{}'", listInfoInBuyBlock[2]);
        return this;
    }

    /**
     * Установить параметр 'Дата доставки' товара
     *
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterDeliveryDate(ProductModel product) {
        String[] listInfoInBuyBlock = getElementText(availabilityAndDeliveryProductsInfoString).split("\n");
        product.setDeliveryDate(listInfoInBuyBlock[3]);
        logger.info("Установлен параметр 'Дата доставки' товара - '{}'", listInfoInBuyBlock[3]);
        return this;
    }

    /**
     * Установить параметр 'Подробное описание' товара
     *
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CardProductPage setParameterDetailedDescription(ProductModel product) {
        String detailInfo = getElementText(detailedDescriptionProductString);
        product.setDetailedDescription(getElementText(detailedDescriptionProductString));
        logger.info("Установлен параметр 'Подробное описание' товара - '{}'", detailInfo);
        return this;
    }
}
