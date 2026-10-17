import java.math.BigDecimal;
import java.math.RoundingMode;

public class Currency {
    private final String name;
    private final String code;
    private final BigDecimal averageRate;
    private final int multiplier;

    public Currency(
            String name,
            String code,
            BigDecimal averageRate,
            int multiplier
    ) {
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
        return averageRate.divide(BigDecimal.valueOf(multiplier), RoundingMode.HALF_UP);
    }

    public BigDecimal getAverageRatePerUnitRounded() {
        return getAverageRatePerUnit().setScale(2, RoundingMode.HALF_UP);
    }
}
