package pl.wojdylak.wallet.domain.valueData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.wojdylak.wallet.domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionValueData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    private LocalDateTime dateTime;
    private BigDecimal unitPrice;
    private BigDecimal currentTransactionPrice;
    private BigDecimal currencyRatePrice;
    private BigDecimal profit;
}
