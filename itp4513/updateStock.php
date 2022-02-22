<?php
if(isset($_POST["partID"])){
	extract($_POST);
	require_once('ini/conn.php');
	$sql = "UPDATE part SET email='$adminEmail', stockQuantity='$stockQuantity', stockPrice='$stockPrice' WHERE partNumber='$partID'";
	$result = mysqli_query($conn,$sql) or die(mysqli_error($conn));
	if(mysqli_affected_rows($conn)>0){
		header("location: stock.php?partID=$partID&update=TRUE");
	}else{
		header("location: stock.php?");
	}
}else{
	header("location: stock.php?update=FALSE");
}





?>