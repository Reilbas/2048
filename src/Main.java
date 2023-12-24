import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Jeu jeu = new Jeu(4);
        BorderPane root = new BorderPane();
        root.setTop(new VueMenu(jeu));
        root.setCenter(new VuePlateau(jeu));
        root.setBottom(new VueStats(jeu));
        Scene s = new Scene(root, 1000, 700);
        s.setOnKeyPressed(new EcouteurTouche(jeu));
        primaryStage.setScene(s);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Jeux 2048");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
