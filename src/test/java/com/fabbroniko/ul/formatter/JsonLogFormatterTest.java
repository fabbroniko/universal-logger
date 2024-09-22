package com.fabbroniko.ul.formatter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JsonLogFormatterTest {

    private static final String OPERATION = "operation";
    private static final String[] ARGUMENTS = new String[]{"arg1", "arg2"};
    private static final String TIMESTAMP = "2022-06-22T04:38:55.902569200+03:00";
    private static final String EXCEPTION_MESSAGE = "Exception message";
    private static final String FORMATTED_LOG = """
        {
          "level": "INFO",
          "timestamp": "2022-06-22T04:38:55.902569200+03:00",
          "operation": "doing_something",
          "arguments": [
            "arg1",
            "arg2"
          ]
        }
        """;

    @Mock
    private Supplier<Instant> instantSupplier;
    @Mock
    private Instant instant;
    @Mock
    private Supplier<JSONObject> formatterSupplier;
    @Mock
    private JSONObject jsonObject;
    @Mock
    private Supplier<JSONArray> argumentsSupplier;
    @Mock
    private JSONArray jsonArray;
    @Mock
    private Throwable throwable;
    @Mock
    private JSONObject exceptionObject;

    private JsonLogFormatter jsonLogFormatter;

    @BeforeEach
    void setUp() {
        when(formatterSupplier.get()).thenReturn(jsonObject);
        when(instantSupplier.get()).thenReturn(instant);
        when(argumentsSupplier.get()).thenReturn(jsonArray);
        when(instant.toString()).thenReturn(TIMESTAMP);
        when(jsonObject.toString()).thenReturn(FORMATTED_LOG);

        jsonLogFormatter = new JsonLogFormatter(instantSupplier, formatterSupplier, argumentsSupplier);
    }

    @Test
    void shouldCreateRootJsonObject() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(formatterSupplier).get();
    }

    @Test
    void shouldGetCurrentInstant() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(instantSupplier).get();
    }

    @Test
    void shouldAddTimestampToJson() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(jsonObject).put("timestamp", TIMESTAMP);
    }

    @Test
    void shouldAddLevelToJson() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(jsonObject).put("level", "INFO");
    }

    @Test
    void shouldAddOperationToJson() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(jsonObject).put("operation", OPERATION);
    }

    @Test
    void shouldCreateArgumentsArray() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(argumentsSupplier).get();
    }

    @Test
    void shouldAddFirstArgumentToArgumentsArray() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(jsonArray).put("arg1");
    }

    @Test
    void shouldAddSecondArgumentToArgumentsArray() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(jsonArray).put("arg2");
    }

    @Test
    void shouldAddArgumentsArrayToJson() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(jsonObject).put("arguments", jsonArray);
    }

    @Test
    void shouldNotAddExceptionToJson() {
        jsonLogFormatter.info(OPERATION, ARGUMENTS);

        verify(jsonObject, never()).put(eq("exception"), any(JSONObject.class));
    }

    @Test
    void shouldReturnValueFromFormatter() {
        assertThat(jsonLogFormatter.info(OPERATION, ARGUMENTS))
            .isEqualTo(FORMATTED_LOG);
    }

    @Test
    void shouldCreateExceptionJsonObjectForErrorLog() {
        jsonLogFormatter.error(OPERATION, throwable, ARGUMENTS);

        verify(formatterSupplier, times(2)).get();
    }

    @Test
    void shouldAddTypeToExceptionObjectForErrorLog() {
        when(formatterSupplier.get())
            .thenReturn(jsonObject)
            .thenReturn(exceptionObject);

        jsonLogFormatter.error(OPERATION, throwable, ARGUMENTS);

        verify(exceptionObject).put("type", "java.lang.Throwable");
    }

    @Test
    void shouldAddMessagedToExceptionObjectForErrorLog() {
        when(formatterSupplier.get())
            .thenReturn(jsonObject)
            .thenReturn(exceptionObject);
        when(throwable.getMessage()).thenReturn(EXCEPTION_MESSAGE);

        jsonLogFormatter.error(OPERATION, throwable, ARGUMENTS);

        verify(exceptionObject).put("message", EXCEPTION_MESSAGE);
    }

    @Test
    void shouldAddExceptionObjectToFormatterForErrorLog() {
        when(formatterSupplier.get())
            .thenReturn(jsonObject)
            .thenReturn(exceptionObject);

        jsonLogFormatter.error(OPERATION, throwable, ARGUMENTS);

        verify(jsonObject).put("exception", exceptionObject);
    }

    @Test
    void shouldCreateExceptionJsonObjectForFatalLog() {
        jsonLogFormatter.fatal(OPERATION, throwable, ARGUMENTS);

        verify(formatterSupplier, times(2)).get();
    }

    @Test
    void shouldAddTypeToExceptionObjectForFatalLog() {
        when(formatterSupplier.get())
            .thenReturn(jsonObject)
            .thenReturn(exceptionObject);

        jsonLogFormatter.fatal(OPERATION, throwable, ARGUMENTS);

        verify(exceptionObject).put("type", "java.lang.Throwable");
    }

    @Test
    void shouldAddMessagedToExceptionObjectForFatalLog() {
        when(formatterSupplier.get())
            .thenReturn(jsonObject)
            .thenReturn(exceptionObject);
        when(throwable.getMessage()).thenReturn(EXCEPTION_MESSAGE);

        jsonLogFormatter.fatal(OPERATION, throwable, ARGUMENTS);

        verify(exceptionObject).put("message", EXCEPTION_MESSAGE);
    }

    @Test
    void shouldAddExceptionObjectToFormatterForFatalLog() {
        when(formatterSupplier.get())
            .thenReturn(jsonObject)
            .thenReturn(exceptionObject);

        jsonLogFormatter.fatal(OPERATION, throwable, ARGUMENTS);

        verify(jsonObject).put("exception", exceptionObject);
    }

    @Test
    void shouldFormatAsWarningLevel() {
        jsonLogFormatter.warning(OPERATION, ARGUMENTS);

        verify(jsonObject).put("level", "WARNING");
    }

    @Test
    void shouldFormatAsErrorLevel() {
        jsonLogFormatter.error(OPERATION, throwable, ARGUMENTS);

        verify(jsonObject).put("level", "ERROR");
    }

    @Test
    void shouldFormatAsFatalLevel() {
        jsonLogFormatter.fatal(OPERATION, throwable, ARGUMENTS);

        verify(jsonObject).put("level", "FATAL");
    }

    @Test
    void shouldFormatAsTraceLevel() {
        jsonLogFormatter.trace(OPERATION, ARGUMENTS);

        verify(jsonObject).put("level", "TRACE");
    }
}