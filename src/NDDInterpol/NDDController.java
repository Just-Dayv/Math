package NDDInterpol;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.regex.Pattern;

public class NDDController {

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

    public void initialize(){

        calculateButton.setDisable(true);

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

    private String output;
    public void calculate(){

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
        double [] constVal = Arrays.copyOf(solutions,degree);
        output+="\\\\";
        output+="For\\hspace{0.4cm} first\\hspace{0.4cm} difference :\\\\";
        output+= String.format("b_0 = %.4f\\\\",constVal[0]);
        for (int  i  = 1; i < degree; i++ ){
            output+= String.format("b_{%d} = \\frac{f(x_{%d}) - f(x_{%d})}{x_{%d}-x_{%d}}\\\\",i,i, i-1,i,i-1);
            output+= String.format("b_{%d} = \\frac{%.4f - %.4f}{%.4f-%.4f}\\\\",i,solutions[i], solutions[i-1],variableValues[i],variableValues[i-1]);
            constVal[i]= (solutions[i]-solutions[i-1])/(variableValues[i]-variableValues[i-1]);
            output+= String.format("b_{%d} = %.4f\\\\",i,constVal[i]);
            output+="\\\\";
        }
        for (int i = 2; i < degree; i++){
            output+="\\\\";
            output+="For\\hspace{0.4cm} next\\hspace{0.4cm} difference :\\\\";
            double [] arrayTemp = Arrays.copyOf(constVal,constVal.length);
            for (int k = i,j = 0; k< degree; k++,j++){
                output+= String.format("b_{%d} = \\frac{%.4f - %.4f}{%.4f-%.4f}\\\\",k,constVal[k], constVal[k-1],variableValues[k],variableValues[j]);
                constVal[k]= (constVal[k]-arrayTemp[k-1])/(variableValues[k]-variableValues[j]);
                output+= String.format("b_{%d} = %.4f\\\\",k,constVal[k]);
                output+="\\\\";
            }
        }
        output+="\\\\";
        output+= "Therefore:\\\\";
        output+="\\\\";
        printFormula(degree);
        double finalAnswer = constVal[0];
        output+= String.format("f(x) = %.4f",finalAnswer);
        for (int i = 1; i < degree; i++){
            output+= String.format("+ %.4f",constVal[i]);
            for (int j= 0; j < i; j++ ){
                output+= String.format("(%.4f-%.4f)",interpol,variableValues[j]);
                constVal[i]*= interpol-variableValues[j];
            }
            if(i%2 ==1)
                output+="\\\\";
            finalAnswer+=constVal[i];
        }
        output+="\\\\";
        output+= String.format("f(x) = %.4f\\\\",finalAnswer);
        output+= "\\end{array}";
        Main.output.set(output);
        Main.window.setScene(Main.outputScene);

    }

    public double calc(double funCurr, double funcPrev, double curr, double prev){
        return (funCurr - funcPrev)/(curr-prev);
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }

    public void printFormula(int degree){
        output+= "f(x) = b_0 ";
        for (int i = 1; i < degree; i++)
        {
            output+=String.format("+ b_{%d}",i);
            for (int j = 0; j <i; j++)
                output+= String.format("(x-x_{%d})",j);
        }
        output+="\\\\";

    }
}
