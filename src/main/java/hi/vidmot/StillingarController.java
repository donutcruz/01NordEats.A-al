package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

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

    public void initialize() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());

        // Add choices to the choice box
        fxGreidslaInfo.getItems().addAll("Við afhendingu", "Kortagreiðsla", "Aur");
    }

    public void getGredslumata(ActionEvent event) {
        String minnGreidslumataText = fxGreidslaInfo.getValue();
        minnGreidslumata.setText(minnGreidslumataText);
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

        // Set custom styles for the alert window
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("alert.css")).toExternalForm());
        dialogPane.getStyleClass().add("my-alert");
        alert.showAndWait();
    }
}