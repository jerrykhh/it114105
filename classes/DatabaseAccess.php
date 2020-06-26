<?php

class DatabaseAccess{

  private $conn;

  public function __construct(){
    $this->conn = new DatabaseConnection();
    $this->conn = $this->conn->getConnection();
  }

  public function query($query, $paramType = null, $params = array()){
    if (mysqli_connect_errno()) {
      die("Connection failed: " . $conn->connect_error);
    }

    $stmt = $this->conn->prepare($query);

    if($paramType != null){
      $stmt->bind_param($paramType, ...$params);
    }

    $runStmt = $stmt->execute();
    if (!$runStmt) {
      echo "Execute failed: (" . $stmt->errno . ") " . $stmt->error;
    }else{
      $method = explode(' ', $query)[0];
      $result;
      switch (strtoupper($method)) {
        case "SELECT":
          $result = $stmt->get_result();
          //var_dump($result);
          //$row = $result->fetch_array(MYSQLI_ASSOC);
          //var_dump($row);
          //echo "$row[lastName]";
          break;
        case 'UPDATE':
        case "INSERT":
        case 'DELETE':
          $result = $stmt->affected_rows;
          break;
      }
      $stmt->close();
      //$this->conn->close();
      return $result;

    }
  }

}



?>
