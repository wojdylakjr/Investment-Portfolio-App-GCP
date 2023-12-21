package pl.wojdylak.wallet.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.wallet.domain.Wallet;
import pl.wojdylak.wallet.service.WalletService;

@RestController
@RequestMapping("api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/")
    public void createWallet(@RequestBody Wallet wallet) {
        walletService.save(wallet);
    }

    @GetMapping("/{walletId}")
    public Wallet getWallet(@PathVariable Long walletId) {
        return walletService.findById(walletId);
    }
}
