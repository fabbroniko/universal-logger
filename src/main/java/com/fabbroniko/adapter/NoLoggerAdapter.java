package com.fabbroniko.adapter;

import com.fabbroniko.LogLevel;

public class NoLoggerAdapter implements LoggerAdapter {

    @Override
    public void log(final LogLevel level, final String message) {
    }
}
