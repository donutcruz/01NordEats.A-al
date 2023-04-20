package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import vinnsla.Vidskiptavinur;

import java.util.Optional;

public class InnskraController {

    @FXML
    private Button fxInnskra;

    // Breyta sem heldur utan um núverandi viðskiptavin
    private Vidskiptavinur vidskiptavinur = null;

    // Aðferð sem keyrir þegar innskráningartakki er ýtt á
    public void fxInnskraHandler(ActionEvent actionEvent) {
        if (vidskiptavinur == null) {
            // Ef enginn viðskiptavinur er skráður inn, búum til nýjan
            nyrVidskiptavinur();
        } else {
            // Annars skráum við þennan viðskiptavin inn
            skraInn();
        }
        if (vidskiptavinur !=null) {
            // Skiptum um sjónarsvið í viðskiptavinskerfi
            ViewSwitcher.switchTo(View.PONTUN);
        }
    }

    // Aðferð sem skráir inn núverandi viðskiptavin
    private void skraInn() {
        // Búum til nýjan glugga fyrir innskráningu
        LoginDialog login = new LoginDialog();
        TextInputDialog t = login.upphafsstilla();

        // Sýnum gluggann og bíðum eftir notendainntaki
        Optional<String> o = t.showAndWait();

        // Ef notendur slær inn lykilorð, uppfærum viðskiptavini og takkann sem sýnir nafn viðskiptavins
        o.ifPresent((password) -> {
            // Sækjum viðskiptavini sem er skráður inn
            Vidskiptavinur currentUser = getVidskiptavinur();

            // Uppfærum notandanafn og lykilorð viðskiptavinar
            currentUser.setNotendanafn(t.getEditor().getText());
            currentUser.setLykilord(password);

            // Uppfærum texta á innskrá takkanum
            fxInnskra.setText("innskráð(ur) " + currentUser.getNafn());
        });
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
