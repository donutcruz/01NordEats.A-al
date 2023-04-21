package hi.vidmot;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import vinnsla.Vidskiptavinur;

import java.util.Optional;


public class InnskraController {

    @FXML
    private Button fxInnskra;
    @FXML
    private Button fxHaettavid;

    // Breyta sem heldur utan um núverandi viðskiptavin
    private Vidskiptavinur vidskiptavinur = null;

    // Aðferð fyrir "Hætta við" takka sem slekkur á forritinu.
    @FXML
    private void fxHaettaVidHandler(ActionEvent event) {
        Platform.exit();
    }

    // Aðferð sem keyrir þegar innskráningartakki er ýtt á
    public void fxInnskraHandler(ActionEvent actionEvent) {
        if (vidskiptavinur == null) {
            // Ef enginn viðskiptavinur er skráður inn, búum til nýjan
            nyrVidskiptavinur();
        } else {
            // Annars skráum við þennan viðskiptavin inn
            skraInn();
        }
            ViewSwitcher.switchTo(View.PONTUN);
        }

    // Aðferð sem skráir inn núverandi viðskiptavin
    private void skraInn() {
        // Búum til nýjan glugga fyrir innskráningu
        LoginDialog login = new LoginDialog();
        TextInputDialog t = login.upphafsstilla();

        // Sýnum gluggann og bíðum eftir notendainntaki
        Optional<String> o = t.showAndWait();

            // Sækjum viðskiptavini sem er skráður inn
            Vidskiptavinur currentUser = getVidskiptavinur();

            // Uppfærum nafn viðskiptavin sem er skráður inn
            currentUser.setNotendanafn(t.getEditor().getText());
        }


    // Aðferð sem býr til nýjan viðskiptavin
    private void nyrVidskiptavinur() {
        // Búum til viðskiptavini með tómum upplýsingum
        vidskiptavinur = new Vidskiptavinur("", "");
        // Búum til glugga sem biður um upplýsingar um nýjan viðskiptavin
        Optional<Vidskiptavinur> v = new VidskiptavinurDialog(vidskiptavinur).showAndWait();
        // Ef notandi slær inn upplýsingar, uppfærum viðskiptavini og takkann sem sýnir nafn viðskiptavins
        v.ifPresent(value -> fxInnskra.setText(value.getNafn()));
    }

    // Aðferð sem skilar núverandi viðskiptavini
    public Vidskiptavinur getVidskiptavinur() {
        return vidskiptavinur;
    }

}
