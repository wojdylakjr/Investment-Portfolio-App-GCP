package pl.wojdylak.wallet.domain.dto;

import java.time.LocalDate;
import java.util.Map;

public class WalletValuesHistory {
    private Map<LocalDate, WalletValueRecord> walletValueRecordMap;
}
