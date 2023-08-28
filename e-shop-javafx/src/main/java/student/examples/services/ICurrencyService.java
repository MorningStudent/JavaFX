package student.examples.services;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import student.examples.domain.Currency;

public interface ICurrencyService {
    public Map<String, Currency> getData() throws IOException, ParserConfigurationException, SAXException;
    public String getActiveCurrency();
}
