<?php

	if(isset($_GET["orderID"])){
		require_once('ini/conn.php');
		$sql = "DELETE FROM orders WHERE orderID='$_GET[orderID]';DELETE FROM orderpart WHERE orderID='$_GET[orderID]'";
		$result = mysqli_multi_query($conn,$sql) or die(mysqli_error($conn));
		if(mysqli_affected_rows($conn)>0)
			header("location:viewOrder.php?orderID=$_GET[orderID]&Del=TRUE");
		else
			header("location:viewOrder.php?Del=FALSE");
	}else{
		header("location:viewOrder.php?Del=FALSE");
	}

?>