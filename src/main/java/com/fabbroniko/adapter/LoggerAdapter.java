package com.fabbroniko.adapter;

import com.fabbroniko.LogLevel;

public interface LoggerAdapter {

    void log(final LogLevel level, final String message);
}
