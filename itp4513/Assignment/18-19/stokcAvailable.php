<?php
if(isset($_GET['partNumber'])){
	require_once('ini/conn.php');
    $sql = "UPDATE part set stockStatus=!stockStatus, email='$_GET[adminEmail]' WHERE partNumber='$_GET[partNumber]'";
	mysqli_query($conn, $sql) or die(mysqli_error($conn));
	if(mysqli_affected_rows($conn) > 0)
	 	header("location:stock.php?partID=$_GET[partNumber]&&Status=Changed");
	else
		header("location:stock.php?Status=unchange");
}else{
	header("location:stock.php?Status=unchange");
}
?>