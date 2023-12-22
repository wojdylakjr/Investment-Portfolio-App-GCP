package pl.wojdylak.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wojdylak.wallet.domain.valueData.FinancialAssetValueData;

import java.util.List;

public interface FinancialAssetValueDataRepository extends JpaRepository<FinancialAssetValueData, Long> {
    @Query("SELECT a " +
            "FROM FinancialAssetValueData a " +
            "JOIN a.financialAsset fa " +
            "WHERE fa.ticker = :ticker ")
    List<FinancialAssetValueData> findByTicker(@Param("ticker") String ticker);
}
