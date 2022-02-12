package com.xebisco.kiwilang;

public class KiwiSyntaxError {

    public KiwiSyntaxError(String msg, int line, String lineStr) {
        System.out.println("Syntax Error in line " + line + "(" + lineStr + "): " + msg);
    }

}
