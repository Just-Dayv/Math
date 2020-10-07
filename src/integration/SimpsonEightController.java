package integration;

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

public class SimpsonEightController {

    @FXML
    private TextField equation;
    @FXML
    private TextField segments;
    @FXML
    private TextField upperVal;
    @FXML
    private TextField lowerVal;

    @FXML
    private Button calculateButton;

    @FXML
    private Label errorLabel;

    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]{0,9})?");

    Pattern intValidation = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9])?");

    @FXML
    public void initialize() {

        CornerRadii cornerRadii = new CornerRadii(3);
        calculateButton.setDisable(true);
        BorderStroke borderStroke = new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID,cornerRadii,BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);

        calculateButton.setDisable(true);
        upperVal.setPromptText(">initial value");
        segments.setPromptText("<= 50");
        lowerVal.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    lowerVal.setText(oldValue);
                }

                if (!upperVal.getText().isEmpty() && !lowerVal.getText().isEmpty()&& !segments.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(upperVal.getText());
                    double initialVal = Double.parseDouble(lowerVal.getText());
                    double accuracyVal = Double.parseDouble(segments.getText());
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal)&& accuracyVal <= 50 && accuracyVal >= 2)){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);

                if (!upperVal.getText().isEmpty() && !lowerVal.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(upperVal.getText());
                    double initialVal = Double.parseDouble(lowerVal.getText());

                    if ((finalVal > initialVal)){
                        errorLabel.setText("");
                        lowerVal.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                        lowerVal.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else{
                        errorLabel.setText("Check that your upper value is greater than your lower boundary value");
                        lowerVal.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        lowerVal.setBorder(border);
                        calculateButton.setDisable(true);
                    }
                }
            }
        });
        upperVal.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    upperVal.setText(oldValue);
                }

                if (!upperVal.getText().isEmpty() && !lowerVal.getText().isEmpty()&& !segments.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(upperVal.getText());
                    double initialVal = Double.parseDouble(lowerVal.getText());
                    double accuracyVal = Double.parseDouble(segments.getText());
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal)&& accuracyVal <= 50 && accuracyVal >= 2)){
                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);

                if (!upperVal.getText().isEmpty() && !lowerVal.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(upperVal.getText());
                    double initialVal = Double.parseDouble(lowerVal.getText());

                    if ((finalVal > initialVal)){
                        errorLabel.setText("");
                        lowerVal.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                        lowerVal.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else{
                        errorLabel.setText("Check that your upper value is greater than your lower  boundary  value");
                        lowerVal.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                        lowerVal.setBorder(border);
                        calculateButton.setDisable(true);
                    }
                }

            }
        });
        segments.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!intValidation.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    segments.setText(oldValue);
                }

                if (!upperVal.getText().isEmpty() && !lowerVal.getText().isEmpty()&& !segments.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(upperVal.getText());
                    double initialVal = Double.parseDouble(lowerVal.getText());
                    double accuracyVal = Double.parseDouble(segments.getText());
                    Function function = new Function(equation.getText());

                    if ((function.checkSyntax()&& (finalVal > initialVal)&& accuracyVal <= 50 && accuracyVal>= 2)){

                        calculateButton.setDisable(false);
                    }
                    else
                        calculateButton.setDisable(true);

                }
                else
                    calculateButton.setDisable(true);

                if (!segments.getText().isEmpty()) {
                    double accuracyVal = Double.parseDouble(segments.getText());

                    if (accuracyVal <= 50 && accuracyVal>= 2 ){
                        errorLabel.setText("");
                        segments.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                        segments.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else{
                        errorLabel.setText("Check that your value for the number of segments is between 2 and 50");
                        segments.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                        segments.setBorder(border);
                        calculateButton.setDisable(true);
                    }
                }


            }
        });
        equation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!upperVal.getText().isEmpty() && !lowerVal.getText().isEmpty()&& !segments.getText().isEmpty()&& !equation.getText().isEmpty()) {
                    double finalVal = Double.parseDouble(upperVal.getText());
                    double initialVal = Double.parseDouble(lowerVal.getText());
                    Function function = new Function(equation.getText());
                    double accuracyVal = Double.parseDouble(segments.getText());
                    if ((function.checkSyntax()&& (finalVal > initialVal)&& accuracyVal <= 50 && accuracyVal >= 2)){

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
        String output = "\\begin{array}{l}";
        int segmentNo = Integer.parseInt(segments.getText());
        double upperBound = Double.parseDouble(upperVal.getText());
        double lowerBound = Double.parseDouble(lowerVal.getText());
        Function function = new Function(equation.getText());
        double height = Math.abs(upperBound - lowerBound)/segmentNo;

        int oddCounter = 0;
        int evenCounter = 0;
        if (mod(segmentNo,2)== 0)
        {
            oddCounter = segmentNo-1;
            evenCounter = segmentNo -2;
            output+= "I = \\frac{3h}{8}\\left[f(x_0)+ 3\\left(\\sum\\limits_{i= 1,4,7...}^{n-2}f(x_i)\\right)+ 3\\left(\\sum\\limits_{i= 2,5,8}^{n-1}f(x_i)\\right)+2\\left(\\sum\\limits_{i= 3,6,9}^{n-3}f(x_i)\\right)+ f(x_n) \\right]\\\\";

        }



        output+= String.format("\\hspace{1.4cm}n = %d, a = %.3f, b = %.4f \\\\",segmentNo, upperBound,lowerBound);
        output+="\\\\";
        output += String.format("\\hspace{1.4cm}h =\\frac{b-a}{n} = \\frac{%.3f-%.3f}{(%d)} = %.2f\\\\ ",upperBound,lowerBound,segmentNo,height);
        output+="\\\\";
        output+="Then:\\\\ ";

        output+="\\\\";

        double integral = function.calculate(upperBound)+ function.calculate(lowerBound);


        output+=  String.format("I = \\frac{3(%.3f)}{8}\\left[f(%.3f)+ 3 \\left(\\sum\\limits_{i= 1,4...}^{%d}f(x_i)\\right)+3\\left(\\sum\\limits_{i= 2,5..}^{%d}f(x_i)\\right)+2\\left(\\sum\\limits_{i= 3,6..}^{%d}f(x_i)\\right)+ f(%.3f) \\right]\\\\",
               height, lowerBound,segmentNo-2,segmentNo-1,segmentNo-3,upperBound);

        output+="\\\\";


        output+= String.format("\\hspace{1.4cm}= \\frac{%.3f}{8}\\left[f(%.3f)+",(3*height),lowerBound);


        for (int i = 1; i < segmentNo-2; i+=3)
        {

            output+=String.format("3f(x_{%d})+",i);
        }
        for (int i = 2; i < segmentNo; i+=3)
        {
            output+=String.format("3f(x_{%d})+",i);
        }

        for (int i = 3; i < segmentNo; i+=3)
        {
            output+=String.format("3f(x_{%d})+",i);
        }
        output+=String.format("f(%.4f)\\right]\\\\",upperBound);

        output+="\\\\";


        output+= String.format("\\hspace{1.4cm}= \\frac{%.3f}{8}\\left[f(%.3f)+",(3*height),lowerBound);


        for (int i = 1; i < segmentNo; i+=3)
        {
            output+=String.format("3f(%.4f)+",lowerBound+ i* height);
        }
        for (int i = 2; i < segmentNo; i+=3)
        {
            output+=String.format("3f(%.4f)+",lowerBound+ i* height);
        }
        for (int i = 3; i < segmentNo; i+=3)
        {
            output+=String.format("2f(%.4f)+",lowerBound+ i* height);
        }

        output+=String.format("f(%.4f)\\right]\\\\",upperBound);

        output+="\\\\";
        output+= String.format("\\hspace{1.4cm}= \\frac{%.3f}{8}\\left[%.3f+",(3*height),function.calculate(lowerBound));


        for (int i = 1; i < segmentNo; i+=3)
        {
            integral += 3 * function.calculate(lowerBound+ i* height );
            output+=String.format("3(%.4f)+",function.calculate(lowerBound+ i* height) );


        }
        for (int i = 2; i < segmentNo; i+=3   )
        {
            integral += 3 * function.calculate(lowerBound+ i* height );
            output+=String.format("3(%.4f)+",function.calculate(lowerBound+ i* height) );

        }
        for (int i = 3; i < segmentNo; i+=3   )
        {
            integral += 2 * function.calculate(lowerBound+ i* height );
            output+=String.format("2(%.4f)+",function.calculate(lowerBound+ i* height) );

        }
        output+=String.format("%.4f\\right]\\\\",function.calculate(upperBound));

        output+="\\\\";
        integral = integral*( Math.abs(3*height)/(8));
        output+= String.format("\\hspace{2.8cm}=%.5f\\\\",integral);


        output+= "\\end{array}";
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();


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

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }
}
