package lib;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;

public class DifferentialFunction {
    public DifferentialFunctionParams differentialFunctionParams;




    public  DifferentialFunction(DifferentialFunctionParams differentialFunctionParams){
        this.differentialFunctionParams = differentialFunctionParams;
    }

    public double getInitialEquationSolutionHelper(){
        return getEquationSolution(differentialFunctionParams.getInitialValue());
    }
    public double getEquationSolution(double value){
        double solution = 0;
        for(int i=differentialFunctionParams.getPositiveOrderCoefficients().size(); i>0; i--){
            solution += (Math.pow(value, i) * differentialFunctionParams.getPositiveOrderCoefficients().get(differentialFunctionParams.getPositiveOrderCoefficients().size()-i));
        }
        for(int i=differentialFunctionParams.getNegativeOrderCoefficients().size(); i>0; i--){
            solution += ((1/(Math.pow(value, i))) * differentialFunctionParams.getNegativeOrderCoefficients().get(i-1));
        }
        solution += differentialFunctionParams.getConstant();
        return solution;
    }
    public double getDerivative (double xValue){
        double derivative = 0;
        DerivativeStructure x = new DerivativeStructure(1,1,0,xValue);
        int positive = differentialFunctionParams.getPositiveOrderCoefficients().size();
        int negative = differentialFunctionParams.getNegativeOrderCoefficients().size();

        DerivativeStructure [] positiveOrderVariable = new DerivativeStructure[positive];
        DerivativeStructure [] negativeOrderVariable = new DerivativeStructure[negative];

        for(int i =  0; i < differentialFunctionParams.getPositiveOrderCoefficients().size(); i++ ){
            positiveOrderVariable[i] = x.pow(positive);
            positive-=1;
            derivative+= differentialFunctionParams.getPositiveOrderCoefficients().get(i)* positiveOrderVariable[i].getPartialDerivative(1);
        }
        for(int i =  0; i < differentialFunctionParams.getNegativeOrderCoefficients().size(); i++ ){
            negativeOrderVariable[i] = x.pow(-negative);
            negative-=1;
            derivative+= differentialFunctionParams.getNegativeOrderCoefficients().get(i)* negativeOrderVariable[i].getPartialDerivative(1);
        }
//
        return derivative;
    }
     public void calcPercentError(double x1, double x2 ){
         System.out.println("Calculating percentage error: ");
         System.out.printf("((%.8f - %.8f)/ %.8f) * 100 = ", x1,x2,x1 );
         System.out.printf("%.2f",Math.abs(((x1-x2)/x1) * 100));
         System.out.println();

     }
}
