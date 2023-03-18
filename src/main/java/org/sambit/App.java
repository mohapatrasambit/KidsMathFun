package org.sambit;

import org.sambit.kids.math.equations.*;
import org.sambit.kids.math.equations.print.PrintEquations;
import org.sambit.kids.math.equations.print.PrintToWordDoc;
import org.sambit.kids.math.equations.print.WordDocPrintFormat;

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
        System.out.println("\n1. Additions\n2. Subtractions\n3. RandomStandardNumberTable\n4. Multiplication");
        System.out.print("Enter your choice: ");

        Scanner choiceScanner = new Scanner(System.in);
        Integer choice = choiceScanner.nextInt();

        List<Equation> equations = null;
        EquationsGenerator equation;
        PrintEquations printer = null;

        switch(choice) {
            case 1:
            default:
                equation = new Additions();
                equations = equation.generateEquations(72, 15);
                printer = new PrintToWordDoc(WordDocPrintFormat.MULTI_LINE);
                break;
            case 2:
                equation = new Subtractions();
                equations = equation.generateEquations(72, 15);
                printer = new PrintToWordDoc(WordDocPrintFormat.SINGLE_LINE);
                break;
            case 3:
                equation = new RandomStandardNumberTable();
                equations = equation.generateEquations(72, 12);
                printer = new PrintToWordDoc(WordDocPrintFormat.MULTI_LINE);
                break;
            case 4:
                equation = new Multiplication();
                equations = equation.generateEquations(30, 12);
                printer = new PrintToWordDoc(WordDocPrintFormat.MULTI_LINE);
                break;
        }

        printer.print(equations);
    }
}
