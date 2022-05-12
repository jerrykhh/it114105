import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BstTiming {
    public static void main(String[] args) {
        try {
			StringBst tree = new StringBst();
			Scanner fin = new Scanner(new File(args[0]));
			String line;
			while (fin.hasNextLine()) {
				line = fin.nextLine();
				line = line.trim();
				tree.addNode(line);
			}
			fin.close();
			long startTime = System.nanoTime();
			String ans = tree.search(args[1]);
			long endTime = System.nanoTime();
			if (ans==null) 
				System.out.println("Not found; Time used: " + (endTime - startTime));
			else 
				System.out.println("Found; Time used: " + (endTime - startTime));
		}
		catch (FileNotFoundException e) {
			System.out.println("Failed to open " + args[0]);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: BstTiming <word_file> <search_word>");
		}
    }
}
