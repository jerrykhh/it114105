<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" href="./static/css/style.css">
  </head>
  <body>
    <div class="container">
      <div class="message">
        <?php
        if(isset($_GET['username'])){
          echo '<div class="bs-callout bs-callout-danger"><h4>login Failed</h4>Username or password incorrect</div>';
        }else if (isset($_GET['signup'])){
          echo '<div class="bs-callout bs-callout-success"><h4>Sign up succesful</h4>You can login the system</div>';
        }
        ?>
      </div>
      <div class="wrapper">
        <div class="login">
          <img title="logo" src="./static/image/logo.png"/>
          <form method="POST" action="login" id="loginform"/>
            <input type="text" name="username" id="username" value="<?php echo isset($_GET['username'])? $_GET['username'] : "" ?>" placeholder="Username/Email"/>
            <input type="password" name="password" id="password" value="" placeholder="Password"/>
            <input type="button" onClick="login()" value="Login"/>
          </form>
        </div>
      </div>
      <div class="wrapper">
        <div class="signup">
          <p>Don't have an account? <a id="btnSignUp" onclick="signupForm()">Sign up</a></p>
        </div>
      </div>
    </div>
    <script src="./static/js/login.js"></script>
  </body>
</html>
