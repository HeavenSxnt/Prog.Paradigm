// Siriwat 6581098

//package Ex6_6581098;

import java.util.*;
import java.io.*;

class OneCard {
    public static final int totalCard = 52;
    public static final int CardPerRound = 4;

    private int score; // 0-51
    private int suit;  // (0)clubs, (1)diamonds, (2)hearts, (3)spades
    private int rank;  // (1)ace, 2-10, (11)jack, (12)queen, (13)king   

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

    // Checking duplicate card (for .contains)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof OneCard)) return false;
        OneCard other = (OneCard) obj;
        return this.score == other.score;
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
        // Create PrintWriter object to write result to a separate file
        try(PrintWriter Out = new PrintWriter(new File("T" + this.ThreadNumber))){
            while(true){
                while(randomCards.size() < OneCard.CardPerRound){
                   OneCard start = new OneCard();
                    if(!randomCards.contains(start)){
                        randomCards.add(start);
                    } 
                }
                
                ArrayList<String> Card = new ArrayList<>();
                for(OneCard single : randomCards){
                    // Case for suit
                    String storeRank;
                    switch(single.getRank()){
                        case 1 : storeRank = "A"; break;
                        case 11 : storeRank = "J"; break;
                        case 12 : storeRank = "Q"; break;
                        case 13 : storeRank = "K"; break;
                        default : storeRank = String.valueOf(single.getRank());
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
                    Card.add(String.format("%3s of %-8s",storeRank, storeSuit));
                }
                Out.printf("Round %3d [%s   , %s   , %s   , %s  ]\n",this.round, Card.get(0), Card.get(1), Card.get(2), Card.get(3));
                Out.flush();

                if((randomCards.get(0).getSuit() == randomCards.get(1).getSuit() &&
                    randomCards.get(0).getSuit() == randomCards.get(2).getSuit() &&
                    randomCards.get(0).getSuit() == randomCards.get(3).getSuit()) ||
    
                    (randomCards.get(0).getRank() == randomCards.get(1).getRank() &&
                    randomCards.get(0).getRank() == randomCards.get(2).getRank() &&
                    randomCards.get(0).getRank() == randomCards.get(3).getRank())){
                    System.out.printf("Thread T%d finishes in %3d rounds \n",this.ThreadNumber, this.round);
                    break;
                }
                randomCards.clear();
                this.round++;
            }
        } catch (FileNotFoundException e) { System.err.println(e);}
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
                scan.nextLine();
                if(AskforThread > 0) break;
                else System.out.println("Number of thread should more than 0");
            }catch (Exception e) {
                System.err.println(e);
                System.out.println("Error invalid input");
                scan.nextLine();
            } 
        }
        scan.close();

        for(int i=0; i<AskforThread; i++){
            CardThread amount = new CardThread(i);
            amount.start();
        }
    }
}