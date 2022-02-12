package com.xebisco.kiwilang;

public class KiwiFile {

    private final KiwiPath kiwiPath;
    private KiwiLine[] rawLines, lines;

    public KiwiFile(KiwiPath kiwiPath) {
        this.kiwiPath = kiwiPath;
    }

    public KiwiPath getKiwiPath() {
        return kiwiPath;
    }

    public KiwiLine[] getLines() {
        return lines;
    }

    public void setLines(KiwiLine[] lines) {
        this.lines = lines;
    }

    public KiwiLine[] getRawLines() {
        return rawLines;
    }

    public void setRawLines(KiwiLine[] rawLines) {
        this.rawLines = rawLines;
    }
}
