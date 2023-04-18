package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import vinnsla.Karfa;
import vinnsla.Veitingar;
import vinnsla.Vidskiptavinur;

import javax.swing.tree.DefaultTreeModel;
import java.io.IOException;
import java.util.Optional;

/******************************************************************************
 *
 *  Lýsing  : Controller fyrir pöntunarsenuna
 *
 *
 *****************************************************************************/
public class PontunController {

    public Button fxStillingar;
    @FXML
    private Button fxInnskraning; // innskráningarhnappur sem breytist þegar viðskiptavinur er loggaður inn
    @FXML
    private ListView<Veitingar> fxKarfa; // karfa með veitingum

    @FXML
    private Label fxSamtals;    // samtals verð á veitingum í körfu
    @FXML
    private MatsedillView fxMatsedill;  // matseðillinn

    // Vinnsluhlutir
    private final Karfa karfa = new Karfa();
    private Vidskiptavinur vidskiptavinur = null;
    private DefaultTreeModel scene;

    public void initialize() throws IOException {
        fxKarfa.setItems(karfa.getVeitingar()); // tengja viðmót og vinnslu
        fxSamtals.textProperty().bind(karfa.heildarVerdProperty().asString()); // bindum saman körfu
        InnskraView inn = (InnskraView) ViewSwitcher.lookup(View.INNSKRA);
        vidskiptavinur = inn.getVidskiptavinur();
        fxInnskraning.setText(vidskiptavinur.getNafn());
    }


    /**
     * Greiða fyrir veitingar. Skiptir yfir í greiðslusenu
     *
     * @param actionEvent
     */
    public void fxGreidaHandler(ActionEvent actionEvent) {
        System.out.println("Greiða button clicked");
        {
            System.out.println("Switching to greida-view.fxml");
            ViewSwitcher.switchTo(View.GREIDSLA);
        }
    }


    /**
     * s
     * Setja valdar veitingar í körfu  ef eitthvað er valið
     *
     * @param actionEvent
     */
    public void fxSetjaKorfuHandler(ActionEvent actionEvent) {
        if (fxMatsedill.getSelectionModel().getSelectedItem() != null)
            fxKarfa.getItems().add(fxMatsedill.getSelectionModel().getSelectedItem());
    }

    /**
     * Taka valdar veitingar úr körfu  ef eitthvað er valið
     *
     * @param actionEvent
     */
    public void fxTakaUrKorfuHandler(ActionEvent actionEvent) {
        if (fxKarfa.getSelectionModel().getSelectedItems() != null)
            fxKarfa.getItems().remove(fxKarfa.getSelectionModel().getSelectedItem());
    }

    /**
     * Ef enginn viðskiptavinur er til þá er skráður nýr viðskiptavinur. Annars er hægt
     * að skrá sig inn
     *
     * @param actionEvent
     */
    public void fxInnskraningHandler(ActionEvent actionEvent) {
        if (vidskiptavinur == null) {
            nyrVidskiptavinur();
        } else {
            skraInn();
        }
    }

    /**
     * Innskrá viðskiptavin
     */
    private void skraInn() {
        LoginDialog login = new LoginDialog();
        TextInputDialog t = login.upphafsstilla();
        Optional<String> o = t.showAndWait();
        o.ifPresent((value) -> {
            fxInnskraning.setText("innskráð(ur) " + vidskiptavinur.getNafn());
        });
    }

    /**
     * Skrá nýjan viðskiptavin
     */
    private void nyrVidskiptavinur() {
        vidskiptavinur = new Vidskiptavinur("", "");
        Optional<Vidskiptavinur> v = new VidskiptavinurDialog(vidskiptavinur).showAndWait();
        v.ifPresent(value -> fxInnskraning.setText(value.getNafn()));
    }

    public Karfa getKarfa() {
        return karfa;
    }

    public Vidskiptavinur getVidskiptavinur() {
        return vidskiptavinur;
    }

    public void taemaKorfu() {
        fxKarfa.getItems().removeAll(fxKarfa.getItems());
    }

    public void fxStillingarHandler(ActionEvent actionEvent) {
        System.out.println("Stillingar button clicked");
        // switch to the Stillingar scene and print out what scene you are switching to
        System.out.println("Switching to stillingar-view.fxml");
        ViewSwitcher.switchTo(View.STILLINGAR);
        // get the root of the Stillingar scene from the cache
        Parent stillingarRoot = ViewSwitcher.lookupRoot(View.STILLINGAR);
    }
}