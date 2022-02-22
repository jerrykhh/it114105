<?php
class Store extends Controller{

  private $database;

  function __construct(){
    if(isset($_GET['id'])){
      $this->database = new DatabaseAccess();
      $consignmentStoreID = $_GET['id'];
      $storeItemList = $this->getStoreItem($consignmentStoreID);
      $storeInf = $this->getStoreName($consignmentStoreID);
      $consignmentStore = new ConsignmentStore($storeInf[0], $storeInf[1], $storeItemList);
      parent::__construct("Store", $consignmentStore);
    }else{
      header('Location: home');
    }

  }

  public function getStoreItem($consignmentStoreID){
    $result = $this->database->query("SELECT * FROM Goods WHERE consignmentStoreID = ?", "s", array($consignmentStoreID));
    return $result;
  }

  public function getStoreName($consignmentStoreID){
    $result = $this->database->query("SELECT consignmentStoreID, ConsignmentStoreName FROM ConsignmentStore WHERE consignmentStoreID = ?", "s", array($consignmentStoreID));
    $row = $result->fetch_array(MYSQLI_ASSOC);
    return array($row["consignmentStoreID"], $row["ConsignmentStoreName"]);
  }

}
?>
