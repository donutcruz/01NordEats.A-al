package hi.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import vinnsla.Karfa;
import vinnsla.Veitingar;
import vinnsla.Vidskiptavinur;

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
        if (vidskiptavinur !=null)
            ViewSwitcher.switchTo(View.GREIDSLA);
    }


    /**s
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
    private  void skraInn() {
        LoginDialog login = new LoginDialog();
        TextInputDialog t = login.upphafsstilla();
        Optional<String> o = t.showAndWait();
        o.ifPresent((value)-> {fxInnskraning.setText("innskráð(ur) "+vidskiptavinur.getNafn());});
    }

    /**
     * Skrá nýjan viðskiptavin
     *
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
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stillingar-view.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the stage from the button's action event
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}