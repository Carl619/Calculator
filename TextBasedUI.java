/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import MathFunctions.*;
import CalculatorLogic.*;

public class TextBasedUI{
    private Calculator calculator1;
    private Scanner in = new Scanner(System.in);

    public TextBasedUI(){
        // Build calculator and register functions
        calculator1 = new FlexCalculator();
        calculator1.addFunction(new SinFunction());
        calculator1.addFunction(new AddFunction());

        displayMenu(calculator1, calculator1.getFunctions());
    }

    private void displayMenu(Calculator calculator, List<MathFunction> functions) {

        while (true) {
            System.out.println();
            System.out.println("=== The Flexible Calculator ===");
            System.out.println("  [M] Make computation");
            System.out.println("  [H] Show history");
            System.out.println("  [U] Undo last");
            System.out.println("  [S] Save history");
            System.out.println("  [L] Load history");
            System.out.println("  [C] Clear history");
            System.out.println("  [Q] Exit");
            System.out.print("Choose an option: ");

            String choice = in.nextLine().trim().toUpperCase();
            switch (choice) {
                case "M":
                    computeMenu(calculator1); break;
                case "H":
                    calculator1.printHistory(); break;
                case "U":
                    calculator1.undoLast(); break;
                case "S":
                    calculator1.saveHistory(); break;
                case "L":
                    calculator1.loadHistory(); break;
                case "C":
                    calculator1.clearHistory(); break;
                case "Q":
                    System.out.println("Exiting..!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void computeMenu(Calculator calculator) {           

        while (true) {
            List<MathFunction> functions = calculator.getFunctions();
            System.out.println();
            System.out.println("--- Make computation ---");
            for (int i = 0; i < functions.size(); i++) {
                System.out.printf("  [%d] %s%n", i + 1, functions.get(i).getName());
            }
            System.out.println("  [0] Back");
            System.out.print("Choose a function: ");

            int id = in.nextInt();

            if (id == 0) return;

            MathFunction fn = functions.get(id - 1);
            if (fn instanceof SingleArgMathFunction) {
                System.out.print("Enter x: ");
                double a = in.nextDouble();
                double res = calculator.doCalculation(fn.getName(), a);
                System.out.printf("%s(%s) = %s%n", fn.getName(), a, res);

            } else if (fn instanceof DoubleArgMathFunction) {
                System.out.print("Enter x: ");
                double a = in.nextDouble();
                System.out.print("Enter y: ");
                double b = in.nextDouble();
                double res = calculator.doCalculation(fn.getName(), a, b);
                System.out.printf("%s(%s,%s) = %s%n", fn.getName(), a, b, res);

            } else {
                System.out.println("Unsupported function type.");
            }
        }
    }

    public static void main(String[] args) {
        TextBasedUI textUI = new TextBasedUI();
    }

}
