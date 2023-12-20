package pl.wojdylak.wallet.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wojdylak.wallet.apiClient.XtbApiClient;
import pl.wojdylak.wallet.domain.Transaction;
import pl.wojdylak.wallet.domain.TransactionData;
import pl.wojdylak.wallet.service.TransactionService;


@RestController
@RequestMapping("api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final XtbApiClient xtbClient;

    @PostMapping("/")
    public void createTransaction(@RequestBody Transaction transaction) {
        transactionService.createTransaction(transaction);
    }

    @GetMapping("/{ticker}")
    public String getTickerCurrentPrice(@PathVariable String ticker) throws Exception {
        return xtbClient.getTickerCurrentPrice(ticker).toString();
    }

    @GetMapping("/symbols")
    public String getAllSymbols() throws Exception {
        return xtbClient.getAllSymbols();
    }

    @GetMapping("/{id}/getPrice")
    public TransactionData getPrice(@PathVariable Long id) throws Exception {
        return transactionService.getCurrentPrice(id);
//        return xtbClient.getTickerCurrentPrice(ticker);
    }
}
