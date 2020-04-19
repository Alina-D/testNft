package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import ru.my_shop.autotest.models.ProductModel;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.junit.Assert.*;


/**
 * Класс описывающий страницу "Карточка товара"
 */
public class CardProductPage extends CommonPage{

    //todo конструктор класса родителя нужен же везде

    // todo проверить названия локаторов (xpath)
    // Поле для установки количества товаров в блоке "Купить"
    private static final SelenideElement AMOUNT_OF_PRODUCT_IN_BUY_BLOCK_FIELD = $("[name='quantity']");
    // Кнопка с указанным именем в блоке "Купить" (в блоке купить может бвть несколько кнопок)
    private static final String BUTTON_IN_BUY_BLOCK_XPATH = "input[value='%s']";
    // Имя товара
    private static final SelenideElement name_product = $("[itemprop='name']");
    // Характеристики товара
    private static final ElementsCollection name_product_feature =
            $$x("//div[@id='tabs-1']//tbody//table//tr/td[1]");
    // Характеристики товара
    private static final ElementsCollection value_product_feature =
            $$x("//div[@id='tabs-1']//tbody//table//tr/td[2]");
    // Подробное описание товара
    private static final SelenideElement detailed_description_product = $("[itemprop='description']");
    // Основная информация о товаре
    private static final ElementsCollection basic_product_info = $$("[data-o='good_description'] table tr");




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
        for (int countTries = 0; cart_icon.getText().contains("0"); countTries ++) {
            clickElement(format(BUTTON_IN_BUY_BLOCK_XPATH, "Купить"));
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
                name_product.getText().contains(config.getProductModel().getProductName()));
        logger.info("Карточка товара содержит верное наименование '{}'", name_product.getText());
        return this;
    }

    // todo спользовать методы из AbstractPage везде
    /**
     * Получает и сохраняет информацию о товаре на карточке товара
     * todo нужно, что бы получило и наличие и дату доставки, и разделить бы это все на методы
     */
    public CardProductPage getAndSaveProductInfoOnCardProduct() {
        ProductModel product = config.getProductModel();
        HashMap<String, String> detailInfoProduct = product.getDetailInfoProduct();
        detailInfoProduct.put("Подробное описание", detailed_description_product.getText());
        setBasicProductInfo(detailInfoProduct);
        setProductFeatures(detailInfoProduct);
        logger.info(product.getDetailInfoProduct().toString());
        return this;
    }

    /**
     * Устанавливает характеристики товара
     */
    private CardProductPage setProductFeatures(HashMap<String, String> detailInfoProduct) {
        for (int index = 0; index < name_product_feature.size(); index++) {
            detailInfoProduct.put(name_product_feature.get(index).getText(), value_product_feature.get(index).getText());
        }
        return this;
    }

    /**
     * Устанавливает базовую информацию товара
     */
    private CardProductPage setBasicProductInfo(HashMap<String, String> detailInfoProduct) {
        // todo подумать как назвать информацию о товаре product_info
        for (SelenideElement productInfo: basic_product_info) {
            String[] productInfoList = productInfo.getText().split(": ");
            detailInfoProduct.put(productInfoList[0], productInfoList[1]);
        }
        return this;
    }

}
