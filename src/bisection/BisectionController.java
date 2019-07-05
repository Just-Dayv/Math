package bisection;

//import lib.DifferentialFunction;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lib.DifferentialFunction;
import lib.EquationGenerator;



public class BisectionController {
    private static EquationGenerator equationGenerator;
    private static DifferentialFunction differentialFunction;

    @FXML
    private TextField initialValue;
    @FXML
    private TextField finalValue;
    @FXML
    private TextField equation;

   public void calculate(){
       String initValue = initialValue.getText();
       String finValue = finalValue.getText();
       String equ = equation.getText();

   }
}
