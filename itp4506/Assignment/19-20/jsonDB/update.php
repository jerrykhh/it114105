<?php
if(isset($_POST["fav"])){
    $jsonCustomer = $_POST["dataCus"];
    print($jsonCustomer);
    $jsonCustomerPath = $_POST["dataEmail"].".json";
    print($jsonCustomerPath);
    file_put_contents($jsonCustomerPath, $jsonCustomer);
}else{
    $jsonAdmin = $_POST["dataAdmin"];
    $jsonCustomer = $_POST["dataCus"];
    $jsonAdminPath = "adminCustomer.json";
    file_put_contents($jsonAdminPath, $jsonAdmin);
    $jsonCustomerPath = $_POST["dataEmail"].".json";
    fopen($jsonCustomerPath, "w");
    file_put_contents($jsonCustomerPath, $jsonCustomer);
}
?>
