package pl.wojdylak.wallet.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletValueRecord {
    private BigDecimal cashDeposit;
    private BigDecimal currentValue;
    private BigDecimal profit;
    private BigDecimal percentageChange;
}
