package pl.wojdylak.wallet.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "wallet")
    private List<FinancialAsset> financialAssets;

    public void addFinancialAsset(FinancialAsset financialAsset) {
        financialAsset.setWallet(this);
    }
}
