import java.util.Scanner;

public class Ex8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Exam mark? ");
        double mark = input.nextDouble();
        char grade = 'A';
        if (mark < 39)
            grade = 'F';
        else if(mark <= 49)
            grade = 'D';
        else if(mark <= 59)
            grade = 'C';
        else if (mark <= 69)
            grade = 'B';

        System.out.printf("Grade = %c\n", grade);
    }
}
