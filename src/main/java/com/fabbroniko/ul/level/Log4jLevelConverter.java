package com.fabbroniko.ul.level;

import org.apache.logging.log4j.Level;

public class Log4jLevelConverter implements LevelConverter<Level> {

    @Override
    public Level convert(final LogLevel logLevel) {
        return switch (logLevel) {
            case INFO -> Level.INFO;
            case WARNING -> Level.WARN;
            case ERROR -> Level.ERROR;
            case FATAL -> Level.FATAL;
            case TRACE -> Level.TRACE;
        };
    }
}
