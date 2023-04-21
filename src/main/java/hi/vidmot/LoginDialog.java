package hi.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Búum til glugga fyrir innskráningu
 * Notandi gefur upp nafn og heimilisfang(lykilorð)
 */
public class LoginDialog {
    private TextField usernameField;


    /**
     * Búum til Textdialog þar sem LoginDialog er lesið
     * @param Lesa LoginDialog
     * @param Buttons til að velja á milli "Í lagi" "Hætta við"
     * @return login-view.fxml skráin kemur
     */

    public TextInputDialog upphafsstilla() {
        TextInputDialog dialog = new TextInputDialog();
        DialogPane dialogPane = lesaLoginDialog().getDialogPane();
        dialogPane.getButtonTypes().setAll(
                new ButtonType("Í lagi", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE));
        dialogPane.setContent(usernameField);
        dialog.setDialogPane(dialogPane);

        // Bætum við event handler til að eyða hnappi til að loka forritinu
        Button cancelButtonNode = (Button) dialogPane.lookupButton(dialogPane.getButtonTypes().get(1));
        cancelButtonNode.setOnAction(event -> System.exit(0));

        return dialog;
    }

    private Dialog<?> lesaLoginDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
