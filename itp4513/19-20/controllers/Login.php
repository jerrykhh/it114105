<?php
class Login extends Controller {

  private $database;

  public function __construct(){
    $this->login();
  }

  public function login(){
    $database = new DatabaseAccess();
    $username = $_POST['username'];
    $password = $_POST['password'];
    $role;

    if (filter_var($username, FILTER_VALIDATE_EMAIL)) {
      // Login Customer
      $result = $database->query("SELECT * FROM Customer WHERE customerEmail = ? AND password = ?", "ss" ,array($username, $password));
      $role = 1;
    } else {
      // Login Tenant
      $result = $database->query("SELECT * FROM Tenant WHERE tenantID = ? AND password = ?", "ss" ,array($username, $password));
      $role = 0;
    }

    if($result->num_rows == 1){
      session_start();
      $_SESSION['username'] = $username;
      $_SESSION['role'] = $role;
      header('Location: home');
    }else{
      header("Location: index?username=$username");
    }
  }
}
?>
