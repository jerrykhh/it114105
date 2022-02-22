import javax.swing.JOptionPane;

public class InputParsing {
    public static void main(String[] args) {
        String input;
        int num = 0;
        int total = 0;
        int average = 0;
        int[] a = { 80, 60, 72, 85, 90 };
        String output;
        output = "The 5 marks are:";
        for (int i = 0; i < 5; i++)
            output += " " + a[i];
        output += "\nAverage of how many numbers?";
        input = JOptionPane.showInputDialog(output);
        try {
            System.out.println("Input length = " + input.length());
            num = Integer.parseInt(input);
            if (num == 0)
                throw new ArithmeticException();
            if (num == -1)
                throw new NegativeNumberException();
            total = 0;
            for (int i = 0; i < num; i++)
                total += a[i];
            average = total / num;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No more than 5 please!");
        } catch (NumberFormatException e) {
            System.out.println("Integer please!");
        } catch (NullPointerException e) {
            System.out.println("Input length = 0; cancelled");
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Don't input zero!");
        } catch (RuntimeException e) {
            System.out.println("Run time error!");
            e.printStackTrace();
            return;
        } catch (Exception e) {
            System.out.println("Something wrong!");
            e.printStackTrace();
        } finally {
            System.out.println("Number = " + num);
        }
        System.out.println("Average over first " + num + " numbers = " + average);

    }
}