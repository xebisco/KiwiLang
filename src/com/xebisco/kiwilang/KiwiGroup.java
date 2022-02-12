package com.xebisco.kiwilang;

import java.util.Arrays;

public class KiwiGroup {

    public String first, type, argument;
    public String[] contents, arguments;

    @Override
    public String toString() {
        return "KiwiGroup{" +
                "first='" + first + '\'' +
                ", argument='" + argument + '\'' +
                ", contents=" + Arrays.toString(contents) +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}
