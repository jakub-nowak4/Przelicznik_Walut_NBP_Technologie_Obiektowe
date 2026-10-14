import java.math.BigDecimal;

public class Currency {
    private String name;
    private String code;
    private BigDecimal averageRate;
    private int multiplier;

    public Currency(String name, String code, BigDecimal averageRate, int multiplier) {
        this.name = name;
        this.code = code;
        this.averageRate = averageRate;
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getAverageRate() {
        return averageRate;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public BigDecimal getAverageRatePerUnit() {
        return averageRate.divide(BigDecimal.valueOf(multiplier), BigDecimal.ROUND_HALF_UP);
    }
}
