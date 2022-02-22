import java.util.*;
import java.io.*;

public class ReadTextFile {
    public static void main(String[] args) {
        String filename = "";
        String line;
        try {
            filename = args[0];
            Scanner fin = new Scanner(new File(filename));
            while (fin.hasNextLine()) {
                line = fin.nextLine();
                System.out.println(line);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java ReadTextFile <filename>");
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file " + filename);
        }
    }
}