package newton;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.util.regex.Pattern;

public class NewtonRaphsonController {

    @FXML
    private TextField initialValue;
    @FXML
    private TextField accuracy;
    @FXML
    private TextField equation;
    @FXML
    private Button calculateButton;
    @FXML
    private Label errorLabel;
    private String output;
    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]{0,9})?");
    @FXML
    public void initialize() {

        CornerRadii cornerRadii = new CornerRadii(3);
        calculateButton.setDisable(true);
        BorderStroke borderStroke = new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID,cornerRadii,BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);

        calculateButton.setDisable(true);
        initialValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                   initialValue.setText(oldValue);
                }
                if (!initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    Function function = new Function(equation.getText());

                    double accuracyVal = Double.parseDouble(accuracy.getText());
                    if ((function.checkSyntax()&& accuracyVal <= 0.1 && accuracyVal >= 0.0001)){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);
                }
                else
                    calculateButton.setDisable(true);



            }
        });

        accuracy.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    accuracy.setText(oldValue);
                }
                if (!initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    Function function = new Function(equation.getText());

                    double accuracyVal = Double.parseDouble(accuracy.getText());
                    if ((function.checkSyntax()&& accuracyVal <= 0.1 && accuracyVal >= 0.0001)){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);
                if (!accuracy.getText().isEmpty()) {
                    double accuracyVal = Double.parseDouble(accuracy.getText());

                    if (accuracyVal <= 0.1 && accuracyVal >= 0.0001){
                        errorLabel.setText("");
                        accuracy.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                        accuracy.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else{
                        accuracy.setBorder(border);
                        errorLabel.setText("Check that your accuracy is between 0.0001 and 0.1");
                        accuracy.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        calculateButton.setDisable(true);
                    }
                }
            }
        });
        equation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    Function function = new Function(equation.getText());

                    double accuracyVal = Double.parseDouble(accuracy.getText());
                    if ((function.checkSyntax()&& accuracyVal <= 0.1 && accuracyVal >= 0.0001)){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);

                if (!equation.getText().isEmpty()) {
                    Function function = new Function(equation.getText());

                    // double accuracyVal = Double.parseDouble(accuracy.getText());
                    if ((function.checkSyntax())) {
                        errorLabel.setText("");
                        equation.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                        equation.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else
                    {
                        errorLabel.setText("Check your function");
                        equation.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        equation.setBorder(border);
                        calculateButton.setDisable(true);
                    }

                }
                else
                    calculateButton.setDisable(true);

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
        while (Math.abs((nextValue- initValue)/initValue) > epsilon){
            i++;
            initValue = nextValue;
            nextValue = calcRaph(nextValue,i);
        }
        output += String.format("Since \\hspace{0.4cm}stopping\\hspace{0.4cm}criteria\\hspace{0.4cm}\\varepsilon = %.4f\\hspace{0.4cm} has\\hspace{0.4cm}been\\hspace{0.4cm}met,\\hspace{0.4cm}the\\hspace{0.4cm}  value \\hspace{0.4cm} of \\hspace{0.4cm} root \\hspace{0.4cm}is \\hspace{0.4cm}:\\hspace{0.4cm} %.10f\\\\"
                ,epsilon, nextValue);
        output+= "\\end{array}";
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();

    }

    public double calcRaph (double xValue, int iteration){
        Function function = new Function(equation.getText());
        Argument x = new Argument("x=" + xValue);
        System.out.println(function.getFunctionExpressionString());
        String[] eqn = equation.getText().split("=");
        Expression expression = new Expression("der((" + eqn[1] + "),x)", x);
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
        output+= String.format("\\hspace{1.4cm} x_{%d} \\hspace{0.4cm} = %.8f - \\frac{%.8f}{%.8f} = %.12f\\\\",iteration+1, xValue, function.calculate(xValue),derivative,nextXValue);
        output+= "\\\\";
        output+="\\\\";
        calcPercentError(nextXValue,xValue);
        output+= "\\\\";
        output+="\\\\";

        return nextXValue;
    }
    public void calcPercentError(double x2, double x1 ){
//       output+= ("\\hspace{1.4cm} Calculating\\hspace{0.4cm} absolute\\hspace{0.4cm}relative \\hspace{0.4cm}approximate\\hspace{0.4cm} error \\hspace{0.4cm}\\left|\\varepsilon\\right|: \\\\");
//        output+= String.format("\\hspace{1.4cm} \\left|\\frac{(%.12f - %.12f)}{%.12f}\\right| * 100 = %.4f \\\\", x2,x1,x2 , Math.abs(((x2-x1)/x2) * 100));

        output+= ("\\hspace{1.4cm}However,\\hspace{0.4cm}we \\hspace{0.4cm} calculate\\hspace{0.4cm} absolute\\hspace{0.4cm}relative \\hspace{0.4cm}approximate\\hspace{0.4cm} error\\hspace{0.4cm}to \\hspace{0.4cm}check\\hspace{0.4cm}if\\hspace{0.4cm}we\\hspace{0.4cm}have\\hspace{0.4cm}met\\hspace{0.4cm}stopping\\hspace{0.4cm}criteria  \\\\");
        output += "\\hspace{1.4cm} \\left|\\varepsilon_a\\right| = \\left|\\frac{{x}^{new} \\hspace{0.4cm}- \\hspace{0.4cm}{x}^{old}}{{x}^{new}}\\right| \\\\";
        double abs = Math.abs(((x2 - x1) / x2));
        output+= String.format("\\hspace{1.4cm} \\left|\\frac{(%.15f - %.15f)}{%.15f}\\right|  = %.10f \\\\", x2,x1,x2 , abs);
        output+="\\\\" ;
        output+="\\\\" ;

    }
    public void goBack() {
        Main.window.setScene(Main.homepage);
    }
}
