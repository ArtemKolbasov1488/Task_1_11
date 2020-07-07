package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Constructor { // ? модификатор доступа конструктора
    private String title;
    private ArrayList<Parameter> parameters = new ArrayList<>();


    public Constructor(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        if (Check.checkTitle(title)) this.title = title;
    }

    public void addParamsToConstructor(Parameter p) throws InputMismatchException {
        if (getParameters().contains(p.getParameter())) throw new InputMismatchException();
        parameters.add(p);
    }

    public ArrayList<Parameter> getParametersToArrayList() {
        return parameters;
    }

    public String getParameters() {
        String code = "";
        for (int i = 0; i < parameters.size(); i++) {
            Parameter p = parameters.get(i);
            if (i == parameters.size() - 1) {
                code += p.getType() + " " + p.getParameter();
                break;
            }
            code += p.getType() + " " + p.getParameter() + ", ";
        }

        return code;
    }

    public String toString() {
        String result = "\tpublic " + title + "(";

        for (int i = 0; i < parameters.size() - 1; i++) {
            result += parameters.get(i) + ", ";
        }
        result += parameters.get(parameters.size() - 1) + ") {\n\t}\n";

        return result;
    }

}
