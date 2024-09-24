package com.fabbroniko.ul.manager;

import com.fabbroniko.ul.Logger;

public interface LogManager {

    Logger getLogger(final Class<?> clazz);
}
