package pl.wojdylak.wallet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.apiClient.XtbApiClient;
import pl.wojdylak.wallet.domain.Transaction;
import pl.wojdylak.wallet.domain.TransactionData;
import pl.wojdylak.wallet.repository.TransactionDataRepository;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.TickRecord;
import pro.xstore.api.message.response.APIErrorResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionDataService {
    private final XtbApiClient xtbApiClient;
    private final TransactionService transactionService;
    private final TransactionDataRepository transactionDataRepository;

    public TransactionData createTransactionData(Long transactionId) throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException {

        Transaction transaction = transactionService.findById(transactionId);

        TickRecord tickRecord = xtbApiClient.getTickerCurrentPrice(transaction.getFinancialAsset().getTicker());

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
