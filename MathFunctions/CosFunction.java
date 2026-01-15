package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */ 
public class CosFunction implements SingleArgMathFunction {
    
    private static String name = "Cos";

    public String getName() {
        return name;
    }
    
    public double calculate(double arg) {
        return Math.cos(arg);
    }
    
    @Override
    public MathFunction createClone() {
        return new CosFunction();
    }
    @Override
    public int getRequiredArguments() {
    return 1;
}
}
