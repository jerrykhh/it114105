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
<title>Confirm Order</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/all.min.js"></script>
<script src="js/jquery.easing.min.js"></script>
</head>
<body>
	<script type="text/javascript">
        $(document).ready(function() {
            $('#sidebarCollapse').on('click', function() {
                $('#sidebar').toggleClass('active');
            });
        });
    </script>
    <div class="wrapper">
        <nav id="sidebar">
            <div class="sidebar-header">
                <div class="row justify-content-center">
                    <i class="fas fa-user-circle fa-5x"></i>
                </div>
                <div class="row justify-content-center">
                    <h5><?php echo "$_COOKIE[DealerName]" ?></h5>
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
                    <a href="dealerSetting.php">Setting</a>
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
            <div id="content" style="margin-bottom: 50px">
                
				
                <!-- MultiStep Form -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">Sales Order &gt; Create Order</div>
						<?php if(isset($_GET["orderID"]))
									echo '<div class="alert alert-success" style="margin: 20px 3%;" role="alert">Creare Order #'."$_GET[orderID]".' successful.</div>';
							  else
									echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Create Order Unsucessful.</div>';
						?>
                        <div id="msform">
                            <!-- progressbar -->
                            <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%; padding: 5%; height: 150px;">
                                <ul id="progressbar" style="padding: 20px;">
                                    <li class="active">Dealer Information</li>
                                    <li class="active">Create Order</li>
                                    <li class="active">Confirm Order</li>
                                </ul>
                            </div>
                            <!-- fieldsets -->
                            <form method="get" id="creatOrderForm" action="createOrder.php">                               
                                <fieldset form="creatOrderForm" class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">
                                    <h2 class="fs-title">Confirm Order</h2>
                                    <h3 class="fs-subtitle">Order Details</h3>
                                    <p align="left">
										<?php 
											$sql = "SELECT dealer.name, dealer.phoneNumber, orders.* FROM orders,  dealer WHERE dealer.dealerID=orders.dealerID AND dealer.dealerID='$_COOKIE[DealerName]' AND orderID='$_GET[orderID]'";
											$result = mysqli_query($conn,$sql) or die(mysqli_error($conn));
											$record = mysqli_fetch_assoc($result);
											switch($record["status"]){
												case 1:
													$record["status"] = "In processing";
													break;
												case 2:
													$record["status"] = "Delivery";
													break;
												case 3:
													$record["status"] = "Completed";
													break;
												case 0:
													$record["status"] = "Canceled";
													break;
											}
						
											printf('
											  <label id="pOrderID">OrderID: %s</label><br>
											  <label id="pName">Name: %s</label><br>
											  <label id="pAddress">Delivery Address: %s</label><br>
											  <label id="pPhone">Phone Number: %s</label><br>
											  <label id="pdate">Date: %s</label><br>
											  <label id="pstatus">Status: %s</label>
											  ',$_GET["orderID"],$record["name"],$record["deliveryAddress"],$record["phoneNumber"],$record["orderDate"],$record["status"]);
											
											
                                      
										  
                              echo '</p>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">PartName</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">OrderQty</th>
												<th scope="col">Total($)</th>
                                            </tr>
                                        </thead>
                                        <tbody>';
										$sql="SELECT part.*, orderpart.* FROM part, orderpart WHERE orderpart.partNumber=part.partNumber AND orderID='$_GET[orderID]'";
										$rs = mysqli_query($conn,$sql) or die(mysqli_error($conn));
										$totalPrice=0;
										while($rc = mysqli_fetch_assoc($rs)){
											$totalPrice +=$rc["price"]; 
											printf('
                                            <tr>
                                                <td>%s</td>
                                                <td>%s</td>
                                                <td>%.2f</td>

                                                <td>
                                                    <input type="number"  min="0" step="1" value="%d" style="width: 50%%; height: 30px" readonly/>
                                                </td>
												<td>%.2f</td>
                                            </tr>',$rc["partNumber"],$rc["partName"],$rc["stockPrice"],$rc["quantity"],$rc["price"]);
										}
										
										printf("<tr>
													<td colspan='4' align='right'>Total Price</td>
													<td>\$ %.2f</td>
											  </tr>",$totalPrice);
										
										?>
                                        </tbody>
                                    </table>

                                    <a href="viewOrder.php"><input type="button" name="submit" class="action-button" value="View all Order" style="margin-top: 100px"/></a>
                                </fieldset>
                            </form>
                        </div>
                        <!-- link to designify.me code snippets -->

                        <!-- /.link to designify.me code snippets -->
                        
                    </div>
                </div>

            </div>
            <div class="navbar-Black" style="background-color: Black; margin-bottom: -100px: 0; z-index: 1; padding: 15px; z-index: 9999;">
                <font color="White">Copyright © 2019 SLMC All Rights Reserved</font>
            </div>
        </div>
    </div>
</body>
</html>