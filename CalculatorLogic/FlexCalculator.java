package CalculatorLogic;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.List;
import MathFunctions.*;

public class FlexCalculator implements Calculator{

    private List<MathFunction> functions;
    private double argument;
    private HistoryCalculator history;
    
    public FlexCalculator() {
        functions = new ArrayList<MathFunction>();
    }

    public void addFunction(MathFunction function) {
        functions.add(function);
    }

    public double doCalculation(String functionName, double arg) {
        double result = 0.0;
        boolean isFunctionFound = false;
        for (MathFunction function : functions) {
            if (functionName.equalsIgnoreCase(function.getName())) {
                result = ((SingleArgMathFunction)function).calculate(arg);
                isFunctionFound = true;
                history.registerOperation(function, arg,result);
            }
        }
        if(!isFunctionFound)
            System.out.println("No such function found!");

        return result;
    }
    
    public boolean checkNumberArgsFunctionNeeds(String functionName)
    {
        for (MathFunction function : functions) {
            if (functionName.equalsIgnoreCase(function.getName())) {
                return true;
            }
        }
        return false;
    }

    public double doCalculation(String functionName, double arg1, double arg2) {
        double result = 0.0;
        boolean isFunctionFound = false;
        for (MathFunction function : functions) {
            if (functionName.equalsIgnoreCase(function.getName())) {
                result = ((DoubleArgMathFunction)function).calculate(arg1, arg2);
                isFunctionFound = true;
                history.registerOperation(function, arg1,arg2,result);
            }
        }
        if(!isFunctionFound)
            System.out.println("No such function found!");

        return result;
    }

    public void listMathFunction() {
        System.out.println("Available Functions:");
        for (MathFunction function : functions)
            System.out.println(function.getName());
    }

    public List<MathFunction> getFunctions(){
        return functions;
    }

    public void printHistory() {
        history.printHistory();
    }

    public void undoLast() {
        history.undoLast();
    }

    public void clearHistory() {
        history.clearHistory();
    }

    public void saveHistory(){
        history.saveHistory();
    }

    public void loadHistory() {
        history.loadHistory();
    }
}
