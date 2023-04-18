package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StillingarController {

    @FXML
    public TextField fxVidskiptavinur;

    @FXML
    public TextField fxHeimilisfang;

    @FXML
    public TextField fxGreidslaInfo;
    public Button fxVistaStillingar;
    public Button fxBreytaStillingar;

    public void initialize() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());
    }

    @FXML
    private void fxBreytaStillingar(ActionEvent event) {
        fxVidskiptavinur.setDisable(false);
        fxHeimilisfang.setDisable(false);
        fxBreytaStillingar.setDisable(true);
        fxVistaStillingar.setDisable(false);
    }

        public void fxTilbakaHandler (ActionEvent actionEvent) {
            ViewSwitcher.switchTo(View.PONTUN);
        }

    @FXML
    private void fxVistaStillingarhandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stillingar vistaðar");
        alert.setHeaderText(null);
        alert.setContentText("Stillingar hafa verið vistaðar!");
        alert.showAndWait();
    }
}