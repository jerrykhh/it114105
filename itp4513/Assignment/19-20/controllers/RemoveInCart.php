<?php
class RemoveInCart extends Controller{

  public function __construct(){
    $this->processJSON();
  }

  private function processJSON(){
    $json = file_get_contents('php://input');
    $obj = json_decode($json, true);
    $returnJSON = new stdClass();
    if(isset($obj["action"]) && $obj["action"] == "remove"){
      unset($_SESSION["products"]);
      unset($_SESSION["store"]);
      $returnJSON->statue = true;
      $returnJSON->messsage = "cart cleard";
    }elseif(isset($_SESSION["products"][$obj["productId"]])){
      unset($_SESSION["products"][$obj["productId"]]);
      if($_SESSION["products"] == null){
        unset($_SESSION["store"]);
      }
      $returnJSON->statue = true;
      $returnJSON->itemCount = count($_SESSION['products']);
      $returnJSON->messsage = "Item removed";
    }else{
      $returnJSON->statue = false;
      $returnJSON->messsage = "Error";
    }
    echo json_encode($returnJSON);
  }
}

 ?>
