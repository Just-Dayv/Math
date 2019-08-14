package secant;

import homepage.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.mariuszgromada.math.mxparser.Function;

public class SecantController {
    @FXML
    private TextField initialValue;
    @FXML
    private TextField finalValue;
    @FXML
    private TextField accuracy;
    @FXML
    private TextField equation;

    private String output="";

    public void calculate(){
        output+= "\\begin{array}{l}";
        output+="For\\hspace{0.4cm}Secant:\\\\";
        output+="x_{i+1} = x_{i}- \\frac{f(x_i)\\cdot(x_{i}-x_{i-1})}{f(x_i)-f(x_{i-1 })}\\\\";

        double firstGuess= Double.parseDouble(initialValue.getText());
        double secondGuess = Double.parseDouble(finalValue.getText());
        double epsilon = Double.parseDouble(accuracy.getText());
        double currentValue = secondGuess;
        double nextValue = calc(currentValue,firstGuess, 0);
        int i = 0;
        while (Math.abs((nextValue- currentValue)/nextValue) > epsilon){
            i++;
            double temp = nextValue;
            nextValue = calc(nextValue,currentValue,i);
            currentValue = temp;
        }
        output+= String.format("Final\\hspace{0.4cm} value\\hspace{0.4cm} of \\hspace{0.4cm}x: \\hspace{0.4cm}%.3f, \\hspace{0.4cm}total \\hspace{0.4cm}number \\hspace{0.4cm}of \\hspace{0.4cm}iterations:\\hspace{0.4cm} %d\\\\",nextValue, i);

        output += "\\end{array}";

        Main.output.set(output);
        Main.window.setScene(Main.outputScene);

    }

    public double calc (double xValue, double x2Value, int iteration){
        Function function = new Function(equation.getText());
        output+= String.format("For \\hspace{0.4cm}iteration\\hspace{0.4cm} %d :\\\\",iteration);

        output+= String.format("Solution\\hspace{0.4cm} to \\hspace{0.4cm}the \\hspace{0.4cm}equation\\hspace{0.4cm} of\\hspace{0.4cm} first\\hspace{0.4cm} guess \\hspace{0.4cm}is: \\hspace{0.4cm}%.8f\\\\",  function.calculate(x2Value));

        output+= String.format("Solution\\hspace{0.4cm} to\\hspace{0.4cm} the \\hspace{0.4cm}equation\\hspace{0.4cm} of\\hspace{0.4cm} second\\hspace{0.4cm} guess\\hspace{0.4cm} is: \\hspace{0.4cm}%.8f\\\\", function.calculate(xValue));

        double nextXValue = xValue - (function.calculate(xValue)*(xValue-x2Value)/(function.calculate(xValue)-function.calculate(x2Value)));
        output+= String.format(" %.4f -  \\frac{(%.10f \\cdot %.10f)}{(%.10f - %.10f)} = %.10f\\\\",xValue,function.calculate(xValue),xValue-x2Value,function.calculate(xValue),function.calculate(x2Value), nextXValue );

      //  calcPercentError(nextXValue,xValue);

        return nextXValue;
    }

    public void calcPercentError(double x1, double x2 ){
        output+= ("Calculating\\hspace{0.4cm} percentage\\hspace{0.4cm} error: \\\\");

        output+= String.format("\\frac{%.8f - %.8f}{ %.8f} * 100 = ", x1,x2,x1 );
        output+= String.format("%.2f\\\\",Math.abs(((x1-x2)/x1) * 100));


    }

    public void println()
    {
        output+="\\\\";
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }

}
