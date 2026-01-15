package MathFunctions;

/**
 * Write a description of class here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ExpFunction implements SingleArgMathFunction {

    public String getName() { 
        return "exp";
    }

    public double calculate(double x) { 
        return Math.exp(x); 
    }
    
    @Override
    public MathFunction createClone() {
        return new ExpFunction();
    }
}
