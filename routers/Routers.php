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
    $controller = new RemoveInCart();
  });

  Route::map('checkout', function(){
    $controller = new Checkout();
  });

  Route::map('viewCart', function(){
    $controller = new ViewCart();
  });

  Route::map('checking', function(){
    $controller = new Checking();
  });

}else{
  header("location: ../loginUI.php");
}



?>
