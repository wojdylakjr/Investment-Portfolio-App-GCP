package pl.wojdylak.wallet.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wojdylak.wallet.domain.valueData.FinancialAssetValueData;
import pl.wojdylak.wallet.service.FinancialAssetService;
import pl.wojdylak.wallet.service.valueData.FinancialAssetDataService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/financial-assets")
@RequiredArgsConstructor
public class FinancialAssetController {
    private final FinancialAssetService financialAssetService;
    private final FinancialAssetDataService financialAssetDataService;

    @GetMapping("/{ticker}/price")
    public BigDecimal getTickerCurrentPrice(@PathVariable String ticker) throws Exception {
        return financialAssetService.getTickerCurrentPrice(ticker);
    }

    @GetMapping("/tickers")
    public String getAllTickers() throws Exception {
        return financialAssetService.getAllXtbTickers();
    }

    @GetMapping("/{ticker}/history")
    public List<FinancialAssetValueData> getFinancialAssetDataValues(@PathVariable String ticker) {
        return financialAssetDataService.getFinancialAssetDataValues(ticker);
    }
}
