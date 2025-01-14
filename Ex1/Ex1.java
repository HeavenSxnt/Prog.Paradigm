import java.util.Scanner;

public class Ex1{

    public static void main(String[] args){
        int Hour,Min,EndHour,EndMin;
        System.out.println("=== Start time ===");
        while(true){
            System.out.println("Enter hour digit (0 - 23) = ");
            Hour = readHour();
            if (Hour>= 0 && Hour <= 23){
                break;
            }
        }
        while(true){
            System.out.println("Enter minute digit (0 - 59) = ");
            Min = readMin();
            if (Min>= 0 && Min <= 59){
                break;
            }
        }

        System.out.println("\n=== End time ===");
        while(true){
            System.out.println("Enter hour digit (0 - 23) = ");
            EndHour = readHour();
            if (EndHour>= 0 && EndHour <= 23){
                break;
            }
        }
        while(true){
            System.out.println("Enter minute digit (0 - 59) = ");
            EndMin = readMin();
            if (EndMin>= 0 && EndMin <= 59){
                break;
            }
        }
        System.out.printf("Start time = %02d:%02d , End time = %02d:%02d ",Hour,Min,EndHour,EndMin);
        if(EndHour==Hour && EndMin<Min || EndHour<Hour){
            System.out.println("(Tomorrow)");
        }
        else{System.out.println("(Today)");}

        int FinalHour,FinalMin;
        FinalHour = EndHour - Hour;
        FinalMin = EndMin - Min;
        if (FinalMin< 0){FinalMin+=60; FinalHour-=1;}
        if (FinalHour < 0){FinalHour+=24;}
        
        System.out.printf("Duration = %d hours, %d minutes",FinalHour,FinalMin);
    }

    public static int readHour(){
        int hour;
        Scanner input = new Scanner(System.in);
        hour=input.nextInt();
        return hour;
    }

    public static int readMin(){
        int Min;
        Scanner input = new Scanner(System.in);
        Min=input.nextInt();
        return Min;
    }
}