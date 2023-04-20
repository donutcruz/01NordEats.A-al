package hi.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Byrjum á því að importa öll libraries
 */

public class GreidslaController {

    public Label fxVidskiptavinur;
    public Label fxHeimilisfang;
    public Label fxBidtimi;
    @FXML
    private Label fxVerd;

    private int countdownSeconds = 45 * 60; // 45 minutes in seconds
    private Timeline timeline; // Declare timeline as a member variable

    /**
     * Skilgreinum klasa GreislaController.
     * Upphafsstilla reglur sem tengja senurnar
     */

    public void initialize() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxVerd.textProperty().bind(pontunController.getKarfa().heildarVerdProperty().asString());
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());

        // Búum til tímalínu
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            countdownSeconds--;
            int minutes = countdownSeconds / 60;
            int seconds = countdownSeconds % 60;
            fxBidtimi.setText(String.format("%02d:%02d", minutes, seconds));
            if (countdownSeconds == 0) {
                timeline.stop(); // Stoppum tímann

                // Bætum kóðanum okkar til að meðhöndla biðtímann
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Handler fyrir að fara aftur í pöntunarsenu
     *
     * @param actionEvent
     */
    public void pontunHandler(ActionEvent actionEvent) {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        pontunController.taemaKorfu();
        ViewSwitcher.switchTo(View.PONTUN);
    }
}
