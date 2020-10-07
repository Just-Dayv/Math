package LUDecomposition;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.lang.reflect.Array;

import static gaussian.GaussianController.matrixDeterminant;

public class LUInverseController {
        @FXML
        private Button calculateButton;
        @FXML
        private TextArea equationArea;
        @FXML
        private Spinner variables;

        @FXML
        private Label errorLabel;

        private String output;




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
                        if((eqnVar.length==initValue)&&(totalValues == initValue*initValue) && matrixDeterminant(eqnMatrix)!=0  )
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
                        if((eqnVar.length==initValue)&&(totalValues == initValue*initValue) && matrixDeterminant(eqnMatrix)!=0  )
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

        }
        public void calculate() {
            output = "\\begin{array}{l}";
            int initValue = (int) (variables.getValue());
            String[] eqnVar = equationArea.getText().split(";");
            String[][] equations = new String[initValue][initValue];
            double[][] eqnMatrix = new double[initValue][initValue];
            int n = eqnMatrix.length;

            double [][] eqnMatrixCopy = new double[n][n];
            double[][] lower = new double[n][n];

            for (int i = 0; i < n; i++)
            {
                lower[i][i] = 1;

            }

            for (int i = 0; i < initValue; i++) {
                equations[i] = eqnVar[i].split(",");
                for (int j = 0; j < initValue; j++) {

                    eqnMatrix[i][j] = Double.parseDouble(equations[i][j]);
                    eqnMatrixCopy[i][j]  = Double.parseDouble(equations[i][j]);
                }
            }


            output+="Decomposing\\hspace{0.4cm} [A]\\hspace{0.4cm}=\\hspace{0.4cm}[L][U]\\\\";
            output+="\\\\";
            output+="\\\\";
            printMatrix(eqnMatrix);
            output+="=";
            upperMatrixPrinter(n);
            lowerMatrixPrinter(n);
            output+="\\\\";
            output+="\\\\";

            for (int p = 0; p < n; p++)
            {

                for (int i = p+1; i < eqnMatrix.length; i++) {
                    double alpha = eqnMatrix[i][p] / eqnMatrix[p][p];
                    lower[i][p] = alpha;
                    output+= String.format("Pivot \\hspace{0.4cm} value \\hspace{0.4cm} for \\hspace{0.4cm} row \\hspace{0.4cm}%d \\hspace{0.4cm} is:\\hspace{0.4cm} \\frac{a_{%d%d}}{a_{%d%d}}\\hspace{0.4cm} = %.4f\\\\",i+1,i+1,p+1,p+1,p+1,alpha);
                    output+=String.format("Row \\hspace{0.4cm}%d\\hspace{0.4cm} - \\hspace{0.4cm}row%d\\hspace{0.4cm}(%.4f)=",i+1,p+1,alpha);

                    for (int j = p; j < eqnMatrix[i].length; j++) {
                        eqnMatrix[i][j] -= alpha * eqnMatrix[p][j];
                    }

                    printMatrix(eqnMatrix);
                    output+="\\\\";
                    output += String.format("Therefore \\hspace{0.4cm}L_{%d,%d}\\hspace{0.4cm} = %.4f\\\\",i+1,p+1,alpha);
                    output+="\\\\";


                }


            }
            output+="Therefore:\\\\";
            printMatrix(eqnMatrixCopy);
            output+="=";
            printMatrix(eqnMatrix);
            printMatrix(lower);
            output+="\\\\";
            output+="\\\\";

            double [][] inverse = new double[n][n];
            for (int i = 0; i < initValue; i ++) {
                double[] solMatrix = new double[n];
                Array.set(solMatrix, i,1);

                output += "Forward \\hspace{0.4cm}Substitution\\hspace{0.4cm} to\\hspace{0.4cm} obtain\\hspace{0.4cm} Z:\\\\";
                printMatrix(lower);
                printVarMatrix(n, "Z");
                output += "=";
                printColumnMatrix(solMatrix);
                output += "\\\\";
                output += "\\\\";

                double[] z = new double[initValue];
                for (int j = 0; j < initValue; j++) {
                    double sum = 0;
                    output += String.format("Z_{%d,%d}= %.4f", j + 1,i+1, solMatrix[j]);
                    for (int k = 0; k < j; k++) {
                        sum += lower[j][k] * z[k];
                        output += String.format("-%.4f(%.4f)", lower[j][k], z[k]);
                    }
                    output += "\\\\";

                    z[j] = (solMatrix[j] - sum) / lower[j][j];
                    if (j == 0) {
                        continue;
                    }
                    output += String.format(" = %.4f\\\\", z[j]);
                    output += "\\\\";

                }


                output += "Backward \\hspace{0.4cm}Substitution\\hspace{0.4cm} to\\hspace{0.4cm} obtain\\hspace{0.4cm} x:\\\\";
                printMatrix(eqnMatrix);
                printVarMatrix(n, "x");
                output += "=";
                printColumnMatrix(z);
                output += "\\\\";
                output += "\\\\";

                double[] x = new double[initValue];
                for (int j = initValue - 1; j > -1; j--) {
                    double sum = 0;
                    for (int k = j + 1; k < initValue; k++) {
                        sum += eqnMatrix[j][k] * x[k];

                    }


                    x[j] = (z[j] - sum) / eqnMatrix[j][j];
                    inverse[j][i] = x[j];
                    output += String.format("x_{%d,%d} = \\frac{%.2f-%.2f}{%.2f}= %.4f\\\\", j + 1,i+1, z[j], sum, eqnMatrix[j][j], x[j]);

                }

            }

            output+="Inverse[A] =\\\\";

            printMatrix(inverse);
            output+="\\\\";



            output += "\\end{array}";
            Main.output.set(output);
            Stage stage = new Stage();
            stage.setScene(Main.outputScene);
            stage.show();
        }

        public void printColumnMatrix(double[] matrix){
            output+="\\begin{pmatrix}";
            for (int i = 0; i < matrix.length; i++)
            {
                output+=String.format("%.3f\\\\",matrix[i]);
            }
            output+="\\end{pmatrix}";
        }

        public void printVarMatrix(int rows,String s){
            output+= "\\begin{pmatrix}";
            output+=String.format("%s_1\\\\",s);
            int i =1;
            for (; i<rows-1; i++)
            {
                output+="\\vdots\\\\";
            }
            output+= String.format("%s_{%d}",s,rows);
            output+="\\end{pmatrix}";
        }

        public void printMatrix(double [][] matrix)
        {
            output+="\\begin{pmatrix}";
            for (int i = 0; i < matrix.length; i++)
            {
                for (int j = 0; j< matrix[i].length; j++)
                {
                    if(j< matrix[i].length-1)
                        output+= String.format("%.3f&",matrix[i][j]);
                    else
                        output+= String.format("%.3f",matrix[i][j]);
                }
                output+="\\\\";
            }
            output+="\\end{pmatrix}";
        }

        public  void upperMatrixPrinter (int length){
            output+="\\begin{pmatrix}";
            for (int i = 0; i< length; i++)
            {
                for (int j = 0; j < length; j++){
                    if (j<i)
                        output+= ("0& ");
                    else
                    {
                        if (j< length-1)
                            output+= String.format("U_{%d,%d}& ",i+1,j+1);
                        else
                            output+= String.format("U_{%d,%d} ",i+1,j+1);
                    }

                }
                output+="\\\\";

            }
            output+="\\end{pmatrix}";

        }

        public  void lowerMatrixPrinter (int length){
            output+="\\begin{pmatrix}";
            for (int i = 0; i< length; i++)
            {
                for (int j = 0; j < length; j++){
                    if (i== j)
                        output+= ("1& ");

                    else  if (j> i)
                    {
                        if (j<length-1)
                        {
                            output+= ("0& ");

                        }
                        else
                            output+= ("0 ");

                    }

                    else
                    {
                        output+= String.format("L_{%d,%d}& ",i+1,j+1);
                    }

                }
                output+="\\\\";

            }
            output+="\\end{pmatrix}";

        }

        public void goBack() {
            Main.window.setScene(Main.homepage);
        }
}
