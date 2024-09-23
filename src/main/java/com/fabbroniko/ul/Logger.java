package com.fabbroniko.ul;

public interface Logger {

    void info(final String message, final String... arguments);

    void warning(final String message, final String... arguments);

    void error(final String message, final Throwable throwable,  final String... arguments);

    void error(final String message,  final String... arguments);

    void fatal(final String message,  final Throwable throwable, final String... arguments);

    void fatal(final String message, final String... arguments);

    void trace(final String message, final String... arguments);
}
