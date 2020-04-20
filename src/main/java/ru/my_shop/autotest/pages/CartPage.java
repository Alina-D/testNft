package ru.my_shop.autotest.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Класс описывающий страницу "Корзина"
 */
public class CartPage extends CommonPage {

    // ------------------------------------------- Конструктор -----------------------------------------------
    public CartPage () {
        super();
    }

    // --------------------------------------------- Локаторы ------------------------------------------------

    // ---------------------------------------------- String -------------------------------------------------
    // Ссылка на указанный тип сортировки (на странице доступно несколько видов сортировки товаров)
    private static final String TYPE_SORTING_LINK_XPATH = "//a[contains(., '%s')]/../../td";

    // ------------------------------------------ SelenideElement ---------------------------------------------
    // Поле с количеством товара
    private static final SelenideElement amount_field = $("input[name^='quantity']");

    // --------------------------------------------- Методы ------------------------------------------------

    /**
     * Проверяет наличие товара в указанном количестве
     *
     * @param amount - количество едениц товара
     *               todo разбить метод на части
     */
    public CartPage checkAvailabilityOfProductInAmount(int amount) {
        String productName = config.accessProductModel().getName();
        String priceProduct = config.accessProductModel().getPrice().split(" руб.")[0];
        String amountProduct = amount_field.getAttribute("value");
        int sum = Integer.parseInt(priceProduct) * amount;
        String priceProductInCart =
                $$x(format(TYPE_SORTING_LINK_XPATH, productName)).get(3).getText().split(".00")[0];
        String sumInCart = $$x(format(TYPE_SORTING_LINK_XPATH, productName)).get(5).getText()
                .split(".00")[0].replace(" ", "");

        assertTrue("Нет товара в корзине",
                $x(format(LINK_WITH_NAME_XPATH, productName)).isDisplayed());
        assertEquals("Товар содержит не верную цену за единицу товара",
                priceProductInCart, priceProduct);
        assertEquals("У товара установлено не верное количество",
                amountProduct, Integer.toString(amount));
        logger.info(Integer.toString(sum));
        assertEquals("Сумма товара расчитана не верно",
                sumInCart, Integer.toString(sum));
        return this;
    }
}
