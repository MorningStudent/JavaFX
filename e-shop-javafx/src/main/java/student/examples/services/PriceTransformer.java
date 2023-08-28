package student.examples.services;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import student.examples.domain.Currency;
import student.examples.domain.IHasPrice;
import student.examples.domain.Money;

public class PriceTransformer {
    public Money transformPrice(IHasPrice item, ICurrencyService currencyService) throws IOException, ParserConfigurationException, SAXException {
        String activeCurrencyCode = currencyService.getActiveCurrency();
        Currency activeCurrency = currencyService.getData().get(activeCurrencyCode);
        Currency priceCurrency = currencyService.getData().get(item.getPrice().getCurrency().toString());
        float newPriceAmount = item.getPrice().getFloatAmount() * (priceCurrency.getRatio() / activeCurrency.getRatio());
        Money newPrice = new Money(newPriceAmount, activeCurrency);
        return newPrice;
    }
}
