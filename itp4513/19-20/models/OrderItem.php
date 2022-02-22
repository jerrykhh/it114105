<?php
class OrderItem{

  private $id;
  private $qty;
  private $price;

  function __construct($id, $qty, $price){
    $this->id = $id;
    $this->qty = $qty;
    $this->price = $price;
  }

  public function getId(){
    return $this->id;
  }

  public function getQty(){
    return $this->qty;
  }

  public function getPrice(){
    return $this->price;
  }

}

 ?>
