package com.fabbroniko.ul.manager;

import com.fabbroniko.ul.FormattedLogger;
import com.fabbroniko.ul.Logger;
import com.fabbroniko.ul.adapter.Log4jLoggerAdapter;
import com.fabbroniko.ul.adapter.LoggerAdapter;
import com.fabbroniko.ul.formatter.JsonLogFormatter;
import com.fabbroniko.ul.level.Log4jLevelConverter;

public class Log4jLogManager implements LogManager {

    @Override
    public Logger getLogger(final Class<?> clazz) {
        final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(clazz);
        final LoggerAdapter loggerAdapter = new Log4jLoggerAdapter(logger, new Log4jLevelConverter());
        return new FormattedLogger(new JsonLogFormatter(), loggerAdapter);
    }
}
