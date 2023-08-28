package student.examples;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import student.examples.domain.Money;
import student.examples.domain.Product;
import student.examples.domain.ProductRepository;
import student.examples.services.ICurrencyService;
import student.examples.services.PriceTransformer;

public class MainController {
    @FXML
    public HBox content;
    private ProductRepository productRepository;
    private ICurrencyService currencyService;
    private PriceTransformer priceTransformer;
    private boolean isCatalogRendered = false;
    
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setICurrencyService(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public void setPriceTransformer(PriceTransformer priceTransformer) {
        this.priceTransformer = priceTransformer;
    }

    private HBox createNewCatalog() throws IOException, ParserConfigurationException, SAXException {
        List<Product> products = productRepository.getAllProducts();
        for (int i = 0; i < products.size(); i++) {
            VBox container = new VBox();
            Label nameLbl = new Label(products.get(i).getName());
            nameLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            nameLbl.setStyle("-fx-text-fill: red;");
            Label descriptionLbl = new Label(products.get(i).getDescription());
            Money newPrice = priceTransformer.transformPrice(products.get(i), currencyService);
            Label priceLbl = new Label(
                String.valueOf(newPrice.getFloatAmount()) + " " + newPrice.getCurrency());
            Image image = new Image(products.get(i).getImage());
            ImageView imageView = new ImageView();
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            imageView.setImage(image);
            container.getChildren().add(nameLbl);
            container.getChildren().add(descriptionLbl);
            container.getChildren().add(priceLbl);
            container.getChildren().add(imageView);
            content.getChildren().add(container);
        }
        isCatalogRendered = true;
        return content;
    }

    public void renderCatalog() throws IOException, ParserConfigurationException, SAXException {
        if (isCatalogRendered == false) {
            content = createNewCatalog();
        } else if (isCatalogRendered == true) {
            content.getChildren().clear();
            content = createNewCatalog();
        }
    
    }
}
