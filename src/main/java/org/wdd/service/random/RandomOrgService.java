package org.wdd.service.random;

import org.apache.http.client.methods.HttpGet;
import org.wdd.domain.Dictionary;
import org.wdd.handler.HttpRequestHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RandomOrgService implements RandomService {

    private HttpRequestHandler requestHandler;
    private static final int DEFAULT_RESULTS_NUM = 1;
    private static final String URL_TEMPLATE = "https://www.random.org/integers/?min=%d&max=%d&base=10&format=plain&col=1&rnd=new&num=%d";

    public RandomOrgService() {
        this.requestHandler = new HttpRequestHandler();
    }

    public Integer getNumberOfAccountsToGenerate() {
        final String url = String.format(URL_TEMPLATE, MIN_ACCOUNT_NUM, MAX_ACCOUNT_NUM, DEFAULT_RESULTS_NUM);
        String body = doRequest(url);
        return parseInt(body);
    }

    public Integer getParticularAccountNumberToChoose(int maxNumber) {
        final String url = String.format(URL_TEMPLATE, MIN_ACCOUNT_INDEX, maxNumber, DEFAULT_RESULTS_NUM);
        String body = doRequest(url);
        return parseInt(body);
    }

    public List<Integer> getWordIndexes() {
        final String url = String.format(URL_TEMPLATE, MIN_ACCOUNT_NUM, Dictionary.WORDS_SIZE, Dictionary.NUMBER_OF_WORDS_TO_USE);
        String body = doRequest(url);
        String[] splitResult = body.split("\n");
        if (splitResult.length != Dictionary.NUMBER_OF_WORDS_TO_USE) {
            throw new IllegalStateException("Sequence is not equals to number of words in phrase");
        }
        return Arrays.stream(splitResult).map(this::parseInt).collect(Collectors.toList());
    }

    private String doRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return requestHandler.doRequest(httpGet);
    }

    private Integer parseInt(String str) {
        if (str == null || str.isEmpty()) return null;

        try {
            return Integer.valueOf(str.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
