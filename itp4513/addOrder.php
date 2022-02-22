<?php
	require_once('ini/conn.php');

	$name = $_POST["DealerName"];
	$addr = $_POST["Address"];
	$phone = $_POST["phone"];
	$countFaild = 0;
	if(strlen(trim($name))>0&strlen(trim($addr))>0&strlen(trim($phone))>0){
	foreach($_POST as $key => $value){
		if($key!="DealerName" && $key!="Address" && $key!="phone"){
		  if($value>0){
			$sesTitle = "";
			$sesTitle =$name.substr($key,8);
			$orderPart[$sesTitle] = $value;
		  }else
			  $countFaild++;
		  }
	}
	$sql = "SELECT count(*) as count FROM part";
	$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
	$record = mysqli_fetch_assoc($result);
	
	if($record["count"]==($countFaild)){
		header("location:createOrder.php?NotQty=0");
	}else{
	$sql = "SELECT dealerID FROM dealer WHERE name='$name'";
	$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
	$record = mysqli_fetch_assoc($result);
	$getToday=date("Y-m-d H:i:s");

	$sql = "INSERT INTO orders(dealerID, orderDate,deliveryAddress,status) VALUES('$record[dealerID]','$getToday','$addr',1)";
	$re = mysqli_query($conn, $sql) or die(mysqli_error($conn));
	
	$insertID = mysqli_insert_id($conn);
		
	foreach($orderPart as $key => $value){
		$partNo = substr($key,strlen($name));
		$getPricesql = "SELECT stockPrice FROM part WHERE partNumber='$partNo'";
		$getPricesqlrs = mysqli_query($conn, $getPricesql) or die(mysqli_error($conn));
		$getPricesqlrc  = mysqli_fetch_assoc($getPricesqlrs);
		$price = $getPricesqlrc["stockPrice"] * $value;

		$sql = "INSERT INTO orderpart VALUES('$insertID','$partNo','$value','$price')";
		$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
	}
		
		if(mysqli_affected_rows($conn)>0)
			
			header("location:confirmOrder.php?orderID=$insertID");
		else
			header("location:createOrder.php?createOrder=FALSE");
	}
	
}














?>