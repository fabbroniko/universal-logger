package com.fabbroniko.ul.supplier;

import java.time.Instant;
import java.util.function.Supplier;

public class InstantSupplier implements Supplier<Instant> {

    @Override
    public Instant get() {
        return Instant.now();
    }
}
