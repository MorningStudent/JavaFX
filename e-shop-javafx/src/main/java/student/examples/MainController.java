package student.examples;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import student.examples.domain.Product;
import student.examples.domain.ProductRepository;

public class MainController {
    @FXML
    public HBox content;
    private ProductRepository productRepository;
    private boolean isCatalogRendered = false;
    
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void renderCatalog() {
        if (isCatalogRendered == false) {
            List<Product> products = productRepository.getAllProducts();
            for (int i = 0; i < products.size(); i++) {
                VBox container = new VBox();
                Label nameLbl = new Label(products.get(i).getName());
                nameLbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                nameLbl.setStyle("-fx-text-fill: red;");
                Label descriptionLbl = new Label(products.get(i).getDescription());
                Label priceLbl = new Label(
                    Float.toString(products.get(i).getPrice().getFloatAmount()) + " " + products.get(i).getPrice().getCurrency());
                container.getChildren().add(nameLbl);
                container.getChildren().add(descriptionLbl);
                container.getChildren().add(priceLbl);
                Image image = new Image(products.get(i).getImage());
                ImageView imageView = new ImageView();
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                imageView.setImage(image);
                container.getChildren().add(imageView);
                content.getChildren().add(container);
            }
        }
        isCatalogRendered = true;
    }
}
