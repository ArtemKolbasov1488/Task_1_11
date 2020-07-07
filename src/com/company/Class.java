package com.company;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Class {
    private String accessModifierOfClass;
    private String titleOfClass;
    private ArrayList<Method> methods = new ArrayList<>();
    private ArrayList<Field> fields = new ArrayList<>();
    private ArrayList<Constructor> constructors = new ArrayList<>();
    Scanner in = new Scanner(System.in);


    public Class(String accessModifierOfClass, String titleOfClass) throws InputMismatchException {
        if (Check.checkAccessModifier(accessModifierOfClass)) {
            this.accessModifierOfClass = accessModifierOfClass;
        } else throw new InputMismatchException("you entered the wrong access modifier of class");

        if (Check.checkTitle(titleOfClass)) {
            this.titleOfClass = titleOfClass;
        } else throw new InputMismatchException("name the class correctly");
    }

    private boolean checkConstructors(Constructor cr) {
        ArrayList<Parameter> parameters = cr.getParametersToArrayList();
        try {
            String newConstrTypes = "";
            for (Parameter check : parameters) {
                newConstrTypes += check.getType() + " ";
            }
            String oldConstrTypes = "";
            for (Constructor crCheck : constructors) {
                for (Parameter check : crCheck.getParametersToArrayList()) {
                    if (crCheck.equals(cr)) break;
                    oldConstrTypes += check.getType() + " ";
                }
                if (newConstrTypes.equals(oldConstrTypes)) throw new CloneNotSupportedException();
                oldConstrTypes = "";
            }
        } catch (CloneNotSupportedException e) {
            System.out.println("You already have this constructor. This constructor was removed");
            return false;
        }
        return true;
    }

    private boolean checkMethods(Method m) {
        ArrayList<Parameter> parameters = m.getParametersToArrayList();
        try {
            String newMethodTypes = "";
            for (Parameter check : parameters) {
                newMethodTypes += check.getType() + " ";
            }

            String oldMethodTypes = "";
            for (Method mCheck : methods) {
                for (Parameter check : mCheck.getParametersToArrayList()) {
                    if (mCheck.equals(m)) break;
                    oldMethodTypes += check.getType() + " ";
                }
                if (newMethodTypes.equals(oldMethodTypes) && m.getTitleOfMethod().equals(mCheck.getTitleOfMethod()))
                    throw new CloneNotSupportedException();
                oldMethodTypes = "";
            }
        } catch (CloneNotSupportedException e) {
            System.out.println("You already have this method. This method was removed");
            return false;
        }
        return true;
    }

    private boolean checkFields(Field f) {
        for (Field check : fields) {
            try {
                if (check.getName().equals(f.getName()) && !check.equals(f))
                    throw new CloneNotSupportedException("fields cannot have the same name");
            } catch (CloneNotSupportedException e) {
                System.out.println("Fields cannot have the same name. Your field wasn't added");
                return false;
            }
        }
        return true;
    }

    public void setAccessModifierOfClass(String accessModifierOfClass) throws InputMismatchException {
        if (Check.checkAccessModifier(accessModifierOfClass))
            this.accessModifierOfClass = accessModifierOfClass;
        else throw new InputMismatchException();
    }

    public void setTitleOfClass(String titleOfClass) throws InputMismatchException {
        if (Check.checkTitle(titleOfClass))
            this.titleOfClass = titleOfClass;
        else throw new InputMismatchException();
    }

    public String getAccessModifierOfClass() {
        return accessModifierOfClass;
    }

    public String getTitleOfClass() {
        return titleOfClass;
    }

    public void changeClass() {
        System.out.println("What do you want change? /access modifier /title");
        boolean fl = false;
        do {
            String decision = in.nextLine();
            switch (decision) {
                case "access modifier":
                    System.out.println("Enter the new access modifier of class");
                    do {
                        try {
                            setAccessModifierOfClass(in.nextLine());
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("You entered the wrong title of class");
                        }
                    } while (!fl);
                    break;
                case "title":
                    String newTitle = "";
                    String oldTitleOfClass = titleOfClass;
                    System.out.println("Enter the new title of class");
                    do {
                        try {
                            newTitle = in.nextLine();
                            setTitleOfClass(newTitle);
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("You entered the wrong title of class");
                        }
                    } while (!fl);
                    if (!getTitleOfClass().equals(oldTitleOfClass)) {
                        for (Constructor cr : constructors) {
                            cr.setTitle(newTitle);
                        }
                    }
                    break;
                default:
                    System.out.println("You entered the wrong command");
            }
        } while (!fl);
    }


    public void createConstructor(int quantityOfParameters) {
        System.out.println("Enter the type and name of parameters");

        Constructor cr = new Constructor(getTitleOfClass());

        for (int i = 0; i < quantityOfParameters; i++) {
            try {
                Parameter p = new Parameter(in.nextLine(), in.nextLine());
                cr.addParamsToConstructor(p);
            } catch (InputMismatchException e) {
                System.out.println("Name the parameter correctly. Your constructor may already contain this parameter");
                i--;
            }
        }
        constructors.add(cr);
        if (!checkConstructors(cr)) constructors.remove(cr);
    }

    public void changeConstructor() {
        if (constructors.size() == 0) {
            System.out.println("Your class haven't got any constructors");
            return;
        }
        for (int i = 0; i < constructors.size(); i++) {
            System.out.print("number " + i + ":\n" + constructors.get(i).toString());
        }

        System.out.println("Parameters of which constructor do you want to change?");
        int number = -1;
        do {
            try {
                number = Integer.parseInt(in.nextLine());
                if (!(number >= 0 && number < constructors.size())) {
                    System.out.println("Enter the number correctly");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the number correctly");
            }
        } while (!(number >= 0 && number < constructors.size()));

        Constructor cr = constructors.get(number);


        System.out.println("What do you want to change?/parameters" +
                "/remove parameters/add parameters");
        String decision = in.nextLine();
        boolean fl = false;
        int quantityOfParameters = -1;
        ArrayList<Parameter> parameters = cr.getParametersToArrayList();

        do {
            switch (decision) {
                case "parameters":
                    for (int i = 0; i < parameters.size(); i++) {
                        System.out.println("number " + i + ":\n" + parameters.get(i));
                    }

                    System.out.println("How many parameters do you want to change?");
                    do {
                        try {
                            quantityOfParameters = Integer.parseInt(in.nextLine());
                            if (!(quantityOfParameters >= 0 && quantityOfParameters <= parameters.size())) {
                                System.out.println("Enter the quantity of parameters correctly");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Enter the quantity of parameters correctly");
                        }
                    } while (!(quantityOfParameters >= 0 && quantityOfParameters <= parameters.size()));

                    if (quantityOfParameters == 0) return;

                    boolean fl1 = false;
                    for (int i = 0; i < quantityOfParameters; i++) {
                        System.out.println("Enter type and name of the changing parameter");
                        try {
                            Parameter p = new Parameter(in.nextLine(), in.nextLine());
                            for (Parameter check : parameters) {
                                if (check.toString().equals(p.toString())) {
                                    fl1 = true;
                                    parameters.remove(check);
                                    System.out.println("Enter new type and name of the changing parameter");
                                    p = new Parameter(in.nextLine(), in.nextLine());
                                    try {
                                        for (Parameter check1 : parameters) {
                                            if (check1.getParameter().equals(p.getParameter())) {
                                                throw new CloneNotSupportedException();
                                            }
                                        }
                                        parameters.add(p);
                                    } catch (CloneNotSupportedException e) {
                                        System.out.println("The constructor already contains this parameter. Try again");
                                        parameters.add(check);
                                        i--;
                                    }
                                    break;
                                }
                            }
                            if (!fl1) {
                                i--;
                            }
                        } catch (InputMismatchException e) {
                            i--;
                        }
                    }
                    fl = true;
                    break;
                case "add parameters":
                    System.out.println("How many parameters do you want to add?");
                    do {
                        try {
                            quantityOfParameters = Integer.parseInt(in.nextLine());
                            if (quantityOfParameters < 0) {
                                System.out.println("Enter the quantity of parameters correctly");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Enter the quantity of parameters correctly");
                        }
                    } while (quantityOfParameters < 0);

                    if (quantityOfParameters == 0) return;

                    System.out.println("Enter the types and names of parameters");
                    for (int i = 0; i < quantityOfParameters; i++) {
                        Parameter p = null;

                        boolean fl2 = false;
                        do {
                            try {
                                p = new Parameter(in.nextLine(), in.nextLine());
                                fl2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Enter the type and name of parameter correctly");
                            }
                        } while (!fl2);

                        try {
                            for (Parameter check : parameters) {
                                if (check.getParameter().equals(p.getParameter())) {
                                    throw new CloneNotSupportedException();
                                }
                            }
                            parameters.add(p);
                        } catch (CloneNotSupportedException e) {
                            System.out.println("The constructor already contains this parameter. Try again");
                            i--;
                        }
                    }
                    fl = true;
                    break;
                case "remove parameters":
                    for (int i = 0; i < parameters.size(); i++) {
                        System.out.println("number " + i + ":\n" + parameters.get(i));
                    }

                    System.out.println("How many parameters do you want to remove?");
                    do {
                        try {
                            quantityOfParameters = Integer.parseInt(in.nextLine());
                            if (!(quantityOfParameters >= 0 && quantityOfParameters <= parameters.size())) {
                                System.out.println("Enter the quantity of parameters correctly");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Enter the quantity of parameters correctly");
                        }
                    } while (!(quantityOfParameters >= 0 && quantityOfParameters <= parameters.size()));

                    if (quantityOfParameters == 0) return;

                    System.out.println("Enter the numbers of parameters");
                    int numberToDelete = -1;
                    for (int i = 0; i < quantityOfParameters; i++) {
                        do {
                            try {
                                numberToDelete = Integer.parseInt(in.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Enter the number correctly");
                            }
                        } while (!(numberToDelete < parameters.size() && numberToDelete >= 0));

                        parameters.remove(parameters.get(numberToDelete));
                    }

                    fl = true;
                    break;
                default:
                    System.out.println("Enter what do you want to change correctly!");
                    decision = in.nextLine();
                    break;
            }
        } while (!fl);

        if (constructors.size() == 1) return;

        if (!checkConstructors(cr)) constructors.remove(cr);

    }

    public void removeConstructor() {
        if (constructors.size() == 0) {
            System.out.println("Your class haven't got any constructors");
            return;
        }
        for (int i = 0; i < constructors.size(); i++) {
            Constructor c = constructors.get(i); //Тяжелая операция
            System.out.print("number " + i + ":\n" + c.toString());
        }

        System.out.println("Which constructor do you want to remove?");
        int number = -1;
        do {
            try {
                number = Integer.parseInt(in.nextLine());
                if (!(number >= 0 && number < constructors.size())) {
                    System.out.println("Enter the number correctly");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the number correctly");
            }
        } while (!(number >= 0 && number < constructors.size()));


        constructors.remove(constructors.get(number));
    }


    public void createNewMethod(Method m, int quantityOfParams) {//CHECKED
        System.out.println("enter the parameters");
        for (int i = 0; i < quantityOfParams; i++) {
            try {
                Parameter p = new Parameter(in.nextLine(), in.nextLine());
                m.addParamsToMethod(p);
            } catch (InputMismatchException e) {
                System.out.println("Enter the parameter correctly!");
                i--;
            }
        }

        methods.add(m);
        if (!checkMethods(m)) methods.remove(m);
    }


    public void removeMethod() {
        if (methods.size() == 0) {
            System.out.print("Your class haven't got any methods");
            return;
        }
        for (int i = 0; i < methods.size(); i++) {
            Method m = methods.get(i); //Тяжелая операция
            System.out.println("number " + i + ":\n" + m.toString());
        }

        System.out.println("Which method do you want to remove?");
        int number = -1;
        do {
            try {
                number = Integer.parseInt(in.nextLine());
                if (!(number >= 0 && number < methods.size())) {
                    System.out.println("Enter the number correctly");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the number correctly");
            }
        } while (!(number >= 0 && number < methods.size()));

        methods.remove(methods.get(number));
    }


    public void changeMethod() { //CHECKED
        if (methods.size() == 0) {
            System.out.println("Your class haven't got any methods");
            return;
        }
        for (int i = 0; i < methods.size(); i++) {
            System.out.print("number " + i + ":\n" + methods.get(i).toString());
        }

        System.out.println("Parameters of which method do you want to change?");
        int number = -1;
        do {
            try {
                number = Integer.parseInt(in.nextLine());
                if (!(number >= 0 && number < methods.size())) {
                    System.out.println("Enter the number correctly");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the number correctly");
            }
        } while (!(number >= 0 && number < methods.size()));

        Method m = methods.get(number);


        System.out.println("What do you want to change?/access modifier/modifier/return type/title/parameters" +
                "/remove parameters/add parameters");
        String decision = in.nextLine();
        boolean fl = false;
        int quantityOfParameters = -1;
        ArrayList<Parameter> parameters = m.getParametersToArrayList();

        do {
            switch (decision) {
                case "access modifier":
                    System.out.println("Enter the new access modifier of method");
                    do {
                        try {
                            m.setAccessModifier(in.nextLine());
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("You entered the wrong title of method");
                        }
                    } while (!fl);
                    break;
                case "modifier":
                    System.out.println("Enter new modifier");
                    do {
                        try {
                            m.setModifier(in.nextLine());
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("You entered the wrong modifier of method");
                        }
                    } while (!fl);
                    break;
                case "return type":
                    System.out.println("Enter new return type");
                    do {
                        try {
                            m.setReturnType(in.nextLine());
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("You entered the wrong return type");
                        }
                    } while (!fl);
                    break;
                case "title":
                    System.out.println("Enter the new title of method");
                    do {
                        try {
                            m.setTitle(in.nextLine());
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("You entered the wrong title of method");
                        }
                    } while (!fl);
                    break;
                case "parameters":
                    for (int i = 0; i < parameters.size(); i++) {
                        System.out.println("number " + i + ":\n" + parameters.get(i));
                    }

                    System.out.println("How many parameters do you want to change?");
                    do {
                        try {
                            quantityOfParameters = Integer.parseInt(in.nextLine());
                            if (!(quantityOfParameters >= 0 && quantityOfParameters <= parameters.size())) {
                                System.out.println("Enter the quantity of parameters correctly");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Enter the quantity of parameters correctly");
                        }
                    } while (!(quantityOfParameters >= 0 && quantityOfParameters <= parameters.size()));

                    if (quantityOfParameters == 0) return;

                    boolean fl1 = false;
                    for (int i = 0; i < quantityOfParameters; i++) {
                        System.out.println("Enter type and name of the changing parameter");
                        try {
                            Parameter p = new Parameter(in.nextLine(), in.nextLine());
                            for (Parameter check : parameters) {
                                if (check.getParameter().equals(p.getParameter())) {
                                    fl1 = true;
                                    parameters.remove(check);
                                    System.out.println("Enter new type and name of the changing parameter");
                                    p = new Parameter(in.nextLine(), in.nextLine());
                                    try {
                                        for (Parameter check1 : parameters) {
                                            if (check1.getParameter().equals(p.getParameter())) {
                                                throw new CloneNotSupportedException();
                                            }
                                        }
                                        parameters.add(p);
                                    } catch (CloneNotSupportedException e) {
                                        System.out.println("The method already contains this parameter. Try again");
                                        parameters.add(check);
                                        i--;
                                    }
                                    break;
                                }
                            }
                            if (!fl1) {
                                i--;
                            }
                        } catch (InputMismatchException e) {
                            i--;
                        }
                    }
                    fl = true;
                    break;
                case "add parameters":
                    System.out.println("How many parameters do you want to add?");
                    do {
                        try {
                            quantityOfParameters = Integer.parseInt(in.nextLine());
                            if (quantityOfParameters < 0) {
                                System.out.println("Enter the quantity of parameters correctly");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Enter the quantity of parameters correctly");
                        }
                    } while (quantityOfParameters < 0);

                    if (quantityOfParameters == 0) return;

                    System.out.println("Enter the types and names of parameters");
                    for (int i = 0; i < quantityOfParameters; i++) {
                        Parameter p = null;


                        boolean fl2 = false;
                        do {
                            try {
                                p = new Parameter(in.nextLine(), in.nextLine());
                                fl2 = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Enter the type and name of parameter correctly");
                            }
                        } while (!fl2);

                        try {
                            for (Parameter check : parameters) {
                                if (check.getParameter().equals(p.getParameter())) {
                                    throw new CloneNotSupportedException();
                                }
                            }
                            parameters.add(p);
                        } catch (CloneNotSupportedException e) {
                            System.out.println("The method already contains this parameter. Try again");
                            i--;
                        }
                    }
                    fl = true;
                    break;
                case "remove parameters":
                    for (int i = 0; i < parameters.size(); i++) {
                        System.out.println("number " + i + ":\n" + parameters.get(i));
                    }

                    System.out.println("How many parameters do you want to remove?");
                    do {
                        try {
                            quantityOfParameters = Integer.parseInt(in.nextLine());
                            if (!(quantityOfParameters >= 0 && quantityOfParameters <= parameters.size())) {
                                System.out.println("Enter the quantity of parameters correctly");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Enter the quantity of parameters correctly");
                        }
                    } while (!(quantityOfParameters >= 0 && quantityOfParameters <= parameters.size()));

                    if (quantityOfParameters == 0) return;

                    System.out.println("Enter the numbers of parameters");
                    int numberToDelete = -1;
                    for (int i = 0; i < quantityOfParameters; i++) {
                        do {
                            try {
                                numberToDelete = Integer.parseInt(in.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Enter the number correctly");
                            }

                        } while (!(numberToDelete < parameters.size() && numberToDelete >= 0));

                        parameters.remove(parameters.get(numberToDelete));
                    }
                    fl = true;
                    break;
                default:
                    System.out.println("Enter what do you want to change correctly!");
                    decision = in.nextLine();
                    break;
            }
        } while (!fl);

        if (methods.size() == 1) return;

        if (!checkMethods(m)) methods.remove(m);

    }


    public void createNewField(Field f) { //CHECKED
        fields.add(f);
        if (!checkFields(f)) fields.remove(f);
    }

    public void removeField() { //CHECKED
        if (fields.size() == 0) {
            System.out.println("Your class haven't got any fields");
            return;
        }

        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i); //Тяжелая операция
            System.out.println("number " + i + ":\n" + f.toString());
        }

        System.out.println("Which field do you want to remove?");
        int number = -1;
        do {
            try {
                number = Integer.parseInt(in.nextLine());
                if (!(number >= 0 && number < fields.size())) {
                    System.out.println("Enter the number correctly");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the number correctly");
            }
        } while (!(number >= 0 && number < fields.size()));

        fields.remove(fields.get(number));
    }


    public void changeField() { //CHECKED
        if (fields.size() == 0) {
            System.out.println("Your class haven't got any fields");
            return;
        }

        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i); //Тяжелая операция
            System.out.print("number " + i + ":\n" + f.toString());
        }

        System.out.println("Which field do you want to change?");
        int number = -1;
        do {
            try {
                number = Integer.parseInt(in.nextLine());
                if (!(number >= 0 && number < fields.size())) {
                    System.out.println("Enter the number correctly");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the number correctly");
            }
        } while (!(number >= 0 && number < fields.size()));

        Field f = fields.get(number);

        System.out.println("What do you want to change?/access modifier/type/title");
        String decision;
        boolean fl = false;
        do {
            decision = in.nextLine();
            switch (decision) {
                case "access modifier":
                    System.out.println("Enter the access modifier");
                    String accessModifier = in.nextLine();
                    try {
                        f.setAccessModifier(accessModifier);
                    } catch (InputMismatchException e) {
                        System.out.println("You entered the wrong access modifier of field. Enter it correctly");
                        do {
                            accessModifier = in.nextLine();//
                        } while (!Check.checkAccessModifier(accessModifier));
                        f.setAccessModifier(accessModifier);
                    }
                    fl = true;
                    break;
                case "type":
                    System.out.println("Enter new type of the field");
                    String type = in.nextLine();
                    try {
                        f.setType(type);
                    } catch (InputMismatchException e) {
                        System.out.println("You entered the wrong type");
                        do {
                            type = in.nextLine();
                        } while (!Check.checkType(type));
                        f.setType(type);
                    }
                    fl = true;
                    break;
                case "title":
                    System.out.println("Enter the new field's title");
                    String title = in.nextLine();
                    try {
                        f.setTitle(title);
                    } catch (InputMismatchException e) {
                        do {
                            System.out.println("Name the field correctly!");
                            title = in.nextLine(); //inputMismatchException
                        } while (!Check.checkTitle(title));
                        f.setTitle(title);
                    }
                    fl = true;
                    break;
                default:
                    System.out.println("Enter what do you want to change correctly!");
                    break;
            }
        } while (!fl);

        if (fields.size() == 1) return;

        if (!checkFields(f)) fields.remove(f);
    }


    public String toString() {

        StringBuilder result = new StringBuilder(getAccessModifierOfClass() + " " + getTitleOfClass() + " {\n");

        for (Field f : fields) {
            result.append(f.toString());
        }

        for (Constructor c : constructors) {
            result.append(c.toString());
        }

        for (Method m : methods) {
            result.append(m.toString());
        }

        result.append("}");
        return result.toString();
    }
}