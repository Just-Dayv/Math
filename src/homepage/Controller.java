package homepage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.HashMap;

public class Controller {
    @FXML
    private Button proceedButton;
    @FXML
    private ComboBox categories;
    @FXML
    private ComboBox methods;

    ObservableList<String> observableArrayList = FXCollections.observableArrayList();
    HashMap<String, String[]> categoryMap = new HashMap<>();

    @FXML
    public void initialize() {

        proceedButton.setDisable(true);
        //set category map
        categoryMap.put("ODE", new String[]{"Euler", "Rk2 Midpoint", "Rk2 Heun", "Rk2 Ralston", "Rk3", "Rk4"});
        categoryMap.put("Nonlinear Methods", new String[]{"Bisection", "NewtonRaphson", "Secant"});
        categoryMap.put("Interpolation", new String[]{"Lagrangian Interpolation", "NDD"});
        categoryMap.put("System of Equations", new String[]{"Gaussian Elimination"});


        methods.setItems(observableArrayList);
        categories.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                observableArrayList.clear();
                observableArrayList.addAll(categoryMap.get(categories.getValue()));
            }
        });
        methods.valueProperty().addListener(new ChangeListener() {
                                                @Override
                                                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                                    proceedButton.setDisable(false);
                                                }
                                            }
        );
    }

    public void proceed() {
        String methodName = this.methods.getValue().toString();
        System.out.println(methodName);
        Scene s = Main.sceneMap.get(methodName);
        Main.window.setScene(s);
        proceedButton.setDisable(true);
    }


}
