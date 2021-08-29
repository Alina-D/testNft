package ru.testnft.autotest.steps;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.testnft.autotest.helpers.ConfigContainer;

/**
 * Класс предоставляет базовую работу с шагами для классов-наследников
 */
public abstract class AbstractSteps {

    protected ConfigContainer config = ConfigContainer.getInstance();
    // Инициализация логгера Logback
    protected static final Logger logger = LoggerFactory.getLogger(AbstractSteps.class);

    // --------------------------------------------- Методы --------------------------------------------------

    /**
     * Получить строку-разделитель
     *
     * @return строка-разделитель
     */
    protected String getDelimiterString() {
        return StringUtils.repeat("-", 80);
    }

    /**
     * Печатает наименование шага
     *
     * @param stepName - наименование шага
     */
    protected void printStepName(String stepName) {
        logger.info(getDelimiterString());
        logger.info(stepName);
        logger.info(getDelimiterString());
    }

}
