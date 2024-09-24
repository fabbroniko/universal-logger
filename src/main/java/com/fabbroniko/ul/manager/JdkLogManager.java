package com.fabbroniko.ul.manager;

import com.fabbroniko.ul.FormattedLogger;
import com.fabbroniko.ul.Logger;
import com.fabbroniko.ul.adapter.JdkLoggerAdapter;
import com.fabbroniko.ul.adapter.LoggerAdapter;
import com.fabbroniko.ul.formatter.JsonLogFormatter;
import com.fabbroniko.ul.level.JdkLevelConverter;

public class JdkLogManager implements LogManager {

    @Override
    public Logger getLogger(final Class<?> clazz) {
        final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(clazz.getName());
        final LoggerAdapter loggerAdapter = new JdkLoggerAdapter(logger, new JdkLevelConverter());
        return new FormattedLogger(new JsonLogFormatter(), loggerAdapter);
    }
}
