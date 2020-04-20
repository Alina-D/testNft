package ru.my_shop.autotest.steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import ru.my_shop.autotest.helpers.ConfigContainer;
import ru.my_shop.autotest.models.ProductModel;
import ru.my_shop.autotest.pages.*;

/**
 * Класс описывающий общие шаги Интернет-магазина my-shop.ru
 */
public class BaseSteps extends AbstractSteps {

    private CommonPage commonPage = new CommonPage();
    private CardProductPage cardProductPage = new CardProductPage();
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
        catalogPage.sortProduct(typeSorting);
    }

    @И("^проверяет сортировку товаров в алфавитном порядке$")
    public void checkSortingAlphabetically() {
        catalogPage.checkSortingAlphabetically();
    }

    @И("^откывает карточку товара по имени товара$")
    public void openCardProductByName() {
        commonPage.openCardProductByName();
        cardProductPage.checksThatProductCardContainsCorrectInfo();
    }

    @Когда("^выводит информацию о товаре$")
    public void printProductInfo() {
        commonPage.printInfoProduct();
    }

    @И("^выбирает раздел \"([^\"]*)\"$")
    public void selectSection(String sectionName) {
        catalogPage.selectCatalogElement(sectionName);
    }

    @Когда("^нажимает элемент меню \"([^\"]*)\"$")
    public void clickElementMenu(String elementName) {
        commonPage.clickElementMenu(elementName);
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
    public void setAmountOfProductInBuyBlock(int amountProduct) {
        cardProductPage.setAmountOfProductInBuyBlock(amountProduct);
    }

    @И("^добавляет товар в корзину из каточки товара$")
    public void addProductToCartFromProductCard() throws InterruptedException {
        cardProductPage.addProductToCartFromProductCard();
    }

    @И("^переходит в корзину$")
    public void goToCard() {
        commonPage.goToCard();
    }

    @И("^выводит информацию о найденных товарах$")
    public void printInfoAboutProductsFound() {
        catalogPage.printInfoAboutProductsFound();
    }

    @Когда("^получает и сохраняет информацию о товаре на главной странице$")
    public void getAndSaveProductInfoOnHomePage() {
        homePage.getAndSaveProductInfoOnHomePage();
    }

    @И("^получает и сохраняет информацию о товаре на карточке товара$")
    public void getAndSaveProductInfoOnCardProduct() {
        cardProductPage.getAndSaveProductInfoOnCardProduct();
    }

    @И("^проверяет наличие и количество товара (\\d+) шт в корзине$")
    public void checkAvailabilityOfProductInCardInAmount(int amount) {
        cartPage.checkAvailabilityOfProductInAmount(amount);
    }

    @И("^получает и сохраняет информацию о товаре на странице каталога$")
    public void getAndSaveProductInfoOnCatalogPage() {
        ProductModel product = config.accessProductModel();
        catalogPage.getInfoAboutProduct(0, product);
    }

    @И("^откывает карточку товара по №(\\d+) в списке$")
    public void openCardProductByNumber(int number) {
        catalogPage.openCardProduct(number);
    }
}
