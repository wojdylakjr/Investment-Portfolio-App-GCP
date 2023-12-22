package pl.wojdylak.wallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.domain.Wallet;
import pl.wojdylak.wallet.domain.dto.TransactionDto;
import pl.wojdylak.wallet.repository.WalletRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }

    public Wallet findById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow();
    }

    public List<TransactionDto> getAllTransactionsForWallet(Long walletId) {
        return walletRepository.findTransactionDtoForWallet(walletId);
    }

}
