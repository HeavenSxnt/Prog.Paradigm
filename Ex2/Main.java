import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args){
        // Declaration
        int threshold;
        String thresCheck;
        Scanner input = new Scanner(System.in);
        File FileScan = new File("src/main/Java/Ex2_6581098/platforms.txt");
        // File FileScan = new File("platforms.txt");
        File Output = new File("src/main/Java/Ex2_6581098/output.txt");
        // File Output = new File("output.txt");

        // Location of platforms.txt
        try{
            System.out.println("Read platform data from " + FileScan.getPath());
            // System.out.println("Read platform data from " + FileScan.getAbsolutePath());
        } catch (Exception e) {System.err.println(e);}

        // Scan threshold
        System.out.println("Enter MAU threshold in millions = ");
        threshold = input.nextInt();
        input.close();

        // Location of output.txt
        try{
            System.out.println("Write output " + Output.getPath());
            // System.out.println("Write output " + Output.getAbsolutePath());
        } catch (Exception e) {System.err.println(e);}

        // Write on output.txt
        try {
            Scanner Original = new Scanner(FileScan);
            PrintWriter WriteOutput = new PrintWriter(Output);
            WriteOutput.printf("%-15s %-20s %-17s >%,d millions\r\n","Platform", "MAU(thousands)", "YAU(billions)", threshold);
            WriteOutput.printf("=".repeat(80));
            WriteOutput.printf("\r\n");
            while(Original.hasNext()){
                String platform = Original.next();
                int num = Original.nextInt();
                int MAU = num*1000;
                float YAU = (float)num/1000;

                if(num > threshold) {thresCheck = "yes";}
                else {thresCheck = "no";}
                WriteOutput.printf("%-15s %,12d %18.3f %15s\r\n",platform, MAU, YAU, thresCheck);
            }
            WriteOutput.flush();
            WriteOutput.close();
            Original.close();
        } catch (Exception e) {System.err.println(e);}
    }
}
