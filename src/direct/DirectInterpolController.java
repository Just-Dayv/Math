package direct;

import Jama.Matrix;
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

public class DirectInterpolController {
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
                    int spinnerVal = (int) spinnerValue.getValue();
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

                    if (!variableValues.getText().isEmpty()&& spinnerVal== getVariables.length){
                        if (convArray(getVariables))
                            if (spinnerVal== getSolutions.length &&convArray(getSolutions)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }

                    if (!solutions.getText().isEmpty()&& spinnerVal== getSolutions.length){
                        if (convArray(getVariables))
                            if (spinnerVal== getVariables.length &&convArray(getVariables)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }


                    if (!interpol.getText().isEmpty())
                        if (spinnerVal== getSolutions.length && spinnerVal== getVariables.length&&
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
                    int spinnerVal = (int) spinnerValue.getValue();
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

                    if (!variableValues.getText().isEmpty()&& spinnerVal== getVariables.length){
                        if (convArray(getVariables))
                            if (spinnerVal== getSolutions.length &&convArray(getSolutions)&& validateInBetween(getVariables,interpolValue))
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

                    if (!solutions.getText().isEmpty()&& spinnerVal== getSolutions.length){
                        if (convArray(getVariables))
                            if (spinnerVal== getVariables.length &&convArray(getVariables)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }


                    if (!interpol.getText().isEmpty())
                        if (spinnerVal== getSolutions.length && spinnerVal== getVariables.length&&
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
                    int spinnerVal = (int) spinnerValue.getValue();
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

                    if (!variableValues.getText().isEmpty()&& spinnerVal== getVariables.length){
                        if (convArray(getVariables))
                            if (spinnerVal== getSolutions.length &&convArray(getSolutions)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }

                    if (!solutions.getText().isEmpty()&& spinnerVal== getSolutions.length){
                        if (convArray(getVariables))
                            if (spinnerVal== getVariables.length &&convArray(getVariables)&& validateInBetween(getVariables,interpolValue))
                                calculateButton.setDisable(false);
                            else
                                calculateButton.setDisable(true);

                        else
                            calculateButton.setDisable(true);
                    }


                    if (!interpol.getText().isEmpty())
                        if (spinnerVal== getSolutions.length && spinnerVal== getVariables.length&&
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
                    int spinnerVal = (int) spinnerValue.getValue();
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

                    if (!variableValues.getText().isEmpty()&& spinnerVal== getVariables.length){
                        if (convArray(getVariables)) {

                            calculateButton.setDisable(false);
                        }
                        else {
                            calculateButton.setDisable(true);
                        }
                        if (spinnerVal== getSolutions.length &&convArray(getSolutions)&& validateInBetween(getVariables,interpolValue)) {
                        }

                        else {
                            calculateButton.setDisable(true);
                        }
                    }

                    if (!solutions.getText().isEmpty()&& spinnerVal== getSolutions.length){
                        if (convArray(getVariables)) {

                            calculateButton.setDisable(false);
                        }
                        else {
                            calculateButton.setDisable(true);
                        }
                        if (spinnerVal== getVariables.length &&convArray(getVariables)&& validateInBetween(getVariables,interpolValue)) {
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
                        if (spinnerVal== getSolutions.length && spinnerVal== getVariables.length&&
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

    public boolean validateInBetween(String [] arr, double a)
    {
        double[] array = new double[arr.length];
        try {
            for (int i = 0; i < arr.length; i++){
                array[i] = Double.parseDouble(arr[i]);
            }

            Arrays.sort(array);

            double [][] constCoeff = new double[arr.length][arr.length];
            for (int i = 0; i < constCoeff.length; i++){
                constCoeff[i][0] = 1;
                for (int j = 1; j < constCoeff.length; j++){
                    constCoeff[i][j] = Math.pow(array[i],j);
                } }

            Matrix constant = new Matrix(constCoeff);

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
        double [][] constCoeff = new double[variableValues.length][variableValues.length];
        double [][] solutionValues = new double[variableValues.length][];
        double interpol = Double.parseDouble(this.interpol.getText());
        int spinnerVal = (int) spinnerValue.getValue();

        output+= String.format("For\\hspace{0.4cm} order\\hspace{0.4cm} %d \\hspace{0.4cm}interpolation\\\\", spinnerVal-1);
        output+="\\\\";
        for (int i = 0; i < constCoeff.length; i++){
            solutionValues[i] = new double[]{solutions[i]};
            constCoeff[i][0] = 1;
            output+= String.format("\\hspace{1.5cm}f(%.2f)= a_0", variableValues[i]);
            for (int j = 1; j < constCoeff.length; j++){
                output+= String.format("+a_{%d}(%.2f)^{%d} ",j,variableValues[i],j);
                constCoeff[i][j] = Math.pow(variableValues[i],j);
            }
            output+= String.format("= %.4f",solutions[i]);
            output+="\\\\";
            output+="\\\\";
        }

        Matrix matrixCoeff = new Matrix(constCoeff);
        matrixCoeff.print(9,8);
        Matrix matrixSolution = new Matrix(solutionValues);
        Matrix result = matrixCoeff.solve(matrixSolution);
        double [][] resultArray = result.getArray();

        output+="\\hspace{1.5cm}Solving\\hspace{0.4cm} Simultaneously\\hspace{0.4cm}for\\hspace{0.4cm} our\\hspace{0.4cm} constant\\hspace{0.4cm} coefficients:\\\\ ";
        output+="\\\\";
        for (int i = 0; i < resultArray.length; i ++){
            output+=String.format("\\hspace{1.5cm}\\hspace{1.5cm} a_{%d} = %.4f",i,resultArray[i][0]);
            output+="\\\\";
        }

        output+="\\\\";
        output += String.format( "\\hspace{1.5cm}f(x) = %.4f",resultArray[0][0]);
        for (int i = 1; i < resultArray.length; i++){
            output+= String.format("+ %.4f(x)^{%d}", resultArray[i][0],i);
        }
        output+="\\\\";
        output+="\\\\";
        output += String.format( "\\hspace{1.5cm}f(%.2f) = %.4f",interpol,resultArray[0][0]);
        for (int i = 1; i < resultArray.length; i++){
            output+= String.format("+ %.4f(%.2f)^{%d}", resultArray[i][0],interpol,i);
        }
        output+="\\\\";
        double value = 0;
        output+="\\\\";
        for (int i = 0; i < resultArray.length; i++){
            value+= Math.pow(interpol,i)* resultArray[i][0];
        }
        output+= String.format("\\hspace{1.5cm}f(%.2f) = %.4f",interpol,value);
        output+="\\\\";
        output+= "\\end{array}";
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }
}
