package ru.my_shop.autotest.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static org.junit.Assert.*;


/**
 * Класс описывающий страницу "Корзина"
 */
public class CartPage extends CommonPage {

    // Ячейки с информацией указанного товара (в корзине может быть нескоько товаров)
    private static final String TYPE_SORTING_LINK_XPATH = "//a[contains(., '%s')]/../../td";
    // Поле с количеством товара
    private static final SelenideElement amount_field = $("input[name^='quantity']");

    /**
     * Проверяет наличие товара в указанном количестве
     *
     * @param amount - количество едениц товара
     * todo подождать пока тест добавит товар в корзину
     * todo разбить метод на части
     */
    public CartPage checkAvailabilityOfProductInAmount(int amount) {
        String productName = config.getProductModel().getProductName();
        String priceProduct = config.getProductModel().getPriceProduct().split(" руб.")[0];
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
