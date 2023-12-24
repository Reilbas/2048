import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
    J'aurais bien voulu fusionner cette classe avec EcouteurTaille qui est casis identique
    Mais elle est dans le sujet et je ne souhaitais pas vous perdre
 */
public class EcouteurObjectif implements EventHandler {
    private Jeu jeu;

    public EcouteurObjectif(Jeu j){
        jeu = j;
    }
    public void handle(Event actionEvent) {
        Stage stage = new Stage();
        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);

        Label l = new Label("Saisissez un nouvelle Objectif");
        TextField textField = new TextField("2048");
        textField.setMaxWidth(100);
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    jeu.setObjectif(Integer.parseInt(textField.getText()));
                    stage.close();
                }catch (NumberFormatException e){
                    button.setStyle("-fx-text-fill: #ff0000");
                }
            }
        });

        root.add(l,0,0,2,1);
        root.add(textField,0,1);
        root.add(button,1,1);

        stage.setScene(new Scene(root, 200, 50));
        stage.show();
    }
}
