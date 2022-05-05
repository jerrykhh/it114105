
<?php 
if(isset($_GET["orderID"])){
	require_once('ini/conn.php');
	$orderID = $_GET['orderID'];
	$sql = "SELECT status FROM orders WHERE orderID='$orderID'";
	$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
	$record = mysqli_fetch_assoc($result);
	if($record['status']==1){
    	$sql = "UPDATE orders SET status=2 WHERE orderID=$orderID";
		$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
		if(mysqli_affected_rows($conn)>0){
			$sql = "SELECT * FROM orderpart WHERE orderID='$orderID'";
			$rs = mysqli_query($conn, $sql) or die(mysqli_error($conn));
			while($rc = mysqli_fetch_assoc($rs)){
				$sql = "UPDATE part SET stockQuantity=stockQuantity-$rc[quantity] WHERE partNumber='$rc[partNumber]'";
				$result =mysqli_query($conn, $sql) or die(mysqli_error($conn));
			}
			
			header("location:adminOrder.php?orderID=$orderID&Delivery=TRUE");
		}else{
			header("location:adminOrder.php?DeliveryError");
		}
	}else{
		header("location:adminOrder.php?DeliveryError=FALSE&orderID=$orderID");
	}
}else{
	header("location:adminOrder.php?DeliveryError");
}
?>
