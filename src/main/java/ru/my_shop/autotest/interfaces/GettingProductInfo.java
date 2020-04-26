package ru.my_shop.autotest.interfaces;

import ru.my_shop.autotest.models.ProductModel;

/**
 * Интерфейс описывающий получение информации товара
 */
public interface GettingProductInfo {

    /**
     * Сохранить информацию товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    GettingProductInfo saveProductInfo(int productIndex, ProductModel product);

    /**
     * Установить параметр 'Наименование' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    GettingProductInfo setNameParameter(int productIndex, ProductModel product);

    /**
     * Установить параметр 'Цена' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    GettingProductInfo setPriceParameter(int productIndex, ProductModel product);

}
