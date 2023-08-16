package student.examples;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        Scene scene = new Scene(root, 450, 350);

        final Label label = new Label("Click on the button below!");
        Button button = new Button("Click me!");

        root.getChildren().add(label);
        root.getChildren().add(button);

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            int times = 0;
            @Override
            public void handle(MouseEvent event) {
                times++;
                label.setText("You've clicked " + times + " times!");
            }
        };

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, handler); 
        root.setAlignment(Pos.CENTER);

        stage.setScene(scene);
        stage.show();

    }
    
    public static void main(String[] args) {
        launch();
    }

}