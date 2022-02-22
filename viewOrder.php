<?php
if(!isset($_COOKIE["DealerName"]))
	header("location: index.php");
else
	setcookie("DealerName",$_COOKIE["DealerName"],time()+60*15);
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
                    <h5><?php echo "$_COOKIE[DealerName]" ?></h5>
                </div>
            </div>

            <ul class="list-unstyled components">
                <li class="active">
                    <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="true">Sales Order</a>
                    <ul class="collapse list-unstyled" id="homeSubmenu">
                        <li><a href="createOrder.php">Create Order</a></li>
                        <li><a href="#">View Order</a></li>
                    </ul>
                </li>
                <li>
                    <a href="dealerSetting.php">Setting</a>
                </li>
            </ul>
            <ul class="list-unstyled CTAs row justify-content-center">
                <li class="row justify-content-center"><a href="index.php?Logout=TRUE">Log Out</a></li>
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
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">Sales Order &gt; View Order</div>
                        <!-- progressbar -->
						<?php
								if(isset($_GET["Del"])&&isset($_GET["orderID"]))
									echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Delete Order #$_GET[orderID] successful!</div>";
								elseif(isset($_GET["Del"]))
									echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Delete Fail!</div>';
								elseif(isset($_GET["Update"])&&isset($_GET["orderID"]))
									echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Order #$_GET[orderID] Completed!</div>";
								elseif(isset($_GET["Update"]))
									echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Update Order Status Unsuccessful!</div>';												
						?>
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: 0 3%;margin-top: 20px">
							<div class="float-right">
							<input type="text" name="search" class="form-control form-control-lg flat-input float-right" style="border: none;width: 250px; font-size: 14px;" onKeyUp="Search()" id="SearchOrder" placeholder="Search...">
							</div>
                            <table class="table" id="OrderTable">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">Status</th>
										<th scope="col">OrderDate</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
								<tbody>
								<?php
								$sql="SELECT * FROM orders WHERE dealerID='$_COOKIE[DealerName]' ORDER BY orderDate DESC, orderID DESC";
								$result = mysqli_query($conn,$sql) or die(mysqli_error($conn));
								
								while($record = mysqli_fetch_assoc($result)){
									$button = '<a href="order.php?orderID='.$record["orderID"].'">
                                                <button type="button" class="btn btn-secondary"><i class="fas fa-eye"></i></button>
                                            </a>';
									switch($record["status"]){
												case 1:
													$button .=' <a href="deleteOrder.php?orderID='.$record["orderID"].'">
                                                <button type="button" class="btn btn-danger"><i class="fas fa-trash"></i></button>
                                            	</a>';
													$record["status"] = "In processing";
													break;
												case 2:
													$button .= ' <a href="updateStat.php?orderID='.$record["orderID"].'">
                                                <button type="button" class="btn btn-success"><i class="fas fa-check"></i></button>
                                            </a>';
													$record["status"] = "Delivery";
													break;
												case 3:
													$record["status"] = "Completed";
													break;
												case 0:
													$record["status"] = "Canceled";
											}
									
									printf('
									<tr>
                                        <td>%s</td>
                                        <td>%s</td>
                                        <td>%s</td>
										<td>%s</td>
                                        <td>
                                            %s
                                        </td>
                                    </tr>',$record["orderID"], $record["deliveryAddress"],$record["status"],$record["orderDate"],$button);									   
								}
										   
										   ?>
                                </tbody>
                            </table>
                            <nav aria-label="...">
                                <ul class="pagination  justify-content-center" style="margin-bottom: 80px;">
                                    <li class="page-item active" aria-current="page">
                                        <span class="page-link">1
						<span class="sr-only">(current)</span>
                                        </span>
                                    </li>
                                </ul>
                            </nav>
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
			<script>
                    function Search() {
                        var input = document.getElementById("SearchOrder");
                        input = input.value.toUpperCase();
                        var table = document.getElementById("OrderTable");
                        var tr = table.getElementsByTagName("tr");
                        for (var i = 0; i < tr.length; i++) {
                            var td = tr[i].getElementsByTagName("td")[0];
                            if (td ) {
                                var txtValue = td.innerText;
                                if (txtValue.toUpperCase().indexOf(input) == 0) {
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