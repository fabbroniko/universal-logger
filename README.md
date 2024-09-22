# TODO

## Default JsonLogFormatter

```json
{
  "level": "TRACE",
  "timestamp": "ISO-8601 timestamp",
  "identifier": "doing_something",
  "arguments": [
    "arg1",
    "arg2"
  ]
}
```

```json
{
  "level": "ERROR",
  "...": "...",
  "exceptionType": "java.lang.RuntimeException",
  "message": "exception message"
}
```

```json
{
  "level": "ERROR",
  "...": "...",
  "exceptionType": "java.lang.RuntimeException",
  "message": "exception message"
}
```

{"level": "TRACE", "timestamp": "ISO-8601 timestamp", "operation": "doing_something","arguments": ["arg1", "arg2"]}