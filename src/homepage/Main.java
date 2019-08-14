package homepage;

import Lagrange.LagrangeInterpolController;
import NDDInterpol.NDDController;
import com.proudapes.jlatexmathfx.Control.LateXMathControl;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import newton.NewtonRaphsonController;
import runge_kutta.*;
import secant.SecantController;

import java.util.HashMap;

public class Main extends Application {
    public static Stage window = new Stage();
    public static Scene homepage;
    public static Scene bisectionScene;
    public  static Scene eulerScene;
    public static Scene gaussianScene;
    public static Scene secantScene;
    public static  Scene raphsonScene;
    public static Scene outputScene;
    public static Scene kuttaMidpointScene;
    public static Scene kuttaHeunScene;
    public static Scene kuttaRalstonScene;
    public static Scene kutta3Scene;
    public static Scene kutta4Scene;
    public static Scene kuttaFehlbergScene;
    public static Scene NDDScene;
    public static Scene LagrangeScene;
    static HashMap<String, Scene> sceneMap = new HashMap<>();
    public static SimpleStringProperty output=new SimpleStringProperty("");
    public static LateXMathControl lateXMathControl = new LateXMathControl("");

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent root = FXMLLoader.load(Controller.class.getResource("sample.fxml"));
        Parent bisection = FXMLLoader.load(bisection.BisectionController.class.getResource("bisection.fxml"));
        Parent euler = FXMLLoader.load(euler.EulerController.class.getResource("euler.fxml"));
        Parent gaussian = FXMLLoader.load(gaussian.GaussianController.class.getResource("gaussian.fxml"));
        Parent secant = FXMLLoader.load(SecantController.class.getResource("secant.fxml"));
        Parent raphson =  FXMLLoader.load(NewtonRaphsonController.class.getResource("newton.fxml"));
        Parent kuttaMidpoint =FXMLLoader.load(RungeKuttaMidpointController.class.getResource("rungeMid.fxml"));
        Parent kuttaHeun =FXMLLoader.load(RKHeunController.class.getResource("rungeHeun.fxml"));
        Parent kuttaRalston =FXMLLoader.load(RKRalstonController.class.getResource("rungeRalston.fxml"));
        Parent kutta3 =FXMLLoader.load(RK3Controller.class.getResource("runge3rd.fxml"));
        Parent kutta4 =FXMLLoader.load(RK4Controller.class.getResource("runge4th.fxml"));
        Parent NDD = FXMLLoader.load(NDDController.class.getResource("NDDView.fxml"));
        Parent kuttaFehlberg =FXMLLoader.load(RKFehlberg.class.getResource("rungeFehl.fxml"));
        Parent Lagranger = FXMLLoader.load(LagrangeInterpolController.class.getResource("lagrange.fxml"));

        Parent outputDisplay = FXMLLoader.load(output.OutputController.class.getResource("output.fxml"));
        homepage = new Scene(root, 800, 500);
        bisectionScene = new Scene(bisection, 800, 500);
        eulerScene = new Scene(euler, 800, 500);
        gaussianScene = new Scene(gaussian,800,500);
        secantScene = new Scene(secant,800,500);
        raphsonScene = new Scene(raphson,800,500);
        kuttaMidpointScene = new Scene(kuttaMidpoint,800,500);
        kutta3Scene = new Scene(kutta3,800,500);
        kuttaHeunScene = new Scene(kuttaHeun,800,500);
        kutta4Scene = new Scene(kutta4,800,500);
        kuttaRalstonScene = new Scene(kuttaRalston,800,500);
        kuttaFehlbergScene = new Scene(kuttaFehlberg,800,500);
        LagrangeScene = new Scene(Lagranger,800,500);
        NDDScene = new Scene(NDD, 800,500);
        outputScene = new Scene(outputDisplay, 800, 500);

        //set scene map
        sceneMap.put("Bisection",Main.bisectionScene);
        sceneMap.put("Euler",eulerScene);
        sceneMap.put("Gaussian Elimination",gaussianScene);
        sceneMap.put("Secant",secantScene);
        sceneMap.put("NewtonRaphson",raphsonScene);
        sceneMap.put("Rk2 Midpoint", kuttaMidpointScene);
        sceneMap.put("Lagrangian Interpolation", LagrangeScene);
        sceneMap.put("Rk3",kutta3Scene);
        sceneMap.put("Rk4",kutta4Scene);
        sceneMap.put("Rk2 Ralston",kuttaRalstonScene);
        sceneMap.put("Rk2 Heun",kuttaHeunScene);
        sceneMap.put("Rk45",kuttaFehlbergScene);
        sceneMap.put("NDD", NDDScene);

        primaryStage.setTitle("Numerical Methods Calculator");
        primaryStage.setScene(homepage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
