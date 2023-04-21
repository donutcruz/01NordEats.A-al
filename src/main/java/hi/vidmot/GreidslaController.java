package hi.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Skilgreinum klasa sem sér um Greiðsluna
 * Upphafsstillum reglur sem tengja senurnar
 */

public class GreidslaController {

    public Label fxVidskiptavinur;
    public Label fxHeimilisfang;
    public Label fxBidtimi;
    @FXML
    private Label fxVerd;
    @FXML
    private Button fxStadfestaPontun;


    public void initialize() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxVerd.textProperty().bind(pontunController.getKarfa().heildarVerdProperty().asString());
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());
    }

    /**
     *
     * @param event þegar að notandi hefur staðfest pöntun byrjar timerinn
     * @return afhendingartími pöntunarinnar
     */

    @FXML
    private void fxStadfestaPontunHandler(ActionEvent event) {
        AtomicInteger countdownSeconds = new AtomicInteger(15 * 60); // 15 mínútur í sekúndur

        Alert alert = new Alert(Alert.AlertType.INFORMATION); //Stillum viðvöruskilaboðin eftir að Pöntun sé staðfest
        alert.setTitle("Pöntun Staðfest");
        alert.setHeaderText(null);
        alert.setContentText("Yibbí Pöntun staðfest !\n" + "Áætluð afhending NördEats: " + String.format("%02d:%02d", countdownSeconds.get() / 60, countdownSeconds.get() % 60));
        alert.show();

        Timeline timeline = null; // Frumstillum tímalínu breytuna
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
            int i = countdownSeconds.getAndDecrement();
            if (countdownSeconds.get() == 0) {
                alert.close();
                finalTimeline.stop();
            } else {
                alert.setContentText("Yibbí Pöntun staðfest !\n" + "Áætluð afhending NördEats: " + String.format("%02d:%02d", countdownSeconds.get() / 60, countdownSeconds.get() % 60));
            }
        }, new javafx.animation.KeyValue[]{}));
        timeline.setCycleCount(countdownSeconds.get());
        timeline.play();
    }

    /**
     * Handler fyrir að fara aftur í pöntunarsenu
     *
     * @param actionEvent
     *
     */
    public void pontunHandler(ActionEvent actionEvent) {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        pontunController.taemaKorfu();
        ViewSwitcher.switchTo(View.PONTUN);
    }
}
