package com.fabbroniko.adapter;

import com.fabbroniko.LogLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.logging.Level;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JdkLevelConverterTest {

    private LevelConverter<Level> levelConverter;

    @BeforeEach
    void setUp() {
        levelConverter = new JdkLevelConverter();
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void shouldConvertToJdkLevel(final LogLevel input, final Level expectedLevel) {
        assertThat(levelConverter.convert(input)).isEqualTo(expectedLevel);
    }

    static Stream<Arguments> arguments() {
        return Stream.of(
            Arguments.of(LogLevel.INFO, Level.INFO),
            Arguments.of(LogLevel.WARNING, Level.WARNING),
            Arguments.of(LogLevel.ERROR, Level.SEVERE),
            Arguments.of(LogLevel.FATAL, Level.SEVERE),
            Arguments.of(LogLevel.TRACE, Level.FINE)
        );
    }
}