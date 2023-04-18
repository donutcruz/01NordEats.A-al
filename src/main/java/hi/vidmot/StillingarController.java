package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StillingarController {

    private PontunController pontunController;

    public void setPontunController(PontunController pontunController) {
        this.pontunController = pontunController;
    }

    @FXML
    private AnchorPane stillingarAnchorPane;

    @FXML
    private Label fxNafn;

    @FXML
    private Label fxHeimilisfang;

    @FXML
    private Label fxPnr;

    @FXML
    private Label fxNetfang;

    @FXML
    private Button fxBreytaStillingar;

    @FXML
    private Button fxVistaStillingar;

    @FXML
    private void fxVistaStillingarhandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stillingar vistaðar");
        alert.setHeaderText(null);
        alert.setContentText("Stillingar hafa verið vistaðar!");
        alert.showAndWait();
    }

    @FXML
    private void fxBreytaStillingar(ActionEvent event) {
        fxNafn.setDisable(false);
        fxHeimilisfang.setDisable(false);
        fxPnr.setDisable(false);
        fxNetfang.setDisable(false);
        fxBreytaStillingar.setDisable(true);
        fxVistaStillingar.setDisable(false);
    }

    @FXML
    private void fxTilbakaHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.PONTUN);
    }
}
