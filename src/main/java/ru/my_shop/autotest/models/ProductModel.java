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
    private String availabilityInfo;
    private String deliveryDate;
    private HashMap<String, String> detailInfo = new HashMap<>();

    /**
     * Получить имя товара
     */
    public String getName() {
        return name;
    }

    /**
     * Получить сумму товара
     */
    public String getPrice() {
        return price;
    }

    /**
     * Получить краткое описание товара
     */
    public String getShotDescription() {
        return shotDescription;
    }

    /**
     * Получить информацию о наличии товара
     */
    public String getAvailabilityInfo() {
        return availabilityInfo;
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
    public HashMap<String, String> getDetailInfo() {
        return detailInfo;
    }

    /**
     * Установить имя товара
     */
    public ProductModel setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Установить сумму товара
     */
    public ProductModel setPrice(String price) {
        this.price = price;
        return this;
    }

    /**
     * Установить краткое описание товара
     */
    public ProductModel setShotDescription(String shotDescription) {
        this.shotDescription = shotDescription;
        return this;
    }

    /**
     * Установить информацию о наличии товара
     */
    public ProductModel setAvailabilityInfo(String availabilityInfo) {
        this.availabilityInfo = availabilityInfo;
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
     *
     * @return возвращает все заначения объекта в формате строки
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
                name, price, shotDescription, availabilityInfo, deliveryDate, detailInfo);
    }

}
