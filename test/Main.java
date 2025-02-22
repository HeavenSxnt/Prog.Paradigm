import java.util.*;
import java.io.*;

class MyThread extends Thread{
    MyThread(String name) {super(name);}
    public void run(){System.out.println("Thread is running");}
}

public class Main{
    public static void main(String[] args){
        MyThread T1 = new MyThread("A");
        MyThread T2 = new MyThread("B");
        T1.start(); T2.start();
        //T1.run(); T2.run();
    }
}