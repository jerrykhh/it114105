<?php
class Home extends Controller{

  private $database;
  private $shopList;

  function __construct(){
    $this->database = new DatabaseAccess();
    $this->shopList = $this->getShopList();
    parent::__construct("Home", $this->shopList);
  }

  public function getShopList(){
    $result = $this->database->query("SELECT * FROM ConsignmentStore");
    //while($row = $result->fetch_array(MYSQLI_ASSOC)){
    //  echo "$row[tenantID]";
    //}
    return $result;
  }


}
?>
