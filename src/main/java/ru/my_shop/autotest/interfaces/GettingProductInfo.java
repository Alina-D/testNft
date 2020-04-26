package ru.my_shop.autotest.interfaces;

import ru.my_shop.autotest.models.ProductModel;

/**
 * Интерфейс описывающий получение информации о товаре
 */
public interface GettingProductInfo {

    /**
     * Установить информацию о товаре
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    GettingProductInfo setProductInfo(int numberProduct, ProductModel product);

    /**
     * Установить информацию о товаре
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    GettingProductInfo setParameterName(int numberProduct, ProductModel product);

    /**
     * Установить параметр 'Цена' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    GettingProductInfo setParameterPrice(int numberProduct, ProductModel product);

}
