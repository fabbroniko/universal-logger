package com.fabbroniko.ul.adapter;

import com.fabbroniko.ul.level.LogLevel;

public class NoLoggerAdapter implements com.fabbroniko.ul.adapter.LoggerAdapter {

    @Override
    public void log(final LogLevel level, final String message) {
    }
}
