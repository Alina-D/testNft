package ru.my_shop.autotest.models;

import java.util.HashMap;

public class ProductModel {

    private String productName;
    private String priceProduct;
    private String shotDescriptionProduct;
    private String productAvailability;
    private String deliveryDate;
    private HashMap<String, String> detailInfoProduct;

    /**
     * Конструктор класса
     */
    public ProductModel(String productName, String priceProduct, String shotDescriptionProduct,
                        String productAvailability, String deliveryDate,
                        HashMap<String, String> detailInfoProduct) {
        this.productName = productName;
        this.priceProduct = priceProduct;
        this.shotDescriptionProduct = shotDescriptionProduct;
        this.productAvailability = productAvailability;
        this.deliveryDate = deliveryDate;
        this.detailInfoProduct = detailInfoProduct;
    }

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
     * Установить детальное описание товара
     * @return this
     */
    public ProductModel setDetailInfoProduct(HashMap<String, String> detailInfoProduct) {
        this.detailInfoProduct = detailInfoProduct;
        return this;
    }


    /**
     * Переопределенный метод toString
     * @return возвращает все заначения объекта в формате строки
     */
    @Override
    public String toString() {
        return "ProductModel{" +
                "productName='" + productName + '\'' +
                ", priceProduct='" + priceProduct + '\'' +
                ", shotDescriptionProduct='" + shotDescriptionProduct + '\'' +
                ", productAvailability='" + productAvailability + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", detailInfoProduct=" + detailInfoProduct +
                '}';
    }

}
