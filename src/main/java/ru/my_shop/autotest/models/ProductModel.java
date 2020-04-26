package ru.my_shop.autotest.models;

import java.util.HashMap;

import static java.lang.String.format;

/**
 * Класс описывающий товар
 */
public class ProductModel {

    private String name;
    private String price;
    private String shotDescription;
    private String detailedDescription;
    private String availabilityInfo;
    private String deliveryDate;
    private HashMap<String, String> feature = new HashMap<>();
    private HashMap<String, String> basicInfo = new HashMap<>();

    /**
     * Получить имя товара
     *
     * @return имя товара
     */
    public String getName() {
        return name;
    }

    /**
     * Получить цену товара
     *
     * @return цену товара
     */
    public String getPrice() {
        return price;
    }

    /**
     * Получить краткое описание товара
     *
     * @return краткое описание товара
     */
    public String getShotDescription() {
        return shotDescription;
    }

    /**
     * Получить подробное описание товара
     *
     * @return подробное описание товара
     */
    public String getDetailedDescription() {
        return detailedDescription;
    }

    /**
     * Получить информацию о наличии товара
     *
     * @return информацию о наличии товара
     */
    public String getAvailabilityInfo() {
        return availabilityInfo;
    }

    /**
     * Получить дату доставки
     *
     * @return дату доставки товара
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Получить основную описание товара
     *
     * @return основную информацию товара
     */
    public HashMap<String, String> getBasicInfo() {
        return basicInfo;
    }

    /**
     * Получить характеристики товара
     *
     * @return характеристики товара
     */
    public HashMap<String, String> getFeature() {
        return feature;
    }

    /**
     * Установить имя товара
     *
     * @param name - имя товара
     * @return this - ссылка на текущий объект
     */
    public ProductModel setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Установить сумму товара
     *
     * @param price - цена товара
     * @return this - ссылка на текущий объект
     */
    public ProductModel setPrice(String price) {
        this.price = price;
        return this;
    }

    /**
     * Установить краткое описание товара
     *
     * @param shotDescription - краткое описание товара
     * @return this - ссылка на текущий объект
     */
    public ProductModel setShotDescription(String shotDescription) {
        this.shotDescription = shotDescription;
        return this;
    }

    /**
     * Установить подробное описание товара
     *
     * @param detailedDescription - подробное описание товара
     * @return this - ссылка на текущий объект
     */
    public ProductModel setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
        return this;
    }

    /**
     * Установить информацию о наличии товара
     *
     * @param availabilityInfo - информация о наличии товара
     * @return this - ссылка на текущий объект
     */
    public ProductModel setAvailabilityInfo(String availabilityInfo) {
        this.availabilityInfo = availabilityInfo;
        return this;
    }

    /**
     * Установить дату доставки
     *
     * @param deliveryDate - дата доставки товара
     * @return this - ссылка на текущий объект
     */
    public ProductModel setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    /**
     * Установить основную информацию
     *
     * @param basicInfo - основная информация товара
     * @return this - ссылка на текущий объект
     */
    public ProductModel setBasicInfo(HashMap<String, String> basicInfo) {
        this.basicInfo = basicInfo;
        return this;
    }

    /**
     * Установить характеристики
     *
     * @param feature - характеристики товара
     * @return this - ссылка на текущий объект
     */
    public ProductModel setFeature(HashMap<String, String> feature) {
        this.feature = feature;
        return this;
    }


    /**
     * Переопределенный метод toString
     *
     * @return возвращает все значения объекта в формате строки
     */
    @Override
    public String toString() {
        return format("Информация о товаре:  \n" +
                        "Наименование = %s, \n" +
                        "Цена = %s, \n" +
                        "Краткое описание = %s, \n" +
                        "Подробное описание = %s, \n" +
                        "Наличие = %s, \n" +
                        "Дата доставки = %s, \n" +
                        "Базовая информация = %s, \n" +
                        "Характеристики = %s",
                name, price, shotDescription, detailedDescription, availabilityInfo, deliveryDate, basicInfo, feature);
    }

}
