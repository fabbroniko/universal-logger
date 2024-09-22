package com.fabbroniko;

import com.fabbroniko.adapter.LoggerAdapter;

public class FormattedLogger implements Logger {

    private final LogFormatter logFormatter;
    private final LoggerAdapter loggerAdapter;

    public FormattedLogger(final LogFormatter logFormatter, final LoggerAdapter loggerAdapter) {
        this.logFormatter = logFormatter;
        this.loggerAdapter = loggerAdapter;
    }

    @Override
    public void info(final String operation, final String... arguments) {
        loggerAdapter.log(LogLevel.INFO, logFormatter.info(operation, arguments));
    }

    @Override
    public void warning(final String operation, final String... arguments) {
        loggerAdapter.log(LogLevel.WARNING, logFormatter.warning(operation, arguments));
    }

    @Override
    public void error(final String operation, final Throwable throwable, final String... arguments) {
        loggerAdapter.log(LogLevel.ERROR, logFormatter.error(operation, throwable, arguments));
    }

    @Override
    public void fatal(final String operation, final Throwable throwable, final String... arguments) {
        loggerAdapter.log(LogLevel.FATAL, logFormatter.fatal(operation, throwable, arguments));
    }

    @Override
    public void trace(final String operation, final String... arguments) {
        loggerAdapter.log(LogLevel.TRACE, logFormatter.trace(operation, arguments));
    }
}
