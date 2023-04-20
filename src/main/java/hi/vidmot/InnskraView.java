package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import vinnsla.Vidskiptavinur;

import java.util.Optional;

/**
 * Byrjum að importa libraries
 */

public class InnskraView {

    @FXML
    private Button fxInnskra;


    private Vidskiptavinur vidskiptavinur = null;




    public void fxInnskraHandler(ActionEvent actionEvent) {
        if (vidskiptavinur == null) {
            nyrVidskiptavinur();
        } else {
            skraInn();
        }
        if (vidskiptavinur !=null)
            ViewSwitcher.switchTo(View.PONTUN);
    }

    /**
     * Innskrá viðskiptavin
     */
    private  void skraInn() {
        LoginDialog login = new LoginDialog();
        TextInputDialog t = login.upphafsstilla();
        Optional<String> o = t.showAndWait();
        o.ifPresent((value)-> {fxInnskra.setText("innskráð(ur) "+vidskiptavinur.getNafn());});
    }

    private void nyrVidskiptavinur() {
        vidskiptavinur = new Vidskiptavinur("", "");
        Optional<Vidskiptavinur> v = new VidskiptavinurDialog(vidskiptavinur).showAndWait();
        v.ifPresent(value -> fxInnskra.setText(value.getNafn()));
    }

    public Vidskiptavinur getVidskiptavinur() {
        return vidskiptavinur;
    }
}
