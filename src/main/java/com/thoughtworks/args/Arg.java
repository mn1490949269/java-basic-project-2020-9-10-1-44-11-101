package com.thoughtworks.args;

public class Arg {
    private String value;
    private String flag;

    public Arg(String flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getFlag() {
        return flag;
    }
}
