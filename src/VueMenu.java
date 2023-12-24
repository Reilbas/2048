import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.application.Platform;

public class VueMenu extends MenuBar implements Observateur{
    private Menu menu;
    private Jeu jeu;

    public VueMenu(Jeu jeu){
        super();
        menu = new Menu("Jeu");

        MenuItem nouveau = new MenuItem("Nouveau");
        nouveau.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        nouveau.setOnAction(event -> jeu.nouveau());

        MenuItem taille = new MenuItem("Taille: "+jeu.getTaille());
        taille.setOnAction(new EcouteurTaille(jeu));

        MenuItem objectif = new MenuItem("Objectif: "+jeu.getObjectif());
        objectif.setOnAction(new EcouteurObjectif(jeu));

        MenuItem switchGame = new MenuItem("Changer de jeu");
        switchGame.setOnAction(event -> jeu.switchGame());

        MenuItem quitter = new MenuItem("Quitter");
        quitter.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        quitter.setOnAction(event -> Platform.exit());

        menu.getItems().addAll(nouveau, taille, objectif, switchGame, quitter);
        this.getMenus().add(menu);

        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        menu.getItems().get(1).setText("Taille: "+jeu.getTaille());
        menu.getItems().get(2).setText("Objectif: "+jeu.getObjectif());
    }
}
