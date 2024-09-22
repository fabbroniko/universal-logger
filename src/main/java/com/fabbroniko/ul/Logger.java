package com.fabbroniko.ul;

public interface Logger {

    void info(final String operation, final String... arguments);

    void warning(final String operation, final String... arguments);

    void error(final String operation, final Throwable throwable,  final String... arguments);

    void fatal(final String operation,  final Throwable throwable, final String... arguments);

    void trace(final String operation, final String... arguments);
}
