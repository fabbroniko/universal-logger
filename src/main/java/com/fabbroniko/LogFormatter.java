package com.fabbroniko;

public interface LogFormatter {

    String info(final String operation, final String... arguments);

    String warning(final String operation, final String... arguments);

    String error(final String operation, final Throwable throwable, final String... arguments);

    String fatal(final String operation, final Throwable throwable, final String... arguments);

    String trace(final String operation, final String... arguments);
}

