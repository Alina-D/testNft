package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import ru.my_shop.autotest.interfaces.GettingProductInfo;
import ru.my_shop.autotest.models.ProductModel;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу "Главная страница"
 */
public class HomePage extends CommonPage implements GettingProductInfo {

    // ------------------------------------------- Конструктор -----------------------------------------------
    public HomePage() {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------- ElementsCollection -------------------------------------------
    // Список с информацией о товаре на главной странице
    private final ElementsCollection PRODUCT_INFO_ON_HOME_PAGE_LIST = $$("[data-o='show_case_6']  .hb_text div");

    // --------------------------------------------- Методы ------------------------------------------------

    // todo проверить названия методов

    /**
     * Установить информацию о товаре
     *
     * @param numberProduct - номер товара в списке каталога
     * @return this - ссылка на текущий объект
     */
    @Override
    public HomePage setProductInfo(int numberProduct) {
        setParameterName(numberProduct);
        setParameterPrice(numberProduct);

        //todo удалить вывод текста
        ProductModel product = config.getProductModel();
        logger.info(product.toString());
        return this;
    }

    /**
     * Установить параметр 'Наименование' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setParameterName(int numberProduct) {
        ProductModel product = config.getProductModel();
        String productName = getElementText(PRODUCT_INFO_ON_HOME_PAGE_LIST.get(0));
        product.setName(productName);
        logger.info("Установлен параметр 'Наименование' товара - '{}'", productName);
        return this;
    }

    /**
     * Установить параметр 'Цена' товара
     *
     * @param numberProduct - номер товара в списке каталога
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setParameterPrice(int numberProduct) {
        ProductModel product = config.getProductModel();
        String priceProducts = getElementText(PRODUCT_INFO_ON_HOME_PAGE_LIST.get(2));
        product.setPrice(priceProducts);
        logger.info("Установлен параметр 'Цена' товара - '{}'", priceProducts);
        return this;
    }
}
