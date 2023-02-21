package org.sambit;

import jdk.jshell.execution.Util;
import org.sambit.kids.math.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main (String[] args) throws IOException {
        System.out.println( "Welcome to math fun for little kids!\n\n" );
        System.out.println("What do you want your kid to do today?");
        System.out.println("\n1. Additions\n2. RandomStandardNumberTable\n3. Multiplication");
        System.out.print("Enter your choice: ");
//        String choice = System.console().readLine("Enter your choice: ");

        Scanner choiceScanner = new Scanner(System.in);
        Integer choice = choiceScanner.nextInt();
//        choiceScanner.close();

        List<String> equations = null;
        Equations equation;

        switch(choice) {
            case 1:
            default:
                equation = new Additions();
                equations = equation.generateEquations(50, 20);
                break;
            case 2:
                equation = new RandomStandardNumberTable();
                equations = equation.generateEquations(120, 12);
                break;
            case 3:
                equation = new Multiplication();
                equations = equation.generateEquations(30, 12);
                break;
        }

        for (var idx = 0; idx < equations.size(); idx++) {
            System.out.print(equations.get(idx));
        }

        Utils.generateWordDoc(equations);
        System.in.read();
    }
}
