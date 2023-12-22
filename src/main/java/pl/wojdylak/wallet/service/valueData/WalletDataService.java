package pl.wojdylak.wallet.service.valueData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.domain.Wallet;
import pl.wojdylak.wallet.domain.valueData.FinancialAssetValueData;
import pl.wojdylak.wallet.domain.valueData.WalletValueData;
import pl.wojdylak.wallet.repository.WalletValueDataRepository;
import pl.wojdylak.wallet.service.WalletService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletDataService {
    private final WalletService walletService;
    private final FinancialAssetDataService financialAssetDataService;
    private final WalletValueDataRepository walletValueDataRepository;

    public List<WalletValueData> getWalletDataValues(Long walletId) {
        return walletValueDataRepository.findAll();
    }

    public WalletValueData createWalletValueRecord(Long walletId) {
        Wallet wallet = walletService.findById(walletId);
        List<FinancialAssetValueData> financialAssetValueDataList = wallet.getFinancialAssets().stream()
                .map(financialAssetDataService::createFinancialAssetValueRecord)
                .toList();

        BigDecimal walletCashDeposit = BigDecimal.ZERO;
        BigDecimal walletCurrentValue = BigDecimal.ZERO;
        for (FinancialAssetValueData financialAsset : financialAssetValueDataList) {
            walletCashDeposit = walletCashDeposit.add(financialAsset.getCashDeposit());
            walletCurrentValue = walletCurrentValue.add(financialAsset.getCurrentValue());
        }
        WalletValueData walletValueData = WalletValueData.builder()
                .cashDeposit(walletCashDeposit)
                .currentValue(walletCurrentValue)
                .dateTime(LocalDateTime.now())
                .wallet(wallet)
                .build();
        walletValueDataRepository.save(walletValueData);
        return walletValueData;
    }
}
