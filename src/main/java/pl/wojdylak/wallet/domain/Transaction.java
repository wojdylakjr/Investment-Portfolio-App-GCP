package pl.wojdylak.wallet.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import pl.wojdylak.wallet.domain.valueData.TransactionValueData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("transactions")
    @JoinColumn(name = "financial_asset_id", nullable = false)
    private FinancialAsset financialAsset;
    @JsonIgnoreProperties("transaction")
    @OneToMany(mappedBy = "transaction")
    private List<TransactionValueData> transactionsData;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", ticker=" + financialAsset.getTicker() +
                ", startDateTime=" + startDateTime +
                ", quantity=" + quantity +
                ", buyStockPrice=" + buyStockPrice +
                ", currencyCode='" + currencyCode + '\'' +
                ", currencyExchangeBuyRate=" + currencyExchangeBuyRate +
                ", sellPrice=" + sellPrice +
                ", currencyExchangeSellRate=" + currencyExchangeSellRate +
                ", sellDateTime=" + sellDateTime +
                ", sellProfit=" + sellProfit +
                ", buyValue=" + buyValue +
                '}';
    }
}
