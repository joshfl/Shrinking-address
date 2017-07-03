package org.wdd.handler;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequestHandler {

    /**
     * Do requests based on provide object
     *
     * @param requestBase request object (get / post etc)
     * @return body in case of success
     * @throws IllegalStateException in case, when request failed.
     */
    public String doRequest(HttpUriRequest requestBase) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ResponseHandler<String> responseHandler = httpResponse -> {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new ClientProtocolException("Unexpected response status: " + statusCode + " Error: " + httpResponse.getStatusLine().getReasonPhrase());
                }
                return EntityUtils.toString(httpResponse.getEntity());
            };
            return httpclient.execute(requestBase, responseHandler);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
