<?php
class Controller {

  private $viewName;
  private $data;

  public function __construct($viewName, $data = null){
    $this->viewName = $viewName;
    $this->data = $data;
  }

  public function setData($data){
    $this->data = $data;
    var_dump($this->data);
  }

  public function render(){
    session_start();
    View::render($this->viewName, $this->data);
  }



}
?>
