
<?php 
if(isset($_GET["orderID"])){
	require_once('ini/conn.php');
    $sql = "UPDATE orders set status=0 WHERE orderID='$_GET[orderID]'";
	mysqli_query($conn, $sql) or die(mysqli_error($conn));
	if(mysqli_affected_rows($conn) > 0)
	 	header("location:adminOrder.php?orderID=$_GET[orderID]&Cancel=true");
	else
		header("location:adminOrder.php?Cancelerror=false");
}else{
	header("location:adminOrder.php?Cancelerror=false");
}
?>
