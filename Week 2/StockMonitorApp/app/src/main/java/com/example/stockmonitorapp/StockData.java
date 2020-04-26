package com.example.stockmonitorapp;

public class StockData {

    private String indentifier;
    private double price;

    public StockData(String indentifier, double price) {
        this.indentifier = indentifier;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getIndentifier() {
        return indentifier;
    }
}
