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
    private ElementsCollection productInfoOnHomePageList = $$("[data-o='show_case_6'] .hb_text div");

    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Сохранить информацию о товаре
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public HomePage saveProductInfo(int productIndex, ProductModel product) {
        setNameParameter(productIndex, product);
        setPriceParameter(productIndex, product);
        return this;
    }

    /**
     * Установить параметр 'Наименование' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setNameParameter(int productIndex, ProductModel product) {
        String productName = getElementText(productInfoOnHomePageList.get(0));
        product.setName(productName);
        logger.info("Установлен параметр 'Наименование' товара - '{}'", productName);
        return this;
    }

    /**
     * Установить параметр 'Цена' товара
     *
     * @param productIndex - номер товара в списке каталога
     * @param product - объект описывающий товар
     * @return this - ссылка на текущий объект
     */
    @Override
    public GettingProductInfo setPriceParameter(int productIndex, ProductModel product) {
        String priceProducts = getElementText(productInfoOnHomePageList.get(2));
        product.setPrice(priceProducts);
        logger.info("Установлен параметр 'Цена' товара - '{}'", priceProducts);
        return this;
    }
}
