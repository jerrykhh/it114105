public class InvalidInputException extends Exception{
    
    public InvalidInputException(String mes){
        super(mes);
    }

    public InvalidInputException(){
        super("Invalid input! Please input again.");
    }
}
