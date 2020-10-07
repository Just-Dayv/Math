package gaussian;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GaussianController {

    @FXML
    private Button calculateButton;
    @FXML
    private TextArea equationArea;
    @FXML
    private Spinner variables;

    @FXML
    private TextField solutions;

    @FXML
    private Label errorLabel;

    private String output;

    private static final double EPSILON = 1e-8;

    @FXML
    public void initialize() {

        CornerRadii cornerRadii = new CornerRadii(3);
        calculateButton.setDisable(true);
        BorderStroke borderStroke = new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID,cornerRadii,BorderWidths.DEFAULT);
        Border border = new Border(borderStroke);

        calculateButton.setDisable(true);


        variables.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                int initValue = (int)(variables.getValue());
                String [] eqnVar =  equationArea.getText().split(";");
                String [][] equations =  new String[eqnVar.length][];
                double [][] eqnMatrix = new double[equations.length][];
                int totalValues=0;
                for(int i = 0; i < eqnMatrix.length; i++)
                {
                    equations[i]= eqnVar[i].split(",");
                    eqnMatrix[i]= new double[equations[i].length];
                    for (int j=0; j < eqnMatrix[i].length; j++)
                    {

                        eqnMatrix[i][j] = Double.parseDouble(equations[i][j]);
                        totalValues+=1;

                    }
                }
                if((eqnVar.length==initValue)&&(totalValues == initValue*initValue) && matrixDeterminant(eqnMatrix)!=0 &&(solutions.getText().split(",").length== equations.length) )
                {
                    calculateButton.setDisable(false);
                }
                else {
                    calculateButton.setDisable(true);
                }

                    if ((eqnVar.length==initValue)&&(totalValues == initValue*initValue)) {
                        errorLabel.setText("");
                        equationArea.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                        equationArea.setBorder(border);
                        //calculateButton.setDisable(false);
                    }
                    else{
                        errorLabel.setText("Check that you have defined the matrix properly into row and columns\nusing the comma and semicolon appropriately  ");
                        equationArea.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        equationArea.setBorder(border);
                        calculateButton.setDisable(true);
                    }

            }
                catch (Exception e){
                    errorLabel.setText("Check that you have defined the matrix properly into row and columns\nusing the comma and semicolon appropriately  ");
                    equationArea.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                    equationArea.setBorder(border);
                    calculateButton.setDisable(true);
                }
            }
        });

            equationArea.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    try{
                        int initValue = (int)(variables.getValue());
                        String [] eqnVar =  equationArea.getText().split(";");
                        String [][] equations =  new String[eqnVar.length][];
                        double [][] eqnMatrix = new double[equations.length][];
                        int totalValues=0;
                        for(int i = 0; i < eqnMatrix.length; i++)
                        {
                            equations[i]= eqnVar[i].split(",");
                            eqnMatrix[i]= new double[equations[i].length];
                            for (int j=0; j < eqnMatrix[i].length; j++)
                            {

                                eqnMatrix[i][j] = Double.parseDouble(equations[i][j]);
                                totalValues+=1;

                            }
                        }
                        if((eqnVar.length==initValue)&&(totalValues == initValue*initValue) && matrixDeterminant(eqnMatrix)!=0 &&(solutions.getText().split(",").length== equations.length) )
                        {
                            calculateButton.setDisable(false);
                        }
                        else {
                            calculateButton.setDisable(true);
                        }


                        if ((eqnVar.length==initValue)&&(totalValues == initValue*initValue)) {
                                errorLabel.setText("");
                                equationArea.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                                equationArea.setBorder(border);
                                //calculateButton.setDisable(false);
                            }
                        else{
                            errorLabel.setText("Check that you have defined the matrix properly into row and columns\nusing the comma and semicolon appropriately  ");
                            equationArea.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                            equationArea.setBorder(border);
                            calculateButton.setDisable(true);
                        }

                        if (matrixDeterminant(eqnMatrix)!=0) {
                            errorLabel.setText("");
                            equationArea.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                            equationArea.setBorder(border);
                            //calculateButton.setDisable(false);
                        }
                        else{
                            errorLabel.setText("Check that your matrix does not have a determinant of zero   ");
                            equationArea.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                            equationArea.setBorder(border);
                            calculateButton.setDisable(true);
                        }


                    }
                    catch (Exception e){
                        errorLabel.setText("Check that you have defined the matrix properly into row and columns\nusing the comma and semicolon appropriately  ");
                        equationArea.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        equationArea.setBorder(border);
                        calculateButton.setDisable(true);
                    }

                }
            });
            solutions.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try{
                        int initValue = (int)(variables.getValue());
                        String [] eqnVar =  equationArea.getText().split(";");
                        String [][] equations =  new String[eqnVar.length][];
                        double [][] eqnMatrix = new double[equations.length][];
                        int totalValues=0;
                        for(int i = 0; i < eqnMatrix.length; i++)
                        {
                            equations[i]= eqnVar[i].split(",");
                            eqnMatrix[i]= new double[equations[i].length];
                            for (int j=0; j < eqnMatrix[i].length; j++)
                            {

                                eqnMatrix[i][j] = Double.parseDouble(equations[i][j]);
                                totalValues+=1;

                            }
                        }
                        if((eqnVar.length==initValue)&&(totalValues == initValue*initValue) && matrixDeterminant(eqnMatrix)!=0 &&(solutions.getText().split(",").length== equations.length) )
                        {
                            calculateButton.setDisable(false);
                        }
                        else {
                            calculateButton.setDisable(true);
                        }

                        if (!solutions.getText().isEmpty()){
                            if (convArray(solutions.getText().split(",")))
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

                        if ((eqnVar.length==initValue)&&(totalValues == initValue*initValue)) {
                            errorLabel.setText("");
                            equationArea.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                            equationArea.setBorder(border);
                            //calculateButton.setDisable(false);
                        }
                        else{
                            errorLabel.setText("Check that you have defined the matrix properly into row and columns\nusing the comma and semicolon appropriately  ");
                            equationArea.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                            equationArea.setBorder(border);
                            calculateButton.setDisable(true);
                        }

                    }
                    catch (Exception e){
                        errorLabel.setText("Check that your values are comma separated decimals");
                        solutions.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                        solutions.setBorder(border);
                        calculateButton.setDisable(true);
                    }

                }
            });
        }

    public void calculate() {

        output = "\\begin{array}{l}";
        int initValue = (int)(variables.getValue());
        String [] eqnVar =  equationArea.getText().split(";");
        String [][] equations =  new String[initValue][initValue];
        double [][] eqnMatrix = new double[initValue][initValue];
        String [] solnString = solutions.getText().split(",");
        double [] solMatrix = new double[solnString.length];
        double [][] augmntdMatrix = new double[initValue][initValue+1];

            for(int i = 0; i < initValue; i++)
            {
                equations[i]= eqnVar[i].split(",");
                for (int j=0; j < initValue; j++)
                {
                    augmntdMatrix[i][j] = Double.parseDouble(equations[i][j]);
                    eqnMatrix[i][j] = Double.parseDouble(equations[i][j]);

                }
            }
        printMatrix(eqnMatrix);
        printVarMatrix(initValue);
        output+="=";
        for (int a = 0; a < initValue; a ++) {
            solMatrix[a] = Double.parseDouble(solnString[a]);
            augmntdMatrix[a][initValue] = solMatrix[a];
        }
        printColumnMatrix(solMatrix);

        output+="\\\\";
        output+="\\\\";

        output+= "The\\hspace{0.4cm} augmented\\hspace{0.4cm} matrix\\hspace{0.4cm} A \\hspace{0.4cm} is:\\\\";

        printAugmentedMatrix(augmntdMatrix);

        output+="\\\\";

        for (int i = 0; i < initValue; i++){

            output+="\\\\";
            output+=   String.format("At\\hspace{0.4cm} column\\hspace{0.4cm}  %d:\\\\", i+1);
            output+="\\\\";

            int max = i;
            for (int l = i+1; l < augmntdMatrix.length; l++) {
                if (Math.abs(augmntdMatrix[l][i]) > Math.abs(augmntdMatrix[max][i])) {
                    max = l;
                }
            }
            output+= String.format("Row\\hspace{0.4cm} with\\hspace{0.4cm} max\\hspace{0.4cm} absolute\\hspace{0.4cm} value\\hspace{0.4cm} is \\hspace{0.4cm} =\\hspace{0.4cm}%d\\\\ ", max+1);

            if (i!=max)
            {
                augmntdMatrix = swap(augmntdMatrix, i, max);
                output+="\\\\";


                printAugmentedMatrix(augmntdMatrix);
                output+="\\\\";
            }


//            if (Math.abs(eqnMatrix[i][i]) >= EPSILON) {
//                continue;
//            }

          augmntdMatrix=  pivot(i, augmntdMatrix);
            output+="\\\\";

            output+="Augmented\\hspace{0.4cm} matrix\\hspace{0.4cm} is\\hspace{0.4cm} now:\\\\";
            printAugmentedMatrix(augmntdMatrix);
            output+="\\\\";
        }
        double [] x = new double[initValue+1];
        for (int i = initValue-1; i>-1; i-- ){
            double sum = 0;
            for (int j = i+1; j < initValue; j++) {
            sum += augmntdMatrix[i][j] * x[j];
            }
            if (Math.abs(augmntdMatrix[i][i]) > EPSILON){
                x[i] = (augmntdMatrix[i][initValue] - sum) / augmntdMatrix[i][i];
                output+= String.format("x_{%d} = \\frac{%.5f-%.5f}{%.2f}= %.5f\\\\",i+1,augmntdMatrix[i][initValue],sum,augmntdMatrix[i][i],x[i]);
            }
            else if (Math.abs(augmntdMatrix[i][initValue] - sum) > EPSILON)
            {
                output+= "No\\hspace{0.4cm} value\\hspace{0.4cm} for\\hspace{0.4cm} x\\\\";
            }
        }
        output+= "\\end{array}";

        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();


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

    private double[][] pivot(int p, double [][] a) {
       // output+="\\begin{bmatrix}";
        for (int i = p+1; i < a.length; i++) {
            double alpha = a[i][p] / a[p][p];
           output+= String.format("Pivot \\hspace{0.4cm} value \\hspace{0.4cm} for \\hspace{0.4cm} row \\hspace{0.4cm}%d \\hspace{0.4cm} is:\\hspace{0.4cm} \\frac{a_{%d%d}}{a_{%d%d}}\\hspace{0.4cm} = %.4f\\\\",i+1,i+1,p+1,p+1,p+1,alpha);
            for (int j = p; j < a[i].length; j++) {
                a[i][j] -= alpha * a[p][j];
//                if(j< a[i].length-1)
//                    output+= String.format("{%.1f}-{%.1f\\left(\\frac{%.1f}{%.1f}\\right)}&",a[i][j],a[p][j],a[i][p],a[p][p]);
//                else
//                    output+= String.format("%.1f",a[i][j]);
            }

           // output+="\\\\";
        }
       // output+="\\end{bmatrix}";
        return a;
    }


    private double[][] swap(double[][] matrix, int row1, int row2) {
        output+= String.format("Swapping\\hspace{0.4cm} row\\hspace{0.4cm} %d\\hspace{0.4cm} and \\hspace{0.4cm}row\\hspace{0.4cm} %d\\\\",row1+1,row2+1);
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
        return matrix;
    }
    private int findLargestPivot(double[][] matrix, int col) {
        int maxRow = -1;
        double maxValue = 0;
        for (int row = col; row < matrix.length; row++) {
            if (Math.abs(matrix[row][col]) > maxValue && Math.abs(matrix[row][col]) > 0) {
                maxRow = row;
                maxValue = Math.abs(matrix[row][col]);
            }
        }
       output+= String.format("Row\\hspace{0.4cm} with\\hspace{0.4cm} max\\hspace{0.4cm} absolute\\hspace{0.4cm} value\\hspace{0.4cm} is \\hspace{0.4cm} =\\hspace{0.4cm}%d\\\\ ", maxRow);
        return maxRow;

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

    public void printAugmentedMatrix(double [][] matrix){
        output+="\\begin{pmatrix}";
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j< matrix[i].length; j++)
            {
                if(j< matrix[i].length-1)
                    output+= String.format("%.5f&",matrix[i][j]);
                else
                    output+= String.format("\\vdots&%.5f",matrix[i][j]);
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

    public void printVarMatrix(int rows){
        output+= "\\begin{pmatrix}";
        output+="x_1\\\\";
        int i =1;
        for (; i<rows-1; i++)
        {
            output+="\\vdots\\\\";
        }
        output+= String.format("x_{%d}",rows);
        output+="\\end{pmatrix}";
    }
    public static double matrixDeterminant (double[][] matrix) {
        double temporary[][];
        double result = 0;

        if (matrix.length == 1) {
            result = matrix[0][0];
            return (result);
        }

        if (matrix.length == 2) {
            result = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
            return (result);
        }

        for (int i = 0; i < matrix[0].length; i++) {
            temporary = new double[matrix.length - 1][matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        temporary[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temporary[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }

            result += matrix[0][i] * Math.pow (-1, (double) i) * matrixDeterminant (temporary);
        }
        return (result);
    }

    public void goBack() {
        Main.window.setScene(Main.homepage);
    }



}
