package Lagrange;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.regex.Pattern;

public class LagrangeInterpolController {

    @FXML
    private Button calculateButton;
    @FXML
    private TextField variableValues;
    @FXML
    private Spinner spinnerValue;

    @FXML
    private TextField interpol;



    @FXML
    private TextField solutions;

    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]{0,4})?");
    @FXML
    private Label errorLabel;

    public void initialize(){

        Insets insets = new Insets(5,8.7,5,8.7);
        CornerRadii cornerRadii = new CornerRadii(3);
        calculateButton.setDisable(true);
        BorderStroke borderStroke = new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID,cornerRadii,BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);

        spinnerValue.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                    int spinnerValue = (int) LagrangeInterpolController.this.spinnerValue.getValue();
                    String [] getVariables = new String[]{};
                    String [] getSolutions = new String[]{};
                    double interpolValue = 0;
                    if (!variableValues.getText().isEmpty())
                    {
                        getVariables = variableValues.getText().split(",");
                    }
                    if (!solutions.getText().isEmpty())
                        getSolutions = solutions.getText().split(",");
                    if (!interpol.getText().isEmpty())
                        interpolValue = Double.parseDouble(interpol.getText());

                    if (!variableValues.getText().isEmpty()&& spinnerValue== getVariables.length){
                        if (convArray(getVariables))
                            if (spinnerValue== getSolutions.length &&convArray(getSolutions)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }

                    if (!solutions.getText().isEmpty()&& spinnerValue== getSolutions.length){
                        if (convArray(getVariables))
                            if (spinnerValue== getVariables.length &&convArray(getVariables)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }


                    if (!interpol.getText().isEmpty())
                        if (spinnerValue== getSolutions.length && spinnerValue== getVariables.length&&
                                convArray(getSolutions)&&convArray(getVariables)&& validateInBetween(getVariables,interpolValue) )
                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);

                    else calculateButton.setDisable(true);

                }
                catch (Exception e){
                    calculateButton.setDisable(true);
                }



            }
        });

        variableValues.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    int spinnerValue = (int) LagrangeInterpolController.this.spinnerValue.getValue();
                    String [] getVariables = new String[]{};
                    String [] getSolutions = new String[]{};
                    double interpolValue = 0;
                    if (!variableValues.getText().isEmpty())
                    {
                       getVariables = variableValues.getText().split(",");
                    }
                    if (!solutions.getText().isEmpty())
                        getSolutions = solutions.getText().split(",");
                    if (!interpol.getText().isEmpty())
                        interpolValue = Double.parseDouble(interpol.getText());

                    if (!variableValues.getText().isEmpty()&& spinnerValue== getVariables.length){
                        if (convArray(getVariables))
                            if (spinnerValue== getSolutions.length &&convArray(getSolutions)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                            else
                                calculateButton.setDisable(true);
                    }

                    if (!variableValues.getText().isEmpty()){
                        if (convArray(getVariables))
                        {
                            errorLabel.setText("");
                            variableValues.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                            variableValues.setBorder(border);

                        }
                        else
                        {
                            errorLabel.setText("Check that your values are comma separated decimals");
                            variableValues.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                            variableValues.setBorder(border);
                            calculateButton.setDisable(true);
                        }
                    }

                    if (!solutions.getText().isEmpty()&& spinnerValue== getSolutions.length){
                        if (convArray(getVariables))
                            if (spinnerValue== getVariables.length &&convArray(getVariables)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }


                    if (!interpol.getText().isEmpty())
                        if (spinnerValue== getSolutions.length && spinnerValue== getVariables.length&&
                                convArray(getSolutions)&&convArray(getVariables)&& validateInBetween(getVariables,interpolValue) )
                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);

                        else calculateButton.setDisable(true);

                    if (!interpol.getText().isEmpty())
                    {
                        if ( validateInBetween(getVariables,interpolValue) )
                        {
                            interpol.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                            errorLabel.setText("");

                            //calculateButton.setDisable(false);
                        }
                        else
                        {
                            interpol.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                            errorLabel.setText("Check that the value is within the range of values specified");
                            calculateButton.setDisable(true);
                        }
                    }
                    else calculateButton.setDisable(true);

                }
                catch (Exception e){
                    calculateButton.setDisable(true);
                }

            }
        });

        interpol.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    interpol.setText(oldValue);
                }

                try{
                    int spinnerValue = (int) LagrangeInterpolController.this.spinnerValue.getValue();
                    String [] getVariables = new String[]{};
                    String [] getSolutions = new String[]{};
                    double interpolValue = 0;
                    if (!variableValues.getText().isEmpty())
                    {
                        getVariables = variableValues.getText().split(",");
                    }
                    if (!solutions.getText().isEmpty())
                        getSolutions = solutions.getText().split(",");
                    if (!interpol.getText().isEmpty())
                        interpolValue = Double.parseDouble(interpol.getText());

                    if (!variableValues.getText().isEmpty()&& spinnerValue== getVariables.length){
                        if (convArray(getVariables))
                            if (spinnerValue== getSolutions.length &&convArray(getSolutions)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }

                    if (!solutions.getText().isEmpty()&& spinnerValue== getSolutions.length){
                        if (convArray(getVariables))
                            if (spinnerValue== getVariables.length &&convArray(getVariables)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }


                    if (!interpol.getText().isEmpty())
                        if (spinnerValue== getSolutions.length && spinnerValue== getVariables.length&&
                                convArray(getSolutions)&&convArray(getVariables)&& validateInBetween(getVariables,interpolValue) )
                        {
                            calculateButton.setDisable(false);
                        }
                        else
                        {
                            calculateButton.setDisable(true);
                        }

                    else calculateButton.setDisable(true);

                    if (!interpol.getText().isEmpty())
                    {
                        if ( validateInBetween(getVariables,interpolValue) )
                        {
                            interpol.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                            errorLabel.setText("");
                            interpol.setBorder(border);

                            //calculateButton.setDisable(false);
                        }
                        else
                        {
                            interpol.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                            errorLabel.setText("Check that the value is within the range of values specified");
                            interpol.setBorder(border);
                            calculateButton.setDisable(true);
                        }
                    }
                    else calculateButton.setDisable(true);

                }
                catch (Exception e){
                    calculateButton.setDisable(true);
                }

            }
        });
        solutions.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    int spinnerValue = (int) LagrangeInterpolController.this.spinnerValue.getValue();
                    String [] getVariables = new String[]{};
                    String [] getSolutions = new String[]{};
                    double interpolValue = 0;
                    if (!variableValues.getText().isEmpty())
                    {
                        getVariables = variableValues.getText().split(",");
                    }
                    if (!solutions.getText().isEmpty()) {
                        getSolutions = solutions.getText().split(",");
                    }
                    if (!interpol.getText().isEmpty()) {
                        interpolValue = Double.parseDouble(interpol.getText());
                    }

                    if (!variableValues.getText().isEmpty()&& spinnerValue== getVariables.length){
                        if (convArray(getVariables)) {

                            calculateButton.setDisable(false);
                        }
                            else {
                            calculateButton.setDisable(true);
                        }
                        if (spinnerValue== getSolutions.length &&convArray(getSolutions)&& validateInBetween(getVariables,interpolValue)) {
                        }

                        else {
                            calculateButton.setDisable(true);
                        }
                    }

                    if (!solutions.getText().isEmpty()&& spinnerValue== getSolutions.length){
                        if (convArray(getVariables)) {

                            calculateButton.setDisable(false);
                        }
                            else {
                            calculateButton.setDisable(true);
                        }
                        if (spinnerValue== getVariables.length &&convArray(getVariables)&& validateInBetween(getVariables,interpolValue)) {
                        }

                        else {
                            calculateButton.setDisable(true);
                        }
                    }


                    if (!solutions.getText().isEmpty()){
                        if (convArray(getSolutions))
                        {
                            errorLabel.setText("");
                            solutions.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                            solutions.setBorder(border);

                        }
                        else
                        {
                            errorLabel.setText("Check that your values are comma separated decimals");
                            solutions.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                            solutions.setBorder(border);
                            calculateButton.setDisable(true);
                        }
                    }


                    if (!interpol.getText().isEmpty())
                        if (spinnerValue== getSolutions.length && spinnerValue== getVariables.length&&
                                convArray(getSolutions)&&convArray(getVariables)&& validateInBetween(getVariables,interpolValue) )
                            calculateButton.setDisable(false);
                        else
                            calculateButton.setDisable(true);

                    else calculateButton.setDisable(true);

                }
                catch (Exception e){
                    calculateButton.setDisable(true);
                }

            }

        });
    }

    private String output;
    public void calculate() {
        output = "\\begin{array}{l}";
        String [] variableVals = this.variableValues.getText().split(",");
        String [] solutionString = this.solutions.getText().split(",");
        double [] variableValues = new double[variableVals.length];
        double [] solutions = new double[variableVals.length];
        for (int i = 0; i < variableValues.length; i++){
            variableValues[i] = Double.parseDouble(variableVals[i]);
            solutions[i] = Double.parseDouble(solutionString[i]);
        }
        double interpol = Double.parseDouble(this.interpol.getText());
        int degree = variableVals.length;
        double [] lagrangeVal = new double[degree];
        Arrays.fill(lagrangeVal,1);
        printFormula(degree);
        output+= String.format("f(%.4f) = ", interpol);
        for (int i = 0; i < degree; i++){
            for (int j = 0; j< degree; j++)
            {
                if (j == i )
                {
                    if (j + 1 < degree)
                    j++;
                    else
                        break;
                }
                lagrangeVal[i] *= Lagrangian(interpol, variableValues[i], variableValues[j]);

            }
            if (i +1 < degree)
            output+= String.format("%.4f + ", solutions[i]);
            else
                output+= String.format("%.4f \\\\", solutions[i]);
            if (i%2 == 0)
                output+="\\\\";
        }
        output+= String.format("f(%.4f) = ", interpol);
        for (int i = 0; i < degree; i ++)
        {
            if (i +1 < degree) {
                output+= String.format("(%.4f  )(%.4f) +", solutions[i],lagrangeVal[i]);
            }
            else {
                output+= String.format("(%.4f )( %.4f)\\\\", solutions[i], lagrangeVal[i]);
            }
            solutions[i] = solutions[i]*lagrangeVal[i];
        }

        double value =0;
        for (int i = 0; i < solutions.length; i++)
            value+=solutions[i];

        output+= String.format("f(%.4f) = %.4f\\\\", interpol, value);

        output+= "\\end{array}";
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();
    }
    public void goBack() {
        Main.window.setScene(Main.homepage);
    }

    public double Lagrangian (double xValue, double currentValue, double otherValue ){

       output += String.format("\\left(\\frac{%.4f -  %.4f}{%.4f - %.4f}\\right) ",xValue,otherValue,currentValue,otherValue);
        return ((xValue -  otherValue)/(currentValue-otherValue));
    }
    public void printFormula (int degree){
        output+= String.format("f(x) = \\sum\\limits_{i= 0}^{%d}L_{i}(x)f(x_i)\\\\", degree-1);
        for (int i = 0; i < degree; i++){
            output += String.format("L_{%d}(x) = \\prod\\limits_{j=%d}^%d \\frac{t-t_j}{t_{%d}-t_j}= ",i,i, degree-1,i);
            for(int j = 0; j < degree; j++){
                if (j == i )
                {
                    if (j + 1 < degree)
                        j++;
                    else
                        break;
                }
                output+= String.format("\\left(\\frac{x -  x_{%d}}{x_{%d} - x_{%d}}\\right)",j,i,j);
            }
            output+="\\\\";
        }
    }

    public boolean convArray(String[] array){
        double[] arr = new double[array.length];
        try {
            for (int i = 0; i < arr.length; i++){
                arr[i] = Double.parseDouble(array[i]);
            }
            return true;
        }
        catch (Exception e ){
            calculateButton.setDisable(true);
            return false;
        }
    }

    public boolean validateInBetween(String [] arr, double a)
    {
        double[] array = new double[arr.length];
        try {
            for (int i = 0; i < arr.length; i++){
                array[i] = Double.parseDouble(arr[i]);
            }

            Arrays.sort(array);
            if (a> array[0] && a < array[array.length-1])
            return true;
            else{

                calculateButton.setDisable(true);
                return false;
            }
        }
        catch (Exception e ){
            calculateButton.setDisable(true);
            return false;
        }
    }
}
