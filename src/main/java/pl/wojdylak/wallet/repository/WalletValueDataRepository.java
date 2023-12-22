package pl.wojdylak.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.wallet.domain.valueData.WalletValueData;

public interface WalletValueDataRepository extends JpaRepository<WalletValueData, Long> {
}
