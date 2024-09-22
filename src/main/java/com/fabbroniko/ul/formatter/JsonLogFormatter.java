package com.fabbroniko.ul.formatter;

import com.fabbroniko.ul.level.LogLevel;
import com.fabbroniko.ul.supplier.InstantSupplier;
import com.fabbroniko.ul.supplier.JsonArraySupplier;
import com.fabbroniko.ul.supplier.JsonObjectSupplier;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.Arrays;
import java.util.function.Supplier;

public class JsonLogFormatter implements LogFormatter {

    private final Supplier<Instant> instantSupplier;
    private final Supplier<JSONObject> formatterSupplier;
    private final Supplier<JSONArray> argumentsSupplier;

    public JsonLogFormatter() {
        this(
            new InstantSupplier(),
            new JsonObjectSupplier(),
            new JsonArraySupplier()
        );
    }

    public JsonLogFormatter(final Supplier<Instant> instantSupplier,
                            final Supplier<JSONObject> formatterSupplier,
                            final Supplier<JSONArray> argumentsSupplier) {

        this.instantSupplier = instantSupplier;
        this.formatterSupplier = formatterSupplier;
        this.argumentsSupplier = argumentsSupplier;
    }

    @Override
    public String info(final String operation, final String... arguments) {
        return createLog(LogLevel.INFO, operation, null, arguments);
    }

    @Override
    public String warning(final String operation, final String... arguments) {
        return createLog(LogLevel.WARNING, operation, null,arguments);
    }

    @Override
    public String error(final String operation, final Throwable throwable, final String... arguments) {
        return createLog(LogLevel.ERROR, operation, throwable,arguments);
    }

    @Override
    public String fatal(final String operation, final Throwable throwable, final String... arguments) {
        return createLog(LogLevel.FATAL, operation, throwable,arguments);
    }

    @Override
    public String trace(final String operation, final String... arguments) {
        return createLog(LogLevel.TRACE, operation, null,arguments);
    }

    private String createLog(final LogLevel logLevel, final String operation, final Throwable throwable, final String... arguments) {
        final JSONObject formatter = formatterSupplier.get();
        formatter.put("timestamp", instantSupplier.get().toString());
        formatter.put("level", logLevel.name());
        formatter.put("operation", operation);

        final JSONArray argumentsJsonArray = argumentsSupplier.get();
        Arrays.stream(arguments).forEach(argumentsJsonArray::put);
        formatter.put("arguments", argumentsJsonArray);

        if (throwable != null) {
            final JSONObject exception = formatterSupplier.get();
            exception.put("type", throwable.getClass().getName());
            exception.put("message", throwable.getMessage());
            formatter.put("exception", exception);
        }

        return formatter.toString();
    }
}
