package converter.model;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import converter.integration.CurrencyDAO;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CurrencyConverter implements Serializable {
    @EJB
    CurrencyDAO dao;

    public void addCurrency(String currency, double rate) {
        dao.addCurrency(new Currency(currency, rate));
    }

    public List<String> getCurrencies() {
        return dao.getCurrencies();
    }

    public ConversionDTO convert(String from, String to, double amount) {
        double fromRate = dao.getConversionRate(from);
        double toRate   = dao.getConversionRate(to);
        double base = amount / fromRate;
        double nextAmount = (double) Math.round(base * toRate * 100) / 100;
        Conversion c = new Conversion(from, to, amount, nextAmount);
        return c;
    }
}
