package com.erenerdilli;

public class RequestBuilder {

    private String mutation;
    private String entryYear;
    private String entryMonth;
    private String entryDay;

    public RequestBuilder(String mutation, String entryYear, String entryMonth, String entryDay) {
        this.mutation = mutation;
        this.entryYear = entryYear;
        this.entryMonth = entryMonth;
        this.entryDay = entryDay;
    }


    /*
    * @Author ereneredilli
    * Build a URL for sending request to NCBI
    * @return url
    */
    public String getRequestURL(){
        String url;
        url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=" + this.mutation +
                "%5BAll%20Fields%5D%20AND%20(%22" + this.entryYear + "%2F" + this.entryMonth + "%2F" + this.entryDay +
                "%22%5BPDAT%5D%20%3A%20%223000%2F12%2F31%22%5BPDAT%5D)&RetMax=50";

        return url;
    }
}
