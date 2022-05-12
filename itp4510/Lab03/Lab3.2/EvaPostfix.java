import java.util.Scanner;

public class EvaPostfix {
    public static void main(String[] args) {
        Stack s = new LinkedStack();
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Input the infix expression? ");
        String exp = keyboard.nextLine();
        try {
            if (parenthesisMatching(exp)) {
                for (int i = 0; i < exp.length(); i++) {
                    char item = exp.charAt(i);
                    if (item == '(' || item == '{' || item == '[') {
                        s.push(item);
                    } else if (isOperater(item)) {
                        if (s.isEmpty()) {
                            s.push(item);
                        } else if ((checkVal(item) == checkVal((char) s.top())) || (checkVal(item) < checkVal((char) s.top()))) {
                            System.out.print(s.pop());
                            if(s.isEmpty()){
                                s.push(item);
                                }else if(checkVal((char) s.top()) == checkVal(item)) {
                                while (checkVal(item) == checkVal((char) s.top())) {
                                    System.out.print(s.pop());
                                    if (s.isEmpty()) {
                                        s.push(item);
                                        break;
                                    }
                                }
                            } else {
                                s.push(item);
                            }
                        } else if (checkVal(item) > checkVal((char) s.top())) {
                            s.push(item);
                        }
                    } else if (item == ')' || item == ']' || item == '}') {
                        while (((char) s.top() != '(')&&((char) s.top() != '{') && ((char) s.top() != '[')) {
                            System.out.print(s.pop());
                        }
                        s.pop();
                    } else {
                        System.out.print(item);
                    }
                }
                while (!s.isEmpty()) {
                    System.out.print(s.pop());
                }
            } else {
                System.out.println("Error");
            }
        } catch (StackEmptyException e) {
            System.out.println("Parenthesis not matched");
        } catch (StackFullException e) {
            System.out.println("Parenthesis not matched");
        }
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

    public static boolean isOperater(char exp) {
        switch (exp) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }

    public static int checkVal(char exp) {
        if (exp == '*' || exp == '/') {
            return 5;
        } else if (exp == '+' || exp == '-') {
            return 3;
        } else {//'('
            return -1;
        }

    }
}
