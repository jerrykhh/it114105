

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
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>View Order</title>
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
                    <a href="#" data-toggle="collapse" aria-expanded="false">View Order</a>
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
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">View Order</div>
						<?php 
							if(isset($_GET["Delivery"])&&isset($_GET["orderID"]))
								echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Change the Order# $_GET[orderID] status is delivery</div>";
							else if(isset($_GET["error"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Change the order status unsuccessful</div>';
							else if(isset($_GET["DeliveryError"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Order #'.$_GET['orderID'].'Status is not In Processing</div>';
							else if(isset($_GET["Cancel"])&&isset($_GET["orderID"]))
								echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Order# $_GET[orderID] Canceled</div>";
							else if(isset($_GET["Cancelerror"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Cancel Order is unsuccessful!</div>';
						?>
                        <!-- progressbar -->
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: 0 3%;margin-top: 20px; min-height: 600px">
							<div class="float-right">
							<input type="text" name="search" class="form-control form-control-lg flat-input float-right" style="border: none;width: 250px; font-size: 14px;" onKeyUp="Search()" id="SearchOrder" placeholder="Search...">
							</div>
                            <table class="table" width="100%" id=OrderTable>
                                
                                   <thead>
                                    <tr>
                                        <th>#Order ID</th>
                                        <th>Dealer ID</th>
                                        <th>Order Date</th>
                                        <th>Total Price</th>
                                        <th>Order Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                   <?php
									require_once('ini/conn.php');
									$sql = "SELECT orderpart.orderID, dealerID, orderDate, deliveryAddress, SUM(price) AS TotalPrice, status FROM orderpart, orders WHERE orders.orderID=orderpart.orderID GROUP BY orderpart.orderID ORDER BY orderpart.orderID DESC";
									$rs = mysqli_query($conn, $sql);								
										while($record = mysqli_fetch_assoc($rs)){
											switch($record["status"]){
												case 1:
													$record["status"] = "In Processing";
													$button = "<a href='adminToDelivery.php?orderID=$record[orderID]'>
                                                <button type='button' class='btn btn-primary'><i class='fas fa-truck'></i></button>
                                            </a><a href='adminOrderCancel.php?orderID=$record[orderID]'>
                                                <button type='button' class='btn btn-danger'><i class='fas fa-trash'></i></button>
                                            </a>";
													break;
												case 2:
													$record["status"] = "Delivery";
													$button = "<a href='adminOrderCancel.php?orderID=$record[orderID]'>
                                                <button type='button' class='btn btn-danger'><i class='fas fa-trash'></i></button>
                                            </a>";
													break;
												case 3:
													$record["status"] = "Completed";
													$button = "<a href='adminOrderCancel.php?orderID=$record[orderID]'>
                                                <button type='button' class='btn btn-danger'><i class='fas fa-trash'></i></button>
                                            </a>";
													break;
												case 0:
													$record["status"] = "Canceled";
													$button="";
													break;
											}
										printf("<tr>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td>%s</td>
										<td><a href='ViewOrderAdmin.php?orderID=%s&DealerName=%s'>
                                                <button type='button' class='btn btn-secondary'><i class='fas fa-eye'></i></button>
                                            </a>%s
                                            
                                            </td></tr>", $record['orderID'], $record['dealerID'], $record['orderDate'], $record['TotalPrice'], $record["status"], $record['orderID'],$record['dealerID'], $button);
											}
									echo '</table></form>';
										
                                    ?>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>

            </div>

            <div class="navbar-Black" style="background-color: Black; margin-bottom: -100px: 0; z-index: 1; padding: 15px;">
                <font color="White">Copyright © 2019 SLMC All Rights Reserved</font>

            </div>

        </div>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#sidebarCollapse').on('click', function() {
                    $('#sidebar').toggleClass('active');
                });
            });
        </script>
			<script>
                    function Search() {
						var input = document.getElementById("SearchOrder");
						input = input.value.toUpperCase();
						var table = document.getElementById("OrderTable");
						var tr = table.getElementsByTagName("tr");
						for (var i = 0; i < tr.length; i++) {
							var td = tr[i].getElementsByTagName("td")[0];
							var td1 = tr[i].getElementsByTagName("td")[1];
							if (td && td1) {
								var txtValue = td.innerText;
								var txtValue2 = td1.innerText;
								if (txtValue.toUpperCase().indexOf(input) == 0 || txtValue2.toUpperCase().indexOf(input) == 0) {
									tr[i].style.display = "";
								} else {
									tr[i].style.display = "none";
								}
							}
						}
					}
               </script>		
</body>

</html>