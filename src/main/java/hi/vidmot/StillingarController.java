package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StillingarController {
    public void saveSettings(ActionEvent actionEvent) {
    }

    public void fxVistaStillingarhandler(ActionEvent actionEvent) {
    }

    public void fxTilbakaHandler(ActionEvent actionEvent) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("pontun-view.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
}
