import java.util.Scanner;

public class Ex9 {
    public static void main(String[] args) {
        
        double[] nums = new double[10];

        Scanner input = new Scanner(System.in);
        int i;
        for(i = 0; i < nums.length; i++){
            System.out.printf("Enter number %d: ", i+1);
            double num = input.nextDouble();
            if(num == -1){
                break;
            }
            nums[i] = num;
        }
        
        double min = nums[0];
        double max = nums[0];

        for(int index = 1; index < i; index++){
            if(nums[index] > max)
                max = nums[index];
            
            if(nums[index] < min)
                min = nums[index];
        }

        System.out.println("Maximum = " + max);
        System.out.println("Minimum = " + min);


        // Non-array version (Quesiton is required Array)
        // double max = -1, min = -1;
        // for (int i = 0; i < 10; ) {
        //     System.out.printf("Enter number %d: ", ++i);
        //     double num = input.nextDouble();
        //     if(num == -1)
        //         break;
        //     if(max == -1 && min == -1)
        //         max = min = num;
        //     if(num > max)
        //         max = num;
        //     if (num < min)
        //         min = num;   
        // }
        // System.out.println("Maximum = " + max);
        // System.out.println("Minimum = " + min);
    
    }
}
