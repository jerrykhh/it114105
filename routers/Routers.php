<?php
  session_start();
  function sessionChecking(){
    if(!isset($_SESSION['username']) || !isset($_SESSION['role']))
      header("location: index");

  }

  Route::map('login', function(){
    $controller = new Login();
  });

  Route::map('index', function(){
    $controller = new Index();
    $controller->render();
  });

  Route::map('home', function(){
    sessionChecking();
    $controller = new Home();
    $controller->render();
  });

  Route::map('profile', function(){
    sessionChecking();
    $controller = new Profile();
    $controller->render();
  });

  Route::map('orderhistory', function(){
    sessionChecking();
    $controller = new OrderHistory();
    $controller->render();
  });

  Route::map('logout', function(){
    $controller = new Logout();
  });

  Route::map('store', function(){
    sessionChecking();
    $controller = new Store();
    $controller->render();
  });

  Route::map('cart', function(){
    sessionChecking();
    if(isset($_SESSION["store"]) && isset($_SESSION["products"])){
      $controller = new Cart();
      $controller->render();
    }else{
      header("location: home");
    }
  });

  Route::map('addToCart', function(){
    $controller = new AddToCart();
  });

  Route::map('removeInCart', function(){
    sessionChecking();
    $controller = new RemoveInCart();
  });

  Route::map('checkout', function(){
    sessionChecking();
    $controller = new CheckOut();
  });

  Route::map('viewCart', function(){
    sessionChecking();
    $controller = new ViewCart();
  });

  Route::map('checking', function(){
    sessionChecking();
    $controller = new Checking();
  });


?>
