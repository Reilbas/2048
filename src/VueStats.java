import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VueStats extends Pane implements Observateur{
    private Label stats;
    private Jeu jeu;

    public VueStats(Jeu jeu){
        super();
        stats = new Label();
        stats.setPadding(new Insets(0,0,0,100));
        Font font = Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD,30);
        this.stats.setFont(font);
        this.getChildren().add(stats);

        this.jeu = jeu;
        dessiner();
        this.jeu.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        dessiner();
    }

    private void dessiner(){
        String st = "Parties gagnées/jouées : ";
        st += jeu.getNbGagnees() + "/" + jeu.getNbJouees();
        this.stats.setText(st);
    }
}
