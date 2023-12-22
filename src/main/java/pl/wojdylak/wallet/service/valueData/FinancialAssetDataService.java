package pl.wojdylak.wallet.service.valueData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wojdylak.wallet.domain.FinancialAsset;
import pl.wojdylak.wallet.domain.Transaction;
import pl.wojdylak.wallet.domain.valueData.FinancialAssetValueData;
import pl.wojdylak.wallet.domain.valueData.TransactionValueData;
import pl.wojdylak.wallet.repository.FinancialAssetValueDataRepository;
import pl.wojdylak.wallet.service.FinancialAssetService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialAssetDataService {
    private final FinancialAssetService financialAssetService;
    private final TransactionDataService transactionDataService;

    private final FinancialAssetValueDataRepository financialAssetValueDataRepository;

    public List<FinancialAssetValueData> getFinancialAssetDataValues(String ticker) {
        return financialAssetValueDataRepository.findByTicker(ticker);
    }

    public FinancialAssetValueData createFinancialAssetValueRecord(FinancialAsset financialAsset) {
        List<TransactionValueData> transactionValueRecordsForFinancialAsset = transactionDataService.createTransactionValueRecordsForFinancialAsset(financialAsset);

        BigDecimal currentTransactionsTotalValue = transactionValueRecordsForFinancialAsset.stream()
                .map(TransactionValueData::getCurrentTransactionPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal cashDeposit = financialAsset.getTransactions().stream()
                .map(Transaction::getBuyValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        FinancialAssetValueData financialAssetValueRecord = FinancialAssetValueData.builder()
                .currentValue(currentTransactionsTotalValue)
                .dateTime(LocalDateTime.now())
                .cashDeposit(cashDeposit)
                .financialAsset(financialAsset)
                .build();

        financialAssetValueDataRepository.save(financialAssetValueRecord);
        return financialAssetValueRecord;

    }
}
