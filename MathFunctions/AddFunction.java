package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AddFunction implements DoubleArgMathFunction {

    public String getName() { 
        return "Add";
    }

    public double calculate(double x, double y) { 
        return x + y; 
    }
    
    @Override
    public MathFunction createClone() {
        return new AddFunction();
    }
    
    @Override
    public int getRequiredArguments() {
    return 2;
}
}
