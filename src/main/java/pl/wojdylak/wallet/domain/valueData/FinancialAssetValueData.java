package pl.wojdylak.wallet.domain.valueData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.wojdylak.wallet.domain.FinancialAsset;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FinancialAssetValueData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_asset_id", nullable = false)
    private FinancialAsset financialAsset;

    private LocalDateTime dateTime;
    private BigDecimal currentUnitPrice;
    private BigDecimal currencyRatePrice;
    private BigDecimal currentValue;
    private BigDecimal cashDeposit;
}
