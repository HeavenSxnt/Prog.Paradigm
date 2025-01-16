import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args){
        // Declaration
        int threshold;
        Scanner input = new Scanner(System.in);
        File FileScan = new File("platforms.txt");
        File Output = new File("output.txt");

        // Location of platforms.txt
        try{
            System.out.println("Read platform data from " + FileScan.getAbsolutePath());
        } catch (Exception e) {System.err.println(e);}

        // Scan threshold
        System.out.println("Enter MAU threshold in millions = ");
        threshold = input.nextInt();

        // Location of output.txt
        try{
            System.out.println("Write output " + Output.getAbsolutePath());
        } catch (Exception e) {System.err.println(e);}

        // Write on output.txt
        try {
            Scanner Original = new Scanner(FileScan);
            PrintWriter WriteOutput = new PrintWriter(Output);
            WriteOutput.printf("%s %s %s >%d millions\r\n","Platform", "MAU(thousands)", "YAU(billions)", threshold);
        } catch (Exception e) {System.err.println(e);}
    }
}
