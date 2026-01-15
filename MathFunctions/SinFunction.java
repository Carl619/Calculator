package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */ 
public class SinFunction implements SingleArgMathFunction {
    
    private static String name = "Sin";

    public String getName() {
        return name;
    }
    
    public double calculate(double arg) {
        return Math.sin(arg);
    }
    
    @Override
    public MathFunction createClone() {
        return new SinFunction();
    }
}
