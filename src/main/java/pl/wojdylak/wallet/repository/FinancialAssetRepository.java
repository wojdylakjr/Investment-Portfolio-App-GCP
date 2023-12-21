package pl.wojdylak.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.wallet.domain.FinancialAsset;

import java.util.Optional;

public interface FinancialAssetRepository extends JpaRepository<FinancialAsset, String> {
    Optional<FinancialAsset> findByTicker(String ticker);
}
