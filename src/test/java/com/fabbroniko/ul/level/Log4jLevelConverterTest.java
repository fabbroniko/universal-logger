package com.fabbroniko.ul.level;

import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Log4jLevelConverterTest {

    private LevelConverter<Level> levelConverter;

    @BeforeEach
    void setUp() {
        levelConverter = new Log4jLevelConverter();
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void shouldConvertToLog4jLevel(final LogLevel input, final Level expectedLevel) {
        assertThat(levelConverter.convert(input)).isEqualTo(expectedLevel);
    }

    static Stream<Arguments> arguments() {
        return Stream.of(
            Arguments.of(LogLevel.INFO, Level.INFO),
            Arguments.of(LogLevel.WARNING, Level.WARN),
            Arguments.of(LogLevel.ERROR, Level.ERROR),
            Arguments.of(LogLevel.FATAL, Level.FATAL),
            Arguments.of(LogLevel.TRACE, Level.TRACE)
        );
    }
}