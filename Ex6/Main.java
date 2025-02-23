// Siriwat 6581098

// package Ex6_6581098;

import java.util.*;
import java.io.*;

class Constant{
    public static final int totalCard = 52;
    public static final int CardPerRound = 4;
}

class OneCard {
    private int score; // 0-51
    private int suit;  // (0)clubs, (1)diamonds, (2)hearts, (3)spades
    private int rank;  // (1)ace, 2-10, (11)jack, (11)queen, (11)king   

    public OneCard(int score, int suit, int rank) {
        Random random = new Random();
        this.score = random.nextInt(52);
        this.suit = this.score / 13; // For 4 suits
        this.rank = this.score % 13; 
    }

    public int getScore(){
        return this.score;
    }

    public int getSuit(){
        return this.suit;
    }

    public int getRank(){
        return this.rank;
    }
}

class CardThread extends Thread {
    private int round;
    private PrintWriter out;
    private int ThreadNumber;
    private ArrayList<OneCard> randomCards;

    public CardThread(int ThreadNumber){
        this.ThreadNumber = ThreadNumber;
        this.round = 1;
        this.randomCards = new ArrayList<>();
    }

    public void run() {
        // For checking : System.out.println("Starting Thread");

        // Create PrintWriter object to write result to a separate file
        try(PrintWriter out = new PrintWriter(new File("T" + this.ThreadNumber))){
            while(true){
                OneCard start = new OneCard();
            }
        } catch (FileNotFoundException e) { System.err.println(e);}

        // Execute steps 1-3 in loop:
        // 1. Draw 4 cards from the same deck. The cards must not duplicate.
        // 2. Print round number and these 4 cards to output file.
        // 3. If all cards are from the same suit or have equal rank, stop the loop.
        // Otherwise, clear randomCards & continue to the next round.

        // After the loop, print #rounds to the screeen
}



}public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Number of threads = ");
        int AskforThread = scan.nextInt();
        if(Ask)
        for(int i=0; i<AskforThread; i++){
            CardThread amount = new CardThread(i);
            amount.start();
        }
    }
}