package differentiation;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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

public class FirstOrderDifferentiationController {


    @FXML
    private TextField initialValue;
    @FXML
    private TextField stepSize;
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
                if (!initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax())){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);
                }
                else
                    calculateButton.setDisable(true);



            }
        });

        stepSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    stepSize.setText(oldValue);
                }
                if (!initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax())){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);
                }
                else
                    calculateButton.setDisable(true);
//
//                if (!accuracy.getText().isEmpty()) {
//                    double accuracyVal = Double.parseDouble(accuracy.getText());
//
//                    if (accuracyVal <= 0.1 && accuracyVal >= 0.0001){
//                        errorLabel.setText("");
//                        accuracy.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
//                        accuracy.setBorder(border);
//                        //calculateButton.setDisable(false);
//                    }
//                    else{
//                        accuracy.setBorder(border);
//                        errorLabel.setText("Check that your accuracy is between 0.0001 and 0.1");
//                        accuracy.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
//                        calculateButton.setDisable(true);
//                    }
//                }
            }
        });
        equation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    Function function = new Function(equation.getText());

                   // double accuracyVal = Double.parseDouble(accuracy.getText());
                    if ((function.checkSyntax())){
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

    public void calculate(ActionEvent actionEvent) {
         output = "\\begin{array}{l}";

        output+="For\\hspace{0.4cm} the\\hspace{0.4cm} Forward \\hspace{0.4cm}Divided\\hspace{0.4cm} Difference:\\\\";
        Function function = new Function(equation.getText());
        double xValue = Double.parseDouble(initialValue.getText());
        Argument x = new Argument("x=" + xValue);
        String[] eqn = equation.getText().split("=");
       Expression expression = new Expression("der((" + eqn[1] + "),x)", x);
        output+= "\\hspace{1.4cm}f'(x) = \\frac{f(x+h) - f(x)}{h}\\\\";
        double stepValue = Double.parseDouble(stepSize.getText());
        output+= String.format("\\hspace{1.4cm}x= %.4f\\\\", xValue);
        output+= String.format("\\hspace{1.4cm}step size = %.4f\\\\", stepValue);
        output += String.format("\\hspace{1.4cm}x+h = %.4f\\\\",xValue+stepValue);


        output+= String.format("\\hspace{1.4cm}f'(x) = \\frac{f(%.4f) - f(%.4f)}{%.4f}\\\\",xValue+stepValue,xValue,stepValue);

        output+= String.format("\\hspace{1.4cm}f'(x) = \\frac{%.4f - %.4f}{%.4f}\\\\",function.calculate(xValue+stepValue),function.calculate(xValue),stepValue);




        double initialDerivative = (function.calculate(xValue+stepValue) - function.calculate(xValue))/stepValue;

        output+= String.format("\\hspace{1.4cm}f'(x)=%.4f\\\\",initialDerivative);

        double exactValue = expression.calculate();
        output+= String.format("\\hspace{1.4cm}Exact value = %.4f\\\\",exactValue);

        calcPercentError(exactValue, initialDerivative);

        output+="\\\\";

        output+="For\\hspace{0.4cm} the\\hspace{0.4cm} Backward\\hspace{0.4cm} Difference\\hspace{0.4cm} Approximation\\\\";
        output+= "\\hspace{1.4cm}f'(x) = \\frac{f(x) - f(x-h)}{h}\\\\";


        output+= String.format("\\hspace{1.4cm}f'(x) = \\frac{f(%.4f) - f(%.4f)}{%.4f}\\\\",xValue,xValue-stepValue,stepValue);

        output+= String.format("\\hspace{1.4cm}f'(x) = \\frac{%.4f - %.4f}{%.4f}\\\\",function.calculate(xValue),function.calculate(xValue-stepValue),stepValue);
        double backDerivative = (function.calculate(xValue) - function.calculate(xValue-stepValue))/stepValue;
        output+= String.format("\\hspace{1.4cm}f'(x)=%.4f\\\\",backDerivative);
        output+="\\\\";

        calcPercentError(exactValue, backDerivative);

        output+="\\\\";

        output+="For\\hspace{0.4cm} the\\hspace{0.4cm} Central\\hspace{0.4cm} Difference\\hspace{0.4cm} Approximation\\\\";
        output+= "\\hspace{1.4cm}f'(x) = \\frac{f(x+h) - f(x-h)}{2*h}\\\\";


        output+= String.format("\\hspace{1.4cm}f'(x) = \\frac{f(%.4f) - f(%.4f)}{%.4f}\\\\",xValue+stepValue,xValue-stepValue,2*stepValue);

        output+= String.format("\\hspace{1.4cm}f'(x) = \\frac{%.4f - %.4f}{%.4f}\\\\",function.calculate(xValue+stepValue),function.calculate(xValue-stepValue),2*stepValue);
        double centralDerivative = (function.calculate(xValue+stepValue) - function.calculate(xValue-stepValue))/(2*stepValue);
        output+= String.format("\\hspace{1.4cm}f'(x)=%.4f\\\\",centralDerivative);
        output+="\\\\";
        calcPercentError(exactValue, centralDerivative);
        output+="\\\\";

        output+= "\\end{array}";
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();


    }

    public void calcPercentError(double x2, double x1 ){
        output+= ("\\hspace{1.4cm} Calculating\\hspace{0.4cm}percentage\\hspace{0.4cm} absolute\\hspace{0.4cm}relative \\hspace{0.4cm}approximate\\hspace{0.4cm} error \\hspace{0.4cm}\\left|\\varepsilon\\right|: \\\\");
        output+= String.format("\\hspace{1.4cm} \\left|\\frac{(%.8f - %.8f)}{%.8f}\\right| * 100 = %.2f \\\\", x2,x1,x2 , Math.abs(((x2-x1)/x2) * 100));

    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }
}
