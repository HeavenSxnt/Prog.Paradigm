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
    public int avgGoals;

    @Override
    public void printPersonalData(){

    }

    @Override
    public void printStat(){

    }
}

class BasketballPlayer extends Player{
    public int totalGames, totalMins, totalPts, avgMins, avgPts;
    int[] games;
    int[] goals;

    @Override
    public void printPersonalData(){

    }
    
    @Override
    public void printStat(){

    }
}

public class Main{
    public static void main(String[] args){
        Player[] allPlayers;
        File FileScan = new File("players.txt");
        Scanner PlayerFile = new Scanner(FileScan);
        for(int i =0; i<= 11; i++){
            Player[i] allPlayers;
            // System.out.print
        }

    }
}
