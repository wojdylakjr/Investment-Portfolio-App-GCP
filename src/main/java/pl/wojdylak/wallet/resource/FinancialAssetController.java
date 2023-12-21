package pl.wojdylak.wallet.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojdylak.wallet.service.FinancialAssetService;

@RestController
@RequestMapping("api/financial-assets")
@RequiredArgsConstructor
public class FinancialAssetController {
    private final FinancialAssetService financialAssetService;

    @GetMapping("/{ticker}/price")
    public String getTickerCurrentPrice(@PathVariable String ticker) throws Exception {
        return financialAssetService.getFinancialAssetCurrentPrice(ticker);
    }

    @GetMapping("/tickers")
    public String getAllTickers() throws Exception {
        return financialAssetService.getAllXtbTickers();
    }


}
