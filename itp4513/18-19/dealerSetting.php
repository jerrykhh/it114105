<?php
if(!isset($_COOKIE["DealerName"]))
	header("location: index.php");
else
	setcookie("DealerName",$_COOKIE["DealerName"],time()+60*15);
require_once("ini/conn.php");
?>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="utf-8">
<title>Setting</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/all.min.js"></script>
</head>
<body>
    <div class="wrapper">
        <nav id="sidebar">
            <div class="sidebar-header">
                <div class="row justify-content-center">
                    <i class="fas fa-user-circle fa-5x"></i>
                </div>
                <div class="row justify-content-center">
                    <h5><?php echo $_COOKIE["DealerName"] ?></h5>
                </div>
            </div>

            <ul class="list-unstyled components">
                <li class="active">
                    <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="true">Sales Order</a>
                    <ul class="collapse list-unstyled" id="homeSubmenu">
                        <li><a href="createOrder.php">Create Order</a></li>
                        <li><a href="viewOrder.php">View Order</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">Setting</a>
                </li>
            </ul>
            <ul class="list-unstyled CTAs row justify-content-center">
                <li class="row justify-content-center"><a href="index.php?Logout=true">Log Out</a></li>
            </ul>
        </nav>

        <div class="container-fluid" style="padding:0;">
            <!-- Page Content Holder -->
            <div class="navHead">
                <nav class="navbar navbar-Black ">
                    <div class="container-fluid">
                        <div class="col-md-8">
                            <a href="#" id="sidebarCollapse"><i class="fas fa-bars fa-2x" style="color: white"> </i></a>
                            <font size="4" color="white" style="margin-left: 10px;"> Spares Order System</font>
                        </div>
                    </div>

                </nav>
            </div>
            <div id="content">
                <div class="row">

                    <!-- progressbar -->

                    <div class="col-md-8 col-sm-12 col-xl-6">

						<div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">Setting</div>
						<?php
							if(isset($_GET["Update"])){
								if($_GET["Update"])
									echo '<div class="alert alert-success" style="margin: 20px 3%;" role="alert">Update successful!</div>';
								else
									echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Update Fail!</div>';
							}
							if(isset($_GET["pwdOld"])){
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;" >Old Password is Wrong!</div>';
							}
							if(isset($_GET["pwdNew"])){
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">New Password and Confirm Password is not match !</div>';
							}
						
						?>
						
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: 0 3%;margin-top: 20px;padding: 5%;">
                            <center><h3 style="margin-bottom: 100px">Update Information</h3></center><?php
							$sql = "SELECT name, address, phoneNumber FROM dealer WHERE dealerID='$_COOKIE[DealerName]'";
							$result = mysqli_query($conn,$sql) or die(mysqli_error($conn));
							$record = mysqli_fetch_assoc($result);					
							
                            printf('<form method="POST" id="msform" action="updateInf.php?dealerID=%s">
								<div class="form-group row">
    <label for="inputName" class="col-sm-2 col-form-label">Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputName" value="%s" name="dealerName" placeholder="Name">
    </div>
  </div>
								<div class="form-group row">
    <label for="inputAddress" class="col-sm-2 col-form-label">Address</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputAddress" name="dealerAddr"  value ="%s" placeholder="Address">
    </div>
  </div>
								<div class="form-group row">
    <label for="Phone" class="col-sm-2 col-form-label">Phone</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id=“inputPhone” name="phone"  value ="%s" placeholder="Phone">
    </div>
  </div>
								<div class="form-group row">
    <label for="inputPhone" class="col-sm-2 col-form-label">Old Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPhone" name="pwdOld"  placeholder="Old Password">
    </div>
  </div>
								<div class="form-group row">
    <label for="inputPwdNew" class="col-sm-2 col-form-label">New Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPwdNew" name="pwdNew"   placeholder="New Password">
    </div>
  </div>
								<div class="form-group row">
    <label for="inputpwdNewConf" class="col-sm-2 col-form-label">Confirm Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="pwdNewConf" name="pwdNewConf"  placeholder="Confirm Password">
    </div>
  </div>
                                ',$_COOKIE["DealerName"], $record["name"], $record["address"], $record["phoneNumber"]);
?>
                            <button type="submit" class="btn btn-dark btn-lg" style="margin-top: 100px;">Update</button></form>
                        </div>
                    </div>

                </div>
            </div>


    <div class="navbar-Black" style="background-color: Black; margin-bottom: -100px: 0; z-index: 1; padding: 15px; z-index: 9999;">
        <font color="White">Copyright © 2019 SLMC All Rights Reserved</font>
    </div>
			        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#sidebarCollapse').on('click', function() {
                $('#sidebar').toggleClass('active');
            });
        });
    </script>

</body>

</html>