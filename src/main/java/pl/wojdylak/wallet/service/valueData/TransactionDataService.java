package pl.wojdylak.wallet.service.valueData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.apiClient.XtbApiClient;
import pl.wojdylak.wallet.domain.FinancialAsset;
import pl.wojdylak.wallet.domain.Transaction;
import pl.wojdylak.wallet.domain.valueData.TransactionValueData;
import pl.wojdylak.wallet.repository.TransactionDataRepository;
import pl.wojdylak.wallet.service.FinancialAssetService;
import pl.wojdylak.wallet.service.TransactionService;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.response.APIErrorResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionDataService {
    private final XtbApiClient xtbApiClient;
    private final TransactionService transactionService;
    private final FinancialAssetService financialAssetService;
    private final TransactionDataRepository transactionDataRepository;

    public TransactionValueData createTransactionValueRecord(Long transactionId) throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException {

        Transaction transaction = transactionService.findById(transactionId);
        BigDecimal financialAssetCurrentPrice = financialAssetService.getTickerCurrentPrice(transaction.getFinancialAsset().getTicker());

        return createAndSaveTransactionValueRecord(transaction, financialAssetCurrentPrice);

    }

    public List<TransactionValueData> createTransactionValueRecordsForFinancialAsset(FinancialAsset financialAsset) {

        BigDecimal financialAssetCurrentPrice = financialAssetService.getTickerCurrentPrice(financialAsset.getTicker());

        return financialAsset.getTransactions().stream()
                .map(transaction -> createAndSaveTransactionValueRecord(transaction, financialAssetCurrentPrice))
                .collect(Collectors.toList());
    }

    private TransactionValueData createAndSaveTransactionValueRecord(Transaction transaction, BigDecimal financialAssetCurrentPrice) {
        BigDecimal currentTransactionPrice = financialAssetCurrentPrice.multiply(BigDecimal.valueOf(transaction.getQuantity()));

        TransactionValueData currentTransactionValueData = TransactionValueData
                .builder()
                .transaction(transaction)
                .unitPrice(financialAssetCurrentPrice)
                .dateTime(LocalDateTime.now())
                .currentTransactionPrice(currentTransactionPrice)
                .profit(currentTransactionPrice.subtract(transaction.getBuyValue()))
                .build();

        transactionDataRepository.save(currentTransactionValueData);

        return currentTransactionValueData;
    }
}
