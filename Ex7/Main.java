// Siriwat 6581098

// package Ex7_6581098;    

import java.util.*;
import java.util.concurrent.*;

////////////////////////////////////////////////////////////////////////////////
class BankThread extends Thread
{
    private Account             sharedAccount;    
    private Exchanger<Account>  exchanger;         
    private CyclicBarrier       barrier;
    private int                 rounds;
    private boolean             modeD;   //deposit (true) or withdraw (false)

    public BankThread(String n, Account sharedAccount, boolean modeD)  { 
        super(n); 
        this.sharedAccount = sharedAccount; 
        this.modeD = modeD;
    }
    
    public void setBarrier(CyclicBarrier barrier)            { this.barrier = barrier; }
    public void setExchanger(Exchanger<Account> exchanger)     { this.exchanger = exchanger; }
    
    public void run() {
        // Loop for banking simulation. In each simulation:        
        //  (1) Wait until main thread gets #rounds from user and pass it to BankThread.
        while(true){

        }
        //  (2) If this is the first simulation, skip this step.
        //      Otherwise, depositing threads exchange accounts with each other.
        //      Withdrawing threads that don't exchange accounts must wait until this is done.
        
        //  (3) Each thread identifies account it is managing in this simulation
        
        //  (4) Depositing threads deposit to sharedAccount for #rounds.
        //      Withdrawing threads withdraws from sharedAccount for #rounds
        
        // Break from loop & return if user doesn't want to run a new simulation
    }
}

////////////////////////////////////////////////////////////////////////////////
class Account {
    private String  name;
    private int     balance;
    
    public Account(String name, int balance) {this.name = name; this.balance = balance;}
    public String getName()            { return name; }
    public int    getBalance()         { return balance; }
    Random random = new Random();
    
    public void deposit() 
    {
        // Random money (1 to 100) to deposit; update the balance
        random.nextInt(101);
        // Report thread activity (see example output)
    }
    
    public void withdraw()
    {
        // Random money (1 to balance/2) to withdraw; update the balance
        random.nextInt();
        // Report thread activity (see example output)
        //   - But if balance is already 0, report that withdrawal fails
    }
}



public class Main {
    public static void main(String[] args) {
        Main mainApp = new Main();
        mainApp.runSimulation();
        Scanner scan = new Scanner(System.in);
        System.out.println("main >> Enter #rounds for a new simulation(-1 to quit)");
        while(true){
            int round = scan.nextInt();
            if(round == -1){
                System.exit(0);
            }
            else if(round >0){ break;}
            else if(round == 0 || round < -1){System.out.println("Try again");}
        }

        for(int i=0; i<round; i++){
            BankThread System = new BankThread(i);
            BankThread.start();
        }
    }

    public void runSimulation()
    {    
        Scanner keyboardScan = new Scanner(System.in);
        Account [] accounts  = { new Account("account A", 0), 
                                 new Account(".".repeat(35) + "account B", 0) };   
                                 // [0] for account A ---- [1] for account B
         
        ArrayList<BankThread> allThreads = new ArrayList<>();
        Exchanger<Account> exchanger     = new Exchanger<>();
        CyclicBarrier barrier            = new CyclicBarrier(4);
        
        // Create BankThread D1 and D2 for deposit(true) task
        //   - In the first simulation, D1 manages account A, D2 manages accout B
        BankThread D1 = new BankThread("D1", accounts[0], true);
        BankThread D2 = new BankThread("D2", accounts[1], true);
        //   - Pass Exchanger & CyclicBarrier objects
        
        // Create BankThread W1 and W2 for withdraw task
        //   - In all simulations, W1 manages only account A, W2 manages only account B
        BankThread W1 = new BankThread("W1", accounts[0], false);
        BankThread W2 = new BankThread("W2", accounts[1], false);
        //   - Pass CyclicBarrier object (Exchanger can be set to null)

        
        // Start all BankThreads

        
        // Loop for banking simulation. In each simulation:
        //  (1) Main thread gets #rounds from user and pass it to BankThread.
        //  (2) Main thread waits until all BankThread completes #rounds of deposit/withdraw
        
        
        // If user doesn't want to run a new simulation:
        //   - Wait until all BankThreads return
        //   - Main thread reports final balances of all accounts
    }
}
