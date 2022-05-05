<?php
class ViewCart extends Controller{
  public function __construct(){
    $this->processJSON();
  }

  private function processJSON(){
    $returnJSON = new stdClass();
    if(isset($_SESSION["products"]) && isset($_SESSION["store"])){
      $returnJSON->statue = true;
      $returnJSON->itemCount = count($_SESSION["products"]);
      $returnJSON->storeId = $_SESSION["store"];
    }else{
        $returnJSON->statue = true;
        $returnJSON->itemCount = 0;
    }
    echo json_encode($returnJSON);
  }
}

 ?>
