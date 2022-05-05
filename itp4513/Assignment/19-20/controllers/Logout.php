<?php
class Logout extends Controller{

 function __construct(){
   session_destroy();
   header('Location: ../loginUI.php');
 }
}

?>
