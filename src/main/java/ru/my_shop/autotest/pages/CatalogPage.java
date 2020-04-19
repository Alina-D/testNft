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
import static org.junit.Assert.*;

/**
 * Класс описывающий страницу "Каталог"
 */
public class CatalogPage extends CommonPage {


    // todo Поля класса.
    private ConfigContainer config = ConfigContainer.getInstance();


    // todo конструктор
    public CatalogPage() {
        super();
    }

    // todo локаторы
    // todo вроде есть этот локатор
    // Ссылка с типом сортировки товаров
    private static final String TYPE_SORTING_LINK_XPATH = "//a[contains(., '%s')]";
    // Список с именами товаров
    private final ElementsCollection nameProductsList = $$("a[href^='/shop/product'] > b");
    // Список с ценой товаров
    private final ElementsCollection priceProductsList =
            $$x("//table[@data-o='listgeneral']//sup//../b[1]");
    // Список с кратким описанием товаров
    private final ElementsCollection shortDescriptionProductsList =
            $$x("//table[@data-o='listgeneral']//td[2]");
    // Список с информацией о наличии и доставке товаров
    private final ElementsCollection infoOnAvailabilityAndDeliveryProductsList =
            $$x("//table[@data-o='listgeneral']//sup/..");
    // Информация о товаре на главной странице
    private final ElementsCollection productInfoOnHomePage = $$("[data-o='show_case_6']  .hb_text div");



    // todo методы

    /**
     * Проверить результаты поиска
     *
     * @param productName - надименование товара
     */
    public CatalogPage checkSearchResults(String productName) {
        // todo привести к нижнему регистру
        nameProductsList.forEach(product -> {
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
        for (int index = 0; index < nameProductsList.size(); index++) {

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
        String productName = nameProductsList.get(index).text();
        product.setProductName(productName);

        // Установить цену товара
        String priceProducts = priceProductsList.get(index).text();
        product.setPriceProduct(priceProducts);

        // Установить краткое описание товара
        String shotDescriptionProducts = shortDescriptionProductsList.get(index).text();
        product.setShotDescriptionProduct(shotDescriptionProducts);

        // Установить информацию о наличии товара
        String infoOnAvailabilityAndDelivery = infoOnAvailabilityAndDeliveryProductsList.get(index).text();
        String productAvailabilityInfo = infoOnAvailabilityAndDelivery.substring(
                infoOnAvailabilityAndDelivery.indexOf('\n') + 1, infoOnAvailabilityAndDelivery.indexOf(';'));
        product.setProductAvailability(productAvailabilityInfo);

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
        clickElement(format(TYPE_SORTING_LINK_XPATH, typeSorting));
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
            listOfProductNames.add(product.getProductName());
            listOfProductNamesAlphabetically.add(product.getProductName());
        });
        Collections.sort(listOfProductNamesAlphabetically);
        Assert.assertEquals("Не успешно выполнена сортировка", listOfProductNames,
                listOfProductNamesAlphabetically);
        logger.info("Проверка сортировки товаров в алфаввитном порядке выполнена успешно");
        return this;
    }

    /**
     * Открыть карточку товара по номеру
     */
    public CatalogPage openCardProductByName() {
        String numberProduct = config.getProductModel().getProductName();
        clickElement(format(LINK_WITH_NAME_XPATH, numberProduct));
        logger.info("Открыта карточка товар по имени: {}", numberProduct);

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
     * Открывает карточку товара
     */
    public CatalogPage openCardProduct() {
        clickElement(nameProductsList.get(0));
        logger.info("Открыт карточку товара");
        return this;
    }


}


























