package pl.wojdylak.wallet.apiClient;

import org.springframework.stereotype.Component;
import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.TickRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.LoginResponse;
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

    public TickRecord getTickerCurrentPrice(String ticker) throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException {

        if (!initializeConnection()) {
            throw new RuntimeException("Failed to establish connection with XTB API");
        }
        return APICommandFactory.executeTickPricesCommand(connector, 0L, List.of(ticker), Instant.now().getEpochSecond())
                .getTicks()
                .get(0);
    }

    public String getAllXtbTickers() throws APIErrorResponse, APICommunicationException, APIReplyParseException, APICommandConstructionException {

        if (!initializeConnection()) {
            throw new RuntimeException("Failed to establish connection with XTB API");
        }

        return APICommandFactory.executeAllSymbolsCommand(connector).toString();
    }

    private boolean initializeConnection() {
        try {
            connector = new SyncAPIConnector(server);
            Credentials credentials = new Credentials(LOGIN, PASSWORD);
            LoginResponse loginResponse = APICommandFactory.executeLoginCommand(connector, credentials);
            return loginResponse != null && loginResponse.getStatus();
        } catch (IOException | APIErrorResponse | APICommunicationException | APIReplyParseException |
                 APICommandConstructionException e) {
            return false;
        }
    }

}

