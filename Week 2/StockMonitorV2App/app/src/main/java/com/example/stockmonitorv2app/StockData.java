package com.example.stockmonitorv2app;

public class StockData {

    private String identifier;
    private double price;

    public StockData(String indentifier, double price) {
        this.identifier = indentifier;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getIdentifier() {
        return identifier;
    }
}
