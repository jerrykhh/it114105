public class Ex4 {
    public static void main(String[] args) {
        CurrencyConverter yenConverter = new CurrencyConverter(115.7, 0.0005);
        CurrencyConverter euroConverter = new CurrencyConverter(0.9881, 0.0003);
        
        yenConverter.setLargeAmount(50000);
        euroConverter.setLargeAmount(50000);
        int yens = 1500000;
        System.out.println(yens + " yens = US$ " + yenConverter.toUSDollar(yens));
        int usd = 20000;
        System.out.println("US$ " + usd + " = " + yenConverter.fromUSDollar(usd) + " yens");

        int euros = 170000;
        System.out.println(euros + " euros = US$ " + euroConverter.toUSDollar(euros));
        usd = 20000;
        System.out.println("US$ " + usd + " = " + euroConverter.fromUSDollar(usd) + " euros");

    }
}
