package homepage;

import LUDecomposition.LUController;
import LUDecomposition.LUInverseController;
import Lagrange.LagrangeInterpolController;
import NDDInterpol.NDDController;
import com.proudapes.jlatexmathfx.Control.LateXMathControl;
import differentiation.FirstOrderDifferentiationController;
import direct.DirectInterpolController;
import false_position.FalsePositionController;
import gaussian.GaussSeidelController;
import integration.SimpsonThirdController;
import integration.TrapezoidalController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import newton.NewtonRaphsonController;
import runge_kutta.*;
import secant.SecantController;
import spline.CubicSplineController;
import spline.QuadSplineController;
import spline.SplineController;

import java.util.HashMap;

public class Main extends Application {
    public static Stage window = new Stage();
    public static Scene homepage;
    public static Scene bisectionScene;
    public static Scene falsePositionScene;
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
    public static Scene SplineScene;
    public static Scene QuadSplineScene;
    public static Scene CubicSplineScene;
    public static Scene DirectInterpolScene;
    public static Scene LUDecompositionScene;
    public static Scene InverseLUScene;
    public static Scene seidelScene;
    public static Scene trapezoidalScene;
    public static Scene simpson3rdScene;
    public static Scene simpson8thScene;

    public static Scene orderOneDifferenceScene;

    static HashMap<String, Scene> sceneMap = new HashMap<>();
    public static SimpleStringProperty output=new SimpleStringProperty("");
    public static LateXMathControl lateXMathControl = new LateXMathControl("");

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Parent root = FXMLLoader.load(Controller.class.getResource("sample.fxml"));
        Parent bisection = FXMLLoader.load(bisection.BisectionController.class.getResource("bisection.fxml"));
        Parent falsePosition = FXMLLoader.load(FalsePositionController.class.getResource("falsePosition.fxml"));
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
        Parent Spline = FXMLLoader.load(SplineController.class.getResource("spline.fxml"));
        Parent QuadSpline = FXMLLoader.load(QuadSplineController.class.getResource("quadSpline.fxml"));
        Parent CubicSpline = FXMLLoader.load(CubicSplineController.class.getResource("cubeSpline.fxml"));
        Parent DirectInterpol = FXMLLoader.load(DirectInterpolController.class.getResource("direct.fxml"));
        Parent LUDecomposition = FXMLLoader.load(LUController.class.getResource("decomposition.fxml"));
        Parent InverseLU = FXMLLoader.load(LUInverseController.class.getResource("inverseDecomp.fxml"));
        Parent Seidel = FXMLLoader.load(GaussSeidelController.class.getResource("seidel.fxml"));
        Parent Trapezoidal = FXMLLoader.load(TrapezoidalController.class.getResource("trapezoid.fxml"));
        Parent Simpson3rd = FXMLLoader.load(SimpsonThirdController.class.getResource("simpsonThird.fxml"));
        Parent Simpson8th = FXMLLoader.load(SimpsonThirdController.class.getResource("simpsonEight.fxml"));
        Parent OrderOneDifference = FXMLLoader.load(FirstOrderDifferentiationController.class.getResource("firstOrderDifferentiation.fxml"));
        Parent outputDisplay = FXMLLoader.load(output.OutputController.class.getResource("output.fxml"));
        homepage = new Scene(root, 800, 500);
        bisectionScene = new Scene(bisection, 1000, 850);
        falsePositionScene = new Scene(falsePosition,1000,850);
        eulerScene = new Scene(euler, 900, 850);
        gaussianScene = new Scene(gaussian,800,500);
        secantScene = new Scene(secant,1000,850);
        raphsonScene = new Scene(raphson,1000,850);
        kuttaMidpointScene = new Scene(kuttaMidpoint,900,850);
        kutta3Scene = new Scene(kutta3,900,850);
        kuttaHeunScene = new Scene(kuttaHeun,900,850);
        kutta4Scene = new Scene(kutta4,900,850);
        kuttaRalstonScene = new Scene(kuttaRalston,850,900);
        kuttaFehlbergScene = new Scene(kuttaFehlberg,850,900);
        LagrangeScene = new Scene(Lagranger,900,850);
        NDDScene = new Scene(NDD, 900,800);
        SplineScene = new Scene(Spline,900,800);
        QuadSplineScene = new Scene(QuadSpline,900,800);
        CubicSplineScene = new Scene(CubicSpline,900,800);
        DirectInterpolScene = new Scene(DirectInterpol,900,800);
        LUDecompositionScene = new Scene(LUDecomposition, 800,500);
        InverseLUScene = new Scene(InverseLU,800,500);
        seidelScene = new Scene(Seidel,800,500);
        trapezoidalScene = new Scene(Trapezoidal,900,850);
        simpson3rdScene = new Scene(Simpson3rd, 900,850);
        simpson8thScene = new Scene(Simpson8th, 900,850);
        orderOneDifferenceScene = new Scene(OrderOneDifference , 900,800);
        outputScene = new Scene(outputDisplay, 800, 800);

        //set scene map
        sceneMap.put("Bisection",Main.bisectionScene);
        sceneMap.put("False Position",falsePositionScene);
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
        sceneMap.put("Newton Divided Difference", NDDScene);
        sceneMap.put("Quadratic Spline", SplineScene);
        sceneMap.put("Improved Quadratic Spline", QuadSplineScene);
        sceneMap.put("Cubic Spline", CubicSplineScene);
        sceneMap.put("Direct Interpolation", DirectInterpolScene);
        sceneMap.put("LU Decomposition",LUDecompositionScene);
        sceneMap.put("Inverse with LU Decomposition",InverseLUScene);
        sceneMap.put("Gauss Seidel",seidelScene);
        sceneMap.put("Trapezoidal Integration",trapezoidalScene);
        sceneMap.put("First Order Divided Differences",orderOneDifferenceScene);
        sceneMap.put("Simpson 1/3 Rule",simpson3rdScene);
        sceneMap.put("Simpson 3/8 Rule",simpson8thScene);

        primaryStage.setTitle("Numerical Methods Calculator");
        primaryStage.setScene(homepage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
