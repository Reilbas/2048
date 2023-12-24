import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VuePlateau extends GridPane implements Observateur{
    private Jeu jeu;

    public VuePlateau(Jeu jeu){
        super();
        this.setPadding(new Insets(20,0,0,95));
        this.setHgap(5);
        this.setVgap(5);
        this.jeu = jeu;
        dessiner();
        this.jeu.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        this.getChildren().clear();
        dessiner();
    }

    private void dessiner(){
        double tailleCase = (700/jeu.getTabJeu().length)*0.8;
        Font font = Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD,tailleCase*0.3);
        for(int i = 0 ; i < jeu.getTabJeu().length ; i++){
            for(int j = 0 ; j < jeu.getTabJeu()[0].length ; j++){
                if(!jeu.getVersionOriginale()){
                    Button b = new Button(jeu.getTabJeu()[i][j]+"");
                    b.setMinWidth(tailleCase*1.4);
                    b.setMinHeight(tailleCase);
                    b.setOnAction(new EcouteurCase(i, j , jeu));
                    b.setFont(font);
                    b.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #4176dc; -fx-text-fill: white");
                    this.add(b, i, j);
                } else {
                    Label lab = new Label();
                    lab.setMinWidth(tailleCase*1.4);
                    lab.setMinHeight(tailleCase);
                    if(jeu.getTabJeu()[i][j]!=0){
                        lab.setText(jeu.getTabJeu()[i][j]+"");
                        lab.setFont(font);
                        lab.setAlignment(Pos.CENTER);
                        setColor(lab, jeu.getTabJeu()[i][j]);
                    } else {
                        lab.setStyle("-fx-border-color: #5e5e5e; -fx-border-width: 2px; -fx-background-color: #a6a6a6");
                    }
                    this.add(lab, i, j);
                }
            }
        }
    }

    private void setColor(Label l, int n){
        switch (n){
            case 2:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ffebda; -fx-text-fill: #000000");
                break;
            case 4:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ffd4ad; -fx-text-fill: #000000");
                break;
            case 8:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ff8e3a; -fx-text-fill: white");
                break;
            case 16:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ff604a; -fx-text-fill: white");
                break;
            case 32:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ff280c; -fx-text-fill: white");
                break;
            case 64:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ff2b2b; -fx-text-fill: white");
                break;
            case 128:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ffd070; -fx-text-fill: white");
                break;
            case 256:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ffc548; -fx-text-fill: white");
                break;
            case 512:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ffbe2a; -fx-text-fill: white");
                break;
            case 1024:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #ffb400; -fx-text-fill: white");
                break;
            case 2048:
            default:
                l.setStyle("-fx-border-color: #000000; -fx-border-width: 2px; -fx-background-color: #a0ff5c; -fx-text-fill: white");
                break;
        }
    }
}
