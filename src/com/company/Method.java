package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;


public class Method {
    private String titleOfMethod;
    private String accessModifierOfMethod;
    private String returnType;
    private String modifier;

    private ArrayList<Parameter> parameters = new ArrayList<>();

    public Method(String accessModifierOfMethod, String modifier, String returnType, String titleOfMethod) throws InputMismatchException {
        if (Check.checkAccessModifier(accessModifierOfMethod)) {
            this.accessModifierOfMethod = accessModifierOfMethod;
        } else throw new InputMismatchException("you entered the wrong access modifier of method");

        if (Check.checkModifier(modifier)) {
            this.modifier = modifier;
        } else throw new InputMismatchException("wrong modifier of method");

        if (Check.checkReturnType(returnType)) {
            this.returnType = returnType;
        } else throw new InputMismatchException("you entered the wrong type of returning value");


        if (Check.checkTitle(titleOfMethod)) {
            this.titleOfMethod = titleOfMethod;
        } else throw new InputMismatchException("name the method correctly");
    }


    public void setAccessModifier(String accessModifierOfMethod) throws InputMismatchException {
        if (Check.checkAccessModifier(accessModifierOfMethod)) {
            this.accessModifierOfMethod = accessModifierOfMethod;
        } else throw new InputMismatchException("You entered the wrong access modifier of method");
    }

    public void setModifier(String modifier) throws InputMismatchException {
        if (Check.checkModifier(modifier)) {
            this.modifier = modifier;
        } else throw new InputMismatchException("You entered the wrong modifier of method");
    }

    public void setReturnType(String returnType) throws InputMismatchException {
        if (Check.checkReturnType(returnType)) {
            this.returnType = returnType;
        } else throw new InputMismatchException("You entered the wrong return type");
    }


    public void setTitle(String titleOfMethod) throws InputMismatchException {
        if (Check.checkTitle(titleOfMethod)) {
            this.titleOfMethod = titleOfMethod;
        } else throw new InputMismatchException("Name the method correctly");
    }

    public void addParamsToMethod(Parameter p) throws InputMismatchException {
        if (getParameters().contains(p.getParameter())) throw new InputMismatchException();
        parameters.add(p);
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

    public ArrayList<Parameter> getParametersToArrayList() {
        return parameters;
    }

    public String getAccessModifierOfMethod() {
        return accessModifierOfMethod;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getTitleOfMethod() {
        return titleOfMethod;
    }

    public String getModifier() {
        return modifier;
    }


    public String toString() {
        StringBuilder values = new StringBuilder();
        for (int i = 0; i < parameters.size(); i++) {
            Parameter p = parameters.get(i);
            if (i == parameters.size() - 1) {
                values.append(p.getType()).append(" ").append(p.getParameter());
                break;
            }
            values.append(p.getType()).append(" ").append(p.getParameter()).append(", ");
        }

        return "\t" + getAccessModifierOfMethod() + " " + getModifier() + " " + getReturnType() + " " + getTitleOfMethod() + "("
                + values + ")" + " {\n\t}\n";
    }
}
