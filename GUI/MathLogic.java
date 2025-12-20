package GUI;
import CalculatorLogic.*;

/**
 * Write a description of class MathLogic here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MathLogic
{
    // instance variables - replace the example below with your own
    private String firstNumber;
    private String secondNumber;
    private String result;
    private String operation;
    private boolean twoNumberOperation;
    private FlexCalculator logic;
    /**
     * Constructor for objects of class MathLogic
     */
    public MathLogic()
    {
        // initialise instance variables
        firstNumber = "";
        secondNumber = "";
        result = "";
        twoNumberOperation = false;
        logic = new FlexCalculator();
    }
    
    public void setOperation(String operation)
    {
        this.operation=operation;
        logic.checkNumberArgsFunctionNeeds(operation);
    }
    public void updateNumber(String number)
    {
        if(twoNumberOperation)
        {
            firstNumber.concat(number);
        }
        else
        {
            secondNumber.concat(number);
        }
    }
    
    public void doOperation()
    {
        if(!twoNumberOperation){
        logic.doCalculation(operation, Double.parseDouble(firstNumber));
        }
        else{
         logic.doCalculation(operation, Double.parseDouble(firstNumber), Double.parseDouble(secondNumber));
        }
         twoNumberOperation = false;
    }
}
