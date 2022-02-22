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
<title>Create Order</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/all.min.js"></script>
<script src="js/jquery.easing.min.js"></script>
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
                        <li><a href="#">Create Order</a></li>
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
                <script>
                    function Search() {
                        var input = document.getElementById("SearchPart");
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
				
                <!-- MultiStep Form -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">Sales Order &gt; Create Order</div>
						<?php 
							if(isset($_GET["NotQty"])){
								echo '<div class="alert alert-danger" style="margin: 20px 3%;" role="alert">You should buy a least one Product</div>';
							}	
						?>
                        <div id="msform">
                            <!-- progressbar -->
                            <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%; padding: 5%; height: 150px;">
                                <ul id="progressbar" style="padding: 20px;" >
                                    <li class="active">Dealer Information</li>
                                    <li>Create Order</li>
                                    <li>Confirm Order</li>
                                </ul>
                            </div>
                            <!-- fieldsets -->
                            <form method="POST" id="creatOrderForm" action="addOrder.php">

                                <fieldset form="creatOrderForm"  class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">
                                    
                                    <h2 class="fs-title">Dealer Information</h2>
									<?php
										$sql="SELECT name, phoneNumber, address FROM dealer WHERE dealerID='$_COOKIE[DealerName]'";
										$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
										$record = mysqli_fetch_assoc($result);
										printf('
                                    <label class=float-left>Name:</label> <input type="text" name="DealerName" placeholder="Name" id="DealerName" value="%s" /><br>
                                    <label class=float-left>Delivery Address:</label> <input type="text" name="Address" value="%s" placeholder="Delivery Address" id="Address" /><br>
                                    <label class=float-left>Phone Number:</label> <input type="text" name="phone" value="%s" placeholder="Phone Number" id="Phone" />
                                    ',$record['name'], $record['address'], $record['phoneNumber']);
									?>
                                    <input type="button" name="next"  class="next action-button" value="Next"  onClick="saveInf()"/>
                                </fieldset>
                                <fieldset form="creatOrderForm" class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">
                                    <h2 class="fs-title">Create Sales Order</h2>
                              		<p align="left">
                                      <label id="pName">Name: </label><br>
                                      <label id="pAddress">Delivery Address: </label><br>
                                      <label id="pPhone">Phone Number:</label>
                                    </p>
                                    <script>
										function saveInf(){
											document.getElementById('pName').innerHTML = "Name : "+ document.getElementById("DealerName").value;
											document.getElementById('pAddress').innerHTML ="Delivery Address: "+ document.getElementById("Address").value;
											document.getElementById('pPhone').innerHTML = "Phone Number: "+ document.getElementById("Phone").value;
										}
										
									</script>
                                    <input type="text" name="search" class="form-control form-control-lg flat-input float-right" style="border: none;width: 250px" onKeyUp="Search()" id="SearchPart" placeholder="Search...">
                                    <table class="table" id="OrderTable">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">PartName</th>
                                                <th scope="col">StockQty</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">OrderQty</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                          <form method="POST" id="partForm" action="inn/conn.php">
                                           <?php
												
												$sql = "SELECT * FROM part WHERE stockStatus";
												$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
												if(mysqli_num_rows($result)>0){
													while($record = mysqli_fetch_assoc($result)){
														printf('<tr>
                                                <td>%s</td>
                                                <td>%s</td>
                                                <td>%s</td>
                                                <td>%.1f</td>
                                                <td>
                                                    <input type="number" value="0" min="0" step="1" name="orderQty%s" style="width: 50%%; height: 30px" />
                                                </td>
                                            </tr>',$record["partNumber"], $record["partName"], $record["stockQuantity"],$record["stockPrice"],$record["partNumber"]);
													}
												}
											?>
											</form>
                                        </tbody>
                                    </table>
                                    <nav aria-label="...">
                                        <ul class="pagination  justify-content-center" style="margin-bottom: 80px;">
                                            <li class="page-item active" aria-current="page">
                                                <span class="page-link">1<span class="sr-only">(current)</span>
                                                </span>
                                            </li>
                                        </ul>
                                    </nav>
                                    <input type="button" name="previous" class="previous action-button-previous" value="Previous" />
                                    <input type="Submit" name="next" class="action-button" value="Submit" onClick=""/>
									<input type="hidden" name="next" class="next action-button" value="hifSub"/>
                                </fieldset>
                            </form>
                        </div>
                        <!-- link to designify.me code snippets -->

                        <!-- /.link to designify.me code snippets -->
                        <script src="js/multiForm.js"></script>
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