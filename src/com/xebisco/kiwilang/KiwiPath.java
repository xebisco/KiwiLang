package com.xebisco.kiwilang;

import java.io.File;
import java.io.IOException;

public class KiwiPath {

    private final String path;

    public enum KiwiPathProps {
        ABSOLUTE, RELATIVE
    }

    public KiwiPath(String path, KiwiPathProps pathProps) throws IOException {
        if (pathProps == KiwiPathProps.RELATIVE) {
            File file = new File(path);
            this.path = file.getCanonicalPath();
        } else {
            this.path = path;
        }
    }

    public KiwiPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }
}
