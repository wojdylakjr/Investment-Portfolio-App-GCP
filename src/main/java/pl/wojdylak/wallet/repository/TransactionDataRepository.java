package pl.wojdylak.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.wallet.domain.valueData.TransactionValueData;

public interface TransactionDataRepository extends JpaRepository<TransactionValueData, Long> {
}
