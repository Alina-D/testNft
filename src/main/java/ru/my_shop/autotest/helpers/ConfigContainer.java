package ru.my_shop.autotest.helpers;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.my_shop.autotest.pages.AbstractPage;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

// todo назначение класса
import static org.junit.Assert.*;

public class ConfigContainer {

    private static final Logger logger
            = LoggerFactory.getLogger(AbstractPage.class);

    // Статический экземпляр этого класса (собственно сам ConfigContainer)
    private static ConfigContainer instance;

    // Настройки тестовой среды (считываются из файла config.properties и используются во всех тестовых сценариях)
    private Properties properties = new Properties();

    // Параметры конкретного тестового сценария (id, name и прочее, что генерируется в ходе теста) для передачи между
    // шагами теста. Существуют в памяти только во время выполнения теста.
    private Map<String, Object> parameters = new HashMap<>();

    /**
     * Методы доступа к экземпляру этого класса
     */
    public static synchronized ConfigContainer getInstance() {
        if (instance == null) instance = new ConfigContainer();
        return instance;
    }

    public Properties getProperties(){
        return this.properties;
    }


    public void setParameter(String key, Object value) {
        logger.info("Установлен ключ: [" + key + "] и параметр: [" + value + "]");

        // Блокируем возможность записать в параметр значения null или "пустая строка"
        assertNotNull("Попытка установить значение ключа равно NULL", key);
        assertNotNull ("Попытка установить значение параметра = null", value);
        assertNotEquals("Попытка установить пустое значение ключа", key,"");
        assertNotEquals("Попытка установить пустое значение параметра", value, "");

        // Защита от "дурака" - перезапись существующего параметра в большинстве случаев признак ошибки в коде
        if (parameters.get(key) != null) {
            logger.info("Перезапись значения параметра, старое значение: " +
                    "[" + parameters.get(key) + "], новое значение: [" + value + "]");
        }

        parameters.put(key, value);
    }

    // todo комменты и поменять логгер (без[])
    public void setParameter(String key, String value) {
        logger.info("Установлен ключ: [" + key + "] и параметр: [" + value + "]");

        // Блокируем возможность записать в параметр значения null или "пустая строка"
        assertNotNull("Попытка установить значение ключа равно NULL", key);
        assertNotNull("Попытка установить значение параметра = null", value);
        assertNotEquals("Попытка установить пустое значение ключа", key,"");
        assertNotEquals("Попытка установить пустое значение параметра", value, "");

        // Защита от "дурака" - перезапись существующего параметра в большинстве случаев признак ошибки в коде
        if (parameters.get(key) != null) {
            logger.info("Перезапись значения параметра, старое значение: " +
                    "[" + parameters.get(key) + "], новое значение: [" + value + "]");
        }

        parameters.put(key, value);
    }

    public Object getParameter(String key) {
        // Контролируем переданное значение ключа для поиска параметра (не null и не пустая строка)
        assertNotNull("Значение переданного ключа = null !", key);
        assertNotEquals("Пустое значение переданного ключа !", key, "");

        Object value = parameters.get(key);

        // Контролируем полученное по ключу значение параметра (не null и не пустая строка)
        logger.info("Получен ключ: <" + key + "> и параметр: <" + value + ">");
        assertNotNull("Значение полученного параметра равно NULL", value);
        assertNotEquals("Пустое значение полученного параметра", value, "");

        return value;
    }




    public void clearParameters(){
        parameters.clear();
    }


}
