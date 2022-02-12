package com.xebisco.kiwilang;

public class KiwiProgram {

    private KiwiReader kiwiReader;
    private KiwiInterpreter interpreter;
    private KiwiRuntime runtime;

    public KiwiProgram() {
        kiwiReader = new KiwiReader();
        runtime = new KiwiRuntime();
        interpreter = new KiwiInterpreter(runtime);
    }

    public void execute(KiwiFile file) {
        kiwiReader.read(file);
        kiwiReader.format(file);
        interpreter.interpret(file, false);
    }

    public KiwiReader getKiwiReader() {
        return kiwiReader;
    }

    public void setKiwiReader(KiwiReader kiwiReader) {
        this.kiwiReader = kiwiReader;
    }

    public KiwiInterpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(KiwiInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public KiwiRuntime getRuntime() {
        return runtime;
    }

    public void setRuntime(KiwiRuntime runtime) {
        this.runtime = runtime;
    }
}
