package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StillingarController {

    @FXML
    public Label fxVidskiptavinur;

    @FXML
    public Label fxHeimilisfang;

    @FXML
    public Label fxGreidslaInfo;
    public Button fxVistaStillingar;
    public Button fxBreytaStillingar;

    public void initialize() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());
    }

        public void fxBreytaStillingar (ActionEvent actionEvent){
        }

        public void fxTilbakaHandler (ActionEvent actionEvent) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("pontun-view.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    public void fxVistaStillingarhandler(ActionEvent actionEvent) {
    }
}