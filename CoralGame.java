import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;

/**
 The CoralGame class creates the model, view, and controller, allowing for the game to launch and be played. 

 @author: Charlotte Crawford
 @date: 12/2/2024


 @input: stage
 @output: launch game

 */

public class CoralGame extends Application
{
    @Override
    public void start(Stage stage)
    {
        CoralModel model = new CoralModel();
        CoralView view = new CoralView();
        CoralController controller = new CoralController(model, view);

        // Starts the view (UI)
        view.start(stage);
    }

    public static void main(String args[])
    {
        launch(args);
    }
}