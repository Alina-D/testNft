package ru.testnft.autotest.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import ru.testnft.autotest.pages.CommonPage;
import ru.testnft.autotest.pages.ProductPage;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

/**
 * Класс описывающий общие шаги Интернет-магазина my-shop.ru
 */
public class BaseSteps extends AbstractSteps {

    private final CommonPage commonPage = new CommonPage();
    private final ProductPage productPage = new ProductPage();

    @When("ожидаем авторизацию в течении {int} минут")
    public void waitAuthorization(int min) {
        printStepName(format("Ожидаем авторизацию %s минут", min));
        commonPage.waitAuthorization("Панель инструментов", min);
        logger.info("Авторизация успешно выполнена");
    }

    @And("открываем страницу покупки боксов по Url {string}")
    public void openPage(String url) {
        printStepName(format("Открываем страницу покупки боксов по Url %s", url));
        open(url);
        logger.info(format("Открыта страница с Url [%s]", url));
    }

    @And("обновляем страницу за 10 мин до начала продаж")
    public void refreshPage() {
        printStepName("Обновляем страницу за %s мин до начала продаж");
        productPage.refreshBoxPage();
    }

    @And("ожидаем кнопку {string} и нажимаем")
    public void waitBtn(String btnName) {
        printStepName(format("Ожидаем кнопку %s", btnName));
        productPage.waitAndClickBtn(btnName);
    }

    @And("устанавливаем кол-во боксов - {string}")
    public void setCountBox(String count) {
        printStepName(format("Устанавливаем кол-во боксов %s", count));
        productPage.setCountBoxes(count);
    }

    @And("принимаем условия Nft-маркета при наличии окна")
    public void acceptTerms() {
        printStepName("Принимаем условия Nft-маркета при наличии окна");
        commonPage.acceptTerms();
    }
}
