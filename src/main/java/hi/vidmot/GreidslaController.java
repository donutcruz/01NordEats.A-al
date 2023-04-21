package hi.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.text.BreakIterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class GreidslaController {

    public Label fxVidskiptavinur;
    public Label fxHeimilisfang;
    public ListView fxGreidsluKarfa;
    @FXML
    private Label fxVerd;
    @FXML
    private Button fxStadfestaPontun;
    @FXML
    private Button fxHaettaVid;
    @FXML
    private ChoiceBox<String> fxGreidslumataInfo;
    private BreakIterator minnGreidslumata;


    /**
     * upphafsstilla reglur sem tengja senurnar
     */
    public void initialize() {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        fxVerd.textProperty().bind(pontunController.getKarfa().heildarVerdProperty().asString());
        fxVidskiptavinur.textProperty().bind(pontunController.getVidskiptavinur().nafnProperty());
        fxHeimilisfang.textProperty().bind(pontunController.getVidskiptavinur().heimilisfangProperty());

        // Add choices to the choice box
        fxGreidslumataInfo.getItems().addAll("Við afhendingu", "Kortagreiðsla", "Aur");
    }
    public void getGredslumata(ActionEvent event) {
        String minnGreidslumataText = fxGreidslumataInfo.getValue();
        minnGreidslumata.setText(minnGreidslumataText);
    }

    @FXML
    private void fxStadfestaPontunHandler(ActionEvent event) {
        AtomicInteger countdownSeconds = new AtomicInteger(15 * 60); // 15 minutes in seconds

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pöntun Staðfest");
        alert.setHeaderText(null);
        alert.setContentText("Yibbí Pöntun staðfest !\n" + "Áætluð afhending NördEats: " + String.format("%02d:%02d", countdownSeconds.get() / 60, countdownSeconds.get() % 60));
        alert.show();

        // Set custom styles for the alert window
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("alert.css")).toExternalForm());
        dialogPane.getStyleClass().add("my-alert");

        alert.show();


        Timeline finalTimeline = null;
        // Initialize the timeline variable
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
            countdownSeconds.getAndDecrement();
            if (countdownSeconds.get() == 0) {
                alert.close();
                finalTimeline.stop();
            } else {
                alert.setContentText("Yibbí Pöntun staðfest !\n" + "Áætluð afhending NördEats: " + String.format("%02d:%02d", countdownSeconds.get() / 60, countdownSeconds.get() % 60) + "Mínutur");
            }
        }));
        timeline.setCycleCount(countdownSeconds.get());
        timeline.play();
    }

    /**
     * Handler fyrir að fara aftur í pöntunarsenu
     *
     */
    public void pontunHandler(ActionEvent ignoredActionEvent) {
        PontunController pontunController = (PontunController) ViewSwitcher.lookup(View.PONTUN);
        pontunController.taemaKorfu();
        ViewSwitcher.switchTo(View.PONTUN);
    }

    public void HaettaVidHandler(ActionEvent event) {
        Platform.exit();
    }
}