package com.test;

import com.erenerdilli.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;

class RequestBuilderTest {

    @org.junit.jupiter.api.Test
    void getRequestURL() {

        RequestBuilder requestBuilder = new RequestBuilder("mutation","entryyear","entrymonth","entryday");

        String actual = requestBuilder.getRequestURL();
        String expected = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=mutation%5BAll%20Fields%5D%20AND%20(%22entryyear%2Fentrymonth%2Fentryday%22%5BPDAT%5D%20%3A%20%223000%2F12%2F31%22%5BPDAT%5D)&RetMax=50";

        assertEquals(expected, actual);
    }
}