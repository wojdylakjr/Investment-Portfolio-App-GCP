package pl.wojdylak.wallet.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class FinancialAsset {
    @Id
    private String ticker;

    private String broker;
    @OneToMany(mappedBy = "financialAsset")
    @JsonIgnoreProperties("financialAssets")
    private List<Transaction> transactions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("financialAssets")
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
        transaction.setFinancialAsset(this);
    }

    @Override
    public String toString() {
        return "FinancialAsset{" +
                "ticker='" + ticker + '\'' +
                ", broker='" + broker + '\'' +
                ", transactions=" + transactions +
                ", wallet=" + wallet.getId() +
                '}';
    }
}
