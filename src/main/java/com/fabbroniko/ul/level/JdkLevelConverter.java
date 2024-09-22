package com.fabbroniko.ul.level;

import java.util.logging.Level;

public class JdkLevelConverter implements LevelConverter<Level> {

    @Override
    public Level convert(final LogLevel logLevel) {
        return switch (logLevel) {
            case INFO -> Level.INFO;
            case WARNING -> Level.WARNING;
            case ERROR, FATAL -> Level.SEVERE;
            case TRACE -> Level.FINE;
        };
    }
}
