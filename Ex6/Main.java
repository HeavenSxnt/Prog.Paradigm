// Siriwat 6581098

// package Ex6_6581098;

import java.util.*;
import java.io.*;

class OneCard {
    private int score; // 0-51
    private int suit; // clubs, diamonds, hearts, spades
    private int rank; // ace, 2-10, jack, queen, king   

    public OneCard(int score) { this.score = score; } 
    public OneCard(int suit) { this.suit = suit; }
    public OneCard(int rank) { this.rank = rank; } 
}

class CardThread extends Thread {
    private PrintWriter out;
    private ArrayList<OneCard> randomCards;

    public void run() {
    // Create PrintWriter object to write result to a separate file
    
    // Execute steps 1-3 in loop:
    // 1. Draw 4 cards from the same deck. The cards must not duplicate.
    // 2. Print round number and these 4 cards to output file.
    // 3. If all cards are from the same suit or have equal rank, stop the loop.
    // Otherwise, clear randomCards & continue to the next round.

    // After the loop, print #rounds to the screeen
}



}public class Main{
    public static void main(String[] args){
        
    }
}