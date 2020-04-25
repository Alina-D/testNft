package ru.my_shop.autotest.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.my_shop.autotest.models.ProductModel;
import ru.my_shop.autotest.pages.AbstractPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Содержит настройки тестовой среды для всех тестов.
 */
public class ConfigContainer {

    // Статический экземпляр этого класса (собственно сам ConfigContainer)
    private static ConfigContainer instance;
    private static final String PATH_PROPERTIES_FILE = "src/test/resources/config.properties";


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
    public ProductModel getProductModel() {
        return productModel;
    }

    /**
     * Загружает конфигурацию
     */
    public void loadConfig() {

        // При выходе из блока try файл, связанный с локальной переменной input, автоматически закрывается
        // с помощью неявно вызываемого метода close().
        try (FileInputStream input = new FileInputStream(PATH_PROPERTIES_FILE)) {

            // считывает список свойств конфигурационного файла (пары ключей и значений)
            properties.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
