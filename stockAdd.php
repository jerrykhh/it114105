<?php 
if(isset($_POST["partName"])){
	require_once('ini/conn.php');
	extract($_POST);	
    $sql = "INSERT INTO part(email,partName,stockQuantity,stockPrice,stockStatus) VALUES ('$adminEmail', '$partName', '$stockQuantity', '$stockPrice', '$stockStatus')";
	mysqli_query($conn, $sql) or die(mysqli_error($conn));
	if(mysqli_affected_rows($conn) > 0)
	 	header("location:stock.php?partName=$partName&Add=true");
	else
		header("location:stock.php?&Add=falseA");
}else{
	header("location:stock.php?&Add=false");
}
?>

