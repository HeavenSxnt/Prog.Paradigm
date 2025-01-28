// Siriwat 6581098

package Ex3_6581098;

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
    protected int[] arrayOfgames;
    protected int[] arrayOfgoals;
    protected int avgGoals;

    public FootballPlayer(String nm, int by, int[] games, int[] goals){
        super(nm,by);
        arrayOfgames = games;
        arrayOfgoals = goals;
    }

    @Override
    public void printPersonalData(){
        System.out.printf("%-25s born %-7d age = %2d\n",getName(), birthyear, CURRENT_YEAR - birthyear);
    }

    @Override
    public void printStat(){
        System.out.printf("%-25s total games = %4d     total goals = %4d (%4.2f per game)     last season goals = %2d\n",getName(), Sum(arrayOfgames), Sum(arrayOfgoals), (float)Sum(arrayOfgoals)/Sum(arrayOfgames), arrayOfgoals[arrayOfgoals.length-1]);
    }

    private int Sum(int[] array){
        int sum = 0;
        for (int i = 0; i<array.length;i++){
            sum += array[i];
        }
        return sum;
    }
}

class BasketballPlayer extends Player{
    protected int totalGames, totalMins, totalPts; 
    protected float avgMins, avgPts;

    public BasketballPlayer(String nm, int by, int totalG, int totalM, int totalP){
        super(nm,by);
        totalGames = totalG;
        totalMins = totalM;
        totalPts = totalP;
        avgMins = (float)totalM/totalG;
        avgPts = (float)totalP/totalG;
    }

    @Override
    public void printPersonalData(){
        System.out.printf("%-25s born %-7d age = %2d\n",getName(), birthyear, CURRENT_YEAR - birthyear);
    }
    
    @Override
    public void printStat(){
        System.out.printf("%-25s total games = %4d     total mins = %5d (%3.2f per game)    total points = %5d (%3.2f per game)\n",getName(), totalGames, totalMins, avgMins, totalPts, avgPts);
    }
}

public class Main{
    public static void main(String[] args){
        Player[] allPlayers = new Player[12];
        File FileScan = new File("src/main/java/Ex3_6581098/players.txt");
        //File FileScan = new File("players.txt");
        try{
            Scanner PlayerFile = new Scanner(FileScan);
            for(int i =0; i<= 11; i++){
                String line = PlayerFile.nextLine();
                String [] cols = line.split(",");
                String Category = cols[0];
                int year = Integer.parseInt(cols[2].trim());

                if(Category.equals("F")){
                    int [] FBgame = new int[3];
                    int [] FBgoal = new int[3];
                    String [] s1 = cols[3].split(":");

                    FBgame[0] = Integer.parseInt(s1[0].trim()); 
                    FBgoal[0] = Integer.parseInt(s1[1].trim()); 

                    String [] s2 = cols[4].split(":");
                    FBgame[1] = Integer.parseInt(s2[0].trim()); 
                    FBgoal[1] = Integer.parseInt(s2[1].trim()); 
                    
                    String [] s3 = cols[5].split(":");
                    FBgame[2] = Integer.parseInt(s3[0].trim()); 
                    FBgoal[2] = Integer.parseInt(s3[1].trim()); 

                    allPlayers[i] = new FootballPlayer(cols[1].trim(),year,FBgame, FBgoal);
                }
                else{
                    int BaskGame = Integer.parseInt(cols[3].trim());
                    int BaskMin = Integer.parseInt(cols[4].trim());
                    int BaskPts = Integer.parseInt(cols[5].trim());

                    allPlayers[i] = new BasketballPlayer(cols[1].trim(), year, BaskGame, BaskMin, BaskPts);
                }
            }
            System.out.println("=== All player data (by reverse order) ===");
            for(int k=11; k>=0; k--){
                allPlayers[k].printPersonalData();
            }


            System.out.println("\n=== Football player statistics (by input order) ===");
            for(int k=0; k<=11; k++){
                if ( allPlayers[k] instanceof FootballPlayer ) {
                    FootballPlayer p = (FootballPlayer) allPlayers[k];
                    p.printStat();
                }
            }

            System.out.println("\n=== Basketball player statistics (by input order) ===");
            for(int k=0; k<=11; k++){
                if ( allPlayers[k] instanceof BasketballPlayer ) {
                    BasketballPlayer p = (BasketballPlayer) allPlayers[k];
                    p.printStat();
                }
            }

            PlayerFile.close();
        }catch (Exception e) {System.err.println(e);}
    }
}
