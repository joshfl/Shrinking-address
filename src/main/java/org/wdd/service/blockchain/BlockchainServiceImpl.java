package org.wdd.service.blockchain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.wdd.domain.blockchain.BlockchainAccountInfo;
import org.wdd.handler.HttpRequestHandler;

import java.io.IOException;

public class BlockchainServiceImpl implements BlockchainService {

    private HttpRequestHandler requestHandler;
    static final private ObjectMapper mapper = new ObjectMapper();
    private static final String URL_TEMPLATE = "https://demo.nxt.org/nxt?requestType=getAccountId";

    public BlockchainServiceImpl() {
        this.requestHandler = new HttpRequestHandler();
    }

    void setRequestHandler(HttpRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public BlockchainAccountInfo getAccountInfo(String phrase) {
        HttpUriRequest request = RequestBuilder.post()
                .setUri(URL_TEMPLATE)
                .addParameter(new BasicNameValuePair("secretPhrase", phrase))
                .build();

        String body = requestHandler.doRequest(request);
        return remap(body);
    }

    private BlockchainAccountInfo remap(String jsonBody) {
        try {
            return mapper.readValue(jsonBody, BlockchainAccountInfo.class);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
