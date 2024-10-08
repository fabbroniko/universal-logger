package com.fabbroniko.ul.adapter;

import com.fabbroniko.ul.level.JdkLevelConverter;
import com.fabbroniko.ul.level.LogLevel;
import com.fabbroniko.ul.level.LevelConverter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JdkLoggerAdapter implements LoggerAdapter {

    private final Logger logger;
    private final LevelConverter<Level> levelConverter;

    public JdkLoggerAdapter() {
        this(Logger.getLogger(Logger.GLOBAL_LOGGER_NAME), new JdkLevelConverter());
    }

    public JdkLoggerAdapter(final Logger logger,
                            final LevelConverter<Level> levelConverter) {
        this.logger = logger;
        this.levelConverter = levelConverter;
    }

    @Override
    public void log(final LogLevel level, final String message) {
        logger.log(levelConverter.convert(level), message);
    }
}
