package pl.wojdylak.wallet.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojdylak.wallet.domain.TransactionData;
import pl.wojdylak.wallet.service.TransactionDataService;

@RestController
@RequestMapping("api/transactions-data")
@RequiredArgsConstructor
public class TransactionDataController {
    private final TransactionDataService transactionDataService;

    @PostMapping("/{transactionId}")
    public TransactionData createTransactionData(@PathVariable Long transactionId) throws Exception {
        return transactionDataService.createTransactionData(transactionId);
    }
}
