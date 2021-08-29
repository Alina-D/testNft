package ru.testnft.autotest.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.testnft.autotest.pages.AbstractPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Содержит настройки тестовой среды для всех тестов.
 */
public class ConfigContainer {

    // Статический экземпляр этого класса (собственно сам ConfigContainer)
    private static ConfigContainer instance;
    // Путь к файлу конфигурации
    private static final String PATH_PROPERTIES_FILE = "src/test/resources/config.properties";
    // Инициализация логгера Logback
    private static final Logger logger = LoggerFactory.getLogger(AbstractPage.class);
    // Настройки тестовой среды (считываются из файла config.properties и используются во всех тестовых сценариях)
    private Properties properties = new Properties();


    /**
     * Методы доступа к экземпляру этого класса
     */
    public static synchronized ConfigContainer getInstance() {
        if (instance == null) {
            instance = new ConfigContainer();
        }
        return instance;
    }

    /**
     * Получить значение настройки тестовой среды из файла config.properties
     *
     * @param key - ключ настройки
     */
    public String getProperties(String key) {
        return this.properties.getProperty(key);
    }


    /**
     * Загрузить конфигурацию
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
