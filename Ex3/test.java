//Phukij Rittapreedanon 6581096


package Ex3_6581096;

import java.util.*;
import java.io.*;


class Player {
    public static final int CURRENT_YEAR = 2025;
    private String name;
    protected int birthyear, age;
    public Player(String nm, int by) { name = nm; birthyear = by; }
    public String getName() { return name; }
    public void printPersonalData() { /* override this in child class */ }
    public void printStat() { /* override this in child class */ }
}

class FootballPlayer extends Player{
    protected int [] arrayOfGames;
    protected int [] arrayOfGoals;
    protected int avgGoals;
    
    public FootballPlayer(String nm, int by, int[] game, int[] goal){
        super(nm,by);
        arrayOfGames = game;
        arrayOfGoals = goal;
    }
    
    @Override public void printPersonalData(){
        System.out.printf("%-25s born %-7d age = %2d\n",getName(),birthyear,CURRENT_YEAR-birthyear);
        
    }
    
    @Override public void printStat(){
        System.out.printf("%-25s total games = %3d    total goals = %3d (%3.2f per game)     last season goals = %2d\n",getName(),arraySum(arrayOfGames),arraySum(arrayOfGoals),(float)arraySum(arrayOfGoals)/arraySum(arrayOfGames),arrayOfGoals[arrayOfGoals.length-1]);
    
    }
    
    private int arraySum(int[] array){
        int sum = 0;
        for (int i = 0; i<array.length;i++){
            sum += array[i];
        }
        return sum;
    }
}

class BasketballPlayer extends Player{
    protected int totalGames;
    protected int totalMins;
    protected int totalPts;
    protected float avgMins;
    protected float avgPts;
    
    public BasketballPlayer(String nm, int by,int g, int m, int p){
        super(nm,by);
        totalGames = g;
        totalMins = m;
        totalPts = p;
        avgMins = (float)m/g;
        avgPts = (float)p/g;
    }
    
    @Override public void printPersonalData(){
        System.out.printf("%-25s born %-5d   age = %2d\n",getName(),birthyear,CURRENT_YEAR-birthyear);
        
    }
    
    @Override public void printStat(){
        System.out.printf("%-25s total games = %3d    total mins = %4d (%-4.2f per game)    total points = %-4d (%-4.2f per game)\n",getName(),totalGames,totalMins,avgMins,totalPts,avgPts);
    }
    
}



public class Ex3_6581096 {
   
    public static void main(String[] args) {
        
        
        File inputFile = new File("src/main/java/Ex3_6581096/players.txt");
        
        try{
            Scanner readFile = new Scanner(inputFile);
            Player [] allPlayers = new Player[12];
        

            for (int i = 0; i<12; i++){
                String line = readFile.nextLine();
                
                String [] cols = line.split(",");
                
                String type = cols[0];
                int year = Integer.parseInt( cols[2].trim() );
                
                if (type.equals("F")){
                    
                    int [] gameArray = new int [3];
                    int [] goalArray = new int [3];
                    
                    for(int x = 0;x<3;x++){
                    String [] s = cols[x+3].split(":");
                    gameArray[x]=Integer.parseInt(s[0].trim());
                    }
                    
                    for(int y = 0;y<3;y++){
                    String [] s = cols[y+3].split(":");
                    goalArray[y]=Integer.parseInt(s[1].trim());
                    }
                    
                    allPlayers[i] = new FootballPlayer(cols[1].trim(),year,gameArray,goalArray);
                    
                    
                    
                }else{
                    int totalGames = Integer.parseInt( cols[3].trim() );
                    int totalMins = Integer.parseInt( cols[4].trim() );
                    int totalPts = Integer.parseInt( cols[5].trim() );
                    
                    allPlayers[i] = new BasketballPlayer(cols[1].trim(),year,totalGames,totalMins,totalPts);
               } 
                
            }
            System.out.println("=== All player data (by reverse order) ===");
            for (int j = 11; j>=0;j--){
                    allPlayers[j].printPersonalData();
                }
            
            System.out.println("\n=== Football player statistics (by input order) ===");
            
            for(int j = 0; j<12; j++){
                if ( allPlayers[j] instanceof FootballPlayer ) {
                    FootballPlayer p = (FootballPlayer) allPlayers[j];
                    p.printStat();
                }
            }

            System.out.println("\n=== Basketball player statistics (by input order) ===");
            
            for(int j = 0; j<12; j++){
                if ( allPlayers[j] instanceof BasketballPlayer ) {
                    BasketballPlayer p = (BasketballPlayer) allPlayers[j];
                    p.printStat();
                }
            }
                
            readFile.close();
        } catch(Exception e){System.err.println(e);}    


       
    }
        
        
        
}