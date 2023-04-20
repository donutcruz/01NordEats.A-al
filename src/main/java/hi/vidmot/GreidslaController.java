package hi.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Duration;
import vinnsla.Karfa;


/**
 * Byrjum á því að importa öll libraries
 */

import java.util.concurrent.atomic.AtomicInteger;


public class GreidslaController {
    private final Karfa karfa = new Karfa();

    public Label fxVidskiptavinur;
    public Label fxHeimilisfang;
    public ListView fxKarfa;
    @FXML
    private Label fxVerd;
    @FXML
    private Button fxStadfestaPontun;

    /**
     * Skilgreinum klasa GreislaController.
     * Upphafsstilla reglur sem tengja senurnar
     */

    public void initialize() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxKarfa.setItems(karfa.getVeitingar()); // tengja viðmót og vinnslu
        fxVerd.textProperty().bind(pontunController.getKarfa().heildarVerdProperty().asString());
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());
    }

    @FXML
    private void fxStadfestaPontunHandler(ActionEvent event) {
        AtomicInteger countdownSeconds = new AtomicInteger(15 * 60); // 15 minutes in seconds

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pöntun Staðfest");
        alert.setHeaderText(null);
        alert.setContentText("Yibbí Pöntun staðfest !\n" + "Áætluð afhending NördEats: " + String.format("%02d:%02d", countdownSeconds.get() / 60, countdownSeconds.get() % 60) + " mínutur");
        alert.show();


        // Búum til tímalínu
        

                // Bætum kóðanum okkar til að meðhöndla biðtímann

        Timeline timeline = null; // Initialize the timeline variable
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
            int i = countdownSeconds.getAndDecrement();
            if (countdownSeconds.get() == 0) {
                alert.close();
                finalTimeline.stop();
            } else {
                alert.setContentText("Yibbí Pöntun staðfest !\n" + "Áætluð afhending NördEats: " + String.format("%02d:%02d", countdownSeconds.get() / 60, countdownSeconds.get() % 60) + " mínutur");

            }
        }, new javafx.animation.KeyValue[]{}));
        timeline.setCycleCount(countdownSeconds.get());
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
