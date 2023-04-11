package hi.vidmot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TakeAwayApplication extends Application {
    @Override
    public void start(Stage stage)  {
        var scene = new Scene(new Pane()); // dummy rót fær aðra rót seinna
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.INNSKRA);
        stage.setTitle("TakeAway");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}