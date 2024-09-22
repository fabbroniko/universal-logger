package com.fabbroniko.ul.adapter;

import com.fabbroniko.ul.level.LevelConverter;
import com.fabbroniko.ul.level.LogLevel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Log4jLoggerAdapterTest {

    private static final String MESSAGE = "This is a test";

    @Mock
    private Logger logger;
    @Mock
    private LevelConverter<Level> levelConverter;
    @Mock
    private LogLevel logLevel;
    @Mock
    private Level level;
    @InjectMocks
    private Log4jLoggerAdapter loggerAdapter;

    @Test
    void shouldConvertLevel() {
        loggerAdapter.log(logLevel, MESSAGE);

        verify(levelConverter).convert(logLevel);
    }

    @Test
    void shouldLog() {
        when(levelConverter.convert(any())).thenReturn(level);

        loggerAdapter.log(logLevel, MESSAGE);

        verify(logger).log(level, MESSAGE);
    }
}