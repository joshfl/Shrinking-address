package org.wdd.service.random;

import org.apache.commons.lang3.RandomUtils;
import org.wdd.domain.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class LocalRandomService implements RandomService {

    @Override
    public Integer getNumberOfAccountsToGenerate() {
        return RandomUtils.nextInt(MIN_ACCOUNT_NUM, MAX_ACCOUNT_NUM);
    }

    @Override
    public Integer getParticularAccountNumberToChoose(int maxNumber) {
        return RandomUtils.nextInt(MIN_ACCOUNT_NUM, maxNumber);
    }

    @Override
    public List<Integer> getWordIndexes() {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < Dictionary.NUMBER_OF_WORDS_TO_USE; i++) {
            results.add(RandomUtils.nextInt(MAX_ACCOUNT_NUM, Dictionary.WORDS_SIZE));
        }
        return results;
    }
}
