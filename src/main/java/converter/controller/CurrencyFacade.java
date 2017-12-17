package converter.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import converter.model.ConversionDTO;
import converter.model.CurrencyConverter;

@Stateless
public class CurrencyFacade {
    @EJB
    CurrencyConverter con;
    
    public List<String> getCurrencies() {
        return con.getCurrencies();
    }

    public ConversionDTO convert(String from, String to, double amount) {
        return con.convert(from, to, amount);
    }
}
