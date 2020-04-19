package ru.my_shop.autotest.models;

import java.util.HashMap;

import static java.lang.String.format;


/**
 * Класс описывающий товар
 */
public class ProductModel {

    // todo убрать из названия переменных product
    private String productName;
    private String priceProduct;
    private String shotDescriptionProduct;
    private String productAvailability;
    private String deliveryDate;
    private HashMap<String, String> detailInfoProduct = new HashMap<>();

    /**
     * Конструктор класса
     */
    public ProductModel() {}

    /**
     * Получить имя товара
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Получить сумму товара
     */
    public String getPriceProduct() {
        return priceProduct;
    }

    /**
     * Получить краткое описание товара
     */
    public String getShotDescriptionProduct() {
        return shotDescriptionProduct;
    }

    /**
     * Получить информацию о наличии товара
     */
    public String getProductAvailabilityInfo() {
        return productAvailability;
    }

    /**
     * Получить дату доставки
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Получить детальное описание товара
     */
    public HashMap<String, String> getDetailInfoProduct() {
        return detailInfoProduct;
    }

    /**
     * Установить имя товара
     */
    public ProductModel setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    /**
     * Установить сумму товара
     */
    public ProductModel setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
        return this;
    }

    /**
     * Установить краткое описание товара
     */
    public ProductModel setShotDescriptionProduct(String shotDescriptionProduct) {
        this.shotDescriptionProduct = shotDescriptionProduct;
        return this;
    }

    /**
     * Установить информацию о наличии товара
     */
    public ProductModel setProductAvailability(String productAvailability) {
        this.productAvailability = productAvailability;
        return this;
    }

    /**
     * Установить дату доставки
     */
    public ProductModel setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }



    /**
     * Переопределенный метод toString
     * @return возвращает все заначения объекта в формате строки
     * todo выводить только ту информацию, которая есть у товара (с помощью хашмап) и добавить разделитель в начале или конце
     */
    @Override
    public String toString() {
        return format("Информация о товаре:  \n" +
                "наименование = '%s', \n" +
                "цена = '%s', \n" +
                "краткое описание = '%s', \n" +
                "наличие = '%s', \n" +
                "дата доставки = '%s', \n" +
                "детальное описание = %s",
                productName, priceProduct, shotDescriptionProduct, productAvailability, deliveryDate, detailInfoProduct);
    }

}
