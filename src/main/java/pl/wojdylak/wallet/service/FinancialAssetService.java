package pl.wojdylak.wallet.service;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.apiClient.XtbApiClient;
import pl.wojdylak.wallet.domain.FinancialAsset;
import pl.wojdylak.wallet.domain.Transaction;
import pl.wojdylak.wallet.domain.Wallet;
import pl.wojdylak.wallet.repository.FinancialAssetRepository;
import pl.wojdylak.wallet.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@Service
public class FinancialAssetService {
    private final XtbApiClient xtbApiClient;
    private final FinancialAssetRepository financialAssetRepository;
    private final WalletRepository walletRepository;

    public FinancialAsset getOrCreateFinancialAssetByTicker(Transaction transaction) {
        FinancialAsset financialAsset = transaction.getFinancialAsset();
        Optional<FinancialAsset> existingTicker = financialAssetRepository.findByTicker(financialAsset.getTicker());

        if (existingTicker.isPresent()) {
            return existingTicker.get();
        }
        Optional<Wallet> optionalWallet = walletRepository.findById(financialAsset.getWallet().getId());
        if (optionalWallet.isEmpty()) {
            return null; //TODO
        }
        Wallet wallet = optionalWallet.get();

        FinancialAsset newFinancialAsset = new FinancialAsset();
        newFinancialAsset.setTicker(financialAsset.getTicker());
        newFinancialAsset.setBroker(financialAsset.getBroker());
        wallet.addFinancialAsset(newFinancialAsset);
        walletRepository.save(wallet);
        return financialAssetRepository.save(newFinancialAsset);

    }
//
//    public String getFinancialAssetCurrentPrice(String ticker) throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException {
//        return xtbApiClient.getTickerCurrentPrice(ticker).toString();
//    }

    public String getAllXtbTickers() throws Exception {
        return xtbApiClient.getAllXtbTickers();
    }

    @SneakyThrows
    public BigDecimal getTickerCurrentPrice(String ticker) {
        return BigDecimal.valueOf(xtbApiClient.getTickerCurrentPrice(ticker).getBid());
    }
}

