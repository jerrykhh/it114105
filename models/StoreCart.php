<?php
class StoreCart {
  private $storeId;
  private $storeName;
  private $storeInf;
  private $cartList;

  function __construct($storeId, $storeName, $storeInf, $cartList){
    $this->storeId = $storeId;
    $this->storeName = $storeName;
    $this->storeInf = $storeInf;
    $this->cartList = $cartList;
  }

  public function getStoreInf(){
    return $this->storeInf;
  }

  public function getCartList(){
    return $this->cartList;
  }

  public function getStoreName(){
    return $this->storeName;
  }

  public function getStoreId(){
    return $this->storeId;
  }


}

 ?>
