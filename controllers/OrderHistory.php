<?php

class OrderHistory extends Controller{

  function __construct(){
    if(isset($_GET['id'])){
      parent::__construct("OrderHistoryDetail");
    }else{
      parent::__construct("OrderHistory");
    }

  }

  function getOrderDetail($orderId){
    
  }

}

?>
