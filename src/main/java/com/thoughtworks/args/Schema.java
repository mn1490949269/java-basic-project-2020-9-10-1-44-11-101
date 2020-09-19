package com.thoughtworks.args;

import com.thoughtworks.args.exceptions.SyntaxException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Schema {

    private final Map<String, String> schemaMap;
    private final Map<String, Object> defaultValueMap;
    private final static String INT_FLAG_TYPE = "int";
    private final static String STRING_FLAF_TYPE = "string";
    private final static String BOOLEAN_FLAG_TYPE = "boolean";

    public Schema(String schemaString) {
        this.defaultValueMap = initialDefaultMap();
        this.schemaMap = formatSchemaStr(schemaString);
    }

    private Map<String, String> formatSchemaStr(String schemaString) {
        return Arrays.asList(schemaString.split(" ")).stream()
                .collect(Collectors.toMap(pair -> pair.split(":")[0], pair -> pair.split(":")[1]));
    }

    private Map<String, Object> initialDefaultMap() {
        Map<String, Object> defaultValueMap = new HashMap<>();
        defaultValueMap = new HashMap<>();
        defaultValueMap.put(INT_FLAG_TYPE, 0);
        defaultValueMap.put(STRING_FLAF_TYPE, "");
        defaultValueMap.put(BOOLEAN_FLAG_TYPE, false);
        return defaultValueMap;
    }

    public Object convert(Arg arg) throws SyntaxException {

        if (!schemaMap.containsKey(arg.getFlag())) {
            throw new SyntaxException("flag is not exist");
        }

        Object value = null;
        if (arg.getValue().isEmpty()) {
            value = this.defaultValueMap.get(this.schemaMap.get(arg.getFlag()));
        } else {
            String flagType = this.schemaMap.get(arg.getFlag());
            if (flagType.equals(INT_FLAG_TYPE)) {
                value = Integer.parseInt(arg.getValue());
            } else if (flagType.equals(STRING_FLAF_TYPE)) {
                value = arg.getValue();
            } else if (flagType.equals(BOOLEAN_FLAG_TYPE)) {
                value = Boolean.parseBoolean(arg.getValue());
            }
        }

        return value;
    }
}
