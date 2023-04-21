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

/**
 * Controller fyrir pöntunarsenuna
 */
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

    /**
     * Tengir saman viðmót og vinnslu
     * @throws IOException
     */

    public void initialize() throws IOException {
        fxKarfa.setItems(karfa.getVeitingar()); // tengja viðmót og vinnslu
        fxSamtals.textProperty().bind(karfa.heildarVerdProperty().asString()); // bindum saman körfu
        InnskraController inn = (InnskraController) ViewSwitcher.lookup(View.INNSKRA);
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
     *
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
        // If a user is already logged in, display their name
        if (Vidskiptavinur.getCurrentUser() != null) {
            fxInnskraning.setText("innskráð(ur) " + Vidskiptavinur.getCurrentUser().getNafn());
        }
        // If no user is logged in, prompt for login credentials
        else {
            LoginDialog login = new LoginDialog();
            TextInputDialog t = login.upphafsstilla();
            Optional<String> o = t.showAndWait();
            o.ifPresent((value) -> {
                // Set the current user and display their name
                Vidskiptavinur.setCurrentUser(vidskiptavinur);
                fxInnskraning.setText("innskráð(ur) " + vidskiptavinur.getNafn());
            });
        }
    }

    /**
     * Skrá nýjan viðskiptavin
     */
    private void nyrVidskiptavinur() {
        vidskiptavinur = new Vidskiptavinur("", "");
        Optional<Vidskiptavinur> v = new VidskiptavinurDialog(vidskiptavinur).showAndWait();
        v.ifPresent(value -> fxInnskraning.setText(value.getNafn()));
    }

    /**
     * Notandi setur í körfuna og vistar vöruval
     * @return
     */

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
        //breytum yfir í Stillingar senu og prentum út senuna sem við erum að skipta
        System.out.println("Switching to stillingar-view.fxml");
        ViewSwitcher.switchTo(View.STILLINGAR);
        // náum í rótina af Stillingar senunni frá cache
        Parent stillingarRoot = ViewSwitcher.lookupRoot(View.STILLINGAR);
    }
}
