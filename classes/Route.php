<?php
class Route {

  public static $routes = array();

  public static function map($route, $function){
    self::$routes[] = $route;
    //print_r(self::$routes);

    if($_GET['url'] == $route){
      $function->__invoke();
    }
  }
}
?>
