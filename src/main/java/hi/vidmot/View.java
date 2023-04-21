package hi.vidmot;

/**
 * Notandi getur séð yfirlit af forritinu þ.e Pöntun, Stillingar ofl.
 */


public enum View {
    PONTUN("pontun-view.fxml"),
    STILLINGAR("stillingar-view.fxml"),
    INNSKRA("innskra-view.fxml"),
    GREIDSLA("greida-view.fxml");

    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
