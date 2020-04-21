package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import ru.my_shop.autotest.models.ProductModel;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу "Главная страница"
 */
public class HomePage extends CommonPage {

    // ------------------------------------------- Конструктор -----------------------------------------------
    public HomePage () {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------- ElementsCollection -------------------------------------------
    // Список с информацией о товаре на главной странице
    private final ElementsCollection PRODUCT_INFO_ON_HOME_PAGE_LIST = $$("[data-o='show_case_6']  .hb_text div");

    // --------------------------------------------- Методы ------------------------------------------------

    // todo проверить названия методов
    /**
     * Получает и сохраняет информацию о товаре на главной странице
     *
     * @return this - ссылка на текущий объект
     */
    public HomePage getAndSaveProductInfoOnHomePage() {
        // получает информацию о товаре
        String productName = getElementText(PRODUCT_INFO_ON_HOME_PAGE_LIST.get(0));
        String manufacturerProducts = getElementText(PRODUCT_INFO_ON_HOME_PAGE_LIST.get(1));
        String priceProducts = getElementText(PRODUCT_INFO_ON_HOME_PAGE_LIST.get(2));

        // сохраняет информацию о товаре
        ProductModel product = config.accessProductModel();
        logger.info(product.toString());
        product.setName(productName);
        product.setPrice(priceProducts);
        product.getDetailInfo().put("Производитель", manufacturerProducts);
        logger.info(product.toString());
        return this;
    }
}
