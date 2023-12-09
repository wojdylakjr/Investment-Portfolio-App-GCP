package pl.wojdylak.wallet.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String stockTicker;
    private LocalDateTime startDateTime;
    private Integer quantity;
    private BigDecimal buyStockPrice;
    private String currencyCode;
    private BigDecimal currencyExchangeBuyRate;
    private BigDecimal sellPrice;
    private BigDecimal currencyExchangeSellRate;
    private LocalDateTime sellDateTime;
    private BigDecimal profit;
}
