package com.fabbroniko.adapter;

import com.fabbroniko.LogLevel;

public interface LevelConverter<T> {

    T convert(final LogLevel logLevel);
}
