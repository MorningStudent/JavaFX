package student.examples.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProductRepository {

    public Product getTestProduct() {
        return new Product(
            "Test Product Name",
            "Test Product Description",
            "/images/test-product.jpg",
            new Money(1.25f, new Currency("USD", 840, 0.9f))
        );
    }

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("src\\main\\resources\\xml\\products.xml"));
            NodeList products = document.getDocumentElement().getElementsByTagName("product");
            int n = 0;
            while(true) {
                Element product = (Element)products.item(n);
                if(product == null) break;
                Element productName =  (Element)product.getElementsByTagName("name").item(0);
                Element productDescription =  (Element)product.getElementsByTagName("description").item(0);
                Element productImage =  (Element)product.getElementsByTagName("image").item(0);
                Element productPrice =  (Element)product.getElementsByTagName("price").item(0);
                Element productPriceAmount = (Element)productPrice.getElementsByTagName("amount").item(0);
                Element productPriceCurrency = (Element)productPrice.getElementsByTagName("currency").item(0);

                allProducts.add(
                    new Product(
                        productName.getTextContent().trim(), productDescription.getTextContent().trim(), 
                        productImage.getTextContent().trim(), new Money(
                            Float.parseFloat(productPriceAmount.getTextContent().trim()), new Currency(
                                productPriceCurrency.getTextContent().trim()))));

                n++;
            }
        } catch (SAXException| IOException| ParserConfigurationException e) {
            System.out.println("ERROR! Parsing products.xml is not functioning well!");
        }
        return allProducts;
    }
}
