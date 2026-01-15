package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MultiplyFunction implements DoubleArgMathFunction {

    public String getName() { 
        return "Multiply";
    }

    public double calculate(double x, double y) { 
        return x * y; 
    }
    
    @Override
    public MathFunction createClone() {
        return new MultiplyFunction();
    }
    
    @Override
    public int getRequiredArguments() {
    return 2;
}
}
