package com.fabbroniko.supplier;

import org.json.JSONArray;

import java.util.function.Supplier;

public class JsonArraySupplier implements Supplier<JSONArray> {

    @Override
    public JSONArray get() {
        return new JSONArray();
    }
}
