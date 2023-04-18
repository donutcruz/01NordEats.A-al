package hi.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import vinnsla.Vidskiptavinur;

import java.io.IOException;
import java.util.Optional;

/******************************************************************************
 *  Lýsing  : Dialogur til að skrá viðskiptavin
 *
 *
 *****************************************************************************/
public class VidskiptavinurDialog extends Dialog<Vidskiptavinur> {

    @FXML
    private ButtonType fxILagi;
    @FXML
    private TextField fxNafn;
    @FXML
    private TextField fxHeimilisfang;

    private final Vidskiptavinur vidskiptavinur;

    /**
     * Lesa inn notendaviðmótið, setja reglu sem bindur gögn við viðmót og
     * nær gögnum úr dialognum
     * @param v
     */
    public VidskiptavinurDialog(Vidskiptavinur v) {
        vidskiptavinur = v;
        setDialogPane(lesaDialog());
        setPropertyBinding();
        setResultConverter();
        // sett regla um hvenær í lagi hnappur er virkur
        iLagiRegla();
    }
    public void BreytaStillingVidskiptavinur() {
        Optional<Vidskiptavinur> result = showAndWait();
        if (result.isPresent()) {
            // Update the Vidskiptavinur object with the new values
            vidskiptavinur.setNafn(fxNafn.getText());
            vidskiptavinur.setHeimilisfang(fxHeimilisfang.getText());
        }
    }


    /**
     * Í lagi hnappur er aðeins virkur ef búið er að setja inn gögn í nafn og heimilisfang
     */
    private void iLagiRegla() {
        Node iLagi = getDialogPane().lookupButton(fxILagi);
        iLagi.disableProperty()
                .bind(fxNafn.textProperty().isEmpty()
                        .or(fxHeimilisfang.textProperty().isEmpty()));
    }

    /**
     * Dialogsviðmót er lesið inn
     * @return
     */
    private DialogPane lesaDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vidskiptavinur-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Viðskiptavinagögnin eru bundin við dialoginn
     */
    private void setPropertyBinding() {
        fxNafn.textProperty().bindBidirectional(vidskiptavinur.nafnProperty());
        fxHeimilisfang.textProperty().bindBidirectional(vidskiptavinur.heimilisfangProperty());
    }

    /**
     * Niðurstöðurnar fengnar úr dialognum
     */
    private void setResultConverter() {
        setResultConverter(b -> {                                 // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return vidskiptavinur; // þarf ekki að setja gögn í vidksiptavin vegna binding
            } else {
                return null;
            }
        });         // endir á d.setResultConverter
    }
}

