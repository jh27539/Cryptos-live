package com.example.android.firstapp;

/**
 * Created by Yatch on 11/18/2017.
 */

public class Currency {

    private int _id;
    private String currencyName;
    private Double currentValue;
    private Double capitalInvestement;
    private Double btcValue;
    private Double netProfit;

    public Currency(){

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getCapitalInvestement() {
        return capitalInvestement;
    }

    public void setCapitalInvestement(Double capitalInvestement) {
        this.capitalInvestement = capitalInvestement;
    }

    public Double getBtcValue() {
        return btcValue;
    }

    public void setBtcValue(Double btcValue) {
        this.btcValue = btcValue;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

}
