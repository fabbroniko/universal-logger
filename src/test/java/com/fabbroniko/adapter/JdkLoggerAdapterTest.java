package com.fabbroniko.adapter;

import com.fabbroniko.LogLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JdkLoggerAdapterTest {

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
    private JdkLoggerAdapter loggerAdapter;

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