package ru.my_shop.autotest.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.my_shop.autotest.helpers.ConfigContainer;

/**
 * Класс предоставляет базовую работу с шагами для классов-наследников
 */
public abstract class AbstractSteps {

    protected ConfigContainer config = ConfigContainer.getInstance();
    protected static final Logger logger
            = LoggerFactory.getLogger(AbstractSteps.class);

}
