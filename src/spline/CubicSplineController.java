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

import java.util.Arrays;
import java.util.regex.Pattern;

public class CubicSplineController {

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

    private String output;

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


    public void calculate() {
        output = "\\begin{array}{l}";

        String [] variableVals = this.variableValues.getText().split(",");
        String [] solutionString = this.solutions.getText().split(",");
        double [] variableValues = new double[variableVals.length];
        double [] solutions = new double[variableVals.length];
        double [] splines = new double[variableVals.length];
        int n = variableVals.length;
        double interpol = Double.parseDouble(this.interpol.getText());

        for (int i = 0; i < n; i++){

            variableValues[i] = Double.parseDouble(variableVals[i]);//converted values
            solutions[i] = Double.parseDouble(solutionString[i]);
        }
        double [] variablevaluesCopy = Arrays.copyOf(variableValues, variableValues.length);
        Arrays.sort(variablevaluesCopy);
        for (int i = 0; i < n; i++){
            int index = Arrays.binarySearch(variablevaluesCopy,variableValues[i]); // trying to sort the solns to corresponding values
            if (index != i ) {
                swap(solutions, index, i);
            }

        }

        for (int i = 0; i < variableValues.length-1; i++)
        {
            output += String.format( "\\hspace{1.5cm}f(x) = a_{%d}(x-x_j)^3+b_{%d}(x-x_j)^2",i+1,i+1);

            output+= String.format("+ c_{%d}(x-x_j) + d_{%d},\\hspace{1.5cm}" , i+1,i+1);


            output+= String.format("%.2f\\leq x \\leq %.2f " , variableValues[i], variableValues[i+1]);
            output+="\\\\";
        }


        splines[0]=splines[splines.length-1]=0;
        double [] hgap = new double[variableVals.length];
        output+="Defining\\hspace{0.4cm}the\\hspace{0.4cm} knot\\hspace{0.4cm}to\\hspace{0.4cm}knot\\hspace{0.4cm}difference \\hspace{0.4cm}(h_i)";
        output+="\\\\";

        output+="h_i = x_{i+1} -x_i";

        output+="\\\\";

        for (int i = 0; i < hgap.length-1; i++) {
            hgap[i] = variableValues[i+1] - variableValues[i];
            output+=String.format("h_{%d} = %.2f-%.2f= %.4f", i,variableValues[i+1], variableValues[i],hgap[i]);
            output+="\\\\";
        }
        output+="\\\\";

      //  printColumnMatrix(hgap);
        output+="\\\\";
        output+="By \\hspace{0.4cm}Setting\\hspace{0.4cm} the\\hspace{0.4cm} conditions\\hspace{0.4cm} S_0 = S_{n}= 0\\\\";
        output+="Using \\hspace{0.4cm}the\\hspace{0.4cm} general\\hspace{0.4cm} matrix\\hspace{0.4cm} formula\\hspace{0.4cm} for\\hspace{0.4cm} the \\hspace{0.4cm}interior\\hspace{0.4cm} points :\\\\";




        double [][] constantA = new double[variableVals.length][variableVals.length];


        output+= "\\begin{pmatrix}2(h_0+h_1)&h_1&0&0&\\cdots&0\\\\h_1&2(h_1+h_2)&h_2&0&\\cdots&0\\\\0&h_2&2(h_2+h_3)&h_3&\\cdots&0\\\\\\vdots&\\vdots&&\\ddots&&\\vdots\\\\0&0&\\cdots&&h_{n-2}&2(h_{n-2}+h_{n-1})\\end{pmatrix}";

           // output+="2(h_0+h_1)&h_1&0&0&\\cdots&0\\\\";
           // output+="h_1&2(h_1+h_2)&h_2&0\\cdots&0\\\\";
          //  output+="0&h_2&2(h_2+h_3)&h_3%\\cdots&0\\\\";
//            output+="0&0&\\cdots&h_{n-2}&2(h_{n-2}+h_{n-1})";
           // output+="\\end{pmatrix}";

        output+= "\\begin{pmatrix}";
        output+="S_1\\\\";

        for (int i =1; i<(n-4); i++)
        {
            output+="\\vdots\\\\";
        }
        output+= "S_{n-1}";

        output+="\\end{pmatrix}";

        output+="=";
        output+= "\\begin{pmatrix}";
        output+="b_0\\\\";

        for (int i =1; i<4; i++)
        {
            output+="\\vdots\\\\";
        }
        output+= "b_{n-2}";
        output+="\\end{pmatrix}";


        output+="\\\\";
        output+="Where\\hspace{0.4cm} b_j = 6\\left(\\frac{y_{j+1}-y_j}{h_j}-\\frac{y_j-y_{j-1}}{h_{j-1}}\\right) \\\\";


        for (int j = 1; j< n-1; j++){
            constantA[j][j] = 2* (hgap[j-1] + hgap[j]);

            constantA[j][j+1] = constantA[j+1][j] =hgap[j];

            constantA[j][constantA.length-1] =
                    6* (((solutions[j+1]-solutions[j])/hgap[j]) -
                            (solutions[j]- solutions[j-1])/hgap[j-1]);
        }

        printAugmentedMatrix(constantA);
        output+="\\\\";
        output+="Solving\\hspace{0.4cm} by\\hspace{0.4cm} gaussian\\hspace{0.4cm} elimination: ";

        output+="\\\\";


        double [][] constantM = new double[variableValues.length][variableValues.length];

        for (int j = 1; j < n-2; j++)
        {
            constantM[j+1][j] = constantA[j+1][j]/constantA[j][j];
            //output+= String.format("M_{%d,%d} = %.2f\\\\",j+1,j, constantM[j+1][j]);

            for (int i = j; i < n; i++ ){
                constantA[j+1][j]= constantA[j+1][j]- constantM[j+1][j]*constantA[j][i];
                //  output+= String.format("A_{%d,%d} = A_{%d,%d} - M_{%d,%d}* A_{%d,%d} =  %.2f\\\\",j+1,j,j+1,j, j+1,j, j,i, constantA[j+1][j]);
            }
        }
        splines[n-2] = constantA[n-2][n-1]/constantA[n-2][n-2];

        output+= "\\begin{pmatrix}";
        output+="S_0\\\\";

        for (int i =1; i<(n-2); i++)
        {
            output+="\\vdots\\\\";
        }
        output+= "S_{n}";

        output+="\\end{pmatrix}";

        //printMatrix(constantM);
        // printMatrix(constantA);
       // printColumnMatrix(splines);
        output+="=";


        for (int k = n-3; k>0; k-- ) {
            double sum = 0;
            //output+= ("sum+= ");
            for (int j = k + 1; j < n - 2; j++) {
                //output+= String.format("A_{%d,%d} * S_{%d}+ ",k,j,j);
            }
           // output+="\\\\";

            //output+= ("sum+= ");
            for (int j = k + 1; j < n - 2; j++) {
                sum += constantA[k][j] * splines[j];
                //output+= String.format(" %.4f * %.4f+ ",constantA[k][j],splines[j]);
            }
           // output+="\\\\";========
            splines[k] = (constantA[k][n - 1] - sum) / constantA[k][k];
//            output+= String.format("s_{%d} = \\frac{A_{%d,%d} - %.4f}{A_{%d,%d}}",k,k,n-1, sum,k,k);
//            output+="\\\\";
//            output+= String.format("s_{%d}= \\frac{%.4f - %.4f}{%.4f} = %,4f",k, constantA[k][n-1], sum,constantA[k][k], splines[k]);
//            output+="\\\\";

        }

        printColumnMatrix(splines);
        output+="\\\\";
        output+="\\\\";
        output+="Deriving \\hspace{0.4cm} the \\hspace{0.4cm} constants \\hspace{0.4cm} a_j,\\hspace{0.4cm}  b_j,\\hspace{0.4cm}  c_j \\hspace{0.4cm} and\\hspace{0.4cm}  d_j, \\hspace{0.4cm} we\\hspace{0.4cm}  use\\hspace{0.4cm}  the \\hspace{0.4cm} formulas: ";
        output+="\\\\";
        output+="a_i =\\frac{S_{i+1}-S_i}{6h_i}\\\\";
        output+="b_i =\\frac{S_i}{2}\\\\";
        output+="c_i = (y_{i+1} -y_i)h_i -\\frac{h_i(2S_i+S_i+1)}{6})";
        output+="\\\\";
        output+="d_i = y_i\\\\";
        output+="\\\\";
        output+="Therefore:\\\\";


        double [][] constantC = new double[n][n];

        double [][] swappedC = new double[constantC.length][constantC.length];
        for (int i = 0; i < n-1; i++){
            constantC[2][i] = splines[i]/2;
            constantC[3][i] = (splines[i+1]-splines[i])/( 6* hgap[i]);
            constantC[1][i] = ((solutions[i+1] - solutions[i])/hgap[i])- hgap[i]*(2 * splines[i] + splines[i+1])/6;
            constantC[0][i] = solutions[i];

            swappedC[0][i]  =(splines[i+1]-splines[i])/( 6* hgap[i]);
            swappedC[1][i] =splines[i]/2;
            swappedC[2][i] =  ((solutions[i+1] - solutions[i])/hgap[i])- hgap[i]*(2 * splines[i] + splines[i+1])/6;
            swappedC[3][i] = solutions[i];

        }

        for (int i = 0; i < n-1; i++){
            output+=String.format("\\hspace{1.5cm}a_{%d} =\\frac{S_{%d}-S_{%d}}{6h_{%d}} =\\frac{%.4f-%.4f}{6(%.2f)}= %.4f \\\\",i,i+1,i,i,splines[i+1],splines[i],hgap[i],constantC[3][i]);
            output+=String.format("\\hspace{1.5cm}b_{%d} =\\frac{S_{%d}}{2} =\\frac{%.4f}{2} =%.4f\\\\",i,i,splines[i],constantC[2][i]);
            output+=String.format("\\hspace{1.5cm}c_{%d} = (y_{%d} -y_{%d})h_{%d} - \\frac{h_{%d}(2S_{%d}+S_{%d})}{6} = (%.2f -%.2f)%.2f -\\frac{%.2f(2(%.2f)+%.2f)}{6} = %.4f)\\\\",i,i+1,i,i,i,i,i+1,solutions[i+1],solutions[i],hgap[i],hgap[i],splines[i],splines[i+1],constantC[1][i]);
            output+=String.format("\\hspace{1.5cm}d_{%d} = y_i =%.4f  \\\\",i,constantC[0][1]);

            output+="\\\\";
            output+="\\\\";

        }
        for (int i = 0; i < variableValues.length-1; i++)
        {
            output += String.format( "\\hspace{1.5cm}f(x) = %.4f(x-x_j)^3 - %.4f(x-x_j)^2",constantC[3][i],constantC[2][i]);

            output+= String.format("+ %.4f(x-x_j) + %.4f,\\hspace{1.5cm}" ,constantC[1][i],constantC[0][i]);


            output+= String.format("%.2f\\leq x \\leq %.2f " , variableValues[i], variableValues[i+1]);
            output+="\\\\";
        }



      //  printMatrix(constantC);

        output+="\\\\";

        int k = 0;

        for(int i = 0; i < n-1; i++ ){
            if (interpol < variableValues[i+1])
            {
                k = i;
                break;
            }
        }
        double y = constantC[3][k] * (interpol-variableValues[k]) * (interpol-variableValues[k]) * (interpol-variableValues[k])+
                constantC[2][k]*(interpol-variableValues[k])*(interpol-variableValues[k])+
                constantC[1][k]*(interpol-variableValues[k])
                + constantC[0][k];

        output+=String.format("\\hspace{1.5cm}for \\hspace{0.4cm}x = %.2f \\\\",interpol );

        output+=String.format("%.4f(%.2f-%.2f)^3+ %.4f(%.2f-%.2f)^2+%.4f(%.2f-%.2f) + %.4f",constantC[3][k],interpol,variableValues[k],constantC[2][k],interpol,variableValues[k],constantC[1][k],interpol,variableValues[k],constantC[0][k]);
        output+="\\\\";
        output+= String.format("\\hspace{1.5cm}f(x)=%.5f",y);
        output+= "\\end{array}";

        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();
    }


    private void swap(double[] solutions, int index, int i) {
        double temp = solutions [index];
        solutions[index] = solutions[i];
        solutions[i]= temp;
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

    public void printAugmentedMatrix(double [][] matrix){
        output+="\\begin{pmatrix}";
        for (int i = 1; i < matrix.length-1; i++)
        {
            for (int j = 1; j< matrix[i].length; j++)
            {
                if(j< matrix[i].length-1)
                    output+= String.format("%.5f&",matrix[i][j]);
                else
                    output+= String.format("\\vdots&%.5f",matrix[i][matrix[i].length-1]);
            }
            output+="\\\\";
        }
        output+="\\end{pmatrix}";
    }

    private double[][] swapRow(double[][] matrix, int row1, int row2) {
        output+= String.format("Swapping\\hspace{0.4cm} row\\hspace{0.4cm} %d\\hspace{0.4cm} and \\hspace{0.4cm}row\\hspace{0.4cm} %d\\\\",row1+1,row2+1);
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
        return matrix;
    }


    public void goBack() {
        Main.window.setScene(Main.homepage);
    }

}
