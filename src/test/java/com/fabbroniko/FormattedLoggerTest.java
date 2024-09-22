package com.fabbroniko;

import com.fabbroniko.ul.adapter.LoggerAdapter;
import com.fabbroniko.ul.FormattedLogger;
import com.fabbroniko.ul.formatter.LogFormatter;
import com.fabbroniko.ul.level.LogLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FormattedLoggerTest {

    private static final String OPERATION = "operation";
    private static final String[] ARGUMENTS = new String[]{"arg1", "arg2"};
    private static final String FORMATTED_MESSAGE = "Formatted message";

    @Mock
    private LogFormatter logFormatter;
    @Mock
    private LoggerAdapter loggerAdapter;
    @Mock
    private Throwable throwable;
    @InjectMocks
    private FormattedLogger formattedLogger;

    @Test
    void shouldFormatInfoLog() {
        formattedLogger.info(OPERATION, ARGUMENTS);

        verify(logFormatter).info(OPERATION, ARGUMENTS);
    }

    @Test
    void shouldFormatWarningLog() {
        formattedLogger.warning(OPERATION, ARGUMENTS);

        verify(logFormatter).warning(OPERATION, ARGUMENTS);
    }

    @Test
    void shouldFormatErrorLog() {
        formattedLogger.error(OPERATION, throwable, ARGUMENTS);

        verify(logFormatter).error(OPERATION, throwable, ARGUMENTS);
    }

    @Test
    void shouldFormatFatalLog() {
        formattedLogger.fatal(OPERATION, throwable, ARGUMENTS);

        verify(logFormatter).fatal(OPERATION, throwable, ARGUMENTS);
    }

    @Test
    void shouldFormatTraceLog() {
        formattedLogger.trace(OPERATION, ARGUMENTS);

        verify(logFormatter).trace(OPERATION, ARGUMENTS);
    }

    @Test
    void shouldDelegateInfoLoggingToAdapter() {
        when(logFormatter.info(anyString(), anyString(), anyString())).thenReturn(FORMATTED_MESSAGE);

        formattedLogger.info(OPERATION, ARGUMENTS);

        verify(loggerAdapter).log(LogLevel.INFO, FORMATTED_MESSAGE);
    }

    @Test
    void shouldDelegateWarningLoggingToAdapter() {
        when(logFormatter.warning(anyString(), anyString(), anyString())).thenReturn(FORMATTED_MESSAGE);

        formattedLogger.warning(OPERATION, ARGUMENTS);

        verify(loggerAdapter).log(LogLevel.WARNING, FORMATTED_MESSAGE);
    }

    @Test
    void shouldDelegateErrorLoggingToAdapter() {
        when(logFormatter.error(anyString(), any(), anyString(), anyString())).thenReturn(FORMATTED_MESSAGE);

        formattedLogger.error(OPERATION, throwable, ARGUMENTS);

        verify(loggerAdapter).log(LogLevel.ERROR, FORMATTED_MESSAGE);
    }

    @Test
    void shouldDelegateFatalLoggingToAdapter() {
        when(logFormatter.fatal(anyString(), any(), anyString(), anyString())).thenReturn(FORMATTED_MESSAGE);

        formattedLogger.fatal(OPERATION, throwable, ARGUMENTS);

        verify(loggerAdapter).log(LogLevel.FATAL, FORMATTED_MESSAGE);
    }

    @Test
    void shouldDelegateTraceLoggingToAdapter() {
        when(logFormatter.trace(anyString(), anyString(), anyString())).thenReturn(FORMATTED_MESSAGE);

        formattedLogger.trace(OPERATION, ARGUMENTS);

        verify(loggerAdapter).log(LogLevel.TRACE, FORMATTED_MESSAGE);
    }
}