<?php

class OrderHistory extends Controller{

  private $database;
  private $orderHistory;

  function __construct(){
    $this->database = new DatabaseAccess();
    if(isset($_GET['id'])){
      parent::__construct("OrderHistoryDetail");
    }else{
      $this->orderHistory = $this->getOrderHistory();
      parent::__construct("OrderHistory", $this->orderHistory);
    }

  }

  function getOrderDetail($orderId){

  }

  public function getOrderHistory(){
      $result = $this->database->query("SELECT Orders.orderID, ConsignmentStore.ConsignmentStoreName, Orders.orderDateTime, Orders.status, Orders.totalPrice FROM Orders, ConsignmentStore WHERE Orders.consignmentStoreID =ConsignmentStore.consignmentStoreID AND Orders.customerEmail = ?", "s" ,array($_SESSION['customer']['customerEmail']));

      return $result;
  }

}

?>
