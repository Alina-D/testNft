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
public class ProductCardPage extends CommonPage implements GettingProductInfo {

    // ------------------------------------------- Конструктор -----------------------------------------------
    public ProductCardPage() {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------------- String -------------------------------------------------
    // Кнопка в блоке "Купить" (в блоке "Купить" может быть несколько кнопок)
    private static final String BUTTON_IN_BUY_BLOCK = "input[value='%s']";

    // ------------------------------------------ SelenideElement ---------------------------------------------
    // Поле "Количество товара" в блоке "Купить"
    private SelenideElement amountProductInBuyBlockField = $("[name='quantity']");
    // Заголовок с именем товара
    private SelenideElement productNameTitle = $("[itemprop='name']");
    // Текст с подробным описанием товара
    private SelenideElement detailedDescriptionProductText = $("[itemprop='description']");
    // Текст с информацией наличия и даты доставки товара
    private SelenideElement availabilityAndDeliveryProductsInfoText =
            $("[itemtype*='schema.org/Offer']");
    // Текст с информацией цены товара
    private SelenideElement priceProductInfoText = $("[itemtype='http://schema.org/Offer'] > b");

    // ----------------------------------------- ElementsCollection --------------------------------------------
    // Список с наименованиями характеристик товара
    private ElementsCollection productCharacteristicsNameList =
            $$x("//div[@id='tabs-1']//tbody//table//tr/td[1]");
    // Список со значениями характеристик товара
    private ElementsCollection productCharacteristicsValueList =
            $$x("//div[@id='tabs-1']//tbody//table//tr/td[2]");
    // Список с основной информацией о товаре
    private ElementsCollection productBaseInfoList = $$("[data-o='good_description'] table tr");


    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Установить количество товара в блок "Купить"
     *
     * @param amountProduct - количество товара
     * @return this - ссылка на текущий объект
     */
    public ProductCardPage setAmountProductInBuyBlock(int amountProduct) {
        clearField(amountProductInBuyBlockField);
        setFieldValue(amountProductInBuyBlockField, Integer.toString(amountProduct));
        logger.info("Установлено количество товара в размере {} шт. в блоке 'Купить'", amountProduct);
        return this;
    }

    /**
     * Добавить товар в корзину из каточки товара
     *
     * @return this - ссылка на текущий объект
     */
    public ProductCardPage addProductToCartFromProductCard() throws InterruptedException {
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
    public ProductCardPage checkProductCardName() {
        String nameProduct = getElementText(productNameTitle);
        assertFalse("Наименование товара на карточке товара указано не корректно",
                nameProduct.contains(config.getProductModel().getName()));
        logger.info("Карточка товара содержит корректное наименование '{}'", nameProduct);
        return this;
    }

    /**
     * Установить параметр 'Характеристики' товара
     *
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private ProductCardPage setProductCharacteristicsParameter(ProductModel product) {
        logger.info("Установить параметр 'Характеристики' товара");
        HashMap<String, String> characteristicsProduct = product.getCharacteristics();
        for (int index = 0; index < productCharacteristicsNameList.size(); index++) {
            String characteristicName = getElementText(productCharacteristicsNameList.get(index));
            String characteristicValue = getElementText(productCharacteristicsValueList.get(index));
            characteristicsProduct.put(characteristicName, characteristicValue);
            logger.info("Добавлен ключ: '{}' со значением: '{}'", characteristicName, characteristicValue);
        }
        return this;
    }

    /**
     * Установить основную информацию товара
     *
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private ProductCardPage setBaseProductInfoParameter(ProductModel product) {
        logger.info("Установить параметр 'Основная информация' товара");
        HashMap<String, String> baseInfoProduct = product.getBaseInfo();
        for (SelenideElement baseInfo : productBaseInfoList) {
            String[] productInfoList = getElementText(baseInfo).split(": ");
            baseInfoProduct.put(productInfoList[0], productInfoList[1]);
            logger.info("Добавлен ключ: '{}' со значением: '{}'", productInfoList[0], productInfoList[1]);
        }
        return this;
    }

    /**
     * Сохранить информацию о товаре
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo saveProductInfo(int productIndex, ProductModel product) {
        setDetailedDescriptionParameter(product);
        setAvailabilityInfoParameter(product);
        setDeliveryDateParameter(product);
        setBaseProductInfoParameter(product);
        setProductCharacteristicsParameter(product);
        return this;
    }

    /**
     * Установить параметр 'Наименование' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setNameParameter(int productIndex, ProductModel product) {
        String productName = getElementText(productNameTitle);
        product.setDetailedDescription(productName);
        logger.info("Установлен параметр 'Наименование' товара - '{}'", productName);
        return this;
    }

    /**
     * Установить параметр 'Цена' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setPriceParameter(int productIndex, ProductModel product) {
        String priceProducts = getElementText(priceProductInfoText);
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
    private ProductCardPage setAvailabilityInfoParameter(ProductModel product) {
        String[] listInfoInBuyBlock = getElementText(availabilityAndDeliveryProductsInfoText).split("\n");
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
    private ProductCardPage setDeliveryDateParameter(ProductModel product) {
        String[] listInfoInBuyBlock = getElementText(availabilityAndDeliveryProductsInfoText).split("\n");
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
    private ProductCardPage setDetailedDescriptionParameter(ProductModel product) {
        String detailInfo = getElementText(detailedDescriptionProductText);
        product.setDetailedDescription(getElementText(detailedDescriptionProductText));
        logger.info("Установлен параметр 'Подробное описание' товара - '{}'", detailInfo);
        return this;
    }
}
