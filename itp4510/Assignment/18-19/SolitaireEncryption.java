// Name: Kwok Ho Hin
// Student ID: 180160427
// Program code-Class: IT114105-1B
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SolitaireEncryption {

    public static void main(String[] args) {
        try {
            LinkedList qll = new LinkedList();                                                //Object of LinkedList
            Scanner input = new Scanner(new File(args[1]));
            while (input.hasNextInt()) {                                                      //if the text file has Integer will put to qll LinkedList, when the text file contain other data type will stop input
                qll.addToTail(input.nextInt());
            }
            input.close();
            if (qll.getCount() != 28) {                                                       //check the qll whether 28 card, if not 28 card will Stop the program 
                throw new NotMatchTypeException();
            }
            if (qll.searchItemAt(27) == 28 || qll.searchItemAt(28) == 28) {
                throw new NotJokerException();                                                 // check the qll whether contain Joker
            }
            if (qll.checkDup()) {
                throw new DuplicateValException();                                             // check the qll whether contain Duplicate Value
            }

            if (args[0].equals("keygen") || args[0].equals("en") || args[0].equals("de")) {   //args[0] should be 3 type: keygen, en or de
                keygen(args[2], qll, args[0]);
            } else {
                System.out.println("Sorry: keygen||en||de  FileLocation  Word");              //not keygen, en or de 
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found in " + args[1]);                               //Can not found the File so throw FileNotFoundException
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can not Encryption or Decryption the message");               //Encryption and Decryption use the array to store the message so it may throw IndexOutOfBoundsException
        } catch (DuplicateValException e) {
            System.out.println("Text file contain Duplicate value");                          //Duplicate value
        } catch (NotMatchTypeException e) {
            System.out.println("Total card should be 28 and Integer number");                 //Number of card is not 28 so throw NotMatchTypeException 
        } catch (NotJokerException e) {
            System.out.println("Not contain all Joker (27, 28)");
        } catch (EmptyListException e) {
            System.out.println("Empty List");                                                 //when the LinkedList remove the node so it may EmptyListException occure
        } catch (Exception e) {
            System.out.println("Unknow Error");
        }
    }

    public static void keygen(String args, LinkedList qll, String type) throws EmptyListException {
        LinkedList keystreamVal = new LinkedList();                                           //Create the LinkedList to store the keystream Value
        args = args.replaceAll("\\s", "").toUpperCase();                                      //Trim the String of the args[2] and change to Upper Case.
        for (int i = 0; i < args.length(); i++) {                                            //each args[2] Sting will generate the keystream Value  
            qll.findJokerA();                                                                //call the LinkedList method to findJokerA
            keygenPrint("S1: ", type, qll);                                                             //check the args[0] wether "keygen"
            qll.findJokerB();                                                                //call the LinkedList method to findJokerB
            keygenPrint("S2: ", type, qll);                                                             //check the args[0] wether "keygen"
            qll.tripleCut();                                                                 //call the LinkedList method to cut the card
            keygenPrint("S3: ", type, qll);                                                             //check the args[0] wether "keygen"
            qll.countCut();                                                                  //call the LinkedList method to count cut
            keygenPrint("S4: ", type, qll);                                                             //check the args[0] wether "keygen"
            if (!qll.topCard().equals(28) && !qll.topCard().equals(27)) {                    //call the LinkedList method and return the keystream value, if keystream value not 27 or 28
                keygenPrint("Key " + (i + 1) + ": ", type, qll.topCard());                                               //check the args[0] wether "keygen"              
                keystreamVal.addToTail(qll.topCard());                                       //add the keystreamVal to keystreamVal LinkedList
            } else {
                keygenPrint("Joker", type, qll);                                                 //check the args[0] wether "keygen"  
                i--;                                                                         //if Is Joker will skip this position so i--
            }
        }
        if (type.equals("en") || type.equals("de")) {                                        //check wether "en" or "de" and wether keep running
            message(args, keystreamVal, type);
        } else {
            System.out.println("Keystream values:" + keystreamVal);                           //Keystream values
        }

    }

    public static void message(String args, LinkedList keystreamVal, String type) throws EmptyListException {
        Object[] message = new Object[args.length()];                                                                               // set the message array to store encryption or decryption message 
        int sum = 0;                                                                                                                // set each encryption or decrpytion message vale of 0
        for (int i = 0; i < args.length(); i++) {                                                                                   // each char will encryption or decrpytion
            System.out.print(args.charAt(i) + "\t" + (letter().searchItemAt(args.charAt(i)) + 1) + "\t" + keystreamVal.getItemAt(i));    // print the current char, and char number and keystream Value
            if (type.equals("en")) {                                                                                                // Encrpytion Procedure
                sum = (int) keystreamVal.getItemAt(i) + (int) (letter().searchItemAt(args.charAt(i)) + 1);                             // keystream + char number and store in sum Varible
                if (sum > 26) {                                                                                                     // if the sum is > 26 should be -26
                    sum -= 26;
                }
            } else if (type.equals("de")) {                                                                                         // Decrpytion Procedure
                sum = (int) (letter().searchItemAt(args.charAt(i)) + 1) - (int) keystreamVal.getItemAt(i);                             // Char number - keystream Value and store in sum Varible
                if (sum < 1) {                                                                                                      // if the sum is < 0 should be +26
                    sum += 26;
                }
            }
            message[i] = (letter().getItemAt((sum - 1)));                                                                                // store the all encrypted or decrypted message in message array 
            System.out.print("\t" + sum + "\t" + (letter().getItemAt(sum - 1)) + "\n");                                                  // print the encrypted or decrypted message and char
            if (i == args.length() - 1 && type.equals("en")) {                                                                     // print the encrypted or decrypted message in message array
                System.out.print("Encrypted message: ");
            } else if (i == args.length() - 1 && type.equals("de")) {
                System.out.print("Decrypted message: ");
            }
        }
        for (int i = 0; i < message.length; i++) {
            System.out.print(message[i]);
        }
    }

    public static void keygenPrint(String number, String type, Object qll) {
        if (type.equals("keygen") && !number.equals("Joker")) {                                                                            //check wether keygen and print the linkedList                                                       
            System.out.println(number + ":" + qll);
        } else if (type.equals("keygen") &&number.equals("Joker")) {
            System.out.println(number + ": Key skipped");
        }
    }

    public static LinkedList letter() {
        LinkedList letter = new LinkedList();                                                                                     //set the LinkedList to store the A-Z char
        for (char alph = 'A'; alph <= 'Z'; alph++) {
            letter.addToTail(alph);
        }
        return letter;
    }
}
