package org.wdd.service.random;

import java.util.List;

public interface RandomService {

    /** Min number of accounts to generate */
    int MIN_ACCOUNT_NUM = 1;
    /** Min account index */
    int MIN_ACCOUNT_INDEX = 0;
    /** Max number of accounts to generate */
    int MAX_ACCOUNT_NUM = 20;

    /**
     * @return random number of account we need to generate
     */
    Integer getNumberOfAccountsToGenerate();

    /**
     * Random account number picker, base on array amount.
     *
     * @param maxNumber upper limit of account index (i.e. result of {@link this#getNumberOfAccountsToGenerate)
     * @return  random account index to use as the app result
     */
    Integer getParticularAccountNumberToChoose(int maxNumber);

    /**
     * @return randomly picked indexes of {@link org.wdd.domain.Dictionary#WORDS} we need to use as the phrase
     */
    List<Integer> getWordIndexes();
}
