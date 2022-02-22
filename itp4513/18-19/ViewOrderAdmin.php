<?php
if(!isset($_COOKIE["AdminName"]))
	header("location: index.php");
else
	setcookie("AdminName",$_COOKIE["AdminName"],time()+60*15);
require_once("ini/conn.php");
?>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <title>Order</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login System</title>
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
                    <h5><?php echo $_COOKIE["AdminName"]?></h5>
                </div>
            </div>

            <ul class="list-unstyled components">
                <li class="active">
                    <a href="adminOrder.php">View Order</a>
                </li>
                <li>
                    <a href="stock.php">View Part</a>
                </li>
            </ul>
            <ul class="list-unstyled CTAs">
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
            <div id="content" style="margin-bottom: 50px">
                <div class="row">
                    <div class="col-md-12">
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">Sales Order &gt; <a href="adminOrder.php">View Order</a> &gt; Order#</div>
						<?php
							if(isset($_GET["orderID"])&&isset($_GET["Cancel"]))
								echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Cancel Order#$_GET[orderID] successful!</div>";
							elseif(isset($_GET["Cancel"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Cancel Order Unsuccessful!</div>';
							elseif(isset($_GET["orderID"])&&isset($_GET["Delivery"]))
								echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Order#$_GET[orderID] Status change to Delivery</div>";
							elseif(isset($_GET["DeliveryError"]) && isset($_GET["orderID"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Order#$_GET[orderID] Status is not In Processing</div>';
							elseif(isset($_GET["DeliveryError"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Cannot Change the order status to Delivery</div>';
						?>
                        <!-- progressbar -->
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: 0 3%;margin-top: 20px;">
                            <?php 
											$sql = "SELECT dealer.name, dealer.phoneNumber, orders.* FROM orders,  dealer WHERE dealer.dealerID=orders.dealerID AND dealer.dealerID='$_GET[DealerName]' AND orderID='$_GET[orderID]'";
											$result = mysqli_query($conn,$sql) or die(mysqli_error($conn));
											$record = mysqli_fetch_assoc($result);
											switch($record["status"]){
												case 1:
													$record["status"] = "In Processing";
													$button = "<a href='adminToDelivery.php?orderID=$_GET[orderID]'>
                                                <button type='button' class='btn btn-primary'><i class='fas fa-truck'></i></button>
                                            </a><a href='adminOrderCancel.php?orderID=$_GET[orderID]'>
                                                <button type='button' class='btn btn-outline-danger'><i class='fas fa-trash'></i> Delete</button>
                                            </a>";
													break;
												case 2:
													$record["status"] = "Delivery";
													$button = "<a href='adminOrderCancel.php?orderID=$_GET[orderID]'>
                                                <button type='button' class='btn btn-outline-danger'><i class='fas fa-trash'></i> Delete</button>
                                            </a>";
													break;
												case 3:
													$record["status"] = "Completed";
													$button = "<a href='adminOrderCancel.php?orderID=$_GET[orderID]'>
                                                <button type='button' class='btn btn-outline-danger'><i class='fas fa-trash'></i> Delete</button>
                                            </a>";
													break;
												case 0:
													$record["status"] = "Canceled";
													$button="";
											}
						
											printf('
												<div class="float-left">
											  <h1>OrderID# %s</h1>
											  <label id="pName">Name: %s</label><br>
											  <label id="pAddress">Delivery Address: %s</label><br>
											  <label id="pPhone">Phone Number: %s</label><br>
											  <label id="pdate">Date: %s</label><br>
											  <label id="pstatus">Status: %s</label>
											 </div>
											 <div class="float-right">%s</div>
											 ',$_GET["orderID"],$record["name"],$record["deliveryAddress"],$record["phoneNumber"],$record["orderDate"],$record["status"],$button);
						?>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Part #</th>
                                        <th scope="col">Part Name</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Price</th>
                                    </tr>
                                </thead>
                                <tbody>
									<?php
									$sql="SELECT part.*, orderpart.* FROM part, orderpart WHERE orderpart.partNumber=part.partNumber AND orderID='$_GET[orderID]'";
										$rs = mysqli_query($conn,$sql) or die(mysqli_error($conn));
										$totalPrice=0;
										while($rc = mysqli_fetch_assoc($rs)){
											$totalPrice +=$rc["price"]; 
                               printf('<tr>
                                        <td>%s</td>
                                        <td>%s</td>
                                        <td>%s</td>
                                        <td>$ %.2f</td>
                                    </tr>
                                    ',$rc["partNumber"],$rc["partName"],$rc["quantity"],$rc["price"]);}
								printf('<tr>
                                        <td colspan="3" align="right">Total Price</td>
                                        <td>$ %.2f</td>
                                    	</tr>',$totalPrice);
											?>
                                </tbody>
                            </table>
                            <a href="viewOrder.php">
                                <button type="button" class="btn btn-dark"><i class="fas fa-arrow-left"></i> Cancel</button>
                            </a>
                        </div>

                    </div>
                </div>

            </div>

            <div class="navbar-Black" style="background-color: Black; margin-bottom: -100px: 0; z-index: 1; padding: 15px; z-index: 9999;">
                <font color="White">Copyright © 2019 SLMC All Rights Reserved</font>
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