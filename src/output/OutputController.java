package output;

import com.proudapes.jlatexmathfx.Control.LateXMathControl;
import homepage.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;

public class OutputController {
    @FXML
    private StackPane outputPane = new StackPane();



    @FXML
    public void initialize(){
      //  Main.lateXMathControl.addL
        //bind label to Main.output
        Main.output.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 TeXFormula tex = new TeXFormula(newValue);
                java.awt.Image awtImage = tex.createBufferedImage(TeXConstants.STYLE_TEXT, 30, java.awt.Color.BLACK, null);
                Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);
                ImageView view = new ImageView(fxImage);
                System.out.println(newValue);
                System.out.println();
                outputPane.getChildren().clear();
                outputPane.getChildren().add(view);
            }
        });
    }

}
