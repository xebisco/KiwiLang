package com.xebisco.kiwilang;

import java.util.ArrayList;
import java.util.Arrays;

public class KiwiInterpreter {

    private String[] primaryModifiers = {"public", "private"};
    private String[] secondaryModifiers = {"final", "abstract", "override", "static", "default"};
    private String[] groupKeywords = {"func"};
    private final KiwiRuntime runtime;

    public KiwiInterpreter(KiwiRuntime runtime) {
        this.runtime = runtime;
    }

    public void interpret(KiwiFile file, boolean inner) {
        if (!inner) {
            KiwiGroup actGroup = null;
            ArrayList<String> groupContents = new ArrayList<>();
            int groupIndex = 1;
            for (KiwiLine line : file.getLines()) {
                String[] words = line.contents.split(" ");
                if (actGroup != null) {
                    String l = line.contents;
                    if (groupIndex == 0 && !l.contains("{")) {
                        new KiwiSyntaxError("'{' expected", line.number, file.getRawLines()[line.number - 1].contents);
                    }
                    if (l.contains("{")) {
                        groupIndex++;
                        if (groupIndex == 1)
                            l = l.replace("{", "");
                    }

                    if (l.contains("}")) {
                        groupIndex--;
                        if (groupIndex == 0) {
                            l = l.replace("}", "");
                            actGroup.contents = groupContents.toArray(new String[0]);
                            if (actGroup.arguments[2].hashCode() == "func".hashCode()) {
                                if (actGroup.arguments[2].equals("func")) {
                                    String[] sa = actGroup.first.split(" ");
                                    String fName = "", fType = "void";
                                    for (int i = 0; i < sa.length; i++) {
                                        String s = sa[i];
                                        if (s.hashCode() == "(".hashCode()) {
                                            if (s.equals("(")) {
                                                fName = sa[i - 1];
                                            }
                                        }
                                    }
                                    for (int i = 0; i < sa.length; i++) {
                                        String s = sa[i];
                                        if (s.hashCode() == ":".hashCode()) {
                                            if (s.equals(":")) {
                                                fType = sa[i + 1];
                                            }
                                        }
                                    }
                                    String[] argumentsTypes;
                                    KiwiFunction function = new KiwiFunction();
                                    function.contents = actGroup.contents;
                                    function.first = fName;
                                    function.line = actGroup.line;
                                    try {
                                        function.type = KiwiPrimitiveType.valueOf("t_" + fType);
                                    } catch (IllegalArgumentException e) {
                                        new KiwiSyntaxError("'" + fType + "' isn't a primitive type", function.line, file.getRawLines()[function.line - 1].contents);
                                    }
                                    boolean isStatic = false;
                                    if (actGroup.arguments[1].hashCode() == "static".hashCode()) {
                                        if (actGroup.arguments[1].equals("static")) {
                                            isStatic = true;
                                            new KiwiSyntaxError("static modifier is not recommended on first class functions", function.line, file.getRawLines()[function.line - 1].contents);
                                        }
                                    }

                                    if (actGroup.arguments[0].hashCode() == "public".hashCode()) {
                                        if (actGroup.arguments[0].equals("public")) {
                                            runtime.getPublicFunctions().add(function);
                                        }
                                    } else if (actGroup.arguments[0].hashCode() == "private".hashCode()) {
                                        if (actGroup.arguments[0].equals("private")) {
                                            if (isStatic) {
                                                new KiwiSyntaxError("static function can't be private", function.line, file.getRawLines()[function.line - 1].contents);
                                            }
                                            runtime.getPrivateFunctions().add(function);
                                        }
                                    }
                                }
                            }
                            actGroup = null;
                        }
                    }
                    if (groupIndex > 0 && !l.equals(""))
                        groupContents.add(l);
                } else {
                    String firstMod = "public";
                    String secMod = "default";
                    int i = 0;
                    int finalI = i;
                    boolean explicitPrimary = Arrays.stream(primaryModifiers).anyMatch(s -> s.hashCode() == words[finalI].hashCode() && s.equals(words[finalI]));

                    if (explicitPrimary) {
                        firstMod = words[i];
                        i++;
                    }

                    int finalI2 = i;
                    boolean explicitSecondary = Arrays.stream(secondaryModifiers).anyMatch(s -> s.hashCode() == words[finalI2].hashCode() && s.equals(words[finalI2]));


                    if (explicitSecondary) {
                        secMod = words[i];
                        i++;
                    }

                    String keyword = words[i];

                    boolean validKeyword = Arrays.stream(groupKeywords).anyMatch(s -> s.hashCode() == keyword.hashCode() && s.equals(keyword));

                    if (!validKeyword)
                        new KiwiSyntaxError(keyword + " isn't a invalid keyword", line.number, file.getRawLines()[line.number - 1].contents);
                    actGroup = new KiwiGroup();
                    actGroup.arguments = new String[]{firstMod, secMod, keyword};
                    actGroup.line = line.number;
                    groupContents.clear();
                    groupIndex = 0;
                    actGroup.first = line.contents;
                }
            }
            if (actGroup != null)
                    new KiwiSyntaxError("'}' expected", actGroup.line + groupContents.size(), file.getRawLines()[actGroup.line + groupContents.size() - 1].contents);
        }

    }

    public String[] getPrimaryModifiers() {
        return primaryModifiers;
    }

    public void setPrimaryModifiers(String[] primaryModifiers) {
        this.primaryModifiers = primaryModifiers;
    }

    public String[] getSecondaryModifiers() {
        return secondaryModifiers;
    }

    public void setSecondaryModifiers(String[] secondaryModifiers) {
        this.secondaryModifiers = secondaryModifiers;
    }

    public String[] getGroupKeywords() {
        return groupKeywords;
    }

    public void setGroupKeywords(String[] groupKeywords) {
        this.groupKeywords = groupKeywords;
    }

    public KiwiRuntime getRuntime() {
        return runtime;
    }
}
