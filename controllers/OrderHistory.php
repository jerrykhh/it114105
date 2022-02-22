<?php

class OrderHistory extends Controller{

  private $database;
  private $orderHistory;

  function __construct(){
    $this->database = new DatabaseAccess();
    if(isset($_GET['id'])){
      $orderDetials = $this->getOrderDetials($_GET['id']);
      parent::__construct("OrderHistoryDetail", $orderDetials);
    }else{
      $this->orderHistory = $this->getOrderHistory();
      parent::__construct("OrderHistory", $this->orderHistory);
    }

  }

  public function getOrderDetials($orderId){

    $orderItemResult = $this->database->query("SELECT Goods.goodsNumber, Goods.goodsName, OrderItem.quantity, OrderItem.sellingPrice FROM Goods, OrderItem WHERE OrderItem.goodsNumber = Goods.goodsNumber AND OrderItem.orderID = ?", "s", array($orderId));
    $orderInfResult =  $this->database->query("SELECT Orders.orderID, ConsignmentStore.consignmentStoreName, Shop.address, Orders.orderDateTime, Orders.status, Orders.totalPrice FROM Orders, ConsignmentStore, Shop WHERE Orders.consignmentStoreID =ConsignmentStore.consignmentStoreID
AND Orders.shopID = Shop.ShopID AND Orders.orderID = ?", "s", array($orderId));

    return new OrderHistoryModel($orderInfResult, $orderItemResult);
  }



  public function getOrderHistory(){
      $result = $this->database->query("SELECT Orders.orderID, ConsignmentStore.ConsignmentStoreName, Orders.orderDateTime, Orders.status, Orders.totalPrice FROM Orders, ConsignmentStore WHERE Orders.consignmentStoreID =ConsignmentStore.consignmentStoreID AND Orders.customerEmail = ? ORDER BY Orders.orderID DESC", "s" ,array($_SESSION['customer']['customerEmail']));

      return $result;
  }

}

?>
