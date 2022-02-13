package com.xebisco.kiwilang;

import java.util.Arrays;

public class KiwiGroup {

    public String first, argument;
    public KiwiPrimitiveType type;
    public int line;
    public String[] contents, arguments;

    @Override
    public String toString() {
        return "KiwiGroup{" +
                "first='" + first + '\'' +
                ", argument='" + argument + '\'' +
                ", type=" + type +
                ", line=" + line +
                ", contents=" + Arrays.toString(contents) +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}
