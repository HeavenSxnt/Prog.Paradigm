// Siriwat 6581098

// package Ex4_6581098;

import java.util.*;
import java.io.*;

class Company{
    private String name;
    private int year;
    private int marketValue;
    private int profit;
    private int sales;

    public Company(String name, int year, int marketValue, int profit, int sales){
        this.name = name;
        this.year = year;
        this.marketValue = marketValue;
        this.profit = profit;
        this.sales = sales;
    }

    // getter & setter
    // Name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    // Year
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }

    // MarketValue
    public int getMarketValue(){
        return marketValue;
    }
    public void setMarketValue(int marketValue){
        this.marketValue = marketValue;
    }

    // Profit
    public int getProfit(){
        return profit;
    }
    public void setProfit(int profit){
        this.profit = profit;
    }


    // Sales
    public int getSales(){
        return sales;
    }
    public void setSales(int sales){
        this.sales = sales;
    }

    public String toString
}

class Company implements Comparator<Company> {
    private String name;
    private int year, marketValue, profit, sales;

    public int compareTo(Company other){
        // 2.3 - 2.6
    }
}

public class Main{
    public static void main(String[] args){

    }
}
