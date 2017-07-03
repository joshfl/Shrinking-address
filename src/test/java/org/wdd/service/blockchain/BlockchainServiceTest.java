package org.wdd.service.blockchain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wdd.domain.blockchain.BlockchainAccountInfo;
import org.wdd.handler.HttpRequestHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class BlockchainServiceTest {

    private @InjectMocks BlockchainServiceImpl service;
    private @Mock HttpRequestHandler requestHandler;

    private static final String MOCK_RESPONSE = "{" +
            "\"accountRS\":\"GEC-69WD-HCR4-TMQ6-57JZU\", " +
            "\"publicKey\": \"a9e72c7532f3343754197bcec7ced7b9514f77c6f90757352f2d8fb3b5a8de0f\", " +
            "\"requestProcessingTime\":2, " +
            "\"account\": \"4430985107844767627\"" +
            "}";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new BlockchainServiceImpl();
        service.setRequestHandler(requestHandler);
        when(requestHandler.doRequest(any())).thenReturn(MOCK_RESPONSE);
    }

    @Test
    public void test_replace() {
        String org = "NXT-69WD-HCR4-TMQ6-57JZU";
        String rpl = org.replaceAll("^NXT-", "GEC-");
        assertEquals("GEC-69WD-HCR4-TMQ6-57JZU", rpl);
    }

    @Test
    public void test_replace_with_NXT_in_the_middle() {
        String org = "NXT-69WD-HNXT-TMQ6-57JZU";
        String rpl = org.replaceAll("^NXT-", "GEC-");
        assertEquals("GEC-69WD-HNXT-TMQ6-57JZU", rpl);
    }

    @Test
    public void getAccountInfo() {
        BlockchainAccountInfo accountInfo = service.getAccountInfo("asd");
        assertNotNull(accountInfo);
        assertNotNull(accountInfo.getAccountRS());
        assertNotNull(accountInfo.getAccount());
        assertEquals("GEC-69WD-HCR4-TMQ6-57JZU", accountInfo.getAccountRS());
        assertEquals("4430985107844767627", accountInfo.getAccount());
    }

}