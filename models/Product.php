<?php
class Product{
  private $id;
  private $name;
  private $status;
  private $price;
  private $stock;
  private $qty;

  function __construct($id, $name, $status, $price, $stock, $qty){
    $this->id = $id;
    $this->name = $name;
    $this->status = $status;
    $this->price = $price;
    $this->stock = $stock;
    $this->qty = $qty;
  }

  function getId(){
    return $this->id;
  }

  function getName(){
    return $this->name;
  }

  function getStatus(){
    return $this->status;
  }

  function getPrice(){
    return $this->price;
  }

  function getStock(){
    return $this->stock;
  }

  function getQty(){
    return $this->qty;
  }

}
?>
