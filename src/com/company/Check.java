package com.company;

import java.util.InputMismatchException;

public class Check {
    public static boolean checkTitle(String newTitle) {
        if (checkReturnType(newTitle) || checkModifier(newTitle) || checkAccessModifier(newTitle)) throw new InputMismatchException();
        return newTitle.matches("([$_a-zA-Z][$_a-zA-Z0-9]*)");
    }

    public static boolean checkAccessModifier(String newAccessModifier) {
        return newAccessModifier.equals("public") || newAccessModifier.equals("private")
                || newAccessModifier.equals("protected") || newAccessModifier.equals("");
    }

    public static boolean checkModifier(String newModifier) {
        return newModifier.equals("static") || newModifier.equals("");
    }

    public static boolean checkReturnType(String type) {
        return type.equals("int") || type.equals("double") || type.equals("String") || type.equals("char")
                || type.equals("int[]") || type.equals("double[]") || type.equals("String[]") || type.equals("char[]") || type.equals("float[]")
                || type.equals("byte") || type.equals("short") || type.equals("long") || type.equals("float") || type.equals("void");
    }

    public static boolean checkType(String type) {
        return type.equals("int") || type.equals("double") || type.equals("String") || type.equals("char")
                || type.equals("int[]") || type.equals("double[]") || type.equals("String[]") || type.equals("char[]") || type.equals("float[]")
                || type.equals("byte") || type.equals("short") || type.equals("long") || type.equals("float");
    }
}