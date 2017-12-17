package converter.model;

public interface ConversionDTO {
    public String getFromCurrency();
    public String getToCurrency();
    public double getBeforeAmount();
    public double getAfterAmount();
}
