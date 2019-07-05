package lib;

import java.util.List;

public class DifferentialFunctionParams {

    private List<Double> positiveOrderCoefficients;
    private List<Double> negativeOrderCoefficients;
    private List<Double> allCoefficients;
    private double constant;
    private double initialValue;

    public void setAllCoefficients(){
        allCoefficients.addAll(positiveOrderCoefficients);
        allCoefficients.addAll(negativeOrderCoefficients);
    }
    public List<Double> getAllCoefficients() {
        return allCoefficients;
    }
    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public List<Double> getPositiveOrderCoefficients() {
        return positiveOrderCoefficients;
    }

    public void setPositiveOrderCoefficients(List<Double> positiveOrderCoefficients) {
        this.positiveOrderCoefficients = positiveOrderCoefficients;
    }

    public List<Double> getNegativeOrderCoefficients() {
        return negativeOrderCoefficients;
    }

    public void setNegativeOrderCoefficients(List<Double> negativeOrderCoefficients) {
        this.negativeOrderCoefficients = negativeOrderCoefficients;
    }

    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
    }
}
