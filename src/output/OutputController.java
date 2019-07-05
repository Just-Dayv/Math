package output;

import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OutputController {
    @FXML
    private Label outputLabel = new Label();

    @FXML
    public void initialize(){
        //bind label to Main.output
        Main.output.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                outputLabel.setText(newValue);
            }
        });
    }

}
