package CalculatorLogic;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import MathFunctions.*;

/**
 * Write a description of class HistoryCalculator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HistoryCalculator
{
    // instance variables - replace the example below with your own
    private ArrayList<String> history;
    private static final String HISTORY_FILE = "HistoryCalculator.txt";
    
    /**
     * Constructor for objects of class HistoryCalculator
     */
    public HistoryCalculator()
    {
        // initialise instance variables
        history = new ArrayList<String>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void registerOperation(MathFunction function, double arg, double result )
    {
        if(function instanceof SingleArgMathFunction)
        {
            history.add(function.getName() + " " + arg + " = " + result);
        }
    }
    
    public void registerOperation(MathFunction function, double arg1, double arg2, double result )
    {
        if(function instanceof DoubleArgMathFunction)
        {
            history.add(function.getName() + " " + arg1 + " "+ arg2+" = " + result);
        }
    }
    
    public ArrayList<String> getHistory() {
        return new ArrayList<>(history);
    }
    
    public void printHistory()
    {
        System.out.println("Printing history:\n");
        for(String operation : history)
            System.out.println(operation+"\n");
    }
    
    public void clearHistory()
    {
        history.clear();
    }
    
    public void saveHistory()
    {
        try {
FileWriter myWriter = new FileWriter("HistoryCalculator.txt");
for(String operation : history)
            myWriter.write(operation+"\n");
myWriter.close();
System.out.println("Successfully wrote to the file.");
} catch (IOException e) {
System.out.println("An error occurred.");
e.printStackTrace();
}
    }
    
    public void undoLast()
    {
        if (!history.isEmpty()) {
        history.removeLast();
    }
    }
    
    public void loadHistory()
    {
         try {
            File file = new File("HistoryCalculator.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                history.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
    
    
}
