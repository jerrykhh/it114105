<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Lab06 Task 3</title>
</head>
<?php
	session_start();
	if(isset($_POST["golQty"])){
		$_SESSION['Golf Pants'] = $_POST["golQty"];
	}
	if(isset($_POST["wovQty"])){
		$_SESSION['Woven Pants'] = $_POST["wovQty"];
	}
	
	echo "<h2>Your shopping cart contains:</h2><p>";
	foreach($_SESSION as $key=>$value){
		if($value>0)
			echo "$key Qty: $value<br>";
	}
	echo "</p>";
	session_destroy();
?>		
<body>
</body>
</html>