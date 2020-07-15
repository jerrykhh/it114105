<?php
class Checkout extends Controller{


  function __construct(){
    $this->database = new DatabaseAccess();
    $this->checkout();
  }

  public function checkout(){
  //  $date = date('Y-m-d H:i:s');
    $storeId = $_POST["store"];
    $address = $_POST["address"];
    $totalPrice = 0.0;
    $itemList = array();

    foreach ($_POST as $key => $value) {
      if($key != "address" && $key != "store" && $key > 0){
        $result = $this->database->query("SELECT stockPrice FROM goods WHERE consignmentStoreID = ? AND goodsNumber = ?", "ss", array($storeId, $key));
        $row = $result->fetch_array(MYSQLI_ASSOC);
        $item = new OrderItem($key, $value, $row["stockPrice"]);
        $totalPrice += ($row["stockPrice"] * $value);
        array_push($itemList, $item);
      }
    }
    $result = $this->database->query("INSERT INTO orders(customerEmail, consignmentStoreID, shopID, orderDateTime, status, totalPrice) VALUES(?,?,?, NOW(),?,?)", "sssid", array($_SESSION['customer']['customerEmail'], $storeId, $address, 2, $totalPrice ));
    if($result > 0){
      print_r($itemList);
      $insert_id = $this->database->getInsertId();
      foreach ($itemList as $row) {
          echo "lopo";
          $insertItemResult = $this->database->query("INSERT INTO orderitem VALUES(?,?,?,?)", "iiid",array($insert_id, $row->getId(), $row->getQty(), $row->getPrice()));
          $updateItemStock = $this->database->query("UPDATE goods SET remainingStock = remainingStock - ? WHERE goodsNumber = ?", "ii", array($row->getQty(), $row->getId()));
      }
      unset($_SESSION["products"]);
      unset($_SESSION["storeId"]);
      unset($_SESSION["store"]);
      header("location: orderhistory?checkout=". $insert_id);
    }


  }



}

 ?>
