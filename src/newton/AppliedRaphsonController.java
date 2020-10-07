package newton;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class AppliedRaphsonController {

    @FXML
    private TextField initialValue;
    @FXML
    private TextField accuracy;
    @FXML
    private TextField equation;
    @FXML
    private Button calculateButton;
    private String output;

    @FXML
    public void initialize() {
        calculateButton.setDisable(true);
        initialValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!initialValue.getText().isEmpty() && !accuracy.getText().isEmpty() && !equation.getText().isEmpty()) {
                    calculateButton.setDisable(false);
                    System.out.println("Enable");
                } else {
                    calculateButton.setDisable(true);
                }
                double val = Double.parseDouble(accuracy.getText());
                if(val == 0.001 || val== 0.01 || val == 0.1 ){
                    calculateButton.setDisable(false);
                }
                else calculateButton.setDisable(true);

            }
        });

        accuracy.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!initialValue.getText().isEmpty() && !accuracy.getText().isEmpty() && !equation.getText().isEmpty()) {
                    calculateButton.setDisable(false);
                    System.out.println("Enable");
                } else {
                    calculateButton.setDisable(true);
                }
                double val = Double.parseDouble(accuracy.getText());
                if(val == 0.001 || val== 0.01 || val == 0.1 ){
                    calculateButton.setDisable(false);
                }
                else calculateButton.setDisable(true);
            }
        });
        equation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!initialValue.getText().isEmpty() && !accuracy.getText().isEmpty() && !equation.getText().isEmpty()) {
                    calculateButton.setDisable(false);
                    System.out.println("Enable");
                } else {
                    calculateButton.setDisable(true);
                }
                double val = Double.parseDouble(accuracy.getText());
                if(val == 0.001 || val== 0.01 || val == 0.1 ){
                    calculateButton.setDisable(false);
                }
                else calculateButton.setDisable(true);

            }
        });
    }
    public void calculate() {

        double initValue = Double.parseDouble(initialValue.getText());
        double epsilon = Double.parseDouble(accuracy.getText());

        output = "\\begin{array}{l}";
        output+="\\\\";
        output+= "For\\hspace{0.4cm} Newton Raphson: x_{i+1} = x_i - \\frac{f(x_i)}{f'(x_i)}\\\\";
        output+= "\\\\";
        output+="\\\\";
        double nextValue = calcRaph(initValue,0);
        output+= "\\\\";
        output+="\\\\";
        int i = 0;
        while (Math.abs((nextValue- initValue)) > epsilon){
            i++;
            initValue = nextValue;
            nextValue = calcRaph(nextValue,i);
        }
        output+=String.format("Final \\hspace{0.4cm} value \\hspace{0.4cm} of \\hspace{0.4cm} X: \\hspace{0.4cm} %.8f, \\hspace{0.4cm} total \\hspace{0.4cm} number \\hspace{0.4cm} of  \\hspace{0.4cm}iterations :\\hspace{0.4cm} %d \\\\",nextValue, i);

        output+= "\\end{array}";
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();

    }

    public double calcRaph (double xValue, int iteration){
        Function function = new Function("f(x)="+equation.getText());
        Argument x = new Argument("x=" + xValue);
        System.out.println(function.getFunctionExpressionString());
        Expression expression = new Expression("der((" + equation.getText() + "),x)", x);
        System.out.println("here"+ expression.getExpressionString());
        output+= String.format("For \\hspace{0.4cm} iteration \\hspace{0.4cm} %d:",iteration);
        output+= "\\\\";
        output+="\\\\";
        output+= String.format("\\hspace{1.4cm} f(x_{%d})\\hspace{0.4cm} = f(%.8f)=  \\hspace{0.4cm}%.8f\\\\", iteration, xValue, function.calculate(xValue));
        double derivative = expression.calculate();
        output+= String.format("\\hspace{1.4cm} f'(x_{%d})\\hspace{0.4cm}= \\hspace{0.4cm} %.8f\\\\", iteration, derivative);
        output+= "\\\\";
        output+="\\\\";
        output+= String.format("\\hspace{1.4cm}  x_{%d} = x_%d - \\frac{f(x_{%d})}{f'(x_{%d})}\\\\",iteration+1,iteration,iteration,iteration);
        output+= "\\\\";
        output+="\\\\";
        double nextXValue = xValue - (function.calculate(xValue)/derivative);
        output+= String.format("\\hspace{1.4cm} x_{%d} \\hspace{0.4cm} = %.8f - \\frac{%.8f}{%.8f} = %.8f\\\\",iteration+1, xValue, function.calculate(xValue),derivative,nextXValue);
        output+= "\\\\";
        output+="\\\\";
        calcPercentError(nextXValue,xValue);
        output+= "\\\\";
        output+="\\\\";

        return nextXValue;
    }
    public void calcPercentError(double x2, double x1 ){
        output+= ("\\hspace{1.4cm} Calculating\\hspace{0.4cm} absolute\\hspace{0.4cm}relative \\hspace{0.4cm}approximate\\hspace{0.4cm} error \\hspace{0.4cm}\\left|\\varepsilon\\right|: \\\\");
        output+= String.format("\\hspace{1.4cm} \\left|\\frac{(%.8f - %.8f)}{%.8f}\\right| * 100 = %.2f \\\\", x2,x1,x2 , Math.abs(((x2-x1)/x2) * 100));

    }
    public void goBack() {
        Main.window.setScene(Main.homepage);
    }
}
