<?php
class SignUp extends Controller {

  private $database;

  function __construct(){
    $this->signup();
  }

  public function signup(){
    $database = new DatabaseAccess();
    $email = $_POST["email"];
    $fname = $_POST["fname"];
    $lname = $_POST["lname"];
    $tel = $_POST["tel"];
    $password = $_POST["password"];
    $result = $database->query("SELECT * FROM Customer WHERE customerEmail = ?", "s", array($email));

    if($result->num_rows > 0){
      header("Location: index?fname=$fname&lname=$lname&tel=$tel");

    }else{
      $result = $database->query("INSERT INTO Customer(customerEmail, firstName, lastName, password, phoneNumber) VALUES(?, ?, ?, ?, ?)", "sssss", array($email, $fname, $lname, $password, $tel));
      if($result == 1){
        header("Location: index?signup=true");
      }
    //  echo $result + "<br>";
    //  var_dump($result);
    }

  }

}

?>
