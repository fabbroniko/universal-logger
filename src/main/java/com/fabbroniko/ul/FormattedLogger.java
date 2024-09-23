package com.fabbroniko.ul;

import com.fabbroniko.ul.adapter.LoggerAdapter;
import com.fabbroniko.ul.formatter.LogFormatter;
import com.fabbroniko.ul.level.LogLevel;

public class FormattedLogger implements Logger {

    private final LogFormatter logFormatter;
    private final LoggerAdapter loggerAdapter;

    public FormattedLogger(final LogFormatter logFormatter, final LoggerAdapter loggerAdapter) {
        this.logFormatter = logFormatter;
        this.loggerAdapter = loggerAdapter;
    }

    @Override
    public void info(final String message, final String... arguments) {
        loggerAdapter.log(LogLevel.INFO, logFormatter.info(message, arguments));
    }

    @Override
    public void warning(final String message, final String... arguments) {
        loggerAdapter.log(LogLevel.WARNING, logFormatter.warning(message, arguments));
    }

    @Override
    public void error(final String message, final Throwable throwable, final String... arguments) {
        loggerAdapter.log(LogLevel.ERROR, logFormatter.error(message, throwable, arguments));
    }

    @Override
    public void error(final String message, final String... arguments) {
        error(message, null, arguments);
    }

    @Override
    public void fatal(final String message, final Throwable throwable, final String... arguments) {
        loggerAdapter.log(LogLevel.FATAL, logFormatter.fatal(message, throwable, arguments));
    }

    @Override
    public void fatal(final String message, final String... arguments) {
        fatal(message, null, arguments);
    }

    @Override
    public void trace(final String message, final String... arguments) {
        loggerAdapter.log(LogLevel.TRACE, logFormatter.trace(message, arguments));
    }
}
