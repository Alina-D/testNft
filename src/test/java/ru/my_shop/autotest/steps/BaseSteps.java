package ru.my_shop.autotest.steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import ru.my_shop.autotest.models.ProductModel;
import ru.my_shop.autotest.pages.*;

import static java.lang.String.format;

/**
 * Класс описывающий общие шаги Интернет-магазина my-shop.ru
 */
public class BaseSteps extends AbstractSteps {

    private CommonPage commonPage = new CommonPage();
    private ProductCardPage productCardPage = new ProductCardPage();
    private CatalogPage catalogPage = new CatalogPage();
    private HomePage homePage = new HomePage();
    private CartPage cartPage = new CartPage();

    @Когда("^выполняет поиск товара \"([^\"]*)\"$")
    public void searchProduct(String productName) {
        printStepName(format("выполняет поиск товара: '%s'", productName));
        commonPage.searchProduct(productName);
    }

    @То("^проверяет результаты поиска по ключевому слову \"([^\"]*)\"$")
    public void checkResultsByKeyword(String productName) {
        printStepName(format("проверяет результаты поиска по ключевому слову: '%s'", productName));
        catalogPage.checkSearchResults(productName);
    }

    @Тогда("^сортирует товары \"([^\"]*)\"$")
    public void sortProduct(String typeSorting) {
        printStepName(format("сортирует товары: '%s'", typeSorting));
        catalogPage.sortProducts(typeSorting);
    }

    @И("^проверяет сортировку товаров в алфавитном порядке$")
    public void checkSortingAlphabetically() {
        printStepName("проверяет сортировку товаров в алфавитном порядке");
        catalogPage.checkSortingAlphabetically();
    }

    @И("^откывает карточку товара по имени товара$")
    public void openCardProductByName() {
        printStepName("откывает карточку товара по имени товара");
        commonPage.openProductCardByName();
        productCardPage.checkProductCardName();
    }

    @Когда("^выводит информацию о товаре$")
    public void printProductInfo() {
        printStepName("выводит информацию о товаре");
        commonPage.printProductInfo();
    }

    @И("^выбирает раздел \"([^\"]*)\"$")
    public void selectSection(String sectionName) {
        printStepName(format("выбирает раздел: '%s'", sectionName));
        catalogPage.selectCatalogElement(sectionName);
    }

    @Когда("^нажимает элемент меню \"([^\"]*)\"$")
    public void clickMenuElement(String elementName) {
        printStepName(format("нажимает элемент меню: '%s'", elementName));
        commonPage.clickMenuElement(elementName);
    }

    @И("^выбирает категорию \"([^\"]*)\"$")
    public void selectCategory(String categoryName) {
        printStepName(format("выбирает категорию: '%s'", categoryName));
        commonPage.selectCategory(categoryName);
    }

    @И("^выбирает подкатегорию \"([^\"]*)\"$")
    public void selectSubcategory(String subcategoryName) {
        printStepName(format("выбирает подкатегорию: '%s'", subcategoryName));
        commonPage.selectCatalogElement(subcategoryName);
    }

    @И("^выбирает подраздел \"([^\"]*)\"$")
    public void selectSubsection(String subsectionName) {
        printStepName(format("выбирает подраздел: '%s'", subsectionName));
        commonPage.selectCatalogElement(subsectionName);
    }

    @И("^устанавливает количество товара (\\d+) шт в блоке 'Купить'$")
    public void setAmountProductInBuyBlock(int amountProduct) {
        printStepName(format("устанавливает количество товара: '%d'", amountProduct));
        productCardPage.setAmountProductInBuyBlock(amountProduct);
    }

    @И("^добавляет товар в корзину из каточки товара$")
    public void addProductToCartFromProductCard() throws InterruptedException {
        printStepName("добавляет товар в корзину из каточки товара");
        productCardPage.addProductToCartFromProductCard();
    }

    @И("^переходит в корзину$")
    public void goToCard() {
        printStepName("переходит в корзину");
        commonPage.clickToCartLink();
    }

    @И("^выводит информацию о найденных товарах$")
    public void printInfoAboutProductsFound() {
        printStepName("выводит информацию о найденных товарах");
        catalogPage.printInfoAboutProductsFound();
    }

    @Когда("^сохраняет информацию о товаре на главной странице$")
    public void saveProductInfoOnHomePage() {
        printStepName("сохраняет информацию о товаре на главной странице");
        ProductModel product = config.getProductModel();
        homePage.saveProductInfo(0, product);
    }

    @И("^сохраняет информацию о товаре на карточке товара$")
    public void setProductInfoOnCardProduct() {
        printStepName("сохраняет информацию о товаре на карточке товара");
        ProductModel product = config.getProductModel();
        productCardPage.saveProductInfo(0, product);
    }

    @И("^проверяет наличие и количество товара (\\d+) шт в корзине$")
    public void checkAvailabilityOfProductInCardInAmount(int amountProduct) {
        printStepName(format("проверяет наличие и количество товара %d шт в корзине", amountProduct));
        cartPage.checkAvailabilityOfProduct(amountProduct);
    }

    @И("^сохраняет информацию о товаре на странице каталога$")
    public void setProductInfoOnCatalogPage() {
        printStepName("сохраняет информацию о товаре на странице каталога");
        ProductModel product = config.getProductModel();
        catalogPage.saveProductInfo(0, product);
    }

    @И("^откывает карточку товара по №(\\d+) в списке$")
    public void openProductCard(int productIndex) {
        printStepName(format("откывает карточку товара по №%d в списке", productIndex));
        catalogPage.openProductCard(productIndex);
    }
}
