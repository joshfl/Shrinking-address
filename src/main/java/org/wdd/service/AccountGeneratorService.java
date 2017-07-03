package org.wdd.service;

import org.wdd.domain.GeneratorType;
import org.wdd.domain.blockchain.BlockchainAccountInfo;
import org.wdd.handler.DictionaryHandler;
import org.wdd.service.blockchain.BlockchainService;
import org.wdd.service.blockchain.BlockchainServiceImpl;
import org.wdd.service.random.LocalRandomService;
import org.wdd.service.random.RandomOrgService;
import org.wdd.service.random.RandomService;

import java.util.ArrayList;
import java.util.List;

public class AccountGeneratorService {

    private RandomService randomOrgService;
    private RandomService localRandomService;
    private BlockchainService blockchainService;
    private DictionaryHandler dictionaryHandler;
    private List<BlockchainAccountInfo> accounts;

    public AccountGeneratorService() {
        this.randomOrgService = new RandomOrgService();
        this.localRandomService = new LocalRandomService();
        this.blockchainService = new BlockchainServiceImpl();
        this.dictionaryHandler = new DictionaryHandler();
        this.accounts = new ArrayList<>();
    }

    /**
     * Generate account number / account rs number base on the results of random sequence generator.
     * <p>
     * Default method is remote sequence generator (web resource random.org)
     * In case, when such resource is unreachable or some kind of unexpected results or any exceptions
     * occurred, local sequence generator will be used.
     *
     * @param type the type of random generator
     * @return randomly picked blockchain account.
     */
    public BlockchainAccountInfo getAccount(GeneratorType type) {
        System.out.print("Phase one: get number of accounts to be generated...");
        int numberOfAccountsToGenerate = phaseOne(type);
        System.out.println(numberOfAccountsToGenerate);

        for (int i = 0; i < numberOfAccountsToGenerate; i++) {
            System.out.print("Phase two: get dictionary words indexes for account " + (i + 1) + "...");
            List<Integer> wordIndexes = phaseTwo(type);
            System.out.println("OK");

            System.out.print("Phase three: generate phrase for account " + (i + 1) + "...");
            String phrase = phaseThree(wordIndexes);
            System.out.println("OK");

            System.out.print("Phase four: retrieve account data from blockchain for account " + (i + 1) + "...");
            accounts.add(phaseFour(phrase));
            System.out.println("OK");
        }

        int accountIndex = phaseFive(type, numberOfAccountsToGenerate - 1);
        System.out.println("Phase five: Randomly pick one of the generated accounts index [0..." + (numberOfAccountsToGenerate - 1) + "]..." + accountIndex);
        return accounts.get(accountIndex);
    }

    /**
     * Phase 1.
     * <p>
     * Randomly get number of accounts we need to generate.
     *
     * @param type the type of sequence generator to use.
     * @return number of accounts we have to generate.
     */
    private Integer phaseOne(GeneratorType type) {
        if (GeneratorType.LOCAL.equals(type)) {
            return localRandomService.getNumberOfAccountsToGenerate();
        }

        try {
            return randomOrgService.getNumberOfAccountsToGenerate();
        } catch (Exception e) {
            return localRandomService.getNumberOfAccountsToGenerate();
        }
    }

    /**
     * Phase 2.
     * <p>
     * Randomly get indexes of the words we need to use to generate passphrase.
     *
     * @param type the type of sequence generator to use.
     * @return words indexes to use
     */
    private List<Integer> phaseTwo(GeneratorType type) {
        if (GeneratorType.LOCAL.equals(type)) {
            return localRandomService.getWordIndexes();
        }

        try {
            return randomOrgService.getWordIndexes();
        } catch (Exception e) {
            return localRandomService.getWordIndexes();
        }
    }

    /**
     * Phase 3.
     * <p>
     * Generate phrase glued with spaces
     *
     * @param indexes indexes of the words we need to use
     * @return generated phrase based on the randomly picked words indexes.
     */
    private String phaseThree(List<Integer> indexes) {
        return dictionaryHandler.generatePhrase(indexes);
    }

    /**
     * Phase 4.
     * <p>
     * Generate account info, based on the phrase.
     *
     * @param phrase phrase to use as the key
     * @return account info.
     */
    private BlockchainAccountInfo phaseFour(String phrase) {
        return blockchainService.getAccountInfo(phrase);
    }

    /**
     * Phase 5.
     * <p>
     * Randomly pick one of the generated accounts.
     *
     * @param type            sequence generator to use (local or remote)
     * @param generatedNumber randomly picked up index.
     * @return account info.
     */
    private Integer phaseFive(GeneratorType type, int generatedNumber) {
        if (GeneratorType.LOCAL.equals(type)) {
            return localRandomService.getParticularAccountNumberToChoose(generatedNumber);
        }

        try {
            return randomOrgService.getParticularAccountNumberToChoose(generatedNumber);
        } catch (Exception e) {
            return localRandomService.getParticularAccountNumberToChoose(generatedNumber);
        }
    }
}
