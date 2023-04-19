package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/******************************************************************************
 *  Lýsing  : Vinnsluklasi fyrir matseðil. Hefur lista af veitingum
 *
 *
 *****************************************************************************/
public class Matsedill {

    protected ObservableList<Veitingar> veitingar = FXCollections.observableArrayList();

    public Matsedill () {

    }

    public void setjaGogn () {
        veitingar.add (new Veitingar ("Margarita Pizza", 2290 ));
        veitingar.add (new Veitingar ("Kjötveisla Pizza", 2590 ));
        veitingar.add (new Veitingar ("Vegan Pizza", 1890));

        veitingar.add (new Veitingar ("Ostabitar", 1290 ));
        veitingar.add (new Veitingar ("Brauðstangir", 1290));
        veitingar.add (new Veitingar ("Osta Brauðstangir ", 1390));

        veitingar.add (new Veitingar ("Sprite", 290));
        veitingar.add (new Veitingar ("Coke", 290));
        veitingar.add (new Veitingar ("Sódavatn", 290));
    }
    public ObservableList<Veitingar> getVeitingar() {
        return veitingar;
    }

    public void setVeitingar(ObservableList<Veitingar> veitingar) {
        this.veitingar = veitingar;
    }
}
