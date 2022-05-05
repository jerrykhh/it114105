<?php

class ConsignmentStore{

  private $storeId;
  private $storeName;
  private $storeItemList;

  function __construct($storeId, $storeName, $storeItemList){
    $this->storeId = $storeId;
    $this->storeName = $storeName;
    $this->storeItemList = $storeItemList;
  }

  public function getStoreName(){
    return $this->storeName;
  }

  public function getStoreItemList(){
    return $this->storeItemList;
  }

  public function getStoreID(){
    return $this->storeId;
  }


}


?>
