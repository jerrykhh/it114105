public class TestFood {
    public static void main(String s[]) {
        Food f[] = new Food[4];
        f[0] = new Drink("Pepsi", 7, 250);
        f[1] = new Coffee("Cappuccino", 13, 200, true);
        f[2] = new Drink("Orange Juice", -10, 180);
        f[3] = new Coffee("Ireland", -11, 200, false);
        for (int i = 0; i < f.length; i++) {
            System.out.println("Food " + i + ": " + f[i]);
        }
    }
}