package org.wdd.service.blockchain;

import org.wdd.domain.blockchain.BlockchainAccountInfo;

public interface BlockchainService {

    /**
     * Get account info from the blockchain, based on provided phrase
     *
     * @param phrase prase to use as the key
     * @return -- blockchain info object, wrapped accountRS / account number
     */
    BlockchainAccountInfo getAccountInfo(String phrase);

}
