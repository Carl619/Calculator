package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LogNFunction implements DoubleArgMathFunction {

    public String getName() { 
        return "Logn";
    }

    public double calculate(double x, double y) { 
        return Math.log(x) / Math.log(y); 
    }
    
    @Override
    public MathFunction createClone() {
        return new LogNFunction();
    }
    @Override
    public int getRequiredArguments() {
    return 2;
}
}
