import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class EcouteurTouche implements EventHandler<KeyEvent> {
    private Jeu jeu;
    final KeyCombination nouveau = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    final KeyCombination quitter = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);

    public EcouteurTouche(Jeu j){
        jeu = j;
    }

    @Override
    public void handle(KeyEvent event) {
        if (nouveau.match(event)) {
            jeu.nouveau();
        }
        if (quitter.match(event)) {
            Platform.exit();
        }
        if(jeu.getVersionOriginale()){
            if(event.getCode() == KeyCode.UP) {
                jeu.jouerHaut();
            }
            if(event.getCode() == KeyCode.DOWN) {
                jeu.jouerBas();
            }
            if(event.getCode() == KeyCode.RIGHT) {
                jeu.jouerDroite();
            }
            if(event.getCode() == KeyCode.LEFT) {
                jeu.jouerGauche();
            }
        }
        event.consume();
    }
}