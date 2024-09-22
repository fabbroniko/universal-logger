package com.fabbroniko.ul.adapter;

import com.fabbroniko.ul.level.LogLevel;

public interface LoggerAdapter {

    void log(final LogLevel level, final String message);
}
