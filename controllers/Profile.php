<?php
class Profile extends Controller{

  private $database;

  public function __construct(){
    $this->database = new DatabaseAccess();
    if(isset($_POST["currentPassword"]) && isset($_POST["fname"])){
      $this->updateUserProfile($_SESSION['customer']['customerEmail']);
    }
    $userProfile = $this->getUserInformation($_SESSION['customer']['customerEmail']);
    parent::__construct("Profile", $userProfile);
  }

  public function getUserInformation($userEmail){
    $result = $this->database->query("SELECT * FROM customer WHERE customerEmail = ?", "s", array($userEmail));
    $row = $result->fetch_array(MYSQLI_ASSOC);
    return new UserProfile($row["customerEmail"], $row["firstName"], $row["lastName"], $row["phoneNumber"]);
  }

  public function updateUserProfile($userEmail){
    $fname = $_POST["fname"];
    $lname = $_POST["lname"];
    $currentPwd = $_POST["currentPassword"];
    $newPwd = $_POST["password"];
    $tel = $_POST["tel"];

    if($currentPwd == ""){
      $result = $this->database->query("UPDATE customer SET firstName = ?, lastName = ?, phoneNumber = ? WHERE customerEmail = ?", "ssss", array($fname, $lname, $tel, $userEmail));
      if($result > 0){
        header("location: profile?update=success");
      }else{
        header("location: profile?inf=invalid");
      }
    }else{
      $result = $this->database->query("SELECT COUNT(*) AS count FROM customer WHERE customerEmail = ? AND password = ?", "ss", array($userEmail, $currentPwd));
      $row = $result->fetch_array(MYSQLI_ASSOC);
      if($row["count"] > 0){
        $result = $this->database->query("UPDATE customer SET firstName = ?, lastName = ?, phoneNumber = ?, password = ? WHERE customerEmail = ?", "sssss", array($fname, $lname, $tel, $newPwd, $userEmail));
        echo "test1";
        if($result > 0){
          header("location: profile?update=success");
          echo "test3";
        }else{
          header("location: profile?pwd=invalid");
        }
      }else{
        header("location: profile?pwd=invalid");
      }
    }
  }
}

?>
