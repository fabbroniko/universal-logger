# Universal Logger ![Build Status](https://github.com/fabbroniko/universal-logger/actions/workflows/build.yml/badge.svg?branch=master&event=push)

This Java library provides a flexible logging solution that allows client libraries to integrate with the logging framework of their choice. Instead of forcing clients to rely on a specific logging implementation, this adapter empowers developers to seamlessly plug in any logging framework (e.g., Log4j, SLF4J, java.util.logging) without modifying the core library.

### Key Features:

- **Framework Agnostic**: Supports multiple logging frameworks, giving clients the freedom to choose the best option for their needs.
- **Pluggable Architecture**: Simplifies logging configuration and reduces tight coupling between the library and logging implementations.
- **Flexible Integration**: Clients can easily switch between different logging frameworks, fostering greater adaptability and customization.
- **Lightweight**: Minimal overhead with a focus on enhancing flexibility without compromising performance.
- **Consistent logs**: Encourages consistent logging messages by offering a configurable FormattedLogger that allows users to define a custom log message structure, ensuring uniformity across logs.

## Setup

### Gradle configuration

The package is currently hosted on GitHub Packages, therefore requiring some extra configuration in your `gradle.properties` or `pom.xml`

Firstly define where gradle can find the package.

```groovy
repositories {
    mavenCentral()
    maven {
        name = "UniversalLoggerGitHubPackage"
        url = uri("https://maven.pkg.github.com/fabbroniko/universal-logger")
        credentials {
            username = project.findProperty("github.username") ?: System.getenv("GITHUB_USERNAME")
            password = project.findProperty("github.token.read") ?: System.getenv("GITHUB_READ_TOKEN")
        }
    }
}
```

GitHub doesn't allow unauthenticated pulls, so you'll need to create `gradle.properties` next to your `build.gradle` (if not
already present) with the following content:

```properties
github.username=# Your github username
github.token.read=# An access token with read:packages scope
```

Finally, import the library as usual.

```groovy
implementation 'com.fabbroniko:universal-logger:1.1.1'
```

### Use

Note: Inversion of control is advised but not required.

```java
import com.fabbroniko.ul.Logger;

public class SomeClass {

    private final Logger logger;

    public SomeClass(final Logger logger) {
        this.logger = logger;
    }

    public void doSomething() {
        logger.info("doing_something");
    }
}
```

Which can be used as below:

```java
public static void main(final String... args) {
    final LoggerAdapter loggerAdapter = new JdkLoggerAdapter();
    final LogFormatter logFormatter = new JsonLogFormatter();
    final Logger logger = new FormattedLogger(logFormatter, loggerAdapter);
    new SomeClass(logger).doSomething();
}
```

To note that if you want to switch to a different logging framework you just need to create your own adapter (if not already included)
and pass it to the `Logger`.

## License

The source code for the site is licensed under the MIT license, which you can find in
the LICENSE.md file.