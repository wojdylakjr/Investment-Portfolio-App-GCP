package pl.wojdylak.wallet.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojdylak.wallet.domain.valueData.TransactionValueData;
import pl.wojdylak.wallet.service.valueData.TransactionDataService;

@RestController
@RequestMapping("api/transactions-data")
@RequiredArgsConstructor
public class TransactionDataController {
    private final TransactionDataService transactionDataService;

    @PostMapping("/{transactionId}")
    public TransactionValueData createTransactionData(@PathVariable Long transactionId) throws Exception {
        return transactionDataService.createTransactionValueRecord(transactionId);
    }
}
