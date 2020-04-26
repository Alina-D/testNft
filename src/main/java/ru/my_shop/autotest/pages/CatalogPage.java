package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import ru.my_shop.autotest.interfaces.GettingProductInfo;
import ru.my_shop.autotest.models.ProductModel;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;
import static org.junit.Assert.assertTrue;

/**
 * Класс описывающий страницу "Каталог"
 */
public class CatalogPage extends CommonPage implements GettingProductInfo {

    // ------------------------------------------- Конструктор ----------------------------------------------
    public CatalogPage() {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------- ElementsCollection -------------------------------------------
    // Список с именами товаров
    private ElementsCollection productNameList = $$("a[href^='/shop/product'] > b");
    // Список с ценой товаров
    private ElementsCollection priceProductsList =
            $$x("//table[@data-o='listgeneral']//sup//../b[1]");
    // Список с кратким описанием товаров
    private ElementsCollection shortDescriptionProductsList =
            $$x("//table[@data-o='listgeneral']//td[2]");
    // Список с информацией о наличии и доставке товаров
    private ElementsCollection availabilityAndDeliveryProductsInfoList =
            $$x("//table[@data-o='listgeneral']//sup/..");


    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Проверить результаты поиска
     *
     * @param productName - наименование товара
     * @return this - ссылка на текущий объект
     */
    public CatalogPage checkSearchResults(String productName) {
        productNameList.forEach(product -> {
            String productNameInCatalog = getElementText(product).toLowerCase();
            assertTrue(format("Не корректное отображение результатьв поиска. Товар '%s'" +
                    "не содержит '%s'", productNameInCatalog, productName), productNameInCatalog.contains(productName));
        });
        logger.info("Результы поиска успешно проверены");
        return this;
    }

    /**
     * Получить информацию о найденных товарах
     *
     * @return ArrayList с информацией о найденных товарах
     */
    private ArrayList<ProductModel> getInfoAboutProductsFound() {
        ArrayList<ProductModel> productList = new ArrayList<>();
        for (int index = 0; index < productNameList.size() ; index++) {
            ProductModel product = new ProductModel();
            saveProductInfo(index, product);
            productList.add(product);
        }
        logger.info("Информация о найденных товарах успешно получена");
        return productList;
    }

    /**
     * Сортировать товары
     *
     * @param typeSorting - тип сортирвки
     * @return this - ссылка на текущий объект
     */
    public CatalogPage sortProducts(String typeSorting) {
        clickElement(format(LINK_WITH_NAME_XPATH, typeSorting));
        logger.info("Выполнена сортировка {}", typeSorting);
        return this;
    }

    /**
     * Проверить, что сортировка товаров выполнена в алфавитном порядке
     *
     * @return this - ссылка на текущий объект
     */
    public CatalogPage checkSortingAlphabetically() {
        ArrayList<ProductModel> productList = getInfoAboutProductsFound();
        ArrayList<String> productNameList = new ArrayList<>();
        ArrayList<String> productNameAlphabeticallyList = new ArrayList<>();
        productList.forEach(product -> {
            productNameList.add(product.getName());
            productNameAlphabeticallyList.add(product.getName());
        });
        Collections.sort(productNameAlphabeticallyList);
        Assert.assertEquals("Не успешно выполнена сортировка", productNameList,
                productNameAlphabeticallyList);
        logger.info("Проверка сортировки товаров в алфаввитном порядке выполнена успешно");
        return this;
    }

    /**
     * Печать информации о найденных товарах в консоль
     *
     * @return this - ссылка на текущий объект
     */
    public CatalogPage printInfoAboutProductsFound() {
        ArrayList<ProductModel> listProduct = getInfoAboutProductsFound();
        listProduct.forEach(product ->
                logger.info(product.toString()));
        return this;
    }

    /**
     * Открыть карточку товара по номеру в списке каталога
     *
     * @param number номер товара в списке
     * @return this - ссылка на текущий объект
     */
    public CatalogPage openProductCard(int number) {
        clickElement(productNameList.get(number - 1));
        logger.info("Открыта карточка товара");
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
        setNameParameter(productIndex, product);
        setPriceParameter(productIndex, product);
        setShortDescriptionParameter(productIndex, product);
        setAvailabilityProductParameter(productIndex, product);
        setDeliveryDateParameter(productIndex, product);
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
        String productName = getElementText(productNameList.get(productIndex));
        product.setName(productName);
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
        String priceProduct = getElementText(priceProductsList.get(productIndex));
        product.setPrice(priceProduct);
        logger.info("Установлен параметр 'Цена' товара - '{}'", priceProduct);
        return this;
    }

    /**
     * Установить параметр 'Наличие' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CatalogPage setAvailabilityProductParameter(int productIndex, ProductModel product) {
        String availabilityAndDeliveryInfo =
                getElementText(availabilityAndDeliveryProductsInfoList.get(productIndex));
        String productAvailabilityInfo = availabilityAndDeliveryInfo.substring(
                availabilityAndDeliveryInfo.indexOf('\n') + 1, availabilityAndDeliveryInfo.indexOf(';'));
        product.setAvailabilityInfo(productAvailabilityInfo);
        logger.info("Установлен параметр 'Наличие' товара - '{}'", productAvailabilityInfo);
        return this;
    }

    /**
     * Установить параметр 'Дата доставки' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CatalogPage setDeliveryDateParameter(int productIndex, ProductModel product) {
        String availabilityAndDeliveryInfo =
                getElementText(availabilityAndDeliveryProductsInfoList.get(productIndex));
        String deliveryDate = availabilityAndDeliveryInfo.substring(
                availabilityAndDeliveryInfo.indexOf(';') + 2, availabilityAndDeliveryInfo.lastIndexOf('\n'));
        product.setDeliveryDate(deliveryDate);
        logger.info("Установлен параметр 'Дата доставки' товара - '{}'", deliveryDate);
        return this;
    }

    /**
     * Установить параметр 'Краткое описание' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CatalogPage setShortDescriptionParameter(int productIndex, ProductModel product) {
        String shortDescriptionProducts = getElementText(shortDescriptionProductsList.get(productIndex));
        product.setShortDescription(shortDescriptionProducts);
        logger.info("Установлен параметр 'Краткое описание' товара - '{}'", shortDescriptionProducts);
        return this;
    }
}


























