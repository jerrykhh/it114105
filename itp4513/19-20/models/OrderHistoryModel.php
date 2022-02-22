<?php
class OrderHistoryModel{

  private $orderInf;
  private $orderItem;

  function __construct($orderInf, $orderItem){
    $this->orderInf = $orderInf;
    $this->orderItem = $orderItem;
  }

  public function getOrderInf(){
    return $this->orderInf;
  }

  public function getOrderItem(){
    return $this->orderItem;
  }
}

 ?>
