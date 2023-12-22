package pl.wojdylak.wallet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;
    private String financialAssetId;
    private LocalDateTime startDateTime;
    private Integer quantity;
    private BigDecimal buyStockPrice;
    private String currencyCode;
    private BigDecimal currencyExchangeBuyRate;
    private BigDecimal sellPrice;
    private BigDecimal currencyExchangeSellRate;
    private LocalDateTime sellDateTime;
    private BigDecimal sellProfit;
    private BigDecimal buyValue;
    private Boolean isActive = true;

}
