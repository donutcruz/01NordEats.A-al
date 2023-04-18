package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

        public void fxTilbakaHandler (ActionEvent actionEvent) {
            ViewSwitcher.switchTo(View.PONTUN);
        }

    public void fxVistaStillingarhandler(ActionEvent actionEvent) {
    }
}