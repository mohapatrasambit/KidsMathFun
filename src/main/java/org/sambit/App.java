package org.sambit;

import org.sambit.kids.math.*;
import org.sambit.kids.math.print.equations.PrintEquations;
import org.sambit.kids.math.print.equations.PrintToConsole;
import org.sambit.kids.math.print.equations.PrintToWordDoc;
import org.sambit.kids.math.print.equations.WordDocPrintFormat;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * App to provide different types of simple equations for little kids!
 *
 */
public class App 
{
    public static void main (String[] args) throws IOException {
        System.out.println( "Welcome to math fun for little kids!\n\n" );
        System.out.println("What do you want your kid to do today?");
        System.out.println("\n1. Additions\n2. RandomStandardNumberTable\n3. Multiplication");
        System.out.print("Enter your choice: ");

        Scanner choiceScanner = new Scanner(System.in);
        Integer choice = choiceScanner.nextInt();

        List<Equation> equations = null;
        EquationsGenerator equation;

        switch(choice) {
            case 1:
            default:
                equation = new Additions();
                equations = equation.generateEquations(40, 20);
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

        PrintEquations printer = new PrintToWordDoc(WordDocPrintFormat.MULTI_LINE);

        printer.print(equations);

//        Utils.generateWordDoc(equations);
        System.in.read();
    }
}
