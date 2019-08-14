package runge_kutta;

import org.mariuszgromada.math.mxparser.Function;

import java.util.Scanner;

public class    RungeKutta {
    private static Function function;
    private static double stepSize;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter equation");
        String eqn =  scanner.nextLine();

        function  = new Function(eqn);

        System.out.println(function.getFunctionExpressionString());

        System.out.println("Enter initial guess value for dependent variable");
        double yValue = scanner.nextDouble();
        System.out.println();
        System.out.println("Enter step size value");
        stepSize = scanner.nextDouble();
        System.out.println();
        System.out.println("Enter final value of independent variable");
        double xValue = scanner.nextDouble();
        System.out.println();

//        startRk2(xValue,yValue,1);
//        System.out.println();
//        startRk2(xValue,yValue,2);
//        System.out.println();
//        startRk2(xValue,yValue,3);
//        System.out.println();
        startRk4(xValue,yValue);
    }
    public static double calculateRk2Heun(double xValue, double yValue, int i){
        double k1 = function.calculate(xValue,yValue);
        System.out.printf("k1 = f(x,y) = %f\n",k1);

        double k2 = function.calculate(xValue+stepSize, yValue+k1*stepSize);
        System.out.printf("k2 = f(x+h,y+k1*h) = %f\n",k2);

        System.out.printf("y%d = %.2f + (%.2f + %.2f) *0.5* %.2f\n", i+1, yValue,i*stepSize, yValue,stepSize);


        return yValue + 0.5*stepSize*(k1 +k2);
    }

    public static double calculateRk2Ralston(double xValue, double yValue, int i){
        double k1 = function.calculate(xValue,yValue);
        System.out.printf("k1 = f(x,y) = %f\n",k1);

        double k2 = function.calculate(xValue+0.75*stepSize, yValue+ 0.75*k1*stepSize);
        System.out.printf("k2 = f(x+0.75*h,y+ 0.75*k1*h) = %f\n",k2);

        System.out.printf("y%d = %.2f + (0.333 * %.2f  + 0.667 * %.2f) * %.2f\n", i+1, yValue,i*stepSize, yValue,stepSize);
        return yValue + stepSize*(k1/3 +2*k2/3);

    }

    public static double calculateRk2Midpoint(double xValue, double yValue, int i){
        double k1 = function.calculate(xValue,yValue);
        System.out.printf("k1 = f(x,y) = %f\n",k1);

        double k2 = function.calculate(xValue+stepSize*0.5, yValue+ 0.5*k1*stepSize);
        System.out.printf("k2 = f(x+ 0.5*h, y + 0.5*k1*h) = %f\n",k2);

        System.out.printf("y%d = %.2f +  %.2f * %.2f\n", i+1, yValue,k2,stepSize);

        return yValue + stepSize*(k2);
    }

    public static void startRk2(double xValue, double yValue, int method)
    {
        int iterations = (int)(xValue/stepSize);
            if(method == 1){
                System.out.println("For Heun: ");
                for (int i = 0; i < iterations+1; i++ ){
                    System.out.println();
                    System.out.println("For iteration "+i+" :");
                    System.out.printf("y%d = y%d + (k1 + k2)*0.5*stepsize\n", i+1, i);
                    yValue = calculateRk2Heun(stepSize*i, yValue, i);
                    System.out.println("y"+(i+1)+" = "+ yValue);
                }
            }
            if(method == 2){
                System.out.println("For Midpoint");
                for (int i = 0; i < iterations+1; i++ ){
                    System.out.println();
                    System.out.println("For iteration "+i+" :");
                    System.out.printf("y%d = y%d + k2*0.5*stepsize\n", i+1, i);
                    yValue = calculateRk2Midpoint(stepSize*i, yValue, i);
                    System.out.println("y"+(i+1)+" = "+ yValue);
                }

            }
            if(method == 3){
                System.out.println("For Ralston");
                for (int i = 0; i < iterations+1; i++ ){
                    System.out.println();
                    System.out.println("For iteration "+i+" :");
                    System.out.printf("y%d = y%d + (k1/3  + 2*k2/3) * stepsize\n", i+1, i);
                    yValue = calculateRk2Ralston(stepSize*i, yValue, i);
                    System.out.println("y"+(i+1)+" = "+ yValue);
                }
            }



    }


    public static double calculateRk3(double xValue, double yValue, int i){
        double F1 = stepSize * function.calculate(xValue,yValue);
        System.out.printf("F_1 = f(x,y) * stepsize = %f\n",F1);

        double F2 = stepSize * function.calculate(xValue+stepSize/2, yValue+F1/2);
        System.out.printf("F_2 = f(\\left \\frac{x+h}{2}, \\frac{y+F1}{2}\\right)* stepsize = %f\n",F2);

        double F3 = stepSize * function.calculate(xValue+ 3*stepSize/4, yValue+3*F2/4);
        System.out.printf("F_3 = f(\\left x+\\frac{3h}{4},y+ \\frac{3F_2}{4}\\right)* stepsize = %f\n",F3);

        System.out.printf("y_{%d} = %.2f + \\frac {2\\left(%.2f + 3 (%.2f) +  4 (%.2f)\\right)}{9} \n", i+1, yValue,F1, F2,F3);

        return yValue + (2*F1 +3*F2+ 4*F3)/9  ;
    }

    public static void startRk3(double xValue, double yValue)
    {
        System.out.println("For RK3: ");
        int iterations = (int)(xValue/stepSize);
        for (int i = 0; i < iterations+1; i++ ){
            System.out.println();
            System.out.println("For iteration "+i+" :");
            System.out.printf("y%d = y%d + (F1 + F2 + F3)/9\n", i+1, i);
            yValue = calculateRk3(stepSize*i, yValue, i);
            System.out.println("y"+(i+1)+" = "+ yValue);
        }

    }

    public static double calculateRk4(double xValue, double yValue, int i){
        double F1 = stepSize * function.calculate(xValue,yValue);
        System.out.printf("F1 = f(x,y) * stepsize = %f\n",F1);

        double F2 = stepSize * function.calculate(xValue+stepSize/2, yValue+F1/2);
        System.out.printf("F2 = f(x+h/2,y+F1/2)* stepsize = %f\n",F2);

        double F3 = stepSize * function.calculate(xValue+stepSize/2, yValue+F2/2);
        System.out.printf("F3 = f(x+h/2,y+F2/2)* stepsize = %f\n",F3);

        double F4 = stepSize * function.calculate(xValue+stepSize,yValue+F3);
        System.out.printf("F4 = f(x + stepsize,y+F3) * stepsize = %f\n",F4);

        System.out.printf("y%d = %.2f + ( %.2f + 2 * %.2f + * 2 * %.2f + %.2f)/6 \n", i+1, yValue,F1, F2,F3,F4);

        return yValue + (F1 +2*F2+ 2*F3+ F4)/6 ;
    }

    public static void startRk4(double xValue, double yValue)
    {
        System.out.println("For RK4: ");
        int iterations = (int)(xValue/stepSize);
        for (int i = 0; i < iterations+1; i++ ){
            System.out.println();
            System.out.println("For iteration "+i+" :");
            System.out.printf("y%d = y%d + (F1 + 2*F2 + 2*F3 + F4)/6\n", i+1, i);
            yValue = calculateRk4(stepSize*i, yValue, i);
            System.out.println("y"+(i+1)+" = "+ yValue);
        }

    }
}

