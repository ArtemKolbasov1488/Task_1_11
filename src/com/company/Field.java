package com.company;


import java.util.InputMismatchException;

public class Field {
    private String type;
    private String name;
    private String accessModifierOfVariable;

    public Field(String accessModifierOfVariable, String type, String name) throws InputMismatchException {
        if (Check.checkAccessModifier(accessModifierOfVariable)) {
            this.accessModifierOfVariable = accessModifierOfVariable;
        } else throw new InputMismatchException("you entered the wrong access modifier of field");

        if (Check.checkType(type)) {
            this.type = type;
        } else throw new InputMismatchException("wrong type of variable");

        if (Check.checkTitle(name)) {
            this.name = name;
        } else throw new InputMismatchException("wrong name of variable");
    }

    public void setAccessModifier(String accessModifierOfVariable) throws InputMismatchException {
        if (Check.checkAccessModifier(accessModifierOfVariable)) {
            this.accessModifierOfVariable = accessModifierOfVariable;
        } else throw new InputMismatchException("You entered the wrong access modifier of variable");
    }

    public void setType(String type) throws InputMismatchException {
        if (Check.checkType(type)) {
            this.type = type;
        } else throw new InputMismatchException("You entered the wrong type");
    }

    public void setTitle(String name) throws InputMismatchException {
        if (Check.checkTitle(name)) {
            this.name = name;
        } else throw new InputMismatchException("Name the field correctly");
    }

    public String getAccessModifierOfVariable() {
        return accessModifierOfVariable;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "\t" + getAccessModifierOfVariable() +  " " + getType() + " " + getName() + ";\n";
    }
}