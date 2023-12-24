import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EcouteurTaille implements EventHandler {
    private Jeu jeu;

    public EcouteurTaille(Jeu j){
        jeu = j;
    }
    public void handle(Event actionEvent) {
        Stage stage = new Stage();
        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);

        Label l = new Label("Saisissez une nouvelle Taille");
        TextField textField = new TextField("4");
        textField.setMaxWidth(100);
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    jeu.setTaille(Integer.parseInt(textField.getText()));
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
