package com.erenerdilli;

/*
* @Author erenerdilli
* Create a Patient with given properties
 */

public class Patient {

    private String name;
    private String TCNo;
    private String mutation;
    private String entryYear;
    private String entryMonth;
    private String entryDay;

    public Patient(String name, String TCNo, String mutation, String entryYear, String entryMonth, String entryDay) {
        this.name = name;
        this.TCNo = TCNo;
        this.mutation = mutation;
        this.entryYear = entryYear;
        this.entryMonth = entryMonth;
        this.entryDay = entryDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTCNo() {
        return TCNo;
    }

    public void setTCNo(String TCNo) {
        this.TCNo = TCNo;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }

    public String getEntryMonth() {
        return entryMonth;
    }

    public void setEntryMonth(String entryMonth) {
        this.entryMonth = entryMonth;
    }

    public String getEntryDay() {
        return entryDay;
    }

    public void setEntryDay(String entryDay) {
        this.entryDay = entryDay;
    }
}
