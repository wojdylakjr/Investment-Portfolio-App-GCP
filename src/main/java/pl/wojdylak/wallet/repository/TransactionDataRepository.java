package pl.wojdylak.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.wallet.domain.TransactionData;

public interface TransactionDataRepository extends JpaRepository<TransactionData, Long> {
}
