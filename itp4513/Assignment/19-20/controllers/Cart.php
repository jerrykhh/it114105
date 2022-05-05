<?php
class Cart extends Controller{

  public function __construct(){
    $this->database = new DatabaseAccess();
    $storeCart = $this->getStoreInf($_SESSION["store"]);
    $cartItem = $this->getSessionItem($_SESSION["store"]);
    $storeInf = $this->getStoreName($_SESSION["store"]);
    $storeCart = new StoreCart($storeInf[0], $storeInf[1], $storeCart, $cartItem);
    parent::__construct("Cart", $storeCart);
  }

  public function getStoreInf($storeId){
    $result = $this->database->query("SELECT consignmentstore.consignmentStoreID, shop.shopID, shop.address FROM consignmentstore_shop, consignmentstore, shop WHERE consignmentstore_shop.shopID = shop.shopID AND consignmentstore_shop.consignmentStoreID = consignmentstore.consignmentStoreID AND consignmentstore.consignmentStoreID = ?", "s" ,array($storeId));

    return $result;
  }

  public function getSessionItem($storeId){
    $itemList = array();
    foreach ($_SESSION["products"] as $key => $value) {
      $result = $this->database->query("SELECT * FROM Goods WHERE consignmentStoreID = ? AND goodsNumber = ?", "ss", array($storeId, $key));
      $row = $result->fetch_array(MYSQLI_ASSOC);
      $item = new Product($row["goodsNumber"], $row["goodsName"], $row["status"], $row["stockPrice"], $row["remainingStock"], $value);
      array_push($itemList, $item);
    }

    return $itemList;
  }

  public function getStoreName($storeId){
    $result = $this->database->query("SELECT consignmentStoreID, ConsignmentStoreName FROM consignmentstore WHERE consignmentStoreID = ?", "i", array($storeId));
    $row = $result->fetch_array(MYSQLI_ASSOC);

    return array($row["consignmentStoreID"], $row["ConsignmentStoreName"]);
  }



}
?>
