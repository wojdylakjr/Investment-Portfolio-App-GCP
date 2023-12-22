package pl.wojdylak.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wojdylak.wallet.domain.Wallet;
import pl.wojdylak.wallet.domain.dto.TransactionDto;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT new pl.wojdylak.wallet.domain.dto.TransactionDto(" +
            "t.id, fa.id, t.startDateTime, t.quantity, t.buyStockPrice, " +
            "t.currencyCode, t.currencyExchangeBuyRate, t.sellPrice, " +
            "t.currencyExchangeSellRate, t.sellDateTime, t.sellProfit, " +
            "t.buyValue, t.isActive) " +
            "FROM Transaction t " +
            "JOIN t.financialAsset fa " +
            "JOIN fa.wallet w " +
            "WHERE w.id = :walletId")
    List<TransactionDto> findTransactionDtoForWallet(@Param("walletId") Long walletId);

}
