package lib;

import lib.enums.EquationGenerationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Utilities;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquationGenerator {
    private int equationOrdrer;
    private boolean equationHasNegativeOrders;
    private List<Double> equationPositiveOrderCoefficients;
    private List<Double> equationNegativeOrderCoefficients;
    private double constant;
    private double initialValue;
    private Scanner scanner;
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

    public EquationGenerator(EquationGenerationState equationGenerationState){
        if(equationGenerationState == EquationGenerationState.AUTO) {
            equationPositiveOrderCoefficients = new ArrayList<Double>();
            equationNegativeOrderCoefficients = new ArrayList<Double>();
            equationHasNegativeOrders = false;
            while (true) {
                try {
                    scanner = new Scanner(System.in);
                    System.out.println("ENTER ORDER OF THE EQUATION: ");
                    this.equationOrdrer = scanner.nextInt();
                    break;
                } catch (Exception e) {
                   logger.error("invalid input format, enter an integer");
                    continue;
                }
            }
            generateParameters(equationOrdrer);
        }
    }

    private void generateParameters(int order){
        if(order > 0) {
            for (int i = order; i > 0; i--) {
                System.out.printf("ENTER THE COEFFICIENT OF THE %s ORDER: \n", Utilities.appendNumberPosition(i));
                equationPositiveOrderCoefficients.add(scanner.nextDouble());
            }
            System.out.println("DOES EQUATION HAVE NEGATIVE ORDERS (ENTER Y <FOR YES> & N <FOR NO>):");
            String resp = scanner.next();
            equationHasNegativeOrders = (resp.toLowerCase().equals("y") ? true: false);
            if(equationHasNegativeOrders){
                System.out.println("ENTER LOWEST ORDER OF THE EQUATION: ");
                System.out.println("(I.E. NEGATIVE ORDER)");
                int negativeOrder = scanner.nextInt();
                int absNegativeOrder = Math.abs(negativeOrder);
                for(int i=1; i <= absNegativeOrder; i++){
                    System.out.printf("ENTER COEFFICIENT OF ORDER TO POWER OF -%s: \n",i);
                    equationNegativeOrderCoefficients.add(scanner.nextDouble());
                }
            }
        }
        else{
            int orderAbs = Math.abs(order);
            for(int i=1; i <= orderAbs; i++){
                System.out.printf("ENTER COEFFICIENT OF ORDER TO POWER OF -%s: \n",i);
                equationNegativeOrderCoefficients.add(scanner.nextDouble());
            }
        }
        System.out.println("ENTER THE CONSTANT OF THE EQUATION");
        constant = scanner.nextDouble();

        System.out.println("ENTER THE INITIAL GUESS VALUE OF X TO BE USED TO SOLVE THE EQUATION");
        initialValue = scanner.nextDouble();
    }

    public DifferentialFunctionParams getFunctionParams(){
        DifferentialFunctionParams differentialFunctionParams = new DifferentialFunctionParams();
        differentialFunctionParams.setConstant(constant);
        differentialFunctionParams.setInitialValue(initialValue);
        differentialFunctionParams.setPositiveOrderCoefficients(equationPositiveOrderCoefficients);
        differentialFunctionParams.setNegativeOrderCoefficients(equationNegativeOrderCoefficients);
        return differentialFunctionParams;
    }

}
