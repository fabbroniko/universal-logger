package com.fabbroniko.ul.adapter;

import com.fabbroniko.ul.level.LevelConverter;
import com.fabbroniko.ul.level.Log4jLevelConverter;
import com.fabbroniko.ul.level.LogLevel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLoggerAdapter implements LoggerAdapter {

    private final Logger logger;
    private final LevelConverter<Level> levelConverter;

    public Log4jLoggerAdapter() {
        this(LogManager.getLogger(), new Log4jLevelConverter());
    }

    public Log4jLoggerAdapter(final Logger logger, final LevelConverter<Level> levelConverter) {
        this.logger = logger;
        this.levelConverter = levelConverter;
    }

    @Override
    public void log(final LogLevel level, final String message) {
        logger.log(levelConverter.convert(level), message);
    }
}
