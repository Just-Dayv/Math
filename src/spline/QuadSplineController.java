package spline;

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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

public class QuadSplineController {
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
        BorderStroke borderStroke = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,cornerRadii, BorderWidths.DEFAULT);
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

    private String output;
    public void calculate() {
        output = "\\begin{array}{l}";
        String [] variableVals = this.variableValues.getText().split(",");
        String [] solutionString = this.solutions.getText().split(",");
        double [] variableValues = new double[variableVals.length];
        double [] solutions = new double[variableVals.length];

        for (int i = 0; i < variableValues.length; i++){

            variableValues[i] = Double.parseDouble(variableVals[i]);//converted values
            solutions[i] = Double.parseDouble(solutionString[i]);
        }
        double [] variablevaluesCopy = Arrays.copyOf(variableValues, variableValues.length);
        Arrays.sort(variablevaluesCopy);
        for (int i = 0; i < variableValues.length; i++){
            int index = Arrays.binarySearch(variablevaluesCopy,variableValues[i]); // trying to sort the solns to corresponding values
            if (index != i )
                swap(solutions, index, i);

        }

        double [] adjustedPoints = new double[variableValues.length-1];
        for(int i = 1; i < adjustedPoints.length-1; i++){
            adjustedPoints[i] = (variableValues[i]+variableValues[i+1])/2;
        }
        adjustedPoints[0]= variableValues[0];
        adjustedPoints[adjustedPoints.length-1] =variableValues[variableValues.length-1];


        double interpol = Double.parseDouble(this.interpol.getText());
        int splineValue = ((int) spinnerValue.getValue())-1; // to get number of splines
        int spinner  = ((int) spinnerValue.getValue()); // to get number of splines
        double [][] constCoeff = new double[variableValues.length][3];
        double [][] adjustedPointsCoeff = new double[adjustedPoints.length-2][3];
        double [][] allEquationsCoeff = new double[(spinner-2)*3][(spinner-2)*3];

//          double [] differentials = new double[variableValues.length];
//          differentials[0] =0;
//
//          for (int i = 0; i < differentials.length-1; i++)
//          {
//                differentials[i+1]  = -differentials[i] +2*((solutions[i+1]- solutions[i])/(variableValues[i+1]- variableValues[i]));
//
//          }
//
//          double [] splines = new double[variableValues.length];
//          for (int i = 0; i <splines.length; i++){
//
//          }

        for (int i = 0; i < adjustedPoints.length-1; i++)
        {
            output += String.format( "\\hspace{1.5cm}f(x) = a_{%d}x^2",i+1);

            output+= String.format("+ b_{%d}x + c_{%d},\\hspace{1.5cm}" , i+1,i+1);


            output+= String.format("%.2f\\leq x \\leq %.2f " , adjustedPoints[i], adjustedPoints[i+1]);
            output+="\\\\";
        }

        output+="\\\\";
        output+="\\\\";
        output+="Each\\hspace{0.4cm} Spline\\hspace{0.4cm} Goes\\hspace{0.4cm} Through\\hspace{0.4cm} Two\\hspace{0.4cm} Consecutive\\hspace{0.4cm} Data\\hspace{0.4cm} Points";
        output+="\\\\";
        output+="Therefore:";
        output+="\\\\";
        output+="Using\\hspace{0.4cm} the \\hspace{0.4cm}conditions \\hspace{0.4cm}provided";
        output+="\\\\";
        int eqnCounter = 1;
        output += String.format( "\\hspace{1.5cm} a_{%d}(%.2f)^2",1, variableValues[0]);
        output+= String.format("+ b_{%d}(%.2f) + c_{%d}= \\hspace{1.5cm}" , 1,variableValues[0],1);
        output+= String.format("%.4f\\hspace{1.5cm} .....(%d)" , solutions[0],eqnCounter++); output+="\\\\";

        for (int j= 1;  j < spinner-1; j++)
        {

            output += String.format( "\\hspace{1.5cm} a_{%d}(%.2f)^2",j, variableValues[j]);
            output+= String.format("+ b_{%d}(%.2f) + c_{%d}= \\hspace{1.5cm}" , j,variableValues[j],j);


            output+= String.format("%.4f\\hspace{1.5cm} .....(%d)" , solutions[j], eqnCounter++);
            output+="\\\\";
        }

        output += String.format( "\\hspace{1.5cm}a_{%d}(%.2f)^2",adjustedPoints.length-1, variableValues[variableValues.length-1]);
        output+= String.format("+ b_{%d}(%.2f) + c_{%d}= \\hspace{1.5cm}" ,adjustedPoints.length-1 ,variableValues[variableValues.length-1],variableValues.length-1);
        output+= String.format("%.4f\\hspace{1.5cm} .....(%d)" , solutions[solutions.length-1],eqnCounter++);
        output+="\\\\";
        output+="Also\\hspace{0.4cm} for \\hspace{0.4cm}adjusted\\hspace{0.4cm} data \\hspace{0.4cm}points \\\\";
        output+="\\\\";
        for (int i = 1;  i   < adjustedPoints.length-1; i++)
        {

            output += String.format( "\\hspace{1.5cm} a_{%d}(%.2f)^2", i, adjustedPoints[i]);
            output+= String.format("+ b_{%d}(%.2f) +c_{%d} -a_{%d}(%.2f)^2- b_{%d}(%.2f)-c_{%d}= \\hspace{1.5cm}0\\hspace{1.5cm} .....(%d)" , i,adjustedPoints[i],i,i+1, adjustedPoints[i],i+1,adjustedPoints[i],i+1,eqnCounter++);
            output+="\\\\";

        }


        output+="\\\\";
        output+="\\\\";


        output+="Derivatives\\hspace{0.4cm} are\\hspace{0.4cm} Continuous\\hspace{0.4cm} at \\hspace{0.4cm}Interior\\hspace{0.4cm} Data\\hspace{0.4cm}Points";
        output+="\\\\";
        output+="\\\\";
        output+="\\frac{d}{dt}\\left(a_ix^2+b_ix+c_i\\right) \\left|_{t_i} = \\frac{d}{dt}\\left(a_{i+1}x^2+b_{i+1}x+c_{i+1}\\right)\\right|_{t_i}";
        output+="\\\\";
        output+="\\\\";
        for (int i = 1;  i < adjustedPoints.length-1; i++)
        {

            output += String.format( "\\hspace{1.5cm} 2a_{%d}(%.2f)", i, adjustedPoints[i]);
            output+= String.format("+ b_{%d} -2a_{%d}(%.2f)- b_{%d}= \\hspace{1.5cm}0\\hspace{1.5cm} .....(%d)" , i,i+1, adjustedPoints[i],i+1,eqnCounter++);
            output+="\\\\";

        }

        output+="\\\\";
        output+="\\\\";
        output+="Solving\\hspace{0.4cm}Using\\hspace{0.4cm}Gaussian \\hspace{0.4cm}Elimination:";
        output+="\\\\";


        for (int i = 0; i < constCoeff.length; i++){
            for (int j =  constCoeff[i].length-1, k= 0; j >-1; k++, j--){
                constCoeff[i][k] = Math.pow(variableValues[i],j);
            }
        }

        for (int i = 0; i < adjustedPointsCoeff.length; i++){
            for (int j =  adjustedPointsCoeff[i].length-1, k= 0; j >-1; k++, j--){
                adjustedPointsCoeff[i][k] = Math.pow(adjustedPoints[i+1],j);
            }
        }


        double [][] differentialCoeffecients = new double[adjustedPoints.length-2][3];
        for (int i = 0; i < differentialCoeffecients.length; i++){
            differentialCoeffecients[i][0] = 2* adjustedPoints[i+1];
            differentialCoeffecients[i][1] = 1;

        }
        output+="\\\\";
        output+="\\\\";

        int equationsIndexCounter = spinner;
        Array.setDouble(allEquationsCoeff[0],0,constCoeff[0][0]);
        Array.setDouble(allEquationsCoeff[0],1,constCoeff[0][1]);
        Array.setDouble(allEquationsCoeff[0],2,constCoeff[0][2]);
        int jIndex = 0;
        for (int i = 1, j=0, k=1; i < equationsIndexCounter-1; i+=1,k+=1){
            Array.setDouble(allEquationsCoeff[i],j,constCoeff[k][0]);
            Array.setDouble(allEquationsCoeff[i],j+1,constCoeff[k][1]);
            Array.setDouble(allEquationsCoeff[i],j+2,constCoeff[k][2]);
            jIndex =j;
            j+=3;
        }
        Array.setDouble(allEquationsCoeff[spinner-1],jIndex,constCoeff[spinner-1][0]);
        Array.setDouble(allEquationsCoeff[spinner-1],jIndex+1,constCoeff[spinner-1][1]);
        Array.setDouble(allEquationsCoeff[spinner-1],jIndex+2,constCoeff[spinner-1][2]);
        equationsIndexCounter+=adjustedPoints.length-2;
        for (int i = spinner, j=0, k=0; i < equationsIndexCounter; i+=1,k+=1){
            Array.setDouble(allEquationsCoeff[i],j,adjustedPointsCoeff[k][0]);
            Array.setDouble(allEquationsCoeff[i],j+1,adjustedPointsCoeff[k][1]);
            Array.setDouble(allEquationsCoeff[i],j+2,adjustedPointsCoeff[k][2]);
            Array.setDouble(allEquationsCoeff[i],j+3,-adjustedPointsCoeff[k][0]);
            Array.setDouble(allEquationsCoeff[i],j+4,-adjustedPointsCoeff[k][1]);
            Array.setDouble(allEquationsCoeff[i],j+5,-adjustedPointsCoeff[k][2]);
            j+=3;

        }
        int currentIndex = equationsIndexCounter;
       equationsIndexCounter += adjustedPoints.length-2;
        for (int i = currentIndex, j=0, k=0; i < equationsIndexCounter; i+=1,k+=1){
            Array.setDouble(allEquationsCoeff[i],j,differentialCoeffecients[k][0]);
            Array.setDouble(allEquationsCoeff[i],j+1,differentialCoeffecients[k][1]);
            Array.setDouble(allEquationsCoeff[i],j+2,differentialCoeffecients[k][2]);
            Array.setDouble(allEquationsCoeff[i],j+3,-differentialCoeffecients[k][0]);
            Array.setDouble(allEquationsCoeff[i],j+4,-differentialCoeffecients[k][1]);
            j+=3;

        }
       // allEquationsCoeff[allEquationsCoeff.length-1][0] = 1;
        printMatrix(allEquationsCoeff);
        printVarMatrix((spinner-2)*3);
        output+="=";

        double [] solutionCoefficients = new double[(spinner-2)*3];

        for (int i = 0; i <spinner; i++){
            solutionCoefficients[i] = solutions[i];
        }

        printColumnMatrix(solutionCoefficients);

        double [][] solutionCoefficientMatrix = new double[solutionCoefficients.length][];
        for (int i = 0; i <solutionCoefficientMatrix.length; i++){
            solutionCoefficientMatrix[i] = new double[]{solutionCoefficients[i]};
        }
        output+="\\\\";
        output+="\\\\";
        Matrix matrixCoeff = new Matrix(allEquationsCoeff);
        Matrix matrixSolution = new Matrix(solutionCoefficientMatrix);
        matrixSolution.print(9,8);
        Matrix result = matrixCoeff.solve(matrixSolution);
        double [][] resultArray = result.getArray();
        double [][] resultTable = new double[spinner-2][3];
      //  resultArray[0][0] =0;// correcting matrix library error.
        int k = 0;
        for (int i = 0; i < resultTable.length; i++){

//           if (k==resultArray.length)
//               break;
            for(int j = 0; j< resultTable[i].length; j++, k++){
                resultTable[i][j] = resultArray[k][0];
            }
        }
//       int j = 0; int l = 0;
//       for (int i=0; i < resultArray.length; i++)
//       {
//           if(j%3 == 0 )
//           {
//               l+=1;
//               j=0;
//           }
//           constCoeff[l][j] = resultArray[i][0];
//       }
        printVarMatrix((spinner-2)*3);
        output+="=";
        printMatrix(resultArray);
        output+="\\\\";
        output+="\\\\";

        output+="Therefore:";
        output+="\\\\";
        output+="\\\\";
        for (int i = 0; i < (spinner-2); i++)
        {
            output += String.format( "\\hspace{1.5cm}f(x) = %.4fx^2",resultTable[i][0]);

            output+= String.format("+ %.4fx + %.4f,\\hspace{1.5cm}" ,resultTable[i][1],resultTable[i][2]);


            output+= String.format("%.2f\\leq x \\leq %.2f " , adjustedPoints[i], adjustedPoints[i+1]);
            output+="\\\\";
        }

        for(int i = 0; i < adjustedPoints.length; i++ ){
            if (interpol < adjustedPoints[i+1])
            {
                k = i;
                break;
            }
        }
        output+="\\\\";
        output+="\\\\";
        output+=String.format("For\\hspace{0.4cm} x \\hspace{0.4cm}= \\hspace{0.4cm}%.2f:",interpol );
        output+="\\\\";

        double y = resultTable[k][0] * Math.pow(interpol,2) + resultTable[k][1]*interpol + resultTable[k][2];
        output+=String.format("\\hspace{1.5cm}f(x)=%.4f(%.4f)^2+ %.4f(%.4f)+%.4f = \\hspace{0.4cm}%.4f",resultTable[k][0],interpol,resultTable[k][1],interpol, resultTable[k][2],y );
        output+="\\\\";
        output+="\\\\";
        output+= "\\end{array}";
        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();
    }

    public void printMatrix(double [][] matrix)
    {
        output+="\\begin{pmatrix}";
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j< matrix[i].length; j++)
            {
                if(j< matrix[i].length-1)
                    output+= String.format("%.5f&",matrix[i][j]);
                else
                    output+= String.format("%.5f",matrix[i][j]);
            }
            output+="\\\\";
        }
        output+="\\end{pmatrix}";
    }

    public void printColumnMatrix(double[] matrix){
        output+="\\begin{pmatrix}";
        for (int i = 0; i < matrix.length; i++)
        {
            output+=String.format("%.5f\\\\",matrix[i]);
        }
        output+="\\end{pmatrix}";
    }

    private void swap(double[] solutions, int index, int i) {
        double temp = solutions [index];
        solutions[index] = solutions[i];
        solutions[i]= temp;
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

    public void printVarMatrix(int rows){
        output+= "\\begin{pmatrix}";
        output+="a_1\\\\";
        output+="b_1\\\\";
        output+="c_1\\\\";
        int i =3;
        for (; i<rows-1; i++)
        {
            output+="\\vdots\\\\";
        }
        output+= String.format("c_{%d}",rows/3);
        output+="\\end{pmatrix}";
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }
}
