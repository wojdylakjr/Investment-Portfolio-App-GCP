package pl.wojdylak.wallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.domain.Wallet;
import pl.wojdylak.wallet.repository.WalletRepository;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
