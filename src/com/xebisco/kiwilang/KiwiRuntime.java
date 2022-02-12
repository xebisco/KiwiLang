package com.xebisco.kiwilang;

import java.util.ArrayList;

public class KiwiRuntime {
    private ArrayList<KiwiFunction> publicFunctions = new ArrayList<>(), privateFunctions = new ArrayList<>();

    public ArrayList<KiwiFunction> getPrivateFunctions() {
        return privateFunctions;
    }

    public void setPrivateFunctions(ArrayList<KiwiFunction> privateFunctions) {
        this.privateFunctions = privateFunctions;
    }

    public ArrayList<KiwiFunction> getPublicFunctions() {
        return publicFunctions;
    }

    public void setPublicFunctions(ArrayList<KiwiFunction> publicFunctions) {
        this.publicFunctions = publicFunctions;
    }
}
