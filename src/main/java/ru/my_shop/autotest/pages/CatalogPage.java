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
    private ElementsCollection NAME_PRODUCTS_LIST = $$("a[href^='/shop/product'] > b");
    // Список с ценой товаров
    private ElementsCollection PRICE_PRODUCTS_LIST =
            $$x("//table[@data-o='listgeneral']//sup//../b[1]");
    // Список с кратким описанием товаров
    private ElementsCollection SHORT_DESCRIPTION_PRODUCTS_LIST =
            $$x("//table[@data-o='listgeneral']//td[2]");
    // Список с информацией о наличии и доставке товаров
    private ElementsCollection AVAILABILITY_AND_DELIVERY_PRODUCTS_INFO_LIST =
            $$x("//table[@data-o='listgeneral']//sup/..");


    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Проверить результаты поиска
     *
     * @param productName - наименование товара
     * @return this - ссылка на текущий объект
     */
    public CatalogPage checkSearchResults(String productName) {
        NAME_PRODUCTS_LIST.forEach(product -> {
            String nameProduct = getElementText(product).toLowerCase();
            assertTrue(format("Не корректное отображение результатьв поиска. Товар '%s'" +
                    "не содержит '%s'", nameProduct, productName), nameProduct.contains(productName));
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
        ArrayList<ProductModel> listProduct = new ArrayList<>();
        for (int index = 0; index < NAME_PRODUCTS_LIST.size() ; index++) {
            ProductModel product = new ProductModel();
            setProductInfo(index, product);
            listProduct.add(product);
        }
        logger.info("Информация о найденных товарах успешно получена");
        return listProduct;
    }

    /**
     * Сортировать товары
     *
     * @param typeSorting - тип сортирвки
     * @return this - ссылка на текущий объект
     */
    public CatalogPage sortProduct(String typeSorting) {
        clickElement(format(LINK_WITH_NAME_XPATH, typeSorting));
        logger.info("Выполнена сортировка {}", typeSorting);
        return this;
    }

    /**
     * Проверить, что товары отсортированы в алфавитном порядке
     *
     * @return this - ссылка на текущий объект
     */
    public CatalogPage checkSortingAlphabetically() {
        ArrayList<ProductModel> listProduct = getInfoAboutProductsFound();
        ArrayList<String> listOfProductNames = new ArrayList<>();
        ArrayList<String> listOfProductNamesAlphabetically = new ArrayList<>();
        listProduct.forEach(product -> {
            listOfProductNames.add(product.getName());
            listOfProductNamesAlphabetically.add(product.getName());
        });
        Collections.sort(listOfProductNamesAlphabetically);
        Assert.assertEquals("Не успешно выполнена сортировка", listOfProductNames,
                listOfProductNamesAlphabetically);
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
    public CatalogPage openCardProduct(int number) {
        clickElement(NAME_PRODUCTS_LIST.get(number - 1));
        logger.info("Открыта карточка товара");
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
        setParameterName(numberProduct, product);
        setParameterPrice(numberProduct, product);
        setParameterShotDescription(numberProduct, product);
        setParameterAvailabilityProduct(numberProduct, product);
        setParameterDeliveryDate(numberProduct, product);
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
        String productName = getElementText(NAME_PRODUCTS_LIST.get(numberProduct));
        product.setName(productName);
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
        String priceProducts = getElementText(PRICE_PRODUCTS_LIST.get(numberProduct));
        product.setPrice(priceProducts);
        logger.info("Установлен параметр 'Цена' товара - '{}'", priceProducts);
        return this;
    }

    /**
     * Установить параметр 'Наличие' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CatalogPage setParameterAvailabilityProduct(int numberProduct, ProductModel product) {
        String availabilityAndDeliveryInfo =
                getElementText(AVAILABILITY_AND_DELIVERY_PRODUCTS_INFO_LIST.get(numberProduct));
        String productAvailabilityInfo = availabilityAndDeliveryInfo.substring(
                availabilityAndDeliveryInfo.indexOf('\n') + 1, availabilityAndDeliveryInfo.indexOf(';'));
        product.setAvailabilityInfo(productAvailabilityInfo);
        logger.info("Установлен параметр 'Наличие' товара - '{}'", productAvailabilityInfo);
        return this;
    }

    /**
     * Установить параметр 'Дата доставки' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CatalogPage setParameterDeliveryDate(int numberProduct, ProductModel product) {
        String availabilityAndDeliveryInfo =
                getElementText(AVAILABILITY_AND_DELIVERY_PRODUCTS_INFO_LIST.get(numberProduct));
        String deliveryDate = availabilityAndDeliveryInfo.substring(
                availabilityAndDeliveryInfo.indexOf(';') + 2, availabilityAndDeliveryInfo.lastIndexOf('\n'));
        product.setDeliveryDate(deliveryDate);
        logger.info("Установлен параметр 'Дата доставки' товара - '{}'", deliveryDate);
        return this;
    }

    /**
     * Установить параметр 'Краткое описание' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    private CatalogPage setParameterShotDescription(int numberProduct, ProductModel product) {
        String shotDescriptionProducts = getElementText(SHORT_DESCRIPTION_PRODUCTS_LIST.get(numberProduct));
        product.setShotDescription(shotDescriptionProducts);
        logger.info("Установлен параметр 'Краткое описание' товара - '{}'", shotDescriptionProducts);
        return this;
    }
}


























