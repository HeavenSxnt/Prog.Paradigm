// Siriwat 6581098

// package Ex6_6581098;

import java.util.*;
import java.io.*;

// total card in 1 deck = 52
// card per round = 4
// cannot draw same suit and rank AGAIN

class OneCard {
    public static final int totalCard = 52;
    public static final int CardPerRound = 4;

    private int score; // 0-51
    private int suit;  // (0)clubs, (1)diamonds, (2)hearts, (3)spades
    private int rank;  // (0)ace, 2-10, (11)jack, (11)queen, (11)king   

    public OneCard() {
        Random random = new Random();
        this.score = random.nextInt(totalCard);
        this.suit = this.score / 13; // For 4 suits
        this.rank = (this.score % 13) + 1;
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
    private PrintWriter Out;
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
        try(PrintWriter Out = new PrintWriter(new File("T" + this.ThreadNumber))){
            while(true){
                while(randomCards.size() < OneCard.CardPerRound){
                   OneCard start = new OneCard();
                    if(!randomCards.contains(start)){
                        randomCards.add(start);
                    } 
                }

                // Case for suit
                for(OneCard single : randomCards){
                    String storeRank;
                    switch(single.getSuit()){
                        case 1 : storeRank = "A"; break;
                        case 11 : storeRank = "J"; break;
                        case 12 : storeRank = "Q"; break;
                        case 13 : storeRank = "K"; break;
                        default : storeRank = String.valueOf(OneCard.getSuit());
                    }

                    // Case for Suit
                    String storeSuit;
                    switch(single.getSuit()){
                        case 0 : storeSuit = "Clubs"; break;
                        case 1 : storeSuit = "Diamonds"; break;
                        case 2 : storeSuit = "Hearts"; break;
                        case 3 : storeSuit = "Spades"; break;
                        default : storeSuit = "None"; break;
                    }
                }
                
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
        // Ask for number of thread
        int AskforThread = 0;
        Scanner scan = new Scanner(System.in);
        while(true){
           try{
                System.out.println("Number of threads = ");
                AskforThread = scan.nextInt();
                if(AskforThread > 0) break;
                else System.out.println("Number of thread should more than 0");
            }catch (Exception e) {
                System.err.println(e);
                System.out.println("Error");
                continue;
            } 
        }
        scan.close();

        /*for(int i=0; i<AskforThread; i++){
            CardThread amount = new CardThread(i);
            amount.start();
        }*/
    }
}