package hi.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class CircleImage extends Application {
    @Override
    public void main(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("FXMLDocument.fxml")));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
