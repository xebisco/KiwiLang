package com.xebisco.kiwilang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KiwiReader {

    public void read(KiwiFile file) {
        ArrayList<KiwiLine> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getKiwiPath().getPath()))) {
            String line;
            int lineIndex = 0;
            while ((line = reader.readLine()) != null) {
                lineIndex++;
                KiwiLine kiwiLine = new KiwiLine();
                kiwiLine.contents = line;
                kiwiLine.number = lineIndex;
                lines.add(kiwiLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.setRawLines(lines.toArray(new KiwiLine[0]));
    }

    public void format(KiwiFile file) {
        ArrayList<KiwiLine> lines = new ArrayList<>();
        for (KiwiLine line : file.getRawLines()) {
            String text = line.contents;
            StringBuilder formatted = new StringBuilder();
            boolean lastIsSpace = false, addSpace, breakLines, inString = false, comment = false;
            for (char c : text.toCharArray()) {
                addSpace = false;
                breakLines = false;
                String s = "";
                if (c == '\"') {
                    if (!comment)
                        inString = !inString;
                }
                if (inString) {
                    if (c == ' ') {
                        c = '\20';
                        s = "'s'";
                    } else if (c == ',') {
                        c = '\20';
                        s = "'v'";
                    } else if (c == '#') {
                        c = '\20';
                        s = "'h'";
                    } else if (c == ';') {
                        c = '\20';
                        s = "'p'";
                    }
                } else {
                    if (c == '#') {
                        comment = true;
                    }
                    if (c == ' ') {
                        if (lastIsSpace) {
                            c = '\20';
                        }
                        lastIsSpace = true;
                    }
                    if (c == ',' || c == '(' || c == ')' || c == ':') {
                        addSpace = true;
                    }

                    if (c == '{' || c == '}') {
                        breakLines = true;
                    }
                }
                if (!comment) {
                    if (breakLines) {
                        formatted.append(";");
                    }

                    if (addSpace) {
                        if (lastIsSpace)
                            addSpace = false;
                        if (addSpace)
                            formatted.append(" ");
                    }
                    if (c != '\20')
                        formatted.append(c);
                    formatted.append(s);
                    if (c != ' ' && c != '\20') {
                        lastIsSpace = false;
                    }
                    if (addSpace) {
                        lastIsSpace = true;
                        formatted.append(" ");
                    }
                    if (breakLines) {
                        formatted.append(";");
                    }
                }

            }
            String[] lns = formatted.toString().split(";");
            for (String s : lns) {
                while (s.startsWith(" ")) {
                    s = s.substring(1);
                }
                while (s.endsWith(" ")) {
                    s = s.substring(0, s.length() - 1);
                }
                KiwiLine kiwiLine = new KiwiLine();
                kiwiLine.contents = s;
                kiwiLine.number = line.number;
                if (!kiwiLine.contents.equals(""))
                    lines.add(kiwiLine);
            }
        }
        file.setLines(lines.toArray(new KiwiLine[0]));
    }

}
