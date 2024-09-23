package com.fabbroniko.ul.formatter;

public interface LogFormatter {

    String info(final String message, final String... arguments);

    String warning(final String message, final String... arguments);

    String error(final String message, final Throwable throwable, final String... arguments);

    String fatal(final String message, final Throwable throwable, final String... arguments);

    String trace(final String message, final String... arguments);
}

