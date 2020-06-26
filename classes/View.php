<?php

class View {

  public static function render($viewName, $data){
    require_once("./static/$viewName.php");
  }
  
}

?>
