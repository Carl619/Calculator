package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DivideFunction implements DoubleArgMathFunction {

    public String getName() { 
        return "Divide";
    }

    public double calculate(double x, double y) { 
        return x / y; 
    }
}
