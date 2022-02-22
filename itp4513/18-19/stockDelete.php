<?php 
	if(isset($_GET[partNumber])){
	require_once('conn.php');
    $sql = "DELETE FROM part WHERE partNumber='$_GET[partNumber]'";
	mysqli_query($conn, $sql) or die(mysqli_error($conn));
	if(mysqli_affected_rows($conn) > 0)
	 	header("location:stock.php?partNumber=$_GET[partNumber]&Delete=ture");
	else
		header("location:stock.php?Delete=false");
	}else{
		header("location:stock.php?Delete=false");
	}
?>
