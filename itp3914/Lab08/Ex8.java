import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;
import java.util.stream.DoubleStream;

class Ex8{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[] values = new double[10];
        int index = 0;
        while(index < 10){
            double value = input.nextDouble();
            if(value == -1)
                break;
            if(value > 0)
                values[index++] = value;
        }

        double sum, mean, max, min, mu, sd;
        sum = mean = mu = sd = 0.0;
        min = max = values[0];
        for(int i = 0; i < index; i++){
            sum += values[i];
            if(max < values[i])
                max = values[i];
            if(min > values[i])
                min = values[i];
        }
        mean = mu = sum/index;
        double tmpsum = 0.0;
        for(int i = 0; i < index; i++)
            tmpsum += Math.pow((values[i]-mu) , 2);
    
        sd = Math.sqrt(tmpsum/index);
        System.out.printf("sum=%.2f, mean=%.2f, maximum=%.2f, minimum=%.2f, and standard deviation=%.2f\n", sum, mean, max, min, sd);

    }
}