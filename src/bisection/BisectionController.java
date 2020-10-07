package bisection;

//import lib.DifferentialFunction;

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


public class BisectionController {
    @FXML
    private TextField initialValue;
    @FXML
    private TextField accuracy;
    @FXML
    private TextField equation;
    @FXML
    private Button calculateButton;
    @FXML
    private TextField finalValue;

    private String output;

    @FXML
    private Label errorLabel;

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
                        accuracy.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                        accuracy.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else{
                        errorLabel.setText("Check that your accuracy is between 0.0001 and 0.1");
                        accuracy.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
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

        output = "\\begin{array}{l}";
        double initValue = Double.parseDouble(initialValue.getText());
        double epsilon =Double.parseDouble(accuracy.getText());
        Function function = new Function(equation.getText());
        double finValue = Double.parseDouble(finalValue.getText());


        output+=String.format("x_l = %.4f\\\\",initValue);
        output+=String.format("x_u = %.4f\\\\",finValue);
        output += String.format("f(%.5f)\\hspace{0.4cm}  = \\hspace{0.4cm} %.5f\\\\", initValue, function.calculate(initValue));
        output += String.format("f(%.5f)\\hspace{0.4cm}  = \\hspace{0.4cm} %.5f\\\\", finValue, function.calculate(finValue));
        output += String.format("f(%.5f)*f(%.5f) = \\hspace{0.4cm} %.13f\\hspace{0.4cm}which \\hspace{0.4cm}is \\hspace{0.4cm} < 0\\\\", initValue, finValue, (function.calculate(initValue) * function.calculate(finValue)));

        double midValue = initValue;
        double oldMidValue = midValue;
        int iteration =1;
        double percentError  = 1;
        while (percentError > epsilon) {
            midValue = (initValue + finValue) / 2;

            output += "\\\\";
            output += "\\\\";
//            if (iteration > 1)
//            {
//                percentError = calcPercentError(midValue,oldMidValue);
//                if ((percentError) < epsilon)
//                    break;
//            }
            output+= String.format("For \\hspace{0.4cm} iteration \\hspace{0.4cm} %d:\\\\",iteration);
            output+= String.format("\\hspace{1.4cm} x_m = \\frac{x_l+x_u}{2} = \\frac{%.4f + %.4f}{2}\\\\",initValue,finValue);
            output += String.format("\\hspace{1.4cm} mid value\\hspace{0.4cm} = \\hspace{0.4cm} %.5f\\\\", midValue);
            output += String.format("\\hspace{1.4cm} f(%.5f)\\hspace{0.4cm}  = \\hspace{0.4cm} %.12f\\\\", midValue, function.calculate(midValue));

            // iteration+=1;

            if (function.calculate(midValue) == 0 ) {
                output += "Since\\hspace{0.4cm} f(x_m)\\hspace{0.4cm} = 0,\\hspace{0.4cm} We\\hspace{0.4cm}  have\\hspace{0.4cm}  reached\\hspace{0.4cm} a solution\\hspace{0.4cm}  to\\hspace{0.4cm}the\\hspace{0.4cm}  equation\\\\";
                break;
            } else if ((function.calculate(midValue) * function.calculate(initValue)) < 0) {

                if (iteration > 1)
                {
                    percentError = calcPercentError(midValue,initValue);
                    if ((percentError) < epsilon)
                        break;
                }
                output += String.format("\\hspace{1.4cm} f(x_m)f(x_u)\\hspace{0.4cm}=\\hspace{0.4cm}f(%.5f)*f(%.5f) = \\hspace{0.4cm}%.18f \\hspace{0.4cm}which \\hspace{0.4cm}is \\hspace{0.4cm} > 0\\\\", midValue, finValue, function.calculate(midValue) * function.calculate(finValue));
                finValue = midValue;

                output += String.format("\\hspace{1.4cm} f(x_l)f(x_m)\\hspace{0.4cm}=\\hspace{0.4cm}f(%.5f)*f(%.5f) = \\hspace{0.4cm} %.18f\\hspace{0.4cm}which \\hspace{0.4cm}is \\hspace{0.4cm} < 0\\\\", initValue, midValue, (function.calculate(midValue) * function.calculate(initValue)));
                output += String.format("\\hspace{1.4cm} Therefore\\hspace{0.4cm} a \\hspace{0.4cm} solution\\hspace{0.4cm} exists\\hspace{0.4cm}  between \\hspace{0.4cm} %.10f  \\hspace{0.4cm} and\\hspace{0.4cm}  %.10f \\\\", initValue, midValue);

                output += String.format("\\hspace{1.4cm} Testing\\hspace{0.4cm}  with \\hspace{0.4cm} new \\hspace{0.4cm} constraints\\hspace{0.4cm} :\\hspace{0.4cm}  %.10f, \\hspace{0.4cm} %.10f\\\\", initValue, finValue);
                iteration+=1;



            } else {

                if (iteration > 1)
                {
                    percentError = calcPercentError(midValue,finValue);
                    if ((percentError) < epsilon)
                        break;
                }
                output += String.format("\\hspace{1.4cm}f(x_l)f(x_m)\\hspace{0.4cm} =\\hspace{0.4cm} f(%.5f)*f(%.5f) = \\hspace{0.4cm} %.18f\\hspace{0.4cm}which \\hspace{0.4cm}is \\hspace{0.4cm} > 0\\\\", initValue, midValue, (function.calculate(midValue) * function.calculate(initValue)));
                initValue = midValue;
               // oldMidValue = midValue;
                output += String.format("\\hspace{1.4cm} f(x_m)f(x_u)\\hspace{0.4cm}=\\hspace{0.4cm}f(%.5f)*f(%.5f) = \\hspace{0.4cm}%.18f \\hspace{0.4cm}which \\hspace{0.4cm}is \\hspace{0.4cm} < 0\\\\", midValue, finValue, function.calculate(midValue) * function.calculate(finValue));
                output += String.format("\\hspace{1.4cm} Therefore\\hspace{0.4cm} a \\hspace{0.4cm} solution\\hspace{0.4cm} exists\\hspace{0.4cm}  between \\hspace{0.4cm} %.10f  \\hspace{0.4cm} and\\hspace{0.4cm}  %.10f \\\\", midValue, finValue);

                output += String.format("\\hspace{1.4cm} Testing \\hspace{0.4cm} with \\hspace{0.4cm} new \\hspace{0.4cm} constraints\\hspace{0.4cm}:\\hspace{0.4cm}  %.10f,\\hspace{0.4cm}  %.10f\\\\", initValue, finValue);
                iteration+=1;

            }

        }
        if (function.calculate(midValue) == 0 )
            output += String.format("We\\hspace{0.4cm}have\\hspace{0.4cm}reached\\hspace{0.4cm}a \\hspace{0.4cm} solution,\\hspace{0.4cm}the\\hspace{0.4cm}  value \\hspace{0.4cm} of \\hspace{0.4cm} the\\hspace{0.4cm}  root \\hspace{0.4cm}is \\hspace{0.4cm}:\\hspace{0.4cm} %.10f\\\\"
                    , finValue);
        else
            output += String.format("Since \\hspace{0.4cm}stopping\\hspace{0.4cm}criteria\\hspace{0.4cm}\\varepsilon = %.4f\\hspace{0.4cm} has\\hspace{0.4cm}been\\hspace{0.4cm}met,\\hspace{0.4cm}the\\hspace{0.4cm}  value \\hspace{0.4cm} of \\hspace{0.4cm} root \\hspace{0.4cm}is \\hspace{0.4cm}:\\hspace{0.4cm} %.10f\\\\"
                    ,epsilon, finValue);
        output += "\\end{array}";
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();
    }

    public double calcPercentError(double x2, double x1 ){
        output+= ("\\hspace{1.4cm}Calculating\\hspace{0.4cm} absolute\\hspace{0.4cm}relative \\hspace{0.4cm}approximate\\hspace{0.4cm} error\\hspace{0.4cm}to \\hspace{0.4cm}check\\hspace{0.4cm}if\\hspace{0.4cm}we\\hspace{0.4cm}have\\hspace{0.4cm}met\\hspace{0.4cm}stopping\\hspace{0.4cm}criteria  \\\\");
        output += "\\hspace{1.4cm} \\left|\\varepsilon_a\\right| = \\left|\\frac{{x_m}^{new} \\hspace{0.4cm}- \\hspace{0.4cm}{x_m}^{old}}{{x_m}^{new}}\\right| \\\\";
        double abs = Math.abs(((x2 - x1) / x2));
        output+= String.format("\\hspace{1.4cm} \\left|\\frac{(%.15f - %.15f)}{%.15f}\\right|  = %.10f \\\\", x2,x1,x2 , abs);
        output+="\\\\" ;
        output+="\\\\" ;
        return abs;
    }

    public void goBack() {
       Main.window.setScene(Main.homepage);
    }
}
