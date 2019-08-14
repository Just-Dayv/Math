package bisection;

//import lib.DifferentialFunction;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    private Label accuracyLabel;

    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]{0,4})?");

    @FXML
    public void initialize() {
        calculateButton.setDisable(true);
        finalValue.setPromptText(">initial value");
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
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal))){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);
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
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal))){
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

                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(finalValue.getText());
                    double initialVal = Double.parseDouble(initialValue.getText());
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal))){

                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);
            }
        });
        equation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !accuracy.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(finalValue.getText());
                    double initialVal = Double.parseDouble(initialValue.getText());
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal))){

                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);
            }
        });
    }

    public void calculate() {
        double initValue = Double.parseDouble(initialValue.getText());
        double epsilon =Double.parseDouble(accuracy.getText());
        Function function = new Function(equation.getText());
        double finValue = Double.parseDouble(finalValue.getText());


        String output = "\\begin{array}{l}";
        double midValue = initValue;
        while ((finValue - initValue) > epsilon) {
            midValue = (initValue + finValue) / 2;

            output += "\\\\";
            output += "\\\\";
            output += String.format("mid value\\hspace{0.4cm}  of\\hspace{0.4cm}  assumption\\hspace{0.4cm}  = \\hspace{0.4cm} %.5f\\\\", midValue);
            output += String.format("f(%.5f)\\hspace{0.4cm}  = \\hspace{0.4cm} %.5f\\\\", midValue, function.calculate(midValue));
            if (function.calculate(midValue) == 0) {
                output += "We\\hspace{0.4cm}  have\\hspace{0.4cm}  reached\\hspace{0.4cm}  solution\\hspace{0.4cm}  to\\hspace{0.4cm}  equation\\\\";
                break;
            } else if ((function.calculate(midValue) * function.calculate(initValue)) < 0) {
                finValue = midValue;
                output += String.format("f(%.5f)*f(%.5f) = \\hspace{0.4cm} %.13f\\hspace{0.4cm}which \\hspace{0.4cm}is \\hspace{0.4cm} < 0\\\\", initValue, midValue, (function.calculate(midValue) * function.calculate(initValue)));
                output += String.format("Therefore\\hspace{0.4cm} a \\hspace{0.4cm} solution\\hspace{0.4cm} exists\\hspace{0.4cm}  between \\hspace{0.4cm} %.5f  \\hspace{0.4cm} and\\hspace{0.4cm}  %.5f \\\\", initValue, midValue);
                output += String.format("Testing\\hspace{0.4cm}  with \\hspace{0.4cm} new \\hspace{0.4cm} constraints\\hspace{0.4cm} :\\hspace{0.4cm}  %.5f, \\hspace{0.4cm} %.5f\\\\", initValue, finValue);

            } else {
                initValue = midValue;
                output += String.format("f(%.5f)*f(%.5f) = \\hspace{0.4cm}%.13f \\hspace{0.4cm}which \\hspace{0.4cm}is \\hspace{0.4cm} < 0\\\\", midValue, finValue, function.calculate(midValue) * function.calculate(finValue));
                output += String.format("Therefore\\hspace{0.4cm} a \\hspace{0.4cm} solution\\hspace{0.4cm} exists\\hspace{0.4cm}  between \\hspace{0.4cm} %.5f  \\hspace{0.4cm} and\\hspace{0.4cm}  %.5f \\\\", midValue, finValue);
                output += String.format("Testing \\hspace{0.4cm} with \\hspace{0.4cm} new \\hspace{0.4cm} constraints\\hspace{0.4cm}:\\hspace{0.4cm}  %.5f,\\hspace{0.4cm}  %.5f\\\\", initValue, finValue);
            }

        }
        output += String.format("The\\hspace{0.4cm}  value \\hspace{0.4cm} of \\hspace{0.4cm} root \\hspace{0.4cm}is \\hspace{0.4cm}:\\hspace{0.4cm} %.5f\\\\"
                , midValue);
        output += "\\end{array}";
        Main.output.set(output);
        Main.window.setScene(Main.outputScene);
    }

    public void goBack() {
       Main.window.setScene(Main.homepage);
    }
}
