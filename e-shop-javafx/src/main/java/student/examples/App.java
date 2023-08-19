package student.examples;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import student.examples.domain.ProductRepository;

public class App extends Application {

    

    @Override
    public void start(Stage stage) {
        try {

            URL fxmURL = getClass().getResource("/fxml/layout.fxml");
            FXMLLoader loader = new FXMLLoader(fxmURL);
            
            Parent root = loader.load();
            MainController mainController = loader.getController();
            mainController.setProductRepository(new ProductRepository());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        launch();
    }

}