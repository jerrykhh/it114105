<?php

	if(isset($_GET["orderID"])){
		require_once('ini/conn.php');
		$sql = "UPDATE orders SET status=3 WHERE orderID='$_GET[orderID]'";
		$result = mysqli_query($conn,$sql) or die(mysqli_error($conn));
		if(mysqli_affected_rows($conn)>0){
			header("location:viewOrder.php?orderID=$_GET[orderID]&Update=TRUE");
		}else
			header("location:viewOrder.php?Update=FALSE");
	}else{
		header("location:viewOrder.php?Update=FALSE");
	}

?>