package org.wdd.domain.blockchain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockchainAccountInfo {

    /** Account RS */
    private String accountRS;
    /** Numeric account ID */
    private String account;

    private String getGecAccount() {
        return (accountRS == null) ? null : accountRS.replaceAll("^NXT-", "GEC-");
    }

    @Override public String toString() {
        return String.format("accountRS: %s, account: %s", getGecAccount(), account);
    }
}
