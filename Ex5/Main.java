// Siriwat Ittisompiboon 6581098

// package Ex5_6581098;

import java.util.*;
import java.io.*;

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

    // Compare to decreasing order
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

    public void printInfo(){
        System.out.printf("%-22s (%d) %,17d %17d %15d\n",name, year, marketValue, profit, sales);
    }
}

public class Main{
    public static void main(String[] args){
        File FileInput = new File("companies.txt");
        // File FileInput = new File("src/main/java/Ex4_6581098/companies.txt");
        try{
            while(true){
                // Declaration
                ArrayList<Company> Companies = new ArrayList<Company>();
                Scanner FileScan = new Scanner(FileInput);
                FileScan.nextLine();
                Scanner Scan = new Scanner(System.in);
                
                // Header & non-numeric value
                int threshold;
                System.out.println("Enter year threshold = ");
                while(true){
                    try{
                        threshold = Scan.nextInt();
                        break;
                    }catch(InputMismatchException e){
                        Scan.nextLine();
                        System.err.println(e + ": Invalid Input");
                    }
                }
                System.out.printf("Company established  since %d    Market Value($Bn.).    Profit($Bn.)    Sales($Bn.)\n",threshold);
                System.out.println("=".repeat(90));

                // Skip the 1st line & get info
                for(int i=0; i<=27; i++){
                    String line = FileScan.nextLine();
                    String [] cols = line.split(",");
                    String name = cols[0].trim();
                    int year = Integer.parseInt(cols[1].trim());
                    int marketValue = Integer.parseInt(cols[2].trim());
                    int profit = Integer.parseInt(cols[3].trim());
                    int sales = Integer.parseInt(cols[4].trim());

                    if(year>= threshold){
                        Companies.add(new Company(name, year, marketValue, profit, sales));
                    }
                }

                // Print all
                Collections.sort(Companies);
                for(int i=0; i<Companies.size(); i++){
                    Companies.get(i).printInfo();
                }
                FileScan.close();

                // Continue or break 
                System.out.println("\n\n\nEnter y or Y to continue, else to quit = ");
                String Choice = Scan.nextLine();
                if (Choice.equals("y")||Choice.equals("Y")) {continue;}
                else {Scan.close(); break;}
            }
        }catch (Exception e){ System.err.println(e);}
    }
}
