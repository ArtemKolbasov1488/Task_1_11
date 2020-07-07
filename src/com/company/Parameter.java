package com.company;


import java.util.InputMismatchException;

public class Parameter {
    private String type;
    private String parameter;

    public Parameter(String type, String parameter) throws InputMismatchException {
        if (Check.checkType(type)) {
            this.type = type;
        } else throw new InputMismatchException("wrong type of parameter");

        if (Check.checkTitle(parameter)) {
            this.parameter = parameter;
        } else throw new InputMismatchException("wrong name of parameter");

    }


    public String getType() {
        return type;
    }

    public String getParameter() {
        return parameter;
    }

    public String toString() {
        return getType() + " " + getParameter();
    }
}
