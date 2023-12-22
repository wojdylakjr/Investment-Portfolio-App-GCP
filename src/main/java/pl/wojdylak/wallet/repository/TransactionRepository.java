package pl.wojdylak.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojdylak.wallet.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}