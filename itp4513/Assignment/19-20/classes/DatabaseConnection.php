<?php
class DatabaseConnection {

  private $hostname;
	private $database;
	private $username;
	private $password;

  public function __construct(){
    $this->hostname = "127.0.0.1";
    $this->database = "ProjectDB";
    $this->username = "root";
    $this->password = "";
  }

  public function getConnection(){
    $conn = mysqli_connect($this->hostname, $this->username, $this->password, $this->database);
    return $conn;
  }


}
?>
