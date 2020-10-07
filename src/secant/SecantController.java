package secant;

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
import org.mariuszgromada.math.mxparser.Function;

import java.util.regex.Pattern;

public class SecantController {
    @FXML
    private TextField initialValue;
    @FXML
    private TextField finalValue;
    @FXML
    private TextField accuracy;
    @FXML
    private TextField equation;

    @FXML
    private Button calculateButton;
    @FXML
    private Label errorLabel;

    String  output;

    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]{0,9})?");

    @FXML
    public void initialize() {
        CornerRadii cornerRadii = new CornerRadii(3);
        calculateButton.setDisable(true);
        BorderStroke borderStroke = new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID,cornerRadii,BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);

        calculateButton.setDisable(true);
        finalValue.setPromptText(">initial value");
        accuracy.setPromptText("range (0.0001-0.1)");
        initialValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    initialValue.setText(oldValue);
                }

                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(finalValue.getText());
                    double initialVal = Double.parseDouble(initialValue.getText());
                    double accuracyVal = Double.parseDouble(accuracy.getText());
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal)&& accuracyVal <= 0.1 && accuracyVal >= 0.0001)){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);

                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(finalValue.getText());
                    double initialVal = Double.parseDouble(initialValue.getText());

                    if ((finalVal > initialVal)){
                        errorLabel.setText("");
                        initialValue.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                        initialValue.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else{
                        errorLabel.setText("Check that your second guess is greater than your first guess value");
                        initialValue.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        initialValue.setBorder(border);
                        calculateButton.setDisable(true);
                    }
                }
            }
        });
        finalValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    finalValue.setText(oldValue);
                }

                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(finalValue.getText());
                    double initialVal = Double.parseDouble(initialValue.getText());
                    double accuracyVal = Double.parseDouble(accuracy.getText());
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal)&& accuracyVal <= 0.1 && accuracyVal >= 0.0001)){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);

                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(finalValue.getText());
                    double initialVal = Double.parseDouble(initialValue.getText());

                    if ((finalVal > initialVal)){
                        errorLabel.setText("");
                        initialValue.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                        initialValue.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else{
                        errorLabel.setText("Check that your second guess is greater than your first guess value");
                        initialValue.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                        initialValue.setBorder(border);
                        calculateButton.setDisable(true);
                    }
                }

            }
        });
        accuracy.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    accuracy.setText(oldValue);
                }

                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(finalValue.getText());
                    double initialVal = Double.parseDouble(initialValue.getText());
                    double accuracyVal = Double.parseDouble(accuracy.getText());
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal)&& accuracyVal <= 0.1 && accuracyVal >= 0.0001)){

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
                        //calculateButton.setDisable(false);
                    }
                    else{
                        errorLabel.setText("Check that your accuracy is between 0.0001 and 0.1");
                        accuracy.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        accuracy.setBorder(border);
                        calculateButton.setDisable(true);
                    }
                }


            }
        });
        equation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(finalValue.getText());
                    double initialVal = Double.parseDouble(initialValue.getText());
                    Function function = new Function(equation.getText());
                    double accuracyVal = Double.parseDouble(accuracy.getText());
                    if ((function.checkSyntax()&& (finalVal > initialVal)&& accuracyVal <= 0.1 && accuracyVal >= 0.0001)){

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
    }

    public void calculate(){

        output = "\\begin{array}{l}";
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
        output += String.format("Since \\hspace{0.4cm}stopping\\hspace{0.4cm}criteria\\hspace{0.4cm}\\varepsilon = %.4f\\hspace{0.4cm} has\\hspace{0.4cm}been\\hspace{0.4cm}met,\\hspace{0.4cm}the\\hspace{0.4cm}  value \\hspace{0.4cm} of \\hspace{0.4cm} root \\hspace{0.4cm}is \\hspace{0.4cm}:\\hspace{0.4cm} %.10f\\\\"
                ,epsilon, nextValue);
        output += "\\end{array}";

        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();

    }

    public double calc (double xValue, double x2Value, int iteration){
        Function function = new Function(equation.getText());
        output+= String.format("For \\hspace{0.4cm}iteration\\hspace{0.4cm} %d :\\\\",iteration);

        output+=String.format("\\hspace{1.4cm}x_{%d} = x_{%d}- \\frac{(x_{%d}-x_{%d})f(x_{%d})}{f(x_%d)-f(x_{%d })}\\\\",iteration+1,iteration,iteration,iteration-1,iteration,iteration,iteration-1);

        double nextXValue = xValue - (function.calculate(xValue)*(xValue-x2Value)/(function.calculate(xValue)-function.calculate(x2Value)));
        output+= String.format("\\hspace{1.4cm}x_{%d} = %.8f -  \\frac{(%.10f) (%.10f)}{(%.10f) - (%.10f)} \\\\",iteration+1, xValue,function.calculate(xValue),xValue-x2Value,function.calculate(xValue),function.calculate(x2Value));

        output+="\\\\";
        output+= String.format("\\hspace{1.4cm}= %.10f\\\\", nextXValue );
       calcPercentError(nextXValue,xValue);

        return nextXValue;
    }

    public void calcPercentError(double x2, double x1 ){
//        output+="\\\\";
//        output+="\\\\";
//        output+= ("\\hspace{1.4cm} Calculating\\hspace{0.4cm} absolute\\hspace{0.4cm}relative \\hspace{0.4cm}approximate\\hspace{0.4cm} error \\hspace{0.4cm}\\left|\\varepsilon\\right|: \\\\");
//        output+= String.format("\\hspace{1.4cm} \\left|\\frac{(%.8f - %.8f)}{%.8f}\\right| * 100 = %.2f \\\\", x2,x1,x2 , Math.abs(((x2-x1)/x2) * 100));
//        output+="\\\\";
//        output+="\\\\";

        output+= ("\\hspace{1.4cm}However,\\hspace{0.4cm}we \\hspace{0.4cm} calculate\\hspace{0.4cm} absolute\\hspace{0.4cm}relative \\hspace{0.4cm}approximate\\hspace{0.4cm} error\\hspace{0.4cm}to \\hspace{0.4cm}check\\hspace{0.4cm}if\\hspace{0.4cm}we\\hspace{0.4cm}have\\hspace{0.4cm}met\\hspace{0.4cm}stopping\\hspace{0.4cm}criteria  \\\\");
        output += "\\hspace{1.4cm} \\left|\\varepsilon_a\\right| = \\left|\\frac{{x_{i+1}} \\hspace{0.4cm}- \\hspace{0.4cm}{x_{i}}}{{x_{i+1}}}\\right| \\\\";
        double abs = Math.abs(((x2 - x1) / x2));
        output+= String.format("\\hspace{1.4cm} \\left|\\frac{(%.15f - %.15f)}{%.15f}\\right|  = %.10f \\\\", x2,x1,x2 , abs);
        output+="\\\\" ;
        output+="\\\\" ;

    }
    public void println()
    {
        output+="\\\\";
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }

}
