package converter.integration;

import converter.model.Currency;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class CurrencyDAO {
    @PersistenceContext(unitName = "currencyPU")
    private EntityManager manager;

    @PostConstruct
    void init() {
        addCurrency(new Currency("USD", 1.0));
        addCurrency(new Currency("EUR", 0.85));
        addCurrency(new Currency("SEK", 8.5));
        addCurrency(new Currency("GBP", 0.74));
    }

    public void addCurrency(Currency c) {
        if (manager.find(Currency.class, c.getCurrency()) != null) {
            return;
        }
        manager.persist(c);
    }

    public List<String> getCurrencies() {
        List<String> s = manager.createQuery("SELECT c.currency FROM Currency c").getResultList();
        return s;
    }

    public double getConversionRate(String currency) {
        Currency c = manager.find(Currency.class, currency);
        return c.getValue();
    }
}
