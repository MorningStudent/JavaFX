package student.examples;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import student.examples.domain.ProductRepository;
import student.examples.services.BNMService;
import student.examples.services.ICurrencyService;
import student.examples.services.PriceTransformer;

public class App extends Application {

    private ProductRepository productRepository;
    private ICurrencyService currencyService;
    private PriceTransformer priceTransformer;

    private void loadServices() {
        productRepository = new ProductRepository();
        currencyService = new BNMService();
        priceTransformer = new PriceTransformer();
    }

    @Override
    public void start(Stage stage) {
        try {
            loadServices();
            URL fxmURL = getClass().getResource("/fxml/layout.fxml");
            FXMLLoader loader = new FXMLLoader(fxmURL);
            
            Parent root = loader.load();
            Scene scene = new Scene(root);
            final MainController mainController = loader.getController();
            mainController.setProductRepository(productRepository);
            mainController.setICurrencyService(currencyService);
            mainController.setPriceTransformer(priceTransformer);

            MenuBar menuBar = (MenuBar)root.getChildrenUnmodifiable().get(0);
            final Menu menu = new Menu(currencyService.getActiveCurrency());
            menuBar.getMenus().add(menu);

            for (String currencyCharCode : ((BNMService)currencyService).getSelectedCurrencies()) {
                MenuItem menuItem = new MenuItem(currencyCharCode);
                menu.getItems().add(menuItem);
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String activeCurrency = ((MenuItem)event.getTarget()).getText();
                        ((BNMService) currencyService).setActiveCurrency(activeCurrency);
                        menu.setText(activeCurrency);
                        try {
                            mainController.renderCatalog();
                        } catch (IOException | ParserConfigurationException | SAXException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

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