package pl.wojdylak.wallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.apiClient.XtbApiClient;
import pl.wojdylak.wallet.domain.FinancialAsset;
import pl.wojdylak.wallet.domain.Transaction;
import pl.wojdylak.wallet.repository.TransactionRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final FinancialAssetService financialAssetService;
    private final TransactionRepository transactionRepository;
    private final XtbApiClient xtbApiClient;

    public void createTransaction(Transaction transaction) {
        FinancialAsset financialAsset = financialAssetService.getOrCreateFinancialAssetByTicker(transaction);
        financialAsset.addTransaction(transaction);
        transaction.setBuyValue(transaction.getBuyStockPrice().multiply(BigDecimal.valueOf(transaction.getQuantity())));

        System.out.println("Ticker: " + financialAsset);
        transactionRepository.save(transaction);
    }


    public Transaction findById(Long transactionId) {
        return transactionRepository
                .findById(transactionId)
                .orElseThrow();
    }
}
