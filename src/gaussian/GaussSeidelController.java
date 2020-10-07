package gaussian;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;

import static gaussian.GaussianController.matrixDeterminant;

public class GaussSeidelController {

    @FXML
    private Button calculateButton;
    @FXML
    private TextArea equationArea;
    @FXML
    private Spinner variables;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField solutions;

    @FXML
    private TextField guesses;

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

        guesses.textProperty().addListener(new ChangeListener<String>() {
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
                    if((eqnVar.length==initValue)&&(totalValues == initValue*initValue) && matrixDeterminant(eqnMatrix)!=0
                            &&(solutions.getText().split(",").length== equations.length)&&(guesses.getText().split(",").length== equations.length) )
                    {
                        calculateButton.setDisable(false);
                    }
                    else {
                        calculateButton.setDisable(true);
                    }

                    if (!guesses.getText().isEmpty()){
                        if (convArray(solutions.getText().split(",")))
                        {
                            errorLabel.setText("");
                            guesses.setBackground(new Background(new BackgroundFill(Color.WHITE, cornerRadii, null)));
                            guesses.setBorder(border);

                        }
                        else
                        {
                            errorLabel.setText("Check that your values are comma separated decimals");
                            guesses.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                            guesses.setBorder(border);
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
                    guesses.setBackground(new Background(new BackgroundFill(Color.RED, cornerRadii, null)));
                    guesses.setBorder(border);
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

    public void calculate() {
        output = "\\begin{array}{l}";
        int initValue = (int)(variables.getValue());
        String [] eqnVar =  equationArea.getText().split(";");
        String [][] equations =  new String[initValue][initValue];
        double [][] eqnMatrix = new double[initValue][initValue];
        String [] solnString = solutions.getText().split(",");
        double [] solMatrix = new double[solnString.length];
        String [] guessesString = guesses.getText().split(",");
        double [] guessMatrix = new double[guessesString.length];

        for (int i = 0; i < initValue; i++) {
            equations[i] = eqnVar[i].split(",");
            for (int j = 0; j < initValue; j++) {

                eqnMatrix[i][j] = Double.parseDouble(equations[i][j]);
            }
        }
        for (int a = 0; a < initValue; a ++) {
            solMatrix[a] = Double.parseDouble(solnString[a]);
            guessMatrix[a] = Double.parseDouble(guessesString[a]);

        }

        printMatrix(eqnMatrix);
        printVarMatrix(initValue,"x");
        output+="=";
        printColumnMatrix(solMatrix);
        output+="\\\\";
        output+="\\\\";

        output+="Testing \\hspace{0.4cm}if\\hspace{0.4cm} [A]\\hspace{0.4cm} is \\hspace{0.4cm}diagonally \\hspace{0.4cm}dominant\\\\";

//        for (int i = 0, p=1; i< initValue; i++,p++){
//
//            if (!isSumGreater(eqnMatrix[i],i))
//                for (int j = 0; j < initValue; j++){
//                    if (j ==i)
//                        continue;
//                    eqnMatrix = swap(eqnMatrix,i,j);
//                    if(isSumGreater(eqnMatrix[i],i))
//                        break;
//                    printMatrix(eqnMatrix);
//                    output+="\\\\";
//                    output+="\\\\";
//                }
////            if (!isSumGreater(eqnMatrix[i],i))
////            {
////                eqnMatrix = swap(eqnMatrix,i,p);
////                while (!isSumGreater(eqnMatrix[i],i))
////                {
////                    eqnMatrix = swap(eqnMatrix,i,p++);
////                    if (p+1 == initValue)
////                        break;
////                }
////            }
//        }

        int [] index = new int[initValue];
        boolean succesfulSwap = true;
        for (int i = 0; i< initValue; i++){
            int p = 0;
            for (int j = 0; j < initValue; j++){
                if(isSumGreater(eqnMatrix[j],i))
                {
                    index[i] = j;
                    p++;
                }
            }

            if (p !=1)
            {
                succesfulSwap = false;
                output +="Matrix\\hspace{0.4cm} cannot\\hspace{0.4cm} be\\hspace{0.4cm} made\\hspace{0.4cm} diagonally\\hspace{0.4cm} dominant \\\\";
                break;
            }
            else
            {
                swap(eqnMatrix,index[i],i);
            }
        }

        if (succesfulSwap) {


            output += "[A]\\hspace{0.4cm} is\\hspace{0.4cm} now:\\\\ ";
            printMatrix(eqnMatrix);
            output += "\\\\";
            output += "\\\\";

            output += "Testing\\hspace{0.4cm} with\\hspace{0.4cm} initial\\hspace{0.4cm} guesses \\\\";
            printVarMatrix(initValue, "x");
            output += "=";
            printColumnMatrix(guessMatrix);
            output += "\\\\";
            double[] oldValues = new double[initValue];
            double[] approxError = new double[initValue];
            for (int i = 0; i < initValue; i++) {
                double sum = 0;
                output += String.format("x_{%d}= %.4f", i + 1, solMatrix[i]);
                for (int j = 0; j < initValue; j++) {
                    if (i == j)
                        continue;
                    sum += eqnMatrix[i][j] * guessMatrix[j];
                    output += String.format("-%.4f(%.4f)", eqnMatrix[i][j], guessMatrix[j]);
                }
                output += "\\\\";
                oldValues[i] = guessMatrix[i];
                guessMatrix[i] = (solMatrix[i] - sum) / eqnMatrix[i][i];
                output += String.format("x_{%d} = \\frac{%.2f-%.2f}{%.2f}= %.4f\\\\", i + 1, solMatrix[i], sum, eqnMatrix[i][i], guessMatrix[i]);
                output += "\\\\";

            }

            for (int i = 0; i < initValue; i++) {
                approxError[i] = calcPercentError(guessMatrix[i], oldValues[i]);
            }

            Arrays.sort(approxError);

            while (approxError[initValue - 1] > 1) {
                output += "Testing\\hspace{0.4cm} with\\hspace{0.4cm} new\\hspace{0.4cm}values \\\\";
                printVarMatrix(initValue, "x");
                output += "=";
                printColumnMatrix(guessMatrix);
                output += "\\\\";
                for (int i = 0; i < initValue; i++) {
                    double sum = 0;
                    output += String.format("x_{%d}= %.4f", i + 1, solMatrix[i]);
                    for (int j = 0; j < initValue; j++) {
                        if (i == j)
                            continue;
                        sum += eqnMatrix[i][j] * guessMatrix[j];
                        output += String.format("-%.4f(%.4f)", eqnMatrix[i][j], guessMatrix[j]);
                    }
                    output += "\\\\";
                    oldValues[i] = guessMatrix[i];
                    guessMatrix[i] = (solMatrix[i] - sum) / eqnMatrix[i][i];
                    output += String.format("x_{%d} = \\frac{%.2f-%.2f}{%.2f}= %.4f\\\\", i + 1, solMatrix[i], sum, eqnMatrix[i][i], guessMatrix[i]);
                    output += "\\\\";

                }

                for (int i = 0; i < initValue; i++) {
                    approxError[i] = calcPercentError(guessMatrix[i], oldValues[i]);
                }

                Arrays.sort(approxError);
            }
        }
        output+= "\\end{array}";

        Main.output.set(output);
        Stage stage = new Stage();
        stage.setScene(Main.outputScene);
        stage.show();

    }

    public boolean isSumGreater(double [] array, int index){
        double sum = 0;
        for (int i = 0; i<array.length; i++)
        {
            if (i == index)
                continue;
            sum += array[i];
        }
        if (sum< array[index])
            return true;
        else
            return false;
    }

    private double[][] swap(double[][] matrix, int row1, int row2) {
       // output+= String.format("Swapping\\hspace{0.4cm} row\\hspace{0.4cm} %d\\hspace{0.4cm} and \\hspace{0.4cm}row\\hspace{0.4cm} %d\\\\",row1+1,row2+1);
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
        return matrix;
    }

    public double calcPercentError(double x2, double x1 ){
        output+= (" Calculating\\hspace{0.4cm} absolute\\hspace{0.4cm}relative \\hspace{0.4cm}approximate\\hspace{0.4cm} error \\hspace{0.4cm}\\left|\\varepsilon\\right|: \\\\");
        output += "\\frac{{x_m}^{new} \\hspace{0.4cm}- \\hspace{0.4cm}{x_m}^{old}}{{x_m}^{new}} * 100\\\\";
        output+= String.format("\\left|\\frac{(%.8f - %.8f)}{%.8f}\\right| * 100 = %.2f \\\\", x2,x1,x2 , Math.abs(((x2-x1)/x2) * 100));
        output+="\\\\" ;
        output+="\\\\" ;
        return Math.abs(((x2-x1)/x2) * 100);
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
    public void goBack() {
        Main.window.setScene(Main.homepage);
    }

}
