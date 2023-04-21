package hi.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Búum til glugga fyrir innskráningu
 */
public class LoginDialog {
    private TextField usernameField;


    public TextInputDialog upphafsstilla() {
        TextInputDialog dialog = new TextInputDialog();
        DialogPane dialogPane = lesaLoginDialog().getDialogPane();
        dialogPane.getButtonTypes().setAll(
                new ButtonType("Í lagi", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE));
        dialogPane.setContent(usernameField);
        dialog.setDialogPane(dialogPane);

        // Add event handler to cancel button to close the program
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
