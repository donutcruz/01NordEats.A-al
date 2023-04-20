package vinnsla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vidskiptavinur {
    // Nafn og heimilisfang viðskiptavinar eru geymd sem einfaldar strengjabreytur.
    private final StringProperty nafn = new SimpleStringProperty();
    private final StringProperty heimilisfang = new SimpleStringProperty();

    // Notandanafn og lykilorð viðskiptavinar eru geymd sem strengjabreytur.
    private String notendanafn;
    private String lykilord;

    // Statískur breytistærð sem geymir upplýsingar um notanda sem er skráður inn
    private static Vidskiptavinur currentUser;

    // Smiður fyrir viðskiptavin með nafn og heimilisfang
    public Vidskiptavinur (String nafn, String heimilisfang) {
        this.nafn.set(nafn);
        this.heimilisfang.set(heimilisfang);
    }

    // Get- og set-aðferðir fyrir nafn
    public String getNafn() {
        return nafn.get();
    }

    public StringProperty nafnProperty() {
        return nafn;
    }

    public void setNafn(String nafn) {
        this.nafn.set(nafn);
    }

    // Get- og set-aðferðir fyrir heimilisfang
    public String getHeimilisfang() {
        return heimilisfang.get();
    }

    public StringProperty heimilisfangProperty() {
        return heimilisfang;
    }

    public void setHeimilisfang(String heimilisfang) {
        this.heimilisfang.set(heimilisfang);
    }

    // Get- og set-aðferðir fyrir notandanafn
    public String getNotendanafn() {
        return notendanafn;
    }

    public void setNotendanafn(String notendanafn) {
        this.notendanafn = notendanafn;
    }

    // Get- og set-aðferðir fyrir lykilorð
    public String getLykilord() {
        return lykilord;
    }

    public void setLykilord(String lykilord) {
        this.lykilord = lykilord;
    }

    // Get- og set-aðferðir fyrir currentUser
    public static Vidskiptavinur getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Vidskiptavinur currentUser) {
        Vidskiptavinur.currentUser = currentUser;
    }
}
