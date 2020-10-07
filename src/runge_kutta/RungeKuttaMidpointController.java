package runge_kutta;

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
import org.mariuszgromada.math.mxparser.Function;

import java.util.regex.Pattern;

public class RungeKuttaMidpointController {
    @FXML
    private TextField initialValue;
    @FXML
    private TextField finalValue;
    @FXML
    private TextField equation;
    @FXML
    private TextField stepSize;

    @FXML
    private Button calculateButton;

    private String output;
    @FXML
    private Label inputError;

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
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    initialValue.setText(oldValue);
                }
                Function function = new Function("");
                double finalVal = 0;
                double step = 0;
                if (!finalValue.getText().isEmpty())
                    finalVal = Double.parseDouble(finalValue.getText());
                else
                    calculateButton.setDisable(true);

                if (!stepSize.getText().isEmpty())
                    step = Double.parseDouble(stepSize.getText());
                else
                    calculateButton.setDisable(true);
                if  ( !equation.getText().isEmpty())
                    function = new Function(equation.getText());
                else
                    calculateButton.setDisable(true);

                if (!finalValue.getText().isEmpty()&& !stepSize.getText().isEmpty() )
                {
                    if (mod(finalVal,step)==0)
                        if (function.checkSyntax() && !initialValue.getText().isEmpty())

                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);

                    else
                        calculateButton.setDisable(true);
                }

                if (!equation.getText().isEmpty())
                {
                    if (function.checkSyntax()){
                        if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&&mod(finalVal,step)==0)
                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);
                    }
                    else
                        calculateButton.setDisable(true);
                }
                else
                    calculateButton.setDisable(true);


//                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&& !equation.getText().isEmpty()) {
//                    double  finalVal = Double.parseDouble(finalValue.getText());
//                    double step = Double.parseDouble(stepSize.getText());
//                    Function function = new Function(equation.getText());
//                    if (mod(finalVal,step)==0 && function.checkSyntax()){
//                        calculateButton.setDisable(false);
//                    }
//                    else
//                        calculateButton.setDisable(true);
//                }
//                else {
//                    calculateButton.setDisable(true);
//                }

            }

        });
        equation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                Function function = new Function("");
                double finalVal = 0;
                double step = 0;
                if (!finalValue.getText().isEmpty())
                    finalVal = Double.parseDouble(finalValue.getText());
                else
                    calculateButton.setDisable(true);

                if (!stepSize.getText().isEmpty())
                    step = Double.parseDouble(stepSize.getText());
                else
                    calculateButton.setDisable(true);
                if  ( !equation.getText().isEmpty())
                    function = new Function(equation.getText());
                else
                    calculateButton.setDisable(true);

                if (!finalValue.getText().isEmpty()&& !stepSize.getText().isEmpty() )
                {
                    if (mod(finalVal,step)==0)
                        if (function.checkSyntax() && !initialValue.getText().isEmpty())
                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);

                    else
                        calculateButton.setDisable(true);
                }

                if (!equation.getText().isEmpty())
                {
                    if (function.checkSyntax()){
                        if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&&mod(finalVal,step)==0)
                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);
                    }
                    else
                        calculateButton.setDisable(true);
                }
                else
                    calculateButton.setDisable(true);

                if (!equation.getText().isEmpty()) {
                    // double accuracyVal = Double.parseDouble(accuracy.getText());
                    if ((function.checkSyntax())) {
                        inputError.setText("");
                        equation.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                        equation.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else
                    {
                        inputError.setText("Check your function");
                        equation.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        equation.setBorder(border);
                        calculateButton.setDisable(true);
                    }

                }
                else
                    calculateButton.setDisable(true);



//                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&& !equation.getText().isEmpty()) {
//                    double  finalVal = Double.parseDouble(finalValue.getText());
//                    double step = Double.parseDouble(stepSize.getText());
//                    Function function = new Function(equation.getText());
//                    if (mod(finalVal,step)==0 && function.checkSyntax()){
//                        calculateButton.setDisable(false);
//                    }
//                    else
//                        calculateButton.setDisable(true);
//                }
//                else {
//                    calculateButton.setDisable(true);
//                }

            }
        });
        finalValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                Function function = new Function("");
                double finalVal = 0;
                double step = 0;
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    finalValue.setText(oldValue);
                }
                if (!finalValue.getText().isEmpty())
                    finalVal = Double.parseDouble(finalValue.getText());
                else
                    calculateButton.setDisable(true);
                if (!stepSize.getText().isEmpty())
                    step = Double.parseDouble(stepSize.getText());
                else
                    calculateButton.setDisable(true);
                if  ( !equation.getText().isEmpty())
                    function = new Function(equation.getText());
                else
                    calculateButton.setDisable(true);

                if (!finalValue.getText().isEmpty()&& !stepSize.getText().isEmpty() )
                {
                    if (mod(finalVal,step)==0&& finalVal/step <=50 )
                    {
                        stepSize.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                        stepSize.setBorder(border);
                        inputError.setText("");
                        if (function.checkSyntax() && !initialValue.getText().isEmpty())

                        {
                            calculateButton.setDisable(false);
                        }
                        else
                            calculateButton.setDisable(true);
                    }
                    else
                    {
                        stepSize.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                        inputError.setText("Your step size and initial value may be incompatible\nFinal value of X should be exactly divisible by step size\nCheck that number of iterations is also less than 100");
                        calculateButton.setDisable(true);
                    }

                }

                if (!equation.getText().isEmpty())
                {
                    if (function.checkSyntax()){
                        if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&&mod(finalVal,step)==0)

                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);
                    }
                    else
                        calculateButton.setDisable(true);
                }
                else
                    calculateButton.setDisable(true);

//                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&& !equation.getText().isEmpty()) {
//                    double  finalVal = Double.parseDouble(finalValue.getText());
//                    double step = Double.parseDouble(stepSize.getText());
//                    Function function = new Function(equation.getText());
//                    if (mod(finalVal,step)==0 && function.checkSyntax()){
//                        calculateButton.setDisable(false);
//                    }
//                    else
//                        calculateButton.setDisable(true);
//                }
//                else {
//                    calculateButton.setDisable(true);
//                }
            }

        });


        stepSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    stepSize.setText(oldValue);
                }
                Function function = new Function("");
                double finalVal = 0;
                double step = 0;
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    initialValue.setText(oldValue);
                }
                if (!finalValue.getText().isEmpty())
                    finalVal = Double.parseDouble(finalValue.getText());
                else
                    calculateButton.setDisable(true);
                if (!stepSize.getText().isEmpty())
                    step = Double.parseDouble(stepSize.getText());
                else
                    calculateButton.setDisable(true);
                if  ( !equation.getText().isEmpty())
                    function = new Function(equation.getText());
                else
                    calculateButton.setDisable(true);

                if (!finalValue.getText().isEmpty()&& !stepSize.getText().isEmpty() )
                {
                    if (mod(finalVal,step)==0&& finalVal/step <=50 )
                    {
                        stepSize.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                        stepSize.setBorder(border);
                        inputError.setText("");
                        if (function.checkSyntax() && !initialValue.getText().isEmpty())
                        {
                            calculateButton.setDisable(false);
                        }
                        else
                            calculateButton.setDisable(true);
                    }
                    else
                    {
                        stepSize.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        stepSize.setBorder(border);
                        inputError.setText("Your step size and initial value may be incompatible\nFinal value of x should be exactly divisible by step size\nCheck that number of iterations is also less than 100");
                        calculateButton.setDisable(true);
                    }

                }

                if (!equation.getText().isEmpty())
                {
                    if (function.checkSyntax()){
                        if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&&mod(finalVal,step)==0)
                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);
                    }
                    else
                        calculateButton.setDisable(true);
                }
                else
                    calculateButton.setDisable(true);
//                if (!finalValue.getText().isEmpty() && !initialValue.getText().isEmpty()&& !stepSize.getText().isEmpty()&& !equation.getText().isEmpty()) {
//                    double  finalVal = Double.parseDouble(finalValue.getText());
//                    double step = Double.parseDouble(stepSize.getText());
//                    Function function = new Function(equation.getText());
//                    if (mod(finalVal,step)==0 && function.checkSyntax()){
//                        calculateButton.setDisable(false);
//                    }
//                    else
//                        calculateButton.setDisable(true);
//                }
//                else {
//                    calculateButton.setDisable(true);
//                }
            }
        });
    }
    public void startKutta(ActionEvent actionEvent) {

        double yValue = Double.parseDouble(initialValue.getText());
        double xValue = Double.parseDouble(finalValue.getText());
        double stepGap = Double.parseDouble(stepSize.getText());
        String eqn = equation.getText();
         output = "\\begin{array}{l}";

        output += "y_{i+1} = y_i + f(x,y) * step size\\\\";

        int iterations = (int) (xValue / stepGap);

        for (int i = 0; i < iterations; i++) {

            output+="\\\\";
            output+="\\\\";
            output += "For\\hspace{0.4cm} iteration\\hspace{0.8cm} " + i + " :\\\\";
            output+="\\\\";
            output += String.format("\\hspace{1.5cm}y_{%d} = y_{%d} + f(x_{%d},y_{%d}) * stepSize\\\\", i + 1, i, i, i);
            output += String.format("\\hspace{1.5cm}y_{%d} = %.2f + f(%.2f,%.2f) * %.2f\\\\", i + 1, yValue, i * stepGap, yValue, stepGap);
            yValue = calculateRk2Midpoint(stepGap * i, yValue, eqn,i, stepGap);
            output += String.format("\\hspace{1.5cm}y_{%d} = %.4f\\\\", i+1, yValue);


        }
        output += "\\end{array}";
        // Main.lateXMathControl.setFormula(output);
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();
    }
    public double calculateRk2Midpoint(double xValue, double yValue,String eqn, int i,double stepSize ){
        Function function = new Function(eqn);
        double k1 = function.calculate(xValue,yValue);
        output+= String.format ("\\hspace{1.5cm}k_1 = f(x,y) = f(%.4f,%.4f) = %.4f\\\\",xValue,yValue,k1);

        double k2 = function.calculate(xValue+stepSize*0.5, yValue+ 0.5*k1*stepSize);
       output+=String.format("\\hspace{1.5cm}k_2 = f(x + 0.5*h, y + 0.5*k_1*h) = f(%.4f,%.4f) = %.4f\\\\",xValue+stepSize*0.5, yValue+ 0.5*k1*stepSize,k2);

      output+=  String.format("\\hspace{1.5cm}y_{%d} = %.2f +  %.2f * %.2f\\\\", i+1, yValue,k2,stepSize);

        return yValue + stepSize*(k2);
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }

    public double mod(double x, double y){
        double quot = Math.floor(x/y);
        System.out.println("quot "+ quot);
        quot = x - ( quot * y);
        System.out.println(quot);
        if (quot < 0.0001)
        return 0;
        else return quot;
    }


}
