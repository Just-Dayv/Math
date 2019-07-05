package homepage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import java.util.HashMap;

public class Controller {
    @FXML
    private ComboBox categories;
    @FXML
    private ComboBox methods;

    ObservableList<String> observableArrayList = FXCollections.observableArrayList();
    HashMap<String,String[]> categoryMap = new HashMap<>();

    @FXML
    public void initialize(){
        //set category map
        categoryMap.put("ODE",new String[]{"Euler","Rk2","Rk3","Rk4"});
        categoryMap.put("Nonlinear Methods",new String[]{"Bisection"});
        categoryMap.put("System of Equations",new String[]{"Gaussian Elimination","RREF"});


        methods.setItems(observableArrayList);
        categories.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                observableArrayList.clear();
                observableArrayList.addAll(categoryMap.get(categories.getValue()));
            }
        });
    }

    public void proceed(){
        String methodName = this.methods.getValue().toString();
        System.out.println(methodName);
        Scene s = Main.sceneMap.get(methodName);
        Main.window.setScene(s);
    }


}
