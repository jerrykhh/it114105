import java.util.Scanner;

public class OrderSystem {
  private static Scanner sc;
  private static LinkedList orders;
  private static int nextGuestID = 9000;
  private static MenuItem[] menus;
  private static FoodOrder currentFoodOrder;

  public static void main(String[] args) {
    sc = new Scanner(System.in);
    orders = new LinkedList();

    regFoodMenu();
    while (true) {
      start();
    }
  }

  private static void regFoodMenu() {
    menus = new MenuItem[4];
    menus[0] = new MenuItem("Chicken Salad");
    menus[1] = new MenuItem("Grilled Ribeye Steak");
    menus[2] = new MenuItem("Angel Hair Pasta with Shrimp");
    menus[3] = new MenuItem("Grilled Fish and Potatoes");
  }

  public static void start() {

    try {
      int memberId = inputMemberId(false);

      if (memberId <= -1) {
        System.err.println("Have a nice day!!!");
        System.exit(1);
      }

      if (memberId == 9999) {
        adminFunc();
      } else {

        currentFoodOrder = new FoodOrder(memberId);

        if (memberId == 0) {
          memberId = nextGuestID++;
          currentFoodOrder.setMemberID(memberId);
          currentFoodOrder.setPriority(3);
        } else if (memberId > 8000 && memberId < 8200)
          currentFoodOrder.setPriority(1);
        else if (memberId > 8199 && memberId < 9000)
          currentFoodOrder.setPriority(2);

        inputOrder();
      }

    } catch (InvalidInputException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  public static void printMenu() {
    System.out.println("----------------- Food Menu ----------------");

    for (int dec = 65, i = 0; i < menus.length; dec++)
      System.out.println("Set " + (char) dec + " : " + menus[i++]);

    System.out.println("--------------------------------------------");
  }

  public static void printAdminMenu() {
    System.out.println("----------------- Admin Function ----------------");
    System.out.println("1 : Print order list");
    System.out.println("2 : Remove order");
  }

  public static int inputMemberId(boolean isAdmin) throws InvalidInputException {

    try {
      if(isAdmin){
        System.out.print("Enter Member ID:");
        int memberId = Integer.parseInt(sc.nextLine());
        if ( memberId > 8000 && memberId < 9999) 
          return memberId;
        
      }else{
        System.out.print("Please input your member ID [input 0 for guest]:");
        int memberId = Integer.parseInt(sc.nextLine());
        if (memberId <= 0 || memberId == 9999 || memberId > 8000 && memberId < 8999)
        return memberId;
      }


    } catch (NumberFormatException e) {
      System.out.println("Input Error");
      System.exit(1);
    }
    throw new InvalidInputException();
  }

  public static void inputOrder() throws InvalidInputException {
    printMenu();
    System.out.print("Select food:");
    String foodOrder = sc.nextLine().toUpperCase();

    if (!isValidFoodOrderChar(foodOrder))
      throw new InvalidInputException();

    currentFoodOrder.setFoodOrder(foodOrder);
    orders.add(currentFoodOrder);
  }

  private static boolean isValidFoodOrderChar(String foodOrder) {

    if (foodOrder.length() > 2 || foodOrder.length() == 0)
      return false;

    char value = foodOrder.charAt(0);
    return (value >= 65 && value <= 65 + menus.length)? true: false;
  }

  public static void adminFunc() throws InvalidInputException {
    try {
      printAdminMenu();
      System.out.print(">");
      int adminFuncInput = Integer.parseInt(sc.nextLine());
      if (!isValidAdminFunction(adminFuncInput))
        throw new InvalidInputException();

      if (adminFuncInput == 1)
        new AdminPrintOrderList(orders).execute();
      else if (adminFuncInput == 2) {
        int memberId = inputMemberId(true);
        if (!orders.contain(memberId))
          throw new NoneOfOrderException();
        new AdminRemoveOrder(memberId, orders).execute();
        
      } else
        throw new InvalidInputException();

    } catch (NumberFormatException e) {
      throw new InvalidInputException();
    }

  }

  private static boolean isValidAdminFunction(int input) {
    return (input == 1 || input == 2)? true: false;
  }

}