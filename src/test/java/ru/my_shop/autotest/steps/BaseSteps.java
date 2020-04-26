package ru.my_shop.autotest.steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import ru.my_shop.autotest.models.ProductModel;
import ru.my_shop.autotest.pages.*;

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
        // todo добавить логи для каждого шага
        commonPage.searchProduct(productName);
    }

    @То("^проверяет результаты поиска по ключевому слову \"([^\"]*)\"$")
    public void checkResultsByKeyword(String productName) {
        catalogPage.checkSearchResults(productName);
    }

    @Тогда("^сортирует товары \"([^\"]*)\"$")
    public void sortProduct(String typeSorting) {
        catalogPage.sortProducts(typeSorting);
    }

    @И("^проверяет сортировку товаров в алфавитном порядке$")
    public void checkSortingAlphabetically() {
        catalogPage.checkSortingAlphabetically();
    }

    @И("^откывает карточку товара по имени товара$")
    public void openCardProductByName() {
        commonPage.openProductCardByName();
        productCardPage.checkProductCardName();
    }

    @Когда("^выводит информацию о товаре$")
    public void printProductInfo() {
        commonPage.printProductInfo();
    }

    @И("^выбирает раздел \"([^\"]*)\"$")
    public void selectSection(String sectionName) {
        catalogPage.selectCatalogElement(sectionName);
    }

    @Когда("^нажимает элемент меню \"([^\"]*)\"$")
    public void clickMenuElement(String elementName) {
        commonPage.clickMenuElement(elementName);
    }

    @И("^выбирает категорию \"([^\"]*)\"$")
    public void selectCategory(String categoryName) {
        commonPage.selectCategory(categoryName);
    }

    @И("^выбирает подкатегорию \"([^\"]*)\"$")
    public void selectSubcategory(String subcategoryName) {
        commonPage.selectCatalogElement(subcategoryName);
    }

    @И("^выбирает подраздел \"([^\"]*)\"$")
    public void selectSubsection(String subsectionName) {
        commonPage.selectCatalogElement(subsectionName);
    }

    @И("^устанавливает количество товара (\\d+) шт в блоке 'Купить'$")
    public void setAmountProductInBuyBlock(int amountProduct) {
        productCardPage.setAmountProductInBuyBlock(amountProduct);
    }

    @И("^добавляет товар в корзину из каточки товара$")
    public void addProductToCartFromProductCard() throws InterruptedException {
        productCardPage.addProductToCartFromProductCard();
    }

    @И("^переходит в корзину$")
    public void goToCard() {
        commonPage.clickToCartLink();
    }

    @И("^выводит информацию о найденных товарах$")
    public void printInfoAboutProductsFound() {
        catalogPage.printInfoAboutProductsFound();
    }

    // todo устанавливает сделать сохраняет удалить
    @Когда("^сохраняет информацию о товаре на главной странице$")
    public void saveProductInfoOnHomePage() {
        ProductModel product = config.getProductModel();
        homePage.saveProductInfo(0, product);
    }

    @И("^сохраняет информацию о товаре на карточке товара$")
    public void setProductInfoOnCardProduct() {
        ProductModel product = config.getProductModel();
        productCardPage.saveProductInfo(0, product);
    }

    @И("^проверяет наличие и количество товара (\\d+) шт в корзине$")
    public void checkAvailabilityOfProductInCardInAmount(int amount) {
        cartPage.checkAvailabilityOfProduct(amount);
    }

    @И("^сохраняет информацию о товаре на странице каталога$")
    public void setProductInfoOnCatalogPage() {
        ProductModel product = config.getProductModel();
        catalogPage.saveProductInfo(0, product);
    }

    @И("^откывает карточку товара по №(\\d+) в списке$")
    public void openProductCard(int number) {
        catalogPage.openProductCard(number);
    }
}
