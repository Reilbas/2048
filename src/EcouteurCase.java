import javafx.event.Event;
import javafx.event.EventHandler;

public class EcouteurCase implements EventHandler {
    private Jeu jeu;
    private int lig,col;

    public EcouteurCase(int l, int c, Jeu j){
        lig = l;
        col = c;
        jeu = j;
    }

    @Override
    public void handle(Event event) {
        jeu.jouer(lig,col);
    }
}
