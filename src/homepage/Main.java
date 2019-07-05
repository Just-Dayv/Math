package homepage;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {
    public static Stage window = new Stage();
    public static Scene homepage;
    public static Scene bisectionScene;
    public  static Scene eulerScene;
    public static Scene outputScene;
    static HashMap<String, Scene> sceneMap = new HashMap<>();
    public static SimpleStringProperty output=new SimpleStringProperty("");

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent root = FXMLLoader.load(Controller.class.getResource("sample.fxml"));
        Parent bisection = FXMLLoader.load(bisection.BisectionController.class.getResource("bisection.fxml"));
        Parent euler = FXMLLoader.load(euler.EulerController.class.getResource("euler.fxml"));
        Parent output = FXMLLoader.load(output.OutputController.class.getResource("output.fxml"));

        homepage = new Scene(root, 800, 500);
        bisectionScene = new Scene(bisection, 800, 500);
        eulerScene = new Scene(euler, 800, 500);
        outputScene = new Scene(output, 800, 500);

        //set scene map
        sceneMap.put("Bisection",Main.bisectionScene);
        sceneMap.put("Euler",eulerScene);

        primaryStage.setTitle("MathCalc");
        primaryStage.setScene(homepage);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
