package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import ru.my_shop.autotest.helpers.ConfigContainer;
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
public class CatalogPage extends CommonPage {

    // ------------------------------------------- Конструктор ----------------------------------------------
    public CatalogPage() {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------- ElementsCollection -------------------------------------------
    // Список с именами товаров
    private static final ElementsCollection NAME_PRODUCTS_LIST = $$("a[href^='/shop/product'] > b");
    // Список с ценой товаров
    private static final ElementsCollection PRICE_PRODUCTS_LIST =
            $$x("//table[@data-o='listgeneral']//sup//../b[1]");
    // Список с кратким описанием товаров
    private static final ElementsCollection SHORT_DESCRIPTION_PRODUCTS_LIST =
            $$x("//table[@data-o='listgeneral']//td[2]");
    // Список с информацией о наличии и доставке товаров
    private static final ElementsCollection INFO_AVAILABILITY_AND_DELIVERY_PRODUCTS_LIST =
            $$x("//table[@data-o='listgeneral']//sup/..");


    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Проверить результаты поиска
     *
     * @param productName - надименование товара
     */
    public CatalogPage checkSearchResults(String productName) {
        NAME_PRODUCTS_LIST.forEach(product -> {
            String nameProduct = product.getText().toLowerCase();
            assertTrue(format("Не корректное отображение результатьв поиска. Товар '%s'" +
                    "не содержит '%s'", nameProduct, productName), nameProduct.contains(productName));
        });
        logger.info("Результы поиска успешно проверены.");
        return this;
    }

    /**
     * Получить информацию о найденных товарах
     */
    public ArrayList<ProductModel> getInfoAboutProductsFound() {
        ArrayList<ProductModel> listProduct = new ArrayList<>();
        for (int index = 0; index < NAME_PRODUCTS_LIST.size(); index++) {

            ProductModel product = new ProductModel();
            getInfoAboutProduct(index, product);

            listProduct.add(product);
        }
        logger.info("Информация о найденных товарах успешно получена");
        return listProduct;
    }

    /**
     * Получить информацию о товаре
     */
    public void getInfoAboutProduct(int index, ProductModel product) {
        // Получить имя товара
        String productName = NAME_PRODUCTS_LIST.get(index).text();
        product.setName(productName);

        // Установить цену товара
        String priceProducts = PRICE_PRODUCTS_LIST.get(index).text();
        product.setPrice(priceProducts);

        // Установить краткое описание товара
        String shotDescriptionProducts = SHORT_DESCRIPTION_PRODUCTS_LIST.get(index).text();
        product.setShotDescription(shotDescriptionProducts);

        // Установить информацию о наличии товара
        String infoOnAvailabilityAndDelivery = INFO_AVAILABILITY_AND_DELIVERY_PRODUCTS_LIST.get(index).text();
        String productAvailabilityInfo = infoOnAvailabilityAndDelivery.substring(
                infoOnAvailabilityAndDelivery.indexOf('\n') + 1, infoOnAvailabilityAndDelivery.indexOf(';'));
        product.setAvailabilityInfo(productAvailabilityInfo);

        // Установить дату доставки товара
        String deliveryDate = infoOnAvailabilityAndDelivery.substring(
                infoOnAvailabilityAndDelivery.indexOf(':') + 2, infoOnAvailabilityAndDelivery.lastIndexOf('\n'));
        product.setDeliveryDate(deliveryDate);
    }

    /**
     * Сортировать товары
     *
     * @param typeSorting - тип сортирвки
     */
    public void sortProduct(String typeSorting) {
        clickElement(format(LINK_WITH_NAME_XPATH, typeSorting));
        logger.info("Выполнена сортировка {}", typeSorting);
    }

    /**
     * Проверить, что товары отсортированы в алфавитном порядке
     */
    public CommonPage checkSortingAlphabetically() {
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
     * Печатает информацию о найденных товарах в консоль
     */
    public CatalogPage printInfoAboutProductsFound() {
        ArrayList<ProductModel> listProduct = getInfoAboutProductsFound();
        listProduct.forEach(product ->
                logger.info(product.toString()));
        return this;
    }

    /**
     * Открывает карточку товара по нумерации в списке
     *
     * @param number номер товара в списке
     */
    public CatalogPage openCardProduct(int number) {
        clickElement(NAME_PRODUCTS_LIST.get(number - 1));
        logger.info("Открыта карточка товара");
        return this;
    }


}


























