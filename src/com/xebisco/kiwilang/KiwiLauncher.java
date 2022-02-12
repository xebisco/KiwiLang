package com.xebisco.kiwilang;

import java.io.IOException;

public class KiwiLauncher {
    public static void main(String[] args) throws IOException {
        KiwiProgram program = new KiwiProgram();
        KiwiFile file = new KiwiFile(new KiwiPath("C:\\Users\\Xebisco\\OneDrive\\Documentos\\IdeaProjects\\KiwiLang\\kiwi\\Program.kiwi", KiwiPath.KiwiPathProps.ABSOLUTE));
        program.execute(file);
        System.out.println(program.getRuntime().getPublicFunctions());
    }
}
