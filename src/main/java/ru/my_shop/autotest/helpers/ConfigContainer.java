package ru.my_shop.autotest.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.my_shop.autotest.models.ProductModel;
import ru.my_shop.autotest.pages.AbstractPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Содержит настройки тестовой среды для всех тестов.
 */
public class ConfigContainer {

    // Статический экземпляр этого класса (собственно сам ConfigContainer)
    private static ConfigContainer instance;

    /**
     * Методы доступа к экземпляру этого класса
     */
    public static synchronized ConfigContainer getInstance() {
        if (instance == null) {
            instance = new ConfigContainer();
        }
        return instance;
    }


    private static final Logger logger = LoggerFactory.getLogger(AbstractPage.class);

    // Настройки тестовой среды (считываются из файла config.properties и используются во всех тестовых сценариях)
    private Properties properties = new Properties();

    // Объект описывающий товар конкретного тестового сценария (name, price и прочее, что генерируется в ходе теста)
    // для передачи между шагами теста. Существуют в памяти только во время выполнения теста.
    private ProductModel productModel = new ProductModel();

    /**
     * Получить значение настройки тестовой среды из файла config.properties
     *
     * @param key - ключ настройки
     */
    public String getProperties(String key) {
        return this.properties.getProperty(key);
    }

    /**
     * Получить объект товара
     */
    public ProductModel accessProductModel() {
        return productModel;
    }

    /**
     * Загрузка конфигурации
     *
     * @throws IOException - исключение, если файл не найден
     */
    public void loadConfig() throws IOException {
        properties.load(new FileReader(new File("src/test/resources/config.properties")));
    }
}
