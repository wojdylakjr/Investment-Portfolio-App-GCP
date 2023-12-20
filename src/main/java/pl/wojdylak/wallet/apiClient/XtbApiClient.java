package pl.wojdylak.wallet.apiClient;

import org.springframework.stereotype.Component;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.AllSymbolsResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.message.response.TickPricesResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.SyncAPIConnector;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Component
public class XtbApiClient {
    private static final String LOGIN = "15458284";
    private static final String PASSWORD = "Pass123!@#";
    private final ServerData.ServerEnum server = ServerData.ServerEnum.DEMO;
    private SyncAPIConnector connector;

    public TickPricesResponse getTickerCurrentPrice(String ticker) throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException {
        initialConnection();
        return APICommandFactory.executeTickPricesCommand(connector, 0L, List.of(ticker), Instant.now().getEpochSecond());
    }

    public String getAllSymbols() throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException {
        initialConnection();
        AllSymbolsResponse allSymbolsResponse = APICommandFactory.executeAllSymbolsCommand(connector);
        return allSymbolsResponse.toString();
    }

    private boolean initialConnection() {
        try {
            connector = new SyncAPIConnector(server);
            Credentials credentials = new Credentials(LOGIN, PASSWORD);
            LoginResponse loginResponse = APICommandFactory.executeLoginCommand(connector, credentials);
            return loginResponse != null && loginResponse.getStatus();
        } catch (IOException | APIErrorResponse | APICommunicationException | APIReplyParseException |
                 APICommandConstructionException e) {
            throw new RuntimeException(e);
        }
    }

}

