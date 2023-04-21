package hi.vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * Búum til klasa StillingarController sem inniheldur Textfield af Viðskiptavinur, heimilisfang osfrv.
 *
 */

public class StillingarController {
    @FXML
    public TextField fxVidskiptavinur;

    @FXML
    public TextField fxHeimilisfang;

    @FXML
    public ChoiceBox<String> fxGreidslaInfo;
    public Label minnGreidslumata;

    @FXML
    public Button fxVistaStillingar;

    @FXML
    public Button fxBreytaStillingar;

    /**
     * Búum til pontunController sem að inniheldur grunnupplýsingar um innskráðan notananda
     */

    public void initialize() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());

        // Bætum við valkosti í valkostaboxið
        fxGreidslaInfo.getItems().addAll("Við afhendingu", "Kortagreiðsla", "Aur");
    }

    public void getGredslumata(ActionEvent event) {
        String minnGreidslumataText = (String) fxGreidslaInfo.getValue();
        minnGreidslumata.setText(minnGreidslumataText);
    }

    @FXML
    //Notandi getur breytt upplýsingum um sig
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
    //Notandi fær tilkynningu (alert) um vistaðar stillingar
    private void fxVistaStillingarhandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stillingar vistaðar");
        alert.setHeaderText(null);
        alert.setContentText("Stillingar hafa verið vistaðar!");
        alert.showAndWait();
    }
}
