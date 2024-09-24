package com.fabbroniko.ul.manager;

import com.fabbroniko.ul.Logger;

public class NoLogManager implements LogManager {

    private static final Logger NO_LOGGER = new NoLogger();

    @Override
    public Logger getLogger(Class<?> clazz) {
        return NO_LOGGER;
    }

    private static class NoLogger implements Logger {

        @Override
        public void info(final String message, final String... arguments) {
        }

        @Override
        public void warning(final String message, final String... arguments) {
        }

        @Override
        public void error(final String message, final Throwable throwable, final String... arguments) {
        }

        @Override
        public void error(final String message, final String... arguments) {
        }

        @Override
        public void fatal(final String message, final Throwable throwable, final String... arguments) {
        }

        @Override
        public void fatal(final String message, final String... arguments) {
        }

        @Override
        public void trace(final String message, final String... arguments) {
        }
    }
}
