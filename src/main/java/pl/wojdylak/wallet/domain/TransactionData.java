package pl.wojdylak.wallet.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
public class TransactionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    private LocalDateTime dateTime;
    private BigDecimal unitPrice;
    private BigDecimal currentTransactionPrice;
    private BigDecimal currencyPrice;

    private BigDecimal profit;
}
