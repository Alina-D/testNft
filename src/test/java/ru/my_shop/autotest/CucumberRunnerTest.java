package ru.my_shop.autotest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//@CucumberOptions(features = "src/test/resources/features", glue = "steps")
@RunWith(Cucumber.class)
@CucumberOptions(

        // путь к фичам
        features = {"src/test/resource/feature"},

        // путь к шагам
        glue = {"ru/my_shop/autotest/steps"},

        // делает вывод в консоле более читаемым
        monochrome = true,

        // тесты помеченныей данной аннотацией будут проигнорированы
        tags = "not @Ignore",

        // pretty - более подробный вывод в консоль
        // html:target/cucumber-reports/cucumber-pretty - отчеты в формате XML
        // json:target/cucumber-reports/CucumberTestReport.json - отчеты в формате JSON. Этот отчет предназначен
        // для последующей обработки в другом визуальном формате сторонними инструментами, такими как Cucumber Jenkins.
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json"})

// Имя класса должно заканчиваться на Test.
public class CucumberRunnerTest {
}
