package ru.my_shop.autotest.pages;

import com.codeborne.selenide.ElementsCollection;
import ru.my_shop.autotest.models.ProductModel;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывающий страницу "Главная страница"
 */
public class HomePage extends CommonPage{


    // Информация о товаре на главной странице
    private final ElementsCollection productInfoOnHomePage = $$("[data-o='show_case_6']  .hb_text div");

    /**
     * Получает и сохраняет информацию о товаре на главной странице
     */
    public HomePage getAndSaveProductInfoOnHomePage() {
        // получает информацию о товаре
        String productName = productInfoOnHomePage.get(0).getText();
        String manufacturerProducts = productInfoOnHomePage.get(1).getText();
        String priceProducts = productInfoOnHomePage.get(2).getText();

        // сохраняет информацию о товаре
        ProductModel product = config.getProductModel();
        logger.info(product.toString());
        product.setProductName(productName);
        product.setPriceProduct(priceProducts);
        product.getDetailInfoProduct().put("Производитель", manufacturerProducts);
        logger.info(product.toString());
        return this;
    }
}
