package ru.my_shop.autotest.steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonSteps extends AbstractSteps{

    private static final Logger logger
            = LoggerFactory.getLogger(CommonSteps.class);

    @Когда("^выполняет поиск товара \"([^\"]*)\"$")
    public void searchProduct(String productName) {

    }

    @То("^проверяет результаты поиска по ключевому слову \"([^\"]*)\"$")
    public void checkResultsByKeyword(String productName) {
        logger.info("Example log from {}", "asa");
    }

    @И("^выводит информацию о первых (\\d+)-ти товарах$")
    public void printInfoAboutProducts(int amountProducts ) {

    }

    @Тогда("^сортирует \"([^\"]*)\"$")
    public void sort(String typeSorting) {
        logger.debug("Example log from {}", "asa");
    }

    @И("^проверяет сортировку \"([^\"]*)\"$")
    public void checkSorting(String typeSorting) {

    }

    @Когда("^открывает первый товар в списке$")
    public void openFirstProductInList() {

    }

    @Когда("^выводит информацию о товаре$")
    public void printProductInfo() {

    }

    @И("^выбирает раздел \"([^\"]*)\"$")
    public void selectSection(String sectionName) {

    }

    @Когда("^нажимает элемент меню \"([^\"]*)\"$")
    public void clickElementMenu(String elementName) {

    }

    @И("^выбирает категорию \"([^\"]*)\"$")
    public void selectCategory(String categoryName) {

    }

    @И("^выбирает подкатегорию \"([^\"]*)\"$")
    public void selectSubcategory(String subcategoryName) {

    }

    @И("^выбирает подраздел \"([^\"]*)\"$")
    public void selectSubsection(String subsectionName) {

    }

    @И("^устанавливает количество товара \"([^\"]*)\" шт в блоке 'Купить'$")
    public void setAmountOfProductInBuyBlock(String amount) {

    }

    @И("^добавляет товар в корзину из каточки товара$")
    public void addProductToCartFromProductCard () {

    }

    @И("^нажимает кнопку \"([^\"]*)\"$")
    public void clickButton(String buttonName) {

    }


    @И("^устанавливает параметр \"([^\"]*)\"$")
    public void setParameter(String parameterName) {

    }

    @И("^проверяет наличие и количество товара \"([^\"]*)\" шт в корзине$")
    public void checksAvailabilityAndAmountOfProductInCart(String amountProduct) {

    }
}
