package com.fabbroniko.ul.supplier;

import org.json.JSONObject;

import java.util.function.Supplier;

public class JsonObjectSupplier implements Supplier<JSONObject> {

    @Override
    public JSONObject get() {
        return new JSONObject();
    }
}
