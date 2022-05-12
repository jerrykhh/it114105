import java.util.Scanner;

public class ParenthesisMatching {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Expression? ");
        String exp = keyboard.nextLine();
        try {
            if (parenthesisMatching(exp)) {
                System.out.println("Parenthes is matched");
            } else {
                System.out.println("Parenthesis not matched");
            }
        } catch (StackEmptyException e) {
            System.out.println("Parenthesis not matched");
        } catch (StackFullException e) {
            System.out.println("Parenthesis not matched");
        }
        keyboard.close();

    }

    public static boolean parenthesisMatching(String exp) throws StackFullException, StackEmptyException {
        Stack s = new LinkedStack();
        char item;
        for (int i = 0; i < exp.length(); i++) {
            item = exp.charAt(i);
            if (item == '{' || item == '(' || item == '[') {
                s.push(item);
            } else if (item == '}' || item == ')' || item == ']') {
                char x = (char) s.pop();
                if ((x != '(') && (x != '[') && (x != '{')) {
                    return false;
                }
            }
        }
        if (s.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
}
