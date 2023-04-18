module hi.verkefni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens hi.vidmot to javafx.fxml;
    exports hi.vidmot;
}