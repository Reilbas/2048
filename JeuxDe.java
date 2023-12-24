import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class JeuxDe extends Application {
    @Override
    public void start(Stage primaryStage) {
        //------------------------IMAGES------------------------------
        ImageView quitter = new ImageView(new Image("ressources/quitter.png"));
        quitter.setFitHeight(40);
        quitter.setPreserveRatio(true);

        Image[] images = new Image[6];
        for(int i=0 ; i<6 ; i++){
            Image img = new Image("ressources/"+(i+1)+".png");
            images[i] = img;
        }

        ImageView view1 = new ImageView(images[0]);
        view1.setFitHeight(60);
        view1.setPreserveRatio(true);

        ImageView view2 = new ImageView(images[0]);
        view2.setFitHeight(60);
        view2.setPreserveRatio(true);

        ImageView view3 = new ImageView(images[0]);
        view3.setFitHeight(60);
        view3.setPreserveRatio(true);

        //-------------------------INIT----------------------------
        Modele m = new Modele();

        primaryStage.setTitle("Jeux de Dés");
        VBox root = new VBox();

        HBox hboxHaut = new HBox();
        HBox hboxCentre = new HBox();
        HBox hboxBas = new HBox();

        Button b1 = new Button();
        Button b2 = new Button();
        Button b3 = new Button();
        Label nbCoup = new Label("Nombre de Coup: 0  |  ");
        Label combi421 = new Label("Combinaison 421: 0 ");

        b1.setGraphic(view1);
        b1.setOnAction(new EcouteurVerrou(m,0));

        b2.setGraphic(view2);
        b2.setOnAction(new EcouteurVerrou(m,1));

        b3.setGraphic(view3);
        b3.setOnAction(new EcouteurVerrou(m,2));

        Button bJouer = new Button("Jouer");
        bJouer.setOnAction(new Ecouteur(b1,b2,b3,nbCoup,combi421,images,m));

        Button bQuitter = new Button();
        bQuitter.setGraphic(quitter);
        bQuitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        hboxHaut.getChildren().add(nbCoup);
        hboxHaut.getChildren().add(combi421);

        hboxCentre.getChildren().add(b1);
        hboxCentre.getChildren().add(b2);
        hboxCentre.getChildren().add(b3);

        hboxBas.getChildren().add(bJouer);
        hboxBas.getChildren().add(bQuitter);

        root.getChildren().add(hboxHaut);
        root.getChildren().add(hboxCentre);
        root.getChildren().add(hboxBas);

        primaryStage.setScene(new Scene(root, 250, 150));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
class Ecouteur implements EventHandler<ActionEvent> {
    private Button de1,de2,de3;
    private Label labelNbCoup,labelCombi421;
    Image[] images;

    Modele m;
    public Ecouteur (Button de1, Button de2, Button de3,Label labelNbCoup,Label labelCombi421, Image[] images, Modele m) {
        this.de1 = de1;
        this.de2 = de2;
        this.de3 = de3;

        this.labelNbCoup = labelNbCoup;
        this.labelCombi421 = labelCombi421;

        this.images = images;

        this.m = m;
    }
    public void handle(ActionEvent event) {
        m.lanceLesDe();

        ImageView view1 = new ImageView(images[m.vDe1-1]);
        ImageView view2 = new ImageView(images[m.vDe2-1]);
        ImageView view3 = new ImageView(images[m.vDe3-1]);

        view1.setFitHeight(60);
        view2.setFitHeight(60);
        view3.setFitHeight(60);

        view1.setPreserveRatio(true);
        view2.setPreserveRatio(true);
        view3.setPreserveRatio(true);

        de1.setGraphic(view1);
        de2.setGraphic(view2);
        de3.setGraphic(view3);

        labelNbCoup.setText("Nombre de Coup: "+m.nbCoup+"  |  ");
        labelCombi421.setText("Combinaison 421: "+m.combi421+" ");
    }
}

class Modele{
    int vDe1,vDe2,vDe3,nbCoup,combi421;
    boolean[] verrou;

    public Modele(){
        vDe1 = 1; vDe2 = 1; vDe3 = 1;
        nbCoup = 0;
        combi421 = 0;
        verrou = new boolean[3];
        verrou[0] = false; verrou[1] = false; verrou[2] = false;
    }
    void lanceLesDe(){
        if (!verrou[0] || !verrou[1] || !verrou[2]){
            Random r = new Random();

            if(!verrou[0]){
                vDe1 = r.nextInt(6)+1;
            }

            if(!verrou[1]){
                vDe2 = r.nextInt(6)+1;
            }

            if(!verrou[2]){
                vDe3 = r.nextInt(6)+1;
            }

            testCombi();

            nbCoup++;
        }
    }

    void switchVerrou(int nb){
        if(verrou[nb]){
            verrou[nb] = false;
        }else{
            verrou[nb] = true;
        }
    }

    private void testCombi(){
        switch (vDe1){
            case 1:
                switch (vDe2){
                    case 2:
                        if (vDe3==4){
                            combi421++;
                        }
                        break;
                    case 4:
                        if (vDe3==2){
                            combi421++;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (vDe2){
                    case 1:
                        if (vDe3==4){
                            combi421++;
                        }
                        break;
                    case 4:
                        if (vDe3==1){
                            combi421++;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (vDe2){
                    case 1:
                        if (vDe3==2){
                            combi421++;
                        }
                        break;
                    case 2:
                        if (vDe3==1){
                            combi421++;
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}

class EcouteurVerrou implements EventHandler<ActionEvent> {
    Modele m;
    int v;

    public EcouteurVerrou (Modele m, int v) {
        this.m = m;
        this.v = v;
    }
    public void handle(ActionEvent event) {
        m.switchVerrou (v);
    }
}