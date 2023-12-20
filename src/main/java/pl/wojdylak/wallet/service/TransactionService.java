package pl.wojdylak.wallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.apiClient.XtbApiClient;
import pl.wojdylak.wallet.domain.Transaction;
import pl.wojdylak.wallet.domain.TransactionData;
import pl.wojdylak.wallet.repository.TransactionDataRepository;
import pl.wojdylak.wallet.repository.TransactionRepository;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.TickRecord;
import pro.xstore.api.message.response.APIErrorResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDataRepository transactionDataRepository;
    private final XtbApiClient xtbApiClient;

    public void createTransaction(Transaction transaction) {
        transaction.setBuyValue(transaction.getBuyStockPrice().multiply(BigDecimal.valueOf(transaction.getQuantity())));
        transactionRepository.save(transaction);
    }

    public TransactionData getCurrentPrice(Long transactionId) throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
        if (optionalTransaction.isEmpty()) {
            return null;
        }
        Transaction transaction = optionalTransaction.get();
        TickRecord tickRecord = xtbApiClient.getTickerCurrentPrice(transaction.getStockTicker())
                .getTicks()
                .get(0);

        BigDecimal unitPrice = BigDecimal.valueOf(tickRecord.getBid());
        BigDecimal currentTransactionPrice = unitPrice.multiply(BigDecimal.valueOf(transaction.getQuantity()));

        TransactionData currentTransactionData = TransactionData
                .builder()
                .transaction(transaction)
                .unitPrice(unitPrice)
                .dateTime(LocalDateTime.now())
                .currentTransactionPrice(currentTransactionPrice)
                .profit(currentTransactionPrice.subtract(transaction.getBuyValue()))
                .build();

        transactionDataRepository.save(currentTransactionData);
        return currentTransactionData;
    }
}
