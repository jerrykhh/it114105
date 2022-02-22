<?php
class Checking extends Controller{

  public function __construct(){
    $this->database = new DatabaseAccess();
    $this->processJSON();
  }

  private function processJSON(){
    $json = file_get_contents('php://input');
    $obj = json_decode($json, true);
    $returnJSON = new stdClass();
    if(isset($obj["password"])){
      $result = $this->database->query("SELECT COUNT(*) AS count FROM customer WHERE customerEmail = ? AND password = ?", "ss", array($_SESSION['customer']['customerEmail'], $obj["password"]));
      $row = $result->fetch_array(MYSQLI_ASSOC);
      if($row["count"] > 0){
        $selectOrderResult = $this->database->query("SELECT orderID FROM orders WHERE customerEmail = ?", "s", array($_SESSION['customer']['customerEmail']));
        while($row = $selectOrderResult->fetch_array(MYSQLI_ASSOC)){
          $deleteOrderItem = $this->database->query("DELETE FROM orderitem WHERE orderID = ?", "i", array($row["orderID"]));
        }
        $deleteOrders = $this->database->query("DELETE FROM orders WHERE customerEmail = ? ", "s", array($_SESSION['customer']['customerEmail']));
        $deleteUser =  $this->database->query("DELETE FROM customer WHERE customerEmail = ? ", "s", array($_SESSION['customer']['customerEmail']));

        $returnJSON->statue = true;
        $returnJSON->message = "confirm";
      }else{
        $returnJSON->statue = false;
        $returnJSON->message = "password incorrect";
      }
    }else{
        $returnJSON->statue = false;
        $returnJSON->message = "Missing arguments";
    }
    echo json_encode($returnJSON);
  }

}
 ?>
