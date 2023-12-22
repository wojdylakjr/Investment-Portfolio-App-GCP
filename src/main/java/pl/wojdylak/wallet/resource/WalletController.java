package pl.wojdylak.wallet.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.wallet.domain.Wallet;
import pl.wojdylak.wallet.domain.dto.TransactionDto;
import pl.wojdylak.wallet.domain.valueData.WalletValueData;
import pl.wojdylak.wallet.service.WalletService;
import pl.wojdylak.wallet.service.valueData.WalletDataService;

import java.util.List;

@RestController
@RequestMapping("api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final WalletDataService walletDataService;

    @PostMapping("/")
    public void createWallet(@RequestBody Wallet wallet) {
        walletService.save(wallet);
    }

    @GetMapping("/{walletId}")
    public Wallet getWallet(@PathVariable Long walletId) {
        return walletService.findById(walletId);
    }

    @GetMapping("/{walletId}/transactions")
    public List<TransactionDto> getAllTransactionsInWallet(@PathVariable Long walletId) {
        return walletService.getAllTransactionsForWallet(walletId);
    }

    @PostMapping("/{walletId}/create-record")
    public WalletValueData createWalletValueRecord(@PathVariable Long walletId) {
        return walletDataService.createWalletValueRecord(walletId);
    }

    @GetMapping("/{walletId}/history")
    public List<WalletValueData> getWalletDataValues(@PathVariable Long walletId) {
        return walletDataService.getWalletDataValues(walletId);
    }
}
