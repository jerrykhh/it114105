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

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>View Part</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/all.min.js"></script>
</head>
<script>
	function setValue(partID){
		var valName = "partName"+partID;
		var valQty = "stockQty"+partID;
		var valPrice = "stockPrice"+partID;
		document.getElementById("partID").value=partID;
		document.getElementById("partName").value=document.getElementById(valName).innerHTML;
		document.getElementById("quantity").value=document.getElementById(valQty).innerHTML;
		document.getElementById("price").value=document.getElementById(valPrice).innerHTML;
	}
</script>
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

            <div id="content">
                <div class="row">
                    <div class="col-md-12">

                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: -20px 3%;">View Part
                            <button type="button" class="btn btn-primary float-right" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo" style="margin-top: -8px;"><i class="fas fa-plus"></i> Add</button>
                        </div>
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Add item</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form method=post action=stockAdd.php>
                                            <div class="form-group">
                                                <label for="recipient-name" class="col-form-label">partName:</label>
                                                <input type="text" class="form-control" name="partName" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="message-text" class="col-form-label">quantity:</label>
                                                <input type="text" class="form-control" name="stockQuantity" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="message-text" class="col-form-label">price:</label>
                                                <input type="text" class="form-control" name="stockPrice" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="message-text" class="col-form-label">stockStatus:</label>
                                                <select name="stockStatus" class="custom-select custom-select-sm">
                                                    <option value="1" selected>Available</option>
                                                    <option value="0">Unavailable</option>
                                                </select>
                                            </div>
											<input type="hidden" name="adminEmail" value="<?php echo $_COOKIE["AdminName"];?>">
                                        
                                    </div>
                                    <div class="modal-footer">
										<button type='submit' class='btn btn-primary' name='addbutton'>Add</button>
                                        <button type='button' class='btn btn-secondary' data-dismiss='modal' name='closebutton'>Close</button>
										</form>
                                    </div>
                                </div>
                            </div>
                        </div>
						<?php 
							if(isset($_GET["partID"])&&isset($_GET["update"]))
								echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Update part #$_GET[partID] Successful!</div>";
							elseif(isset($_GET["update"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Update part Unsuccessful!</div>';
							elseif(isset($_GET["partID"])&&isset($_GET["Status"]))
								echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Update part Status #$_GET[partID] Successful!</div>";
							elseif(isset($_GET["Status"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Update part Status Unsuccessful!</div>';
							elseif(isset($_GET["partName"])&&isset($_GET["Add"]))
								echo "<div class='alert alert-success' style='margin: 20px 3%;' role='alert'>Add Part #$_GET[partName] Successful!</div>";
							elseif(isset($_GET["Add"]))
								echo '<div class="alert alert-danger" role="alert" style="margin: 20px 3%;">Add Part Unsuccessful!</div>';
						?>
                        <!-- progressbar -->
                        <div class="shadow-sm p-3 mb-5 bg-white rounded" style="margin: 0 3%;margin-top: 20px; min-height: 600px">

                            <div class="float-right">
                                <input type="text" name="search" class="form-control form-control-lg flat-input float-right" style="border: none;width: 250px; font-size: 14px;" onKeyUp="Search()" id="SearchPart" placeholder="Search...">
                            </div>
                            <table class="table" id="PartTable">
                                <thead>
                                    <tr>
                                        <th scope="col">#partNumber</th>
                                        <th scope="col">PartName</th>
                                        <th scope="col">email</th>
                                        <th scope="col">stockQuantity</th>
                                        <th scope="col">stockPrice</th>
                                        <th scope="col">stockStatus</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                   <?php
                                    $sql = "Select * FROM part";
									$rs = mysqli_query($conn, $sql);								
										while($rc = mysqli_fetch_assoc($rs)){
											if($rc['stockStatus']==0){
													$status = "UnAvailable";
												}else{
													$status = "Available";
												}
									printf("<tr>
										<td id='partNo%1\$s'>%s</td>
										<td id='partName%1\$s'>%s</td>
										<td id='email%1\$s'>%s</td>
										<td id='stockQty%1\$s'>%s</td>
										<td id='stockPrice%1\$s'>%s</td>
										<td>%s</td>
										<td>
                                                <button onClick='setValue(%s)' type='button' class='btn btn-secondary' data-toggle='modal' data-target='#editItem' data-whatever='@edit'><i class='fas fa-edit'></i></button>
                                            <a href='stokcAvailable.php?partNumber=%s&adminEmail=%s'>
                                                <button type='button' class='btn btn-info'><i class='fas fa-exchange-alt'></i></button>
                                            </a>
                                            </td></tr>", 
											   $rc['partNumber'], $rc['partName'], $rc['email'], $rc['stockQuantity'], $rc['stockPrice'], $status,$rc['partNumber'], $rc['partNumber'],$_COOKIE['AdminName']);
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
            var input = document.getElementById("SearchPart");
            input = input.value.toUpperCase();
            var table = document.getElementById("PartTable");
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
<div class="modal fade" id="editItem" tabindex="-1" role="dialog" aria-labelledby="editItemLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit Part</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form method="post" action="updateStock.php">
											<input type="hidden" id="partID" name="partID" value="">
											<input type="hidden" id="email" name="email" value="<?php echo "$_COOKIE[AdminName]"?>">
                                            <div class="form-group">
                                                <label for="recipient-name" class="col-form-label">Part Name:</label>
                                                <input id="partName" type="text" class="form-control" name="partName" value="" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label for="message-text" class="col-form-label">Quantity:</label>
                                                <input id="quantity" type="text" class="form-control" name="stockQuantity" value="">
                                            </div>
                                            <div class="form-group">
                                                <label for="message-text" class="col-form-label">Price:</label>
                                                <input id="price" type="text" class="form-control" name="stockPrice" value="">
                                            </div>
											<input type="hidden" name="adminEmail" value="<?php echo $_COOKIE["AdminName"];?>">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Update</button>
      </div>
		  </form>
    </div>
  </div>
</div>
</body>

</html>