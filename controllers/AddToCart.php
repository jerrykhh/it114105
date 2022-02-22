<?php
class AddToCart extends Controller{
  public function __construct(){
    $this->processJSON();
  }

  private function processJSON(){
    $json = file_get_contents('php://input');
    $obj = json_decode($json, true);
    if(isset($_SESSION['store']) && $_SESSION['store'] != $obj['storeId']){
      $returnJSON = new stdClass();
      $returnJSON->statue = false;
      $returnJSON->message = "Store Changed";
      echo json_encode($returnJSON);
    }else{
      $checkDup = false;
      if(isset($_SESSION['store']) && isset($_SESSION['products'])){
        foreach ($_SESSION['products'] as $key => $value) {
          if($key == $obj["productId"]){
            $_SESSION['products'][$key] += $obj["qty"];
            $checkDup = true;
          }
        }
      }
      if(!$checkDup){
        $_SESSION['store'] = $obj['storeId'];
        if(isset($_SESSION['products'])){
          $_SESSION['products'] += array($obj['productId']=> $obj['qty']);
        }else{
          $_SESSION['products'] = array($obj['productId']=> $obj['qty']);
        }
      }
      $returnJSON = new stdClass();
      $returnJSON ->statue = true;
      $returnJSON->itemCount = count($_SESSION['products']);
      $returnJSON->messsage = "Item saved";
      echo json_encode($returnJSON);
    }

  }
}

 ?>
