package converter.view;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import converter.controller.CurrencyFacade;
import javax.enterprise.context.SessionScoped;
import converter.model.ConversionDTO;


@Named("userManager")
@SessionScoped
public class ClientHandler implements Serializable {
    @EJB
    private CurrencyFacade contr;
    private ConversionDTO conversion = null;
    private double amt = 0;
    private String fromCurrency;
    private String toCurrency;
    
    public ClientHandler() {
    }
    
    public void setFromCurrency(String currency) {
        this.fromCurrency = currency;
    }

    public String getFromCurrency() {
        return this.fromCurrency;
    }

    public void setToCurrency(String currency) {
        this.toCurrency = currency;
    }

    public String getToCurrency() {
        return this.toCurrency;
    }

    public List<String> getCurrencies() {
        return contr.getCurrencies();
    }

    public void performConversion() {
        this.conversion = contr.convert(fromCurrency, toCurrency, amt);
    }

    public boolean getConverted() {
        return this.conversion != null;
    }

    public double getResult() {
        return this.conversion.getAfterAmount();
    }

    public double getAmount() {
        if (conversion == null) {
            return 0;
        }
        return this.conversion.getBeforeAmount();
    }

    public void setAmount(double amount) {
        this.amt = amount;
    }
}
