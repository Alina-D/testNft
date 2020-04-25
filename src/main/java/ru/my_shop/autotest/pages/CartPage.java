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
    public CartPage() {
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
     * Проверяет наличие товара в корзине (наименование, цену, количество и сумму товара)
     * todo убрать InAmount
     * @param amountProduct - количество едениц товара
     * @return this - ссылка на текущий объект
     */
    public CartPage checkAvailabilityOfProductInAmount(int amountProduct) {
        checkNameProduct();
        checkPriceProduct();
        checkAmountProduct(amountProduct);
        checkSumProduct(amountProduct);
        return this;
    }

    /**
     * Проверяет наименование товара в корзине
     *  todo проверяет наличие, а не соответствие имени
     * @return this - ссылка на текущий объект
     */
    private CartPage checkNameProduct() {
        String productName = config.getProductModel().getName();
        assertTrue("Нет товара в корзине",
                $x(format(LINK_WITH_NAME_XPATH, productName)).isDisplayed());
        logger.info("Товар с с именем '{}' присутствует в корзине", productName);
        return this;
    }

    /**
     * Проверяет цену товара в корзине
     *
     * @return this - ссылка на текущий объект
     */
    private CartPage checkPriceProduct() {
        String productName = config.getProductModel().getName();

        // приводим цену к общему виду
        String priceProduct = config.getProductModel().getPrice().split(" руб.")[0];
        String priceProductInCart =
                getElementText($$x(format(TYPE_SORTING_LINK_XPATH, productName)).get(3)).split(".00")[0];

        assertEquals("Товар содержит не верную цену за единицу товара",
                priceProductInCart, priceProduct);
        logger.info("Товар содержит верную цену '{}' за единицу товара", priceProduct);
        return this;
    }

    /**
     * Проверяет сумму товара в корзине
     *
     * @param amountProduct - количество товара
     * @return this - ссылка на текущий объект
     */
    private CartPage checkSumProduct(int amountProduct) {
        String productName = config.getProductModel().getName();
        String priceProduct = config.getProductModel().getPrice().split(" руб.")[0];
        int sum = Integer.parseInt(priceProduct) * amountProduct;
        String sumInCart = getElementText($$x(format(TYPE_SORTING_LINK_XPATH, productName)).get(5))
                .split(".00")[0].replace(" ", "");
        assertEquals("Сумма товара расчитана не верно", sumInCart, Integer.toString(sum));
        logger.info("Товар содержит верную сумма {}", amountProduct);
        return this;
    }

    /**
     * Проверяет количество товара в корзине
     *
     * @param amountProduct - количество товара
     * @return this - ссылка на текущий объект
     */
    private CartPage checkAmountProduct(int amountProduct) {
        // todo amount_field с большой буквы?
        String amountProductInCart = amount_field.getAttribute("value");
        assertEquals("У товара установлено не верное количество",
                amountProductInCart, Integer.toString(amountProduct));
        logger.info("Товар содержит верное количество {}", amountProduct);
        return this;
    }
}
