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

    private double argument;
    private HistoryCalculator history;
    private final List<CalculatorObserver> observers = new ArrayList<>();
    
    public FlexCalculator() {
    }

    public double doCalculation(String functionName, double arg) {
        double result = 0.0;
        boolean isFunctionFound = false;
        MathFunction function = MathFunctionFactory.create(functionName);
                result = ((SingleArgMathFunction)function).calculate(arg);
                isFunctionFound = true;
                history.registerOperation(function, arg,result);
                notifyHistoryChanged();
        if(!isFunctionFound)
            System.out.println("No such function found!");

        return result;
    }
    
    public boolean checkNumberArgsFunctionNeeds(String functionName)
    {
        /*for (MathFunction function : functions) {
            if (functionName.equalsIgnoreCase(function.getName())) {
                return true;
            }
        }*/
        return false;
    }
    
    public ArrayList<String> getHistory() {
        return new ArrayList<>(history.getHistory());
    }

    public double doCalculation(String functionName, double arg1, double arg2) {
        double result = 0.0;
        boolean isFunctionFound = false;
        MathFunction function = MathFunctionFactory.create(functionName);

        result = ((DoubleArgMathFunction)function).calculate(arg1, arg2);
        isFunctionFound = true;
        history.registerOperation(function, arg1,arg2,result);
        notifyHistoryChanged();
        
        if(!isFunctionFound)
            System.out.println("No such function found!");

        return result;
    }

    public void listMathFunction() {
        System.out.println("Available Functions:");
        for (String function : getAvailableFunctions())
            System.out.println(function);
    }
    
    public List<String> getAvailableFunctions() {
        return MathFunctionFactory.getAvailableFunctionNames();
    }
    
    public List<MathFunction> getFunctions(){
        return null;
    }

    public void printHistory() {
        history.printHistory();
    }

    public void undoLast() {
        history.undoLast();
        notifyHistoryChanged();
    }

    public void clearHistory() {
        history.clearHistory();
        notifyHistoryChanged();
    }

    public void saveHistory(){
        history.saveHistory();
        notifyHistoryChanged();
    }

    public void loadHistory() {
        history.loadHistory();
        notifyHistoryChanged();
    }
    
    public void addObserver(CalculatorObserver observer) {
    if (observer != null && !observers.contains(observer)) {
        observers.add(observer);
    }
}

public void removeObserver(CalculatorObserver observer) {
    observers.remove(observer);
}

private void notifyHistoryChanged() {
    for (CalculatorObserver observer : new ArrayList<>(observers)) {
        observer.onHistoryChanged();
    }
}
}
