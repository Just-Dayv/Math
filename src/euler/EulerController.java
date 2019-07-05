package euler;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import org.mariuszgromada.math.mxparser.Function;

import java.util.regex.Pattern;

public class EulerController {


    @FXML
    private TextField initialValue;
    @FXML
    private TextField finalValue;
    @FXML
    private TextField equation;
    @FXML
    private TextField stepSize;
    Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]{0,4})?");

    @FXML
    public void initialize() {
        stepSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!validEditingState.matcher(newValue).matches()) {
//                    stepSize.setText(newValue.replaceAll("[^\\d+(\\.)\\d*]", ""));
                    stepSize.setText(oldValue);
                }
            }
        });

        stepSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                double finalVal = Double.parseDouble(finalValue.getText());
                double newVal = Double.parseDouble(newValue);
                if(finalVal%newVal!=0){
                    stepSize.setBackground(new Background(new BackgroundFill(Color.HOTPINK,null,null)));
                }
                else {
                    stepSize.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), null, null)));
                }
            }
        });
    }


    public void startEuler() {
        double yValue = Double.parseDouble(initialValue.getText());
        double xValue = Double.parseDouble(finalValue.getText());
        double stepGap = Double.parseDouble(stepSize.getText());
        String eqn = equation.getText();
        String output = "";

        output += "y(i+1) = y(i) + F(x,y) * step size\n\n";

        int iterations = (int) (xValue / stepGap);

        for (int i = 0; i < iterations + 1; i++) {

            output += "\n\nFor iteration " + i + " :\n\n";
            output += String.format("y%d = y%d + f(x%d,y%d) * stepSize\n", i + 1, i, i, i);
            output += String.format("y%d = %.2f + f(%.2f,%.2f) * %.2f\n", i + 1, yValue, i * stepGap, yValue, stepGap);
            yValue = calculateYValue(stepGap * i, yValue, eqn, stepGap);
            output += "y" + (i + 1) + " = " + yValue;

        }

        Main.output.set(output);
        Main.window.setScene(Main.outputScene);
    }

    public static double calculateYValue(double xValue, double yValue, String equationText, double stepGap) {
        Function function = new Function(equationText);
        return yValue + stepGap * function.calculate(xValue, yValue);
    }


}
