package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Realisation {


    public static String createNewClass() {
        Scanner in = new Scanner(System.in);
        Class c = null;
        System.out.println("Enter the access modifier and name of class");
        boolean fl = false;

        do {
            try {
                c = new Class(in.nextLine(), in.nextLine());
                fl = true;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input!");
            }
        } while (!fl);
        fl = false;

        System.out.println("To change class, enter \"change class\"\n" +
                "To create a new field, enter \"field\", \"remove field\" for removing the field, \"change field\" for change field\n" +
                "To create a new constructor, enter \"constructor\", \"remove constructor\" for removing the constructor. \"change constructor\" for change constructor\n" +
                "To create a new method, enter \"method\", \"remove method\" for removing the method, enter \"change method\" for change method\n" +
                "To see your class, enter \"see\"\n" +
                "To help, enter \"help\"\n" +
                "To end the program, enter \"q\"");
        for (; ; ) {
            switch (in.nextLine()) {
                case ("change class"):
                    c.changeClass();
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("method"):
                    System.out.println("Enter the access modifier, modifier, return type, title and quantity of parameters of the method");
                    Method m = null;
                    int quantityOfParams = 0;
                    do {
                        try {
                            m = new Method(in.nextLine(), in.nextLine(), in.nextLine(), in.nextLine());
                            quantityOfParams = in.nextInt();
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong input! Try again");
                        }
                    } while (!fl);
                    fl = false;
                    c.createNewMethod(m, quantityOfParams);
                    System.out.println("Done! To get help, enter \"help\"");
                    break;

                case ("change method"):
                    c.changeMethod();
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("field"):
                    System.out.println("Enter the field's access modifier, type and name");
                    Field f = null;
                    do {
                        try {
                            f = new Field(in.nextLine(), in.nextLine(), in.nextLine());
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong input! Try again");
                        }
                    } while (!fl);
                    fl = false;

                    c.createNewField(f);
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("change field"):
                    c.changeField();
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("constructor"):
                    System.out.println("Enter quantity of parameters of constructor");
                    int quantityOfParameters = 0;
                    do {
                        try {
                            quantityOfParameters = in.nextInt();
                            fl = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong input!");
                        }
                    } while (!fl);
                    fl = false;
                    c.createConstructor(quantityOfParameters);
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("change constructor"):
                    c.changeConstructor();
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("remove method"):
                    c.removeMethod();
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("remove field"):
                    c.removeField();
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("remove constructor"):
                    c.removeConstructor();
                    System.out.println("Done! To get help, enter \"help\"");
                    break;
                case ("see"):
                    System.out.println(c.toString());
                    break;
                case ("help"):
                    System.out.println("To change class, enter \"change class\"\n" +
                            "To create a new field, enter \"field\", \"remove field\" for removing the field, \"change field\" for change field\n" +
                            "To create a new constructor, enter \"constructor\", \"remove constructor\" for removing the constructor. \"change constructor\" for change constructor\n" +
                            "To create a new method, enter \"method\", \"remove method\" for removing the method, enter \"change method\" for change method\n" +
                            "To see your class, enter \"see\"\n" +
                            "To help, enter \"help\"\n" +
                            "To end the program, enter \"q\"");
                    break;
                case ("q"):
                    return c.toString();
            }
        }
    }
}
