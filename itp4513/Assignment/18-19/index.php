<?php
if(isset($_GET["Logout"])){
	setcookie("DealerName","",time()-3600);
	setcookie("AdminName","",time()-3600);
}
if(isset($_COOKIE["DealerName"])){
	header("location: viewOrder.php");
}
if(isset($_COOKIE["AdminName"])){
	header("location: adminOrder.php");
}
?>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login System</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container" id="Login">
		<?php if(isset($_GET["Login"])){
						echo '<div class="alert alert-danger" role="alert" style="margin-top: 20px;">Username or Password Wrong!</div>';
					}
			  if(isset($_GET["pwd"]))
				 echo '<div class="alert alert-danger" role="alert" style="margin-top: 20px;">Please input Username and Password!</div>';;
			  if(isset($_GET["Register"])){
				  echo '<div class="alert alert-success" role="alert" style="margin-top: 20px;">Registed</div>';
			  }
			?>
        <div class="row justify-content-md-center">
			
            <div class="col-md-5 col-md-auto loginBox">
                <h1 class="text-center" style="padding-bottom:60px; font-family:serif;">Login</h1>
                <form method="POST" action="Login.php">
                    <div class="form-row">
                        <div class="col-md-12">
                            <input type="text" name="username" class="form-control form-control-lg flat-input" placeholder="Username">
                        </div>
                        <div class="col-md-12">
                            <input type="password" name="password" class="form-control form-control-lg flat-input" placeholder="Password" style="margin-bottom:15px">
                            <a href="javascript:void(0);" id="btnReg" style="float:right; margin-bottom: 20px; color:black;"><b>Register</b></a>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-lg btn-block btn-login">Login</button>
            	</div>
            </form>
        </div>
    </div>

    <div class="container" id="Register">
		<?php if(isset($_GET["regRe"])){
						echo '<div class="alert alert-danger" role="alert" style="margin-top: 20px; margin-bottom:-50px">Dealer ID is Signed</div>';
					}
			  if(isset($_GET["regPwd"]))
				 echo '<div class="alert alert-danger" role="alert" style="margin-top: 20px; margin-bottom:-50px">Password not match</div>';;
			?>
        <div class="row justify-content-md-center">
            <div class="col-md-6 col-md-auto loginBox" style="  margin-top: 100px;">
                <h1 class="text-center" style="padding-bottom:60px; font-family:serif;">Register</h1>
                <form method="POST" action="Register.php">
                    <div class="form-row">
                        <div class="col-md-12">
                            <input type="text" name="dealerID" class="form-control form-control-lg flat-input" placeholder="Dealer ID" required>
                            <input type="text" name="dealerName" class="form-control form-control-lg flat-input" placeholder="Dealer Name" required>
                            <input type="tel" name="phoneNo" class="form-control form-control-lg flat-input" placeholder="Phone Number" required>
                            <textarea type="text" name="Address" rows="2" class="form-control form-control-lg flat-input" placeholder="Address" required></textarea>
                            <input type="password" name="password" class="form-control form-control-lg flat-input" placeholder="Password" required>
							<input type="password" name="Cpassword" class="form-control form-control-lg flat-input" placeholder="Confirm Password" required>
                            <a href="javascript:changeType();" id="btnLogin" style="float:right; margin-bottom: 20px; color:black;"><b>Login</b></a>
                        </div>
                        <button type="submit" class="btn btn-lg btn-block btn-login">Register</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

	
    <script type="text/javascript">
        $(document).ready(function() {
				<?php if(isset($_GET["regPwd"])|| isset($_GET["regRe"])){
					echo '$("#Register").show();
					$("#Login").hide();

            $("#btnReg").click(function() {
                $("#Login").fadeOut(800, function() {
                    $("#Login").hide();
                    $("#Register").fadeIn(800, function() {
                        $("#Register").show();
                    });
                })

            });
            $("#btnLogin").click(function() {
                $("#Register").fadeOut(800, function() {
                    $("#Register").hide();
                    $("#Login").fadeIn(800, function() {
                        $("#Login").show();
                    });

                });
            });
        });';
				}else{ echo'
            $("#Register").hide();

            $("#btnReg").click(function() {
                $("#Login").fadeOut(800, function() {
                    $("#Login").hide();
                    $("#Register").fadeIn(800, function() {
                        $("#Register").show();
                    });
                })

            });
            $("#btnLogin").click(function() {
                $("#Register").fadeOut(800, function() {
                    $("#Register").hide();
                    $("#Login").fadeIn(800, function() {
                        $("#Login").show();
                    });

                });
            });
        });';
					 }?>
    </script>

</body>

</html>