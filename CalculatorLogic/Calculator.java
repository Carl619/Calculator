package CalculatorLogic;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.List; 
import MathFunctions.*;

public interface Calculator {

    public double doCalculation(String functionName, double arg);

    public double doCalculation(String functionName, double arg1, double arg2);

    public void listMathFunction();

    public List<MathFunction> getFunctions();

    public void printHistory();

    public void undoLast();

    public void clearHistory();

    public void saveHistory();

    public void loadHistory();
}
