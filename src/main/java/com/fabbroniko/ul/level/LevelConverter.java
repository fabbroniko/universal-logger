package com.fabbroniko.ul.level;

public interface LevelConverter<T> {

    T convert(final LogLevel logLevel);
}
