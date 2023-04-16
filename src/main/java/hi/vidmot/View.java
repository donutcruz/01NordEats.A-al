package hi.vidmot;


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
