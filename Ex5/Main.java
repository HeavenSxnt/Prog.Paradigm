// Siriwat Ittisompiboon 6581098

// package Ex5_6581098;

import java.util.*;
import java.io.*;

class InvalidYearException extends Exception {
    public InvalidYearException(String message) {
        super(message);
    }
}

class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}

class Company implements Comparable<Company>{
    private String name;
    private int year, marketValue, profit, sales;

    public Company(String name, int year, int marketValue, int profit, int sales){
        this.name = name;
        this.year = year;
        this.marketValue = marketValue;
        this.profit = profit;
        this.sales = sales;
    }

    public void printInfo(){
        System.out.printf("%-22s (%d) %,17d %17d %15d\n",name, year, marketValue, profit, sales);
    }

    @Override
    public int compareTo(Company other){
        if(this.marketValue > other.marketValue){return -1;}
        else if(this.marketValue < other.marketValue){return 1;}

        else if(this.profit > other.profit) {return -1;}
        else if(this.profit < other.profit) {return 1;}

        else if(this.sales > other.sales) {return -1;}
        else if(this.sales < other.sales) {return 1;}

        else if(this.name.compareToIgnoreCase(other.name)<= 0) {return -1;}
        else if(this.name.compareToIgnoreCase(other.name)>0) {return 1;}

        else{return 0;} 
    }

    @Override
    public String toString(){
        return name + ":" + year + ":" + marketValue + ":" + profit + ":" + sales;
    }
}

public class Main{
    public static void CheckingYear(int year) throws InvalidYearException {
        if (year < 0 || year > 2024) throw new InvalidYearException("For year: \"" + year + "\"");
    }

    public static void PositiveNum(int[] number) throws InvalidNumberException {
        for (int i=0; i<number.length; i++) {
            if (number[i] < 0) throw new InvalidNumberException("For input: \"" + number[i] + "\"");
        }
    }

    public static void main(String[] args){
        Scanner Scan = new Scanner(System.in);
        // File FileInput = new File("companies.txt");
        File FileInput = new File("src/main/java/Ex4_6581098/companies.txt");
        ArrayList<Company> Companies = new ArrayList<Company>();

        while(true){
            try(Scanner FileScan = new Scanner(FileInput)){
                FileScan.nextLine();

                while(FileScan.hasNext()){
                    String line = FileScan.nextLine();
                    try{
                        String [] cols = line.trim().split("\\s*,\\s*");
                        String name = cols[0];
                        int year = Integer.parseInt(cols[1]);
                        int marketValue = (int) Double.parseDouble(cols[2]);
                        int profit = (int) Double.parseDouble(cols[3]);
                        int sales = (int) Double.parseDouble(cols[4]);
                        CheckingYear(year);
                        int[] number = {marketValue, profit, sales};
                        PositiveNum(number);
                        Companies.add(new Company(name, year, marketValue, profit, sales));
                    }catch(Exception e){
                        System.err.println(e);
                        System.out.println(line + "\n");
                    }
                }
                FileScan.close();
                Collections.sort(Companies);
                break;
            }catch (FileNotFoundException e){
                System.err.println(e);
                System.out.println("New File Name = ");
                FileInput = new File(Scan.nextLine().trim());   
                //FileInput = new File("src/main/Java/Ex5_6581098/" + Scan.nextLine().trim());  
            }
        }
        
        // Scanner
        int threshold;
        //System.out.println("Enter year threshold = ");
        while(true){
            try{
                System.out.println("Enter year threshold = ");
                threshold = Scan.nextInt();
                break;
            }catch(InputMismatchException e){
                Scan.nextLine();
                System.err.println(e + ": Try again, Invalid Input");
            }
        }

        System.out.printf("Company established  since %d    Market Value($Bn.).    Profit($Bn.)    Sales($Bn.)\n",threshold);
        System.out.println("=".repeat(90));
        for(int i=0; i<Companies.size(); i++){
            Companies.get(i).printInfo();
        }
        Scan.close();
    }
}
