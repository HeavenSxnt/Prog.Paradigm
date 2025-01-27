// Siriwat 6581098

// Package Ex3_6581098

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
    protected int avgGoals;
    protected int[] arrayOfgames;
    protected int[] arrayOfgoals;

    class FootballPlayer(String nm, int by, int[] games, int[] goals){
        super(nm,by);
        arrayOfgames = games;
        arrayOfgoals = goals;
    }

    @Override
    public void printPersonalData(){
        System.out.printf("%s born %d age = %d\n",getName(), birthyear, CURRENT_YEAR - birthyear);
    }

    @Override
    public void printStat(){
        System.out.printf("%s total games = %d total goals = %d (%.2f per game) last season goals = %d\n",getName(), Sum(arrayOfgames), Sum(arrayOfgoals), (float)Sum(arrayOfgoals)/Sum(arrayOfgames), arrayOfgoals[arrayOfgoals.length-1]);
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

    class BasketballPlayer(String nm, int by, int totalG, int totalM, int totalP){
        super(nm,by);
        totalGames = totalG;
        totalMins = totalM;
        totalPts = totalP;
        avgMins = (float)totalM/totalG;
        avgPts = (float)totalP/totalMG;
    }

    @Override
    public void printPersonalData(){
        System.out.printf("%s born %d age = %d\n",getName(), birthyear, CURRENT_YEAR - birthyear);
    }
    
    @Override
    public void printStat(){
        System.out.printf("%s total games = %d total mins = %d (%.2f per game) total points = %d (%.2f per game)\n",getName(), totalGames, totalMins, avgMins, totalPts, avgPts);
    }
}

// Exta class

public class Main{
    public static void main(String[] args){
        Player[] allPlayers = new Player[12];
        File FileScan = new File("players.txt");
        try{
            Scanner PlayerFile = new Scanner(FileScan);
            for(int i =0; i<= 11; i++){
                String line = FileScan.nextLine();
                String [] cols = line.spilt(",");
            }
        }catch (Exception e) {System.err.println(e);}
    }
}
