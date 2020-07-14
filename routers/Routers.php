<?php
  session_start();
  if(isset($_SESSION['customer'])){
  Route::map('index', function(){
    $controller = new Index();
    $controller->render();
  });

  Route::map('signup', function(){
    $controller = new SignUp();
  });

  Route::map('login', function(){
    $controller = new Login();

  });

  Route::map('home', function(){
    $controller = new Home();
    $controller->render();
  });

  Route::map('profile', function(){
    $controller = new Profile();
    $controller->render();
  });

  Route::map('orderhistory', function(){
    $controller = new OrderHistory();
    $controller->render();
  });

  Route::map('logout', function(){
    $controller = new Logout();
  });

  Route::map('store', function(){
    $controller = new Store();
    $controller->render();
  });

  Route::map('cart', function(){
    $controller = new Cart();
    $controller->render();
  });

  Route::map('addToCart', function(){
    $controller = new AddToCart();
  });
}else{
  header("location: ../loginUI.php");
}



?>
