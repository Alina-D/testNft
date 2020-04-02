package ru.my_shop.autotest.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class CommonSteps {


    @Given("^Проверка - Шаг 1$")
    public void checkStepOne() {
        System.out.println("Проверка: Шаг 1");
    }

    // todo вроде можно указвать {} вместо "([^"]*)", но не факт.
    @When("^Проверка - Шаг 2 с параметром \"([^\"]*)\"$")
    public void checkStepOneWithParameter(String parameter) {
        System.out.printf("Проверка: Шаг 1  параметром %s%n", parameter);
    }
}
