package com.erenerdilli;

public class Response {

    private String paperID;

    public Response() {
    }

    public Response(String paperID) {
        this.paperID = paperID;
    }

    public String getPaperID() {
        return paperID;
    }

    public void setPaperID(String paperID) {
        this.paperID = paperID;
    }
}
