package pl.wojdylak.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.wallet.domain.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
